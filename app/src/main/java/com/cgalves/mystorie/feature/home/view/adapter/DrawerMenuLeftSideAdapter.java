package com.cgalves.mystorie.feature.home.view.adapter;

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

        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_menu_left_bottom, parent, false);
        TextView textViewName = view.findViewById(R.id.tv_title);
        ImageView img = view.findViewById(R.id.img_icon);

        setByposition(img, position);
        Section section = list.get(position);
        textViewName.setText(section.getName());

        return view;
    }

    private void setByposition(ImageView img, int position) {
        switch (position) {
            case 0: {
                img.setImageDrawable(mContext.getDrawable(R.drawable.noticia));
                break;
            }
            case 1: {
                img.setImageDrawable(mContext.getDrawable(R.drawable.novidade));
                break;
            }
            case 2: {
                img.setImageDrawable(mContext.getDrawable(R.drawable.contact));
                break;
            }
            default: {
                break;
            }
        }
    }
}
