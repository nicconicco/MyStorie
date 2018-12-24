package com.cgalves.mystorie.feature.noticias.presenter;

import com.cgalves.mystorie.common.presenter.MvpPresenter;
import com.cgalves.mystorie.common.presenter.MvpView;
import com.cgalves.mystorie.feature.noticias.model.NoticiasResponseList;

/**
 * Created by scopus on 27/07/18.
 */

public class NoticiasContract {

    public interface NoticiasPresenter<V extends NoticiasPresenterView & MvpView> extends MvpPresenter<V> {
        void findSectionNoticias();
    }

    public interface NoticiasPresenterView extends MvpView {
        void onResulSectiontNoticias(NoticiasResponseList result);
    }
}
