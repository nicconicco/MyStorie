package com.cgalves.mystorie.feature.admin.home;

import android.support.v7.app.AppCompatActivity;

import com.cgalves.mystorie.R;
import com.cgalves.mystorie.common.model.Noticia;
import com.cgalves.mystorie.common.model.Novidade;
import com.cgalves.mystorie.feature.admin.contact.ContactActivity_;
import com.parse.ParseUser;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_master_home)
public class MasterHomeActivity extends AppCompatActivity {

    @Click(R.id.btn_contact)
    void onClickBtnContact() {
        ContactActivity_.intent(this).start();
    }

    @Click(R.id.btn_create_new)
    void onClickBtnCreateNew() {
        Novidade novidade = new Novidade();
        novidade.NovidadeConstruct("Nova novidade", "Texto novidade", "urldaimagem");
        novidade.setOwner(ParseUser.getCurrentUser());
        novidade.saveInBackground();
    }

    @Click(R.id.btn_create_news)
    void onClickBtnCreateNews() {
        Noticia noticia = new Noticia();
        noticia.NoticiaConstruct("Nova noticia", "Texto noticia", "urldaimagem");
        noticia.setOwner(ParseUser.getCurrentUser());
        noticia.saveInBackground();
    }

}
