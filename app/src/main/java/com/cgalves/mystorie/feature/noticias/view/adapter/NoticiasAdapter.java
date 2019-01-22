package com.cgalves.mystorie.feature.noticias.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.cgalves.mystorie.R;
import com.cgalves.mystorie.common.model.Noticia;

import java.util.List;


/**
 * Created by scopus on 27/07/18.
 */

public class NoticiasAdapter extends RecyclerView.Adapter<NoticiasAdapter.ViewHolder> {

    private final Context context;
    private final List<Noticia> sectionList;
    private final OnClickListener onClickListener;

    public interface OnClickListener {
        void onClick(Noticia n);
    }

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

        if(section.getImg() != null) {
            viewHolderSection.progressBar.setVisibility(View.VISIBLE);

            //todo:
            Glide.with(context).
                    load(section.getImg())
                    .listener(new RequestListener<String, GlideDrawable>() {
                        @Override
                        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                            viewHolderSection.progressBar.setVisibility(View.GONE);
                            Log.e("Erro_Glide : ", e.getMessage());
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                            viewHolderSection.progressBar.setVisibility(View.GONE);
                            return false;
                        }
                    })
                    .crossFade(1000)
                    .into(viewHolderSection.ivPhoto);
            Glide.with(context).load(section.getImg()).into(viewHolderSection.ivPhoto);
        }

        viewHolderSection.btn.setOnClickListener(view -> {
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

        LinearLayout btn;
        TextView tTitulo;
        TextView tSubtitulo;
        ImageView ivPhoto;
        ProgressBar progressBar;

        ViewHolderNoticias(View view) {
            super(view);
            btn = view.findViewById(R.id.btn);
            tTitulo = view.findViewById(R.id.tv_title);
            tSubtitulo = view.findViewById(R.id.tv_subtitle);
            ivPhoto = view.findViewById(R.id.iv_photo);
            progressBar = view.findViewById(R.id.progress_bar);
        }
    }
}