package com.frydm_n.ordomissae;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    static JSONArray rows;
    LinearLayout contentLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setToolbar();
        loadData();
    }

    private void loadData() {
        contentLayout = findViewById(R.id.content_layout);

        String dataString = getAssetJsonData(getApplicationContext());
        JSONObject insideObject;

        try {
            rows = new JSONArray(dataString);
        } catch (JSONException e) {
            e.printStackTrace();
            return;
        }

        int titleCounter = 0;

        for (int i = 0; i < rows.length(); i++) {
            String title = "";
            int titleLevel = 0;
            String rubrics = "";
            String latin = "";
            String polish = "";

            try {
                insideObject = rows.getJSONObject(i);
            } catch (JSONException e) {
                e.printStackTrace();
                return;
            }
            try {
                title = insideObject.getString("title");
                titleLevel = insideObject.getInt("level");
            } catch (JSONException e) {
                e.printStackTrace();
                try {
                    rubrics = insideObject.getString("rubrics");
                } catch (JSONException ee) {
                    ee.printStackTrace();
                    try {
                        latin = insideObject.getString("latin");
                        polish = insideObject.getString("polish");
                    } catch (JSONException eee) {
                        eee.printStackTrace();
                    }
                }

            }

            if (title.length() > 0) {
                TextView textView = new TextView(contentLayout.getContext());
                textView.setText(title);
                textView.setTextSize(22 - 2 * titleLevel);
                textView.setTypeface(textView.getTypeface(), Typeface.BOLD);
                textView.setTextColor(getResources().getColor(R.color.colorTitle, getTheme()));
                textView.setPadding(0,20,0,10);
                if (titleLevel < 3) {
                    textView.setId(titleCounter++);
                }
                contentLayout.addView(textView);
            } else if (rubrics.length() > 0) {
                TextView textView = new TextView(contentLayout.getContext());
                textView.setText(rubrics);
                textView.setTypeface(textView.getTypeface(), Typeface.ITALIC);
                textView.setTextColor(getResources().getColor(R.color.colorRubrics, getTheme()));
                textView.setPadding(0,5,0,5);
                contentLayout.addView(textView);
            } else if (latin.length() > 0 || polish.length() > 0) {
                LinearLayout linearLayout = new LinearLayout(contentLayout.getContext());
                TextView latinTextView = new TextView(linearLayout.getContext());
                TextView polishTextView = new TextView(linearLayout.getContext());
                latinTextView.setText(latin);
                polishTextView.setText(polish);
                latinTextView.setTextColor(getResources().getColor(R.color.colorText, getTheme()));
                polishTextView.setTextColor(getResources().getColor(R.color.colorText, getTheme()));
                latinTextView.setPadding(0,5,10, 0);
                polishTextView.setPadding(10,5,0, 0);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                params.weight = 0.5f;
                latinTextView.setLayoutParams(params);
                polishTextView.setLayoutParams(params);
                linearLayout.addView(latinTextView);
                linearLayout.addView(polishTextView);
                contentLayout.addView(linearLayout);
            }

        }
    }

    private void setToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    /*private void loadData() {
        listView = (ListView)findViewById(R.id.contentList);
        listView.setDivider(null);
        textRowsList = new ArrayList<>();

        String dataString = getAssetJsonData(getApplicationContext());
        try {
            rows = new JSONArray(dataString);
            for (int i = 0; i < rows.length(); i++) {
                JSONObject insideObject = rows.getJSONObject(i);
                String title = insideObject.getString("title");
                int titleLevel = insideObject.getInt("level");
                String rubrics = insideObject.getString("rubrics");
                String latin = insideObject.getString("latin");
                String polish = insideObject.getString("polish");
                textRowsList.add(new TextRow(title, titleLevel, rubrics, latin, polish));
            }

        } catch (JSONException e) {
            e.printStackTrace();
            textRowsList.add(new TextRow("Nie udało się odczytać danych"));
        }

        adapter = new TextRowAdapter(textRowsList, getApplicationContext());
        listView.setAdapter(adapter);
    }*/

    private static String getAssetJsonData(Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open("sancta_missa.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_about) {
            onAboutButton();
        } else if (id == R.id.nav_settings) {
            onSettingsButton();
        } else if (id == R.id.nav_toc) {
            onTocButton();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onAboutButton() {
        DialogFragment dialog = new AboutAppFragment();
        dialog.show(getSupportFragmentManager(), "AboutAppFragmentTag");
    }

    public void onSettingsButton() {
        DialogFragment dialog = new SettingsFragment(SettingsFragment.Theme.LIGHT);
        dialog.show(getSupportFragmentManager(), "SettingsFragment");
    }

    public void onTocButton() {
        ScrollView sv = findViewById(R.id.scroll_view);
        DialogFragment dialog = new TocFragment(sv);
        dialog.show(getSupportFragmentManager(), "TocFragmentTag");
    }

    /*public void turnOnTranslation() {
        translation = true;
    }

    public void turnOffTranslation() {
        translation = false;
    }*/
}
