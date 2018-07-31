package com.cgalves.mystorie.feature.novidades.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cgalves.mystorie.R;
import com.cgalves.mystorie.common.model.DetailSection;
import com.cgalves.mystorie.feature.list.view.activity.DetailActivity_;
import com.cgalves.mystorie.feature.novidades.model.NovidadesResponseList;
import com.cgalves.mystorie.feature.novidades.presenter.NovidadesContract;
import com.cgalves.mystorie.feature.novidades.presenter.NovidadesPresenterImpl;
import com.cgalves.mystorie.feature.novidades.view.adapter.NovidadesAdapter;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

/**
 * Created by scopus on 27/07/18.
 */


@EFragment
public class NovidadesFragment extends Fragment implements NovidadesContract.NovidadesPresenterView {

    @Bean
    NovidadesPresenterImpl<NovidadesFragment> presenter;

    @ViewById
    RecyclerView recyclerList;

    private NovidadesAdapter novidadesAdapter;


    @AfterInject
    void afterINject() {
        presenter.attachView(this);
        presenter.register();
    }

    @AfterViews
    void calledAfterViews() {
        Log.d(NovidadesFragment.class.getName(), "calledAfterViewInjection");

        presenter.attachView(this);
        presenter.register();
        recyclerList.setLayoutManager(new LinearLayoutManager(getContext()));
        presenter.findSectionNovidades();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_novidades, container, false);
        Log.d(NovidadesFragment.class.getName(), "onCreateView");
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        Log.d(NovidadesFragment.class.getName(), "onViewCreated");
    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);

        if (menuVisible) {
            if (verifiyIfPresenterIsNotNull()) {
                presenter.findSectionNovidades();
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
        Log.d(NovidadesFragment.class.getName(), "onPause");
        if (verifiyIfPresenterIsNotNull()) {
            presenter.detachView();
            presenter.unregister();
        }
    }

    private boolean verifiyIfPresenterIsNotNull() {
        return presenter != null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(NovidadesFragment.class.getName(), "onDestroy");
        presenter.detachView();
        presenter.unregister();
    }

    @Override
    public void onResulSectiontNovidades(NovidadesResponseList result) {
        novidadesAdapter = new NovidadesAdapter(getContext(), result.getNovidadeList(), onClickListener());
        recyclerList.setAdapter(novidadesAdapter);
    }

    private NovidadesAdapter.OnClickListener onClickListener() {
        return noticia -> {
            presenter.unregister();
            presenter.detachView();

            DetailSection d = new DetailSection();

            d.setName(noticia.getName());
            d.setTxt(noticia.getTxt());
            d.setImage(noticia.getImg());

            DetailActivity_.intent(getActivity()).detailSection(d).start();
        };
    }
}
