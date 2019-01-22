package com.cgalves.mystorie.feature.novidades.view.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cgalves.mystorie.R;
import com.cgalves.mystorie.common.fragment.BaseFragment;
import com.cgalves.mystorie.common.model.DetailSection;
import com.cgalves.mystorie.feature.list.view.activity.DetailActivity_;
import com.cgalves.mystorie.feature.novidades.model.NovidadesResponseList;
import com.cgalves.mystorie.feature.novidades.presenter.NovidadesContract;
import com.cgalves.mystorie.feature.novidades.presenter.NovidadesPresenterImpl;
import com.cgalves.mystorie.feature.novidades.view.adapter.NovidadesAdapter;

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

@EFragment(R.layout.fragment_novidades)
public class NovidadesFragment extends BaseFragment implements NovidadesContract.NovidadesPresenterView {

    @ViewById
    LinearLayout toolbarFragment;

    @FragmentArg
    boolean showToolBarBack = false;

    @ViewById
    TextView tvTitle;

    @Bean
    NovidadesPresenterImpl<NovidadesFragment> presenter;

    @ViewById
    RecyclerView recyclerList;
    private View view;

    @Click(R.id.btn_back)
    void onClickBack() {
        getActivity().finish();
    }

    @AfterInject
    void afterInject() {
        presenter.attachView(this);
        presenter.register();
    }

    @AfterViews
    void calledAfterViews() {
        Log.d(NovidadesFragment.class.getName(), "calledAfterViewInjection");
        showToolbarOrNot(showToolBarBack, toolbarFragment);
        tvTitle.setText(getString(R.string.novidades));

        presenter.attachView(this);
        presenter.register();
        recyclerList.setLayoutManager(new LinearLayoutManager(getContext()));
        presenter.findSectionNovidades();
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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view;
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
        if (verifiyIfPresenterIsNotNull()) {
            presenter.detachView();
            presenter.unregister();
        }
    }

    @Override
    public void onResulSectiontNovidades(NovidadesResponseList result) {
        NovidadesAdapter novidadesAdapter = new NovidadesAdapter(getContext(), result.getNovidadeList(), onClickListener());

        recyclerList = view.findViewById(R.id.recycler_list);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerList.setLayoutManager(manager);
        recyclerList.setHasFixedSize(true);
        recyclerList.setAdapter(novidadesAdapter);
    }


    private NovidadesAdapter.OnClickListener onClickListener() {
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
