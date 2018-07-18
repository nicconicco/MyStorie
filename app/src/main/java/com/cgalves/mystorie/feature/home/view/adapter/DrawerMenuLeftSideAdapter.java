package com.cgalves.mystorie.feature.home.view.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cgalves.mystorie.R;
import com.cgalves.mystorie.feature.home.model.Section;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Scopus on 18/07/18.
 */

public class DrawerMenuLeftSideAdapter extends BaseAdapter {

    Context mContext;
    List<Section> list = new ArrayList<>();

    public DrawerMenuLeftSideAdapter(Context mContext, List<Section> section) {
        this.mContext = mContext;
        this.list = section;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return list.get(i).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_section_home_in_menu_left, parent, false);

        TextView textViewName = view.findViewById(R.id.tv_title);
        Section section = list.get(position);
        textViewName.setText(section.getName());

        return view;
    }
}
