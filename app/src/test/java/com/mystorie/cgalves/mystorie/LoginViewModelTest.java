package com.mystorie.cgalves.mystorie;

import android.arch.lifecycle.ViewModelProviders;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import mvvm.feature.login.ui.LoginJaderActivity;
import mvvm.feature.login.vm.LoginViewModel;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotSame;
import static junit.framework.Assert.assertTrue;

/**
 * Created by scopus on 31/08/18.
 */

@RunWith(RobolectricTestRunner.class)
public class LoginViewModelTest {

    @Test
    public void testWorngName() {
        LoginViewModel vm = getViewModel();
        vm.login("nicco","senha");
        assertNotSame("nicco", vm.userResponse.getValue().getUser().getName());
    }

    @Test
    public void testFakeName() {
        LoginViewModel vm = getViewModel();
        vm.login("nicco","senha");
        assertEquals("Fake Test", vm.userResponse.getValue().getUser().getName());
    }

    @Test
    public void testIsAdminFalse() {
        LoginViewModel vm = getViewModel();
        vm.login("nicco","senha");
        assertFalse(vm.userResponse.getValue().getUser().getIsAdmin());
    }

    private LoginViewModel getViewModel() {
        LoginJaderActivity activity = Robolectric.setupActivity(LoginJaderActivity.class);
        return ViewModelProviders.of(activity).get(LoginViewModel.class);
    }
}
