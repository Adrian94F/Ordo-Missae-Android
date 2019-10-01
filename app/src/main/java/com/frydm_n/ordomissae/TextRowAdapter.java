package com.frydm_n.ordomissae;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class TextRowAdapter extends ArrayAdapter<TextRow> /*implements View.OnClickListener*/ {

    private ArrayList<TextRow> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView txtTitle;
        TextView txtRubrics;
        TextView txtLatin;
        TextView txtPolish;
    }

    public TextRowAdapter(ArrayList<TextRow> data, Context context) {
        super(context, R.layout.row_item, data);
        this.dataSet = data;
        this.mContext = context;
    }

    /*@Override
    public void onClick(View v) {

        int position=(Integer) v.getTag();
        Object object= getItem(position);
        com.frydm_n.ordomissae.TextRow dataModel=(com.frydm_n.ordomissae.TextRow)object;

        switch (v.getId())
        {
            case R.id.item_info:
                Snackbar.make(v, "Release date " +dataModel.getFeature(), Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();
                break;
        }
    }*/

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        TextRow textRow = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_item, parent, false);
            viewHolder.txtTitle = (TextView) convertView.findViewById(R.id.title);
            viewHolder.txtRubrics = (TextView) convertView.findViewById(R.id.rubrics);
            viewHolder.txtLatin = (TextView) convertView.findViewById(R.id.latin);
            viewHolder.txtPolish = (TextView) convertView.findViewById(R.id.polish);

            result = convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;

        String title = textRow.getTitle();
        viewHolder.txtTitle.setText(title);
        if (title.length() > 0) {
            viewHolder.txtTitle.setTextSize(22 - 2 * textRow.getLevel());
        }
        else {
            viewHolder.txtTitle.setTextSize(0);
        }

        String rubrics = textRow.getRubrics();
        viewHolder.txtRubrics.setText(rubrics);
        if (rubrics.length() > 0) {
            viewHolder.txtRubrics.setTextSize(14);
            viewHolder.txtRubrics.setPadding(0, 5, 0, 0);
        } else {
            viewHolder.txtRubrics.setTextSize(0);
            viewHolder.txtRubrics.setPadding(0, 0, 0, 0);
        }

        String latin = textRow.getLatin();
        String polish = textRow.getPolish();
        viewHolder.txtLatin.setText(latin);
        viewHolder.txtPolish.setText(polish);
        if (latin.length() > 0 || polish.length() > 0) {
            viewHolder.txtLatin.setTextSize(14);
            viewHolder.txtPolish.setTextSize(14);
        } else {
            viewHolder.txtLatin.setTextSize(0);
            viewHolder.txtPolish.setTextSize(0);
        }

        viewHolder.txtPolish.setTag(position);

        // Return the completed view to render on screen
        return convertView;
    }
}
