package com.cgalves.mystorie.feature.AdminUser.home;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.cgalves.mystorie.R;
import com.cgalves.mystorie.common.model.Contact;
import com.cgalves.mystorie.common.model.Noticia;
import com.cgalves.mystorie.common.model.Novidade;
import com.cgalves.mystorie.common.utils.GsonUtils;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;

import java.util.List;

@EActivity(R.layout.activity_master_home)
public class MasterHomeActivity extends AppCompatActivity {

    @Click(R.id.btn_contact)
    void onClickBtnContact() {
        ParseQuery<Contact> query = ParseQuery.getQuery(Contact.class);
        query.whereEqualTo("owner", ParseUser.getCurrentUser());
        query.findInBackground(new FindCallback<Contact>() {
            @Override
            public void done(List<Contact> objects, ParseException e) {
                if (e == null) {
//                    String facebook = item.getFacebook();
//                    String twitter = item.getTwitter();
//                    String cellphone = item.getCellphone();
                    Log.d(getString(R.string.tag_next_flow), "ACHOU" + GsonUtils.objectToJson(objects));
                } else {
                    Contact contact = new Contact("Nova contact", "Texto contact", "urldaimagem", ParseUser.getCurrentUser());
                    contact.setOwner(ParseUser.getCurrentUser());
                    contact.saveInBackground();
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });
    }

    @Click(R.id.btn_create_new)
    void onClickBtnCreateNew() {
        Novidade novidade = new Novidade("Nova novidade", "Texto novidade", "urldaimagem");
        novidade.setOwner(ParseUser.getCurrentUser());
        novidade.saveInBackground();
    }

    @Click(R.id.btn_create_news)
    void onClickBtnCreateNews() {
        Noticia noticia = new Noticia("Nova noticia", "Texto noticia", "urldaimagem");
        noticia.setOwner(ParseUser.getCurrentUser());
        noticia.saveInBackground();
    }

}
