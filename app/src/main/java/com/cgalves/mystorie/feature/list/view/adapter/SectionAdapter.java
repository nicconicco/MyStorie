package com.cgalves.mystorie.feature.list.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cgalves.mystorie.R;
import com.cgalves.mystorie.common.model.DetailSection;

import java.util.List;

/**
 * Created by scopus on 25/07/18.
 */

public class SectionAdapter extends RecyclerView.Adapter<SectionAdapter.ViewHolder> {

    private final List<DetailSection> sectionList;
    private final SectionAdapter.OnClickListener onClickListener;

    public interface OnClickListener {
        void onClick(DetailSection detailSection);
    }


    public SectionAdapter(List<DetailSection> sectionList, SectionAdapter.OnClickListener onClickListener) {
        this.sectionList = sectionList;
        this.onClickListener = onClickListener;
    }

    @Override
    public SectionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_section_list, parent, false);
        return new SectionAdapter.ViewHolderSection(view);
    }

    @Override
    public void onBindViewHolder(final SectionAdapter.ViewHolder viewHolder, int position) {
        setView((SectionAdapter.ViewHolderSection) viewHolder, position);
    }

    private void setView(SectionAdapter.ViewHolderSection viewHolder, int position) {
        final DetailSection section = sectionList.get(position);
        final SectionAdapter.ViewHolderSection viewHolderSection = viewHolder;

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

    private class ViewHolderSection extends SectionAdapter.ViewHolder {

        LinearLayout btnCelula;
        TextView tTitulo;
        TextView tSubtitulo;

        public ViewHolderSection(View view) {
            super(view);

            btnCelula = view.findViewById(R.id.btnCelula);
            tTitulo = view.findViewById(R.id.tTitulo);
            tSubtitulo = view.findViewById(R.id.tSubtitulo);
        }
    }
}
