package com.example.xyzreader.application;

import android.app.Application;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;

/**
 * Created by 123 on 2017/1/18.
 */

public class XyzReaderApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ConnectionSpec spec = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
                .allEnabledCipherSuites()
                .allEnabledTlsVersions()
                .build();

        OkHttpClient client = new OkHttpClient.Builder()
                .connectionSpecs(Collections.singletonList(spec))
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();

        Picasso picasso = new Picasso.Builder(this)
                .downloader(new OkHttp3Downloader(client))
                .build();

        try {
            Picasso.setSingletonInstance(picasso);
        } catch (IllegalStateException ignored) {
            // Picasso instance was already set
            // cannot set it after Picasso.with(Context) was already in use
        }
    }
}
