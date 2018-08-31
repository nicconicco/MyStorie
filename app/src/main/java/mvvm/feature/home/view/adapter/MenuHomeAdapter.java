package mvvm.feature.home.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cgalves.mystorie.R;
import com.cgalves.mystorie.feature.home.model.Section;

import java.util.List;

/**
 * Created by Scopus on 17/07/18.
 */

public class MenuHomeAdapter extends RecyclerView.Adapter<MenuHomeAdapter.ViewHolder> {
    private final Context context;
    private final List<Section> sectionList;

    public interface OnClickListener {
        void onClick(Section n);
    }

    private OnClickListener onClickListener;

    public MenuHomeAdapter(Context context, List<Section> sectionList, OnClickListener onClickListener) {
        this.context = context;
        this.sectionList = sectionList;
        this.onClickListener = onClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_section_home, parent, false);
        return new ViewHolderSection(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int position) {
        setInfosInView((ViewHolderSection) viewHolder, position);
    }

    private void setInfosInView(ViewHolderSection viewHolder, int position) {
        final Section section = sectionList.get(position);
        final ViewHolderSection viewHolderSection = viewHolder;

        if (section.getName() != null) {
            viewHolderSection.tTitulo.setText(section.getName());
        }

        if (section.getSubTitle() != null) {
            viewHolderSection.tSubtitulo.setText(section.getSubTitle());
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

    private class ViewHolderSection extends ViewHolder {

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