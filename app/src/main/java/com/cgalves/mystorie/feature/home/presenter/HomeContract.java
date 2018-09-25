package com.cgalves.mystorie.feature.home.presenter;

import com.cgalves.mystorie.common.presenter.MvpPresenter;
import com.cgalves.mystorie.common.presenter.MvpView;
import com.cgalves.mystorie.feature.home.model.Image;
import com.cgalves.mystorie.feature.home.model.Section;

import java.util.List;

/**
 * Created by Carlos Nicolau Galves on 17/07/18.
 */

public class HomeContract {

    public interface HomePresenter<V extends HomePresenterView & MvpView> extends MvpPresenter<V> {
        void findImagesTopHeader();

        void findSectionInBody();
    }

    public interface HomePresenterView extends MvpView {
        void onResultImages(List<Image> result);

        void onResultSectionBody(List<Section> result);
    }
}
