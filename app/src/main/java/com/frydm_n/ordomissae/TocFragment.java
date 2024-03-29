package com.frydm_n.ordomissae;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TocFragment extends DialogFragment {

    TocFragment(ScrollView scrollView) {
        scrollView_ = scrollView;
    }

    private ScrollView scrollView_;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builderSingle = new AlertDialog.Builder(getActivity());

        builderSingle.setIcon(R.drawable.ic_jerusalem_cross);
        builderSingle.setTitle(getResources().getString(R.string.menu_toc));

        JSONArray rows = MainActivity.rows;
        final ArrayList<String> titles = new ArrayList<>();
        final ArrayList<Integer> levels = new ArrayList<>();

        final ArrayAdapter<String> arrayAdapter = new TocAdapter(getActivity(), titles, levels);

        for (int i = 0; i < rows.length(); i++) {
            String title = "";
            int level = 0;
            try {
                JSONObject insideObject = rows.getJSONObject(i);
                title = insideObject.getString("title");
                level = insideObject.getInt("level");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (title.length() > 0) {
                if (level < 3) {
                    titles.add(title);
                    levels.add(level);
                }
            }
        }

        builderSingle.setNegativeButton(getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final String strName = arrayAdapter.getItem(which);
                scrollView_.post(new Runnable() {
                    public void run() {
                        final int index = titles.indexOf(strName);
                        LinearLayout layout = (LinearLayout)scrollView_.getChildAt(0);
                        TextView firstTextView = layout.findViewById(0);
                        TextView textView = layout.findViewById(index);
                        final int[] startLocation = {0,0};
                        firstTextView.getLocationInWindow(startLocation);
                        final int[] textLocation = {0,0};
                        textView.getLocationInWindow(textLocation);
                        final int scrollY = textLocation[1] - startLocation[1];
                        scrollView_.smoothScrollTo(0, scrollY);

                    }
                });
            }
        });
        return builderSingle.create();
    }
}