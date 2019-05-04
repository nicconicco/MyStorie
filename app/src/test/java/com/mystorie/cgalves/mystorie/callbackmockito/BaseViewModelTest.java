package com.mystorie.cgalves.mystorie.callbackmockito;

import android.support.annotation.RawRes;

import com.cgalves.mystorie.MyStorieApplication_;

import java.io.InputStream;
import java.util.Scanner;


class BaseViewModelTest {
    public String readRawResource(@RawRes int res) {
        return readStream(MyStorieApplication_.getInstance().getResources().openRawResource(res));
    }

    private String readStream(InputStream is) {
        Scanner s = new Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}
