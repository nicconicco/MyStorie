package com.cgalves.mystorie.feature.novidades.presenter;

import com.cgalves.mystorie.common.presenter.MvpPresenter;
import com.cgalves.mystorie.common.presenter.MvpView;
import com.cgalves.mystorie.feature.novidades.model.NovidadesResponseList;

/**
 * Created by scopus on 27/07/18.
 */

public class NovidadesContract {

    public interface NovidadesPresenter<V extends NovidadesPresenterView & MvpView> extends MvpPresenter<V> {
        void findSectionNovidades();
    }

    public interface NovidadesPresenterView extends MvpView {
        void onResulSectiontNovidades(NovidadesResponseList result);
    }
}
