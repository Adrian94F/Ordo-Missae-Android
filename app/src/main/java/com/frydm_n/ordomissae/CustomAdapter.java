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

public class CustomAdapter extends ArrayAdapter<TextRow> /*implements View.OnClickListener*/ {

    private ArrayList<TextRow> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView txtTitle;
        TextView txtRubrics;
        TextView txtNigrics;
        TextView txtLatin;
        TextView txtPolish;
    }

    public CustomAdapter(ArrayList<TextRow> data, Context context) {
        super(context, R.layout.row_item, data);
        this.dataSet = data;
        this.mContext=context;

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
            viewHolder.txtNigrics = (TextView) convertView.findViewById(R.id.nigrics);
            viewHolder.txtLatin = (TextView) convertView.findViewById(R.id.latin);
            viewHolder.txtPolish = (TextView) convertView.findViewById(R.id.polish);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;

        String title = textRow.getTitle();
        if (title.length() > 0)
            viewHolder.txtTitle.setText(title);
        else
            viewHolder.txtTitle.setHeight(0);

        /*String rubrics = textRow.getRubrics();
        if (rubrics.length() > 0)
            viewHolder.txtRubrics.setText(rubrics);
        else
            viewHolder.txtRubrics.setHeight(0);

        /*String nigrics = textRow.getNigrics();
        if (nigrics.length() > 0)
            viewHolder.txtNigrics.setText(nigrics);
        else
            viewHolder.txtNigrics.setHeight(0);

        /*String latin = textRow.getLatin();
        if (latin.length() > 0)
            viewHolder.txtLatin.setText(latin);
        else
            viewHolder.txtLatin.setHeight(0);

        String polish = textRow.getRPolish();
        if (polish.length() > 0)
            viewHolder.txtPolish.setText(polish);
        else
            viewHolder.txtPolish.setHeight(0);*/

        //viewHolder.txtTitle.setText(textRow.getTitle());
        viewHolder.txtRubrics.setText(textRow.getRubrics());
        viewHolder.txtNigrics.setText(textRow.getNigrics());
        viewHolder.txtLatin.setText(textRow.getLatin());
        viewHolder.txtPolish.setText(textRow.getRPolish());
        viewHolder.txtPolish.setTag(position);

        // Return the completed view to render on screen
        return convertView;
    }
}
