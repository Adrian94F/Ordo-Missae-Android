package com.frydm_n.ordomissae;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import androidx.fragment.app.DialogFragment;

public class SettingsFragment extends DialogFragment {
    public enum Theme {
        LIGHT,
        DARK
    }

    Theme currentTheme;

    public SettingsFragment(Theme theme) {
        currentTheme = theme;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(getActivity());

        builderSingle.setIcon(R.drawable.ic_jerusalem_cross);
        builderSingle.setTitle(getResources().getString(R.string.menu_settings));
        builderSingle.setView(R.layout.settings);

        builderSingle.setNegativeButton(getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builderSingle.setPositiveButton(getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO: save settings
                dialog.dismiss();
            }
        });

        /*getView().findViewById(R.id.radio_light).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        getView().findViewById(R.id.radio_dark).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });*/

        return builderSingle.create();
    }

    private void setTranslation() {
        //check if translation is turned on or off
    }

    private void setCurrentTheme() {
        //check which theme is enabled
        int id = currentTheme == Theme.LIGHT ? R.id.radio_light : R.id.radio_dark;
        getView().findViewById(id).setPressed(true);
    }
}