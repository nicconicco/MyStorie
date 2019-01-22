package com.cgalves.mystorie.feature.noticias.view.fragment;


import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cgalves.mystorie.R;
import com.cgalves.mystorie.common.fragment.BaseFragment;
import com.cgalves.mystorie.common.model.DetailSection;
import com.cgalves.mystorie.feature.list.view.activity.DetailActivity_;
import com.cgalves.mystorie.feature.noticias.model.NoticiasResponseList;
import com.cgalves.mystorie.feature.noticias.presenter.NoticiasContract;
import com.cgalves.mystorie.feature.noticias.presenter.NoticiasPresenterImpl;
import com.cgalves.mystorie.feature.noticias.view.adapter.NoticiasAdapter;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by scopus on 27/07/18.
 */


@EFragment(R.layout.fragment_noticias)
public class NoticiasFragment extends BaseFragment implements NoticiasContract.NoticiasPresenterView {

    @ViewById
    LinearLayout toolbarFragment;

    @ViewById
    TextView tvTitle;

    @FragmentArg
    boolean showToolBarBack = false;

    @Bean
    NoticiasPresenterImpl<NoticiasFragment> presenter;

    @ViewById
    RecyclerView recyclerNoticias;
    private View view;

    @AfterViews
    void calledAfterViewInjection() {
        showToolbarOrNot(showToolBarBack, toolbarFragment);
        tvTitle.setText(getString(R.string.noticias));

        if(verifiyIfPresenterIsNotNull()) {
            recyclerNoticias.setLayoutManager(new LinearLayoutManager(getContext()));
            presenter.findSectionNoticias();
        }
    }

    @Click(R.id.btn_back)
    void onClickBack() {
        getActivity().finish();
    }

    @AfterInject
    void calledAfterInjection() {
        presenter.attachView(this);
        presenter.register();
    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);

        if (menuVisible) {
            if (verifiyIfPresenterIsNotNull()) {
                presenter.findSectionNoticias();
            }
        } else {
            if (verifiyIfPresenterIsNotNull()) {
                presenter.detachView();
                presenter.unregister();
            }
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view;
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.detachView();
        presenter.unregister();
    }

    private boolean verifiyIfPresenterIsNotNull() {
        return presenter != null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detachView();
        presenter.unregister();
    }

    @Override
    public void onResulSectiontNoticias(NoticiasResponseList result) {
        NoticiasAdapter noticiasAdapter = new NoticiasAdapter(getContext(), result.getNoticiaList(), onClickListener());

        recyclerNoticias = view.findViewById(R.id.recycler_noticias);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerNoticias.setLayoutManager(manager);
        recyclerNoticias.setHasFixedSize(true);
        recyclerNoticias.setAdapter(noticiasAdapter);
    }

    private NoticiasAdapter.OnClickListener onClickListener() {
        return noticia -> {
            presenter.unregister();
            presenter.detachView();

            DetailSection d = new DetailSection();

            d.setDetailname(noticia.getName());
            d.setTxt(noticia.getTxt());
            d.setImage(noticia.getImg());

            DetailActivity_.intent(getActivity()).detailSection(d).start();
        };
    }
}
