package com.frydm_n.ordomissae;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class TocAdapter extends ArrayAdapter<String> {

    private Context mContext;
    private List<String> titlesList = new ArrayList<>();
    private List<Integer> levelsList = new ArrayList<>();

    public TocAdapter(@NonNull Context context, ArrayList<String> list, ArrayList<Integer> levels) {
        super(context, 0 , list);
        mContext = context;
        titlesList = list;
        levelsList = levels;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.toc_item,parent,false);

        String currentTitle = titlesList.get(position);

        TextView textView = listItem.findViewById(R.id.title);
        textView.setTextSize(20 - 2 * levelsList.get(position));
        textView.setPadding(0, 30 * (3 - levelsList.get(position)), 0, 0);
        textView.setText(currentTitle);

        return listItem;
    }
}