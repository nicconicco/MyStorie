package com.mystories.cgalves.mystorie;

import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.cgalves.mystorie.R;
import com.cgalves.mystorie.feature.login.view.LoginActivity_;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import mvvm.feature.login.ui.LoginJaderActivity_;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


public class LoginActivityTest {

    @Rule
    public ActivityTestRule<LoginJaderActivity_> mActivityRule = new ActivityTestRule<>(
            LoginJaderActivity_.class);

    @Test
    public void login() {
        String login = "jader";
        String password = "password";

        ViewInteraction loginField = onView(withId(R.id.et_username));
        ViewInteraction passwordField = onView(withId(R.id.et_password));

        loginField.perform(typeText(login), closeSoftKeyboard());
        passwordField.perform(typeText(password), closeSoftKeyboard());

        onView(withId(R.id.btn_login)).perform(click());
    }
}
