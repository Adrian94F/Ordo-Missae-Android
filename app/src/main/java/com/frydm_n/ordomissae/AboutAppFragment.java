package com.frydm_n.ordomissae;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import androidx.fragment.app.DialogFragment;

public class AboutAppFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(getActivity());

        /*builderSingle.setIcon(R.drawable.ic_jerusalem_cross);
        builderSingle.setTitle("Select One Name:-");

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.select_dialog_singlechoice);
        arrayAdapter.add("Hardik");
        arrayAdapter.add("Archit");
        arrayAdapter.add("Jignesh");
        arrayAdapter.add("Umang");
        arrayAdapter.add("Gatti");
        arrayAdapter.add("Hardik 2");
        arrayAdapter.add("Archit 2");
        arrayAdapter.add("Jignesh 2");
        arrayAdapter.add("Umang 2");
        arrayAdapter.add("Gatti 2");

        builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
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
                builderInner.setTitle("Your Selected Item is");
                builderInner.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,int which) {
                        dialog.dismiss();
                    }
                });
                builderInner.show();
            }
        });
        return builderSingle.create();*/



        builderSingle.setTitle(getResources().getString(R.string.menu_about));
        builderSingle.setMessage(getResources().getString(R.string.about_application));
        builderSingle.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // You don't have to do anything here if you just
                // want it dismissed when clicked
            }
        });

        // Create the AlertDialog object and return it
        return builderSingle.create();
    }
}