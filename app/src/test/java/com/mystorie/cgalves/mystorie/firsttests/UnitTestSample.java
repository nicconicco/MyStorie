package com.mystorie.cgalves.mystorie.firsttests;

import android.content.Context;

import com.cgalves.mystorie.R;
import com.cgalves.mystorie.common.providers.BusProvider_;
import com.cgalves.mystorie.feature.login.presenter.LoginContract;

import org.greenrobot.eventbus.EventBus;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;

import java.util.Iterator;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNotSame;
import static org.hamcrest.CoreMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by scopus on 03/08/18.
 */
@RunWith(MockitoJUnitRunner.class)
public class UnitTestSample {

    private static final String FAKE_STRING = "Hello World!";

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    Context mMockContext;


    @Test
    public void readStringFromContext_LocalizedString() {
        Mockito.when(mMockContext.getString(R.string.hello_word))
                .thenReturn(FAKE_STRING);
        ClassUnderTest myObjectUnderTest = new ClassUnderTest(mMockContext);

        String result = myObjectUnderTest.getHelloWorldString();

        assertNotNull(mMockContext);
        Assert.assertThat(result, isA(String.class));
        assertEquals(result, FAKE_STRING);
        assertNotSame(result, "Hello World!!");
    }

    private class ClassUnderTest {
        Context context = null;

        public ClassUnderTest(Context mMockContext) {
            this.context = mMockContext;
        }

        public String getHelloWorldString() {
            return context != null ? context.getString(R.string.hello_word) : "Contexto nulo, por favor passe o contexto";
        }
    }


    @Test
    public void testQuery()  {
        Iterator i= mock(Iterator.class);
        when(i.next()).thenReturn("Mockito").thenReturn("rocks");
        String result= i.next()+" "+i.next();
        //assert
        assertEquals("Mockito rocks", result);
    }


    @Mock
    LoginContract.LoginPresenterView view;

//    @Test
//    public void TestAPI() throws Exception {
//        LoginPresenterImpl_ loginPresenterImpl_ = LoginPresenterImpl_.getInstance_(mMockContext);
//
//        loginPresenterImpl_.attachView(view);
//        loginPresenterImpl_.register();
//        loginPresenterImpl_.doLogin("nico", "nico");
//
//        verify(view).onLoginResult(false);
//
//        loginPresenterImpl_.unregister();
//        loginPresenterImpl_.detachView();
//    }

    protected Object getVo(final Class voClass) {
        try {
            Thread.currentThread().sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        BusProvider_ busProvider = BusProvider_.getInstance_(mMockContext);
        EventBus eventBus = busProvider.bus();

        Object vo = eventBus.getStickyEvent(voClass);

        if (vo == null) {
            vo = eventBus.getStickyEvent(null);
        }

        if (vo == null) {
            vo = eventBus.getStickyEvent(String.class);
        }

        eventBus.removeAllStickyEvents();


        return vo;
    }


}