package com.cgalves.mystorie.feature.admin.contact;

import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cgalves.mystorie.R;
import com.cgalves.mystorie.common.activity.BaseActivity;
import com.cgalves.mystorie.common.model.Contact;
import com.cgalves.mystorie.common.utils.GsonUtils;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

@EActivity(R.layout.activity_contact_edit)
public class ContactEditActivity extends BaseActivity {


    @ViewById
    ImageView ivProfile;

    @ViewById
    TextView tvEmailContact, tvFacebookUrl, tvTwitterUrl, tvCellphone;

    @AfterViews
    void init() {

        setupToolbar();
        toolbar.setTitle("MEU CONTATO");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ParseQuery<Contact> query = ParseQuery.getQuery(Contact.class);
        query.whereEqualTo("owner", ParseUser.getCurrentUser());
        query.findInBackground((objects, e) -> {
            if (e == null) {

                if(objects.size() == 0) {
                    configFirstContactInformation();
                    Log.d(getString(R.string.tag_next_flow), "Object Return:\n" + GsonUtils.objectToJson(objects));
                } else {
                    ArrayList<Contact> list = (ArrayList<Contact>) objects;
                    Contact contact = objects.get(0);
                    String json = GsonUtils.objectToJson(objects);
                    tvEmailContact.setText(contact.getEmail());
                    tvFacebookUrl.setText(contact.getFacebook());
                    tvTwitterUrl.setText(contact.getTwitter());
                    tvCellphone.setText(contact.getCellphone());
                    Glide.with(this).load(contact.getImage()).into(ivProfile);
                }
                Log.d(getString(R.string.tag_next_flow), "ACHOU" + GsonUtils.objectToJson(objects));
            } else {
                Log.d("score", "Error: " + e.getMessage());
            }
        });
    }

    private void configFirstContactInformation() {
        Contact contact = new Contact(getString(R.string.facebook_miss_information), getString(R.string.twitter_miss_information),
                getString(R.string.cellphone_miss_information), getString(R.string.email_miss_information), "https://cdn1.iconfinder.com/data/icons/social-messaging-productivity-1-1/128/gender-male2-512.png", ParseUser.getCurrentUser());
        contact.setOwner(ParseUser.getCurrentUser());
        contact.saveInBackground();
    }

//    @OptionsMenuItem(android.R.id.home)
//    void onBack() {
//        onBackPressed();
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
