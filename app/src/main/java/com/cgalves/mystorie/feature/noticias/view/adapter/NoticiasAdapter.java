package com.cgalves.mystorie.feature.noticias.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cgalves.mystorie.R;
import com.cgalves.mystorie.common.model.Noticia;

import java.util.List;

/**
 * Created by scopus on 27/07/18.
 */

public class NoticiasAdapter extends RecyclerView.Adapter<NoticiasAdapter.ViewHolder> {
    private final Context context;
    private final List<Noticia> sectionList;

    public interface OnClickListener {
        void onClick(Noticia n);
    }

    private OnClickListener onClickListener;

    public NoticiasAdapter(Context context, List<Noticia> sectionList, OnClickListener onClickListener) {
        this.context = context;
        this.sectionList = sectionList;
        this.onClickListener = onClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_section_home, parent, false);
        return new ViewHolderNoticias(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int position) {
        setInfosInView((ViewHolderNoticias) viewHolder, position);
    }

    private void setInfosInView(ViewHolderNoticias viewHolder, int position) {
        final Noticia section = sectionList.get(position);
        final ViewHolderNoticias viewHolderSection = viewHolder;

        if (section.getName() != null) {
            viewHolderSection.tTitulo.setText(section.getName());
        }

        if (section.getTxt() != null) {
            viewHolderSection.tSubtitulo.setText(section.getTxt());
        }

        viewHolderSection.btnCelula.setOnClickListener(view -> {
            if (onClickListener != null) {
                onClickListener.onClick(section);
            }
        });
    }

    @Override
    public int getItemCount() {
        return sectionList != null ? sectionList.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ViewHolder(View itemView) {
            super(itemView);
        }
    }

    private class ViewHolderNoticias extends ViewHolder {

        LinearLayout btnCelula;
        TextView tTitulo;
        TextView tSubtitulo;

        public ViewHolderNoticias(View view) {
            super(view);

            btnCelula = view.findViewById(R.id.btnCelula);
            tTitulo = view.findViewById(R.id.tTitulo);
            tSubtitulo = view.findViewById(R.id.tSubtitulo);
        }
    }
}