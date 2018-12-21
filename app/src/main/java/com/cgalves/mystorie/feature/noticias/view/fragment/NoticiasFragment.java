package com.cgalves.mystorie.feature.noticias.view.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

/**
 * Created by scopus on 27/07/18.
 */


@EFragment
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
    RecyclerView recyclerList;

    @AfterViews
    void calledAfterViewInjection() {
        showToolbarOrNot(showToolBarBack, toolbarFragment);
        tvTitle.setText(getString(R.string.noticias));

        if(verifiyIfPresenterIsNotNull()) {
            recyclerList.setLayoutManager(new LinearLayoutManager(getContext()));
            presenter.findSectionNoticias();
        }
    }

    @Click(R.id.btn_back)
    void onClickBack() {
        getActivity().finish();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_noticias, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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
        NoticiasAdapter menuHomeAdapter = new NoticiasAdapter(getContext(), result.getNoticiaList(), onClickListener());
        recyclerList.setAdapter(menuHomeAdapter);
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
