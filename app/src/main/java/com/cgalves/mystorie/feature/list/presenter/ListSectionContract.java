package com.cgalves.mystorie.feature.list.presenter;

import android.util.Log;

import com.cgalves.mystorie.common.model.DetailSection;
import com.cgalves.mystorie.common.presenter.MvpPresenter;
import com.cgalves.mystorie.common.presenter.MvpView;
import com.cgalves.mystorie.feature.home.model.Image;
import com.cgalves.mystorie.feature.home.model.Section;
import com.cgalves.mystorie.feature.home.presenter.HomeContract;

import java.util.List;

/**
 * Created by Carlos Nicolau Galves on 25/07/18.
 */

public class ListSectionContract {

    public interface ListSectioPresenter<V extends ListSectionContract.ListSectioPresenterView & MvpView> extends MvpPresenter<V> {
        void findSectionChoice(Section section);
    }

    public interface ListSectioPresenterView extends MvpView {
        void onResultSectionChoice(List<DetailSection> result);
    }


}
