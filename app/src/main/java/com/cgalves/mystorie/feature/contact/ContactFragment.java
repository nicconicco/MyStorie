package com.cgalves.mystorie.feature.contact;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cgalves.mystorie.R;
import com.cgalves.mystorie.common.fragment.BaseFragment;
import com.cgalves.mystorie.common.model.Contact;
import com.cgalves.mystorie.common.utils.GsonUtils;
import com.cgalves.mystorie.feature.admin.contact.ContactEditActivity_;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

/**
 * Created by scopus on 27/07/18.
 */

@EFragment
public class ContactFragment extends BaseFragment {

    @ViewById
    ImageView ivProfile;

    @ViewById
    TextView tvEmailContact, tvFacebookUrl, tvTwitterUrl, tvCellphone;

    @Click(R.id.fab)
    void onClickEditContact() {
        ContactEditActivity_.intent(this).start();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contatos, container, false);

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
                    tvEmailContact.setText("Entre em contato com\na gente pelo email: " + contact.getEmail());
                    tvFacebookUrl.setText(contact.getFacebook());
                    tvTwitterUrl.setText(contact.getTwitter());
                    tvCellphone.setText("Telefone: " + contact.getCellphone());
                    Glide.with(this).load(contact.getImage()).into(ivProfile);
                }
                Log.d(getString(R.string.tag_next_flow), "ACHOU" + GsonUtils.objectToJson(objects));
            } else {
                Log.d("score", "Error: " + e.getMessage());
            }
        });

        return view;
    }

    private void configFirstContactInformation() {
        Contact contact = new Contact(getString(R.string.facebook_miss_information), getString(R.string.twitter_miss_information),
                getString(R.string.cellphone_miss_information), getString(R.string.email_miss_information), "https://cdn1.iconfinder.com/data/icons/social-messaging-productivity-1-1/128/gender-male2-512.png", ParseUser.getCurrentUser());
        contact.setOwner(ParseUser.getCurrentUser());
        contact.saveInBackground();
    }

}
