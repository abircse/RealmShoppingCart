package com.coxtunes.androidnewshoppingcart.base;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class BaseCart extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //initializing realm, once per app
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration
                .Builder()
                .deleteRealmIfMigrationNeeded()
                .build();

        Realm.setDefaultConfiguration(config);
    }
}
