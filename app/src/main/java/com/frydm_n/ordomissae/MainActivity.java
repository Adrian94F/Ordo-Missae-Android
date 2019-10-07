package com.frydm_n.ordomissae;

import android.content.Context;
import android.os.Bundle;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    static ArrayList<TextRow> textRowsList;
    static JSONArray rows;
    ListView listView;
    TextRowAdapter adapter;

    SettingsFragment.Theme theme = SettingsFragment.Theme.LIGHT;
    boolean translation = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setToolbar();
        loadData();
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

    private void loadData() {
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
    }

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

        DialogFragment dialog = new TocFragment(listView);
        dialog.show(getSupportFragmentManager(), "TocFragmentTag");
    }

    public void turnOnTranslation() {
        translation = true;
    }

    public void turnOffTranslation() {
        translation = false;
    }
}
