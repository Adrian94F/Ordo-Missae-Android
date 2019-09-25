package com.frydm_n.ordomissae;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import androidx.fragment.app.DialogFragment;

public class TocFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(getActivity());

        builderSingle.setIcon(R.drawable.ic_jerusalem_cross);
        builderSingle.setTitle(getResources().getString(R.string.menu_toc));

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.select_dialog_item);
        // TODO: load from JSON
        arrayAdapter.add("Coś 1");
        arrayAdapter.add("Coś innego 1");
        arrayAdapter.add("Coś tam 1");
        arrayAdapter.add("Coś 2");
        arrayAdapter.add("Coś innego 2");
        arrayAdapter.add("Coś tam 2");
        arrayAdapter.add("Coś 3");
        arrayAdapter.add("Coś innego 3");
        arrayAdapter.add("Coś tam 3");
        arrayAdapter.add("Coś 4");
        arrayAdapter.add("Coś innego 4");
        arrayAdapter.add("Coś tam 4");
        arrayAdapter.add("Coś 5");
        arrayAdapter.add("Coś innego 5");
        arrayAdapter.add("Coś tam 5");
        arrayAdapter.add("Coś 6");
        arrayAdapter.add("Coś innego 6");
        arrayAdapter.add("Coś tam 6");

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
                AlertDialog.Builder builderInner = new AlertDialog.Builder(getActivity());
                builderInner.setMessage(strName);
                builderInner.setTitle("Wybrałeś");
                builderInner.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,int which) {
                        dialog.dismiss();
                    }
                });
                builderInner.show();
            }
        });
        return builderSingle.create();



        /*builder.setTitle(getResources().getString(R.string.menu_about));
        builder.setMessage(getResources().getString(R.string.about_application));
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // You don't have to do anything here if you just
                // want it dismissed when clicked
            }
        });

        // Create the AlertDialog object and return it
        return builder.create();*/
    }
}