package com.training.victor.development;

import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.IdlingResource;
import com.jakewharton.espresso.OkHttp3IdlingResource;
import okhttp3.OkHttpClient;

public abstract class NormalIdlingResources {
    private static IdlingResource mIdlingResource;

    public static void registerOkHttp(OkHttpClient client) {
        mIdlingResource = OkHttp3IdlingResource.create("NORMAL_HTTP", client);
        IdlingRegistry.getInstance().register(mIdlingResource);
    }

    public static IdlingResource getmIdlingResource() {
        return mIdlingResource;
    }
}
