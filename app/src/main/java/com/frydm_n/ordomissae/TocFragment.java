package com.frydm_n.ordomissae;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.DialogFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TocFragment extends DialogFragment {

    TocFragment(ListView listView_) {
        listView = listView_;
    }

    private ListView listView;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(getActivity());

        builderSingle.setIcon(R.drawable.ic_jerusalem_cross);
        builderSingle.setTitle(getResources().getString(R.string.menu_toc));

        JSONArray rows = MainActivity.rows;

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.select_dialog_item);

        for (int i = 0; i < rows.length(); i++) {
            String title = "...error...";
            int level = 0;
            try {
                JSONObject insideObject = rows.getJSONObject(i);
                title = insideObject.getString("title");
                level = insideObject.getInt("level");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (title.length() > 0) {
                String spaces = new String(new char[2* level]).replace('\0', ' ');
                title = spaces + title;
                arrayAdapter.add(title);
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
                String strName = arrayAdapter.getItem(which);
                ArrayList<TextRow> textRows = MainActivity.textRowsList;
                int index = 0;
                for (; index < textRows.size(); index++) {
                    if (textRows.get(index).getTitle().equals(strName)) {
                        // TODO: something's wrong here
                        listView.smoothScrollToPosition(index);
                        break;
                    }
                }
            }
        });
        return builderSingle.create();
    }
}