package com.cgalves.mystorie.feature.list.view.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.cgalves.mystorie.R;
import com.cgalves.mystorie.common.activity.BaseActivity;
import com.cgalves.mystorie.common.model.DetailSection;
import com.cgalves.mystorie.feature.home.model.Section;
import com.cgalves.mystorie.feature.list.presenter.ListSectionContract;
import com.cgalves.mystorie.feature.list.presenter.ListSectionPresenterImpl;
import com.cgalves.mystorie.feature.list.view.adapter.SectionAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import java.util.List;

@EActivity(R.layout.activity_list_section)
public class ListSectionActivity extends BaseActivity implements ListSectionContract.ListSectioPresenterView {

    @ViewById
    RecyclerView recycler;

    @Extra
    Section section;

    @Bean
    ListSectionPresenterImpl<ListSectionActivity> presenter;

    SectionAdapter sectionAdapter;

    @AfterViews
    void init() {
        setupToolbar(true);
        toolbar.setTitle(section.getName());

        presenter.attachView(this);
        presenter.register();
        presenter.findSectionChoice(section);

        recycler.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
        presenter.unregister();
    }

    @Override
    public void onResultSectionChoice(List<DetailSection> result) {
        sectionAdapter = new SectionAdapter(this, result, onClickListener());
        recycler.setAdapter(sectionAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.attachView(this);
        presenter.register();
    }

    private SectionAdapter.OnClickListener onClickListener() {
        return detailSection -> {
            presenter.unregister();
            presenter.detachView();
            DetailActivity_.intent(ListSectionActivity.this).detailSection(detailSection).start();
        };
    }

}
