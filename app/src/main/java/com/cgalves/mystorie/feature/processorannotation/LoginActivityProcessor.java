package com.cgalves.mystorie.feature.processorannotation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.cgalves.mystorie.R;
import com.mindorks.binder.Binding;
import com.mindorks.lib.annotations.BindView;
import com.mindorks.lib.annotations.OnClick;

public class LoginActivityProcessor extends AppCompatActivity {

    @BindView(R.id.tv_content)
    TextView tvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_processor);
        Binding.bind(this);
    }

    @OnClick(R.id.bt_1)
    void bt1Click(View v) {
        tvContent.setText("Button 1 Clicked");
    }

    @OnClick(R.id.bt_2)
    void bt2Click(View v) {
        tvContent.setText("Button 2 Clicked");
    }
}
