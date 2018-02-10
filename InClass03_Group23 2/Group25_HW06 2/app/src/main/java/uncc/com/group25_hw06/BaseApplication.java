package uncc.com.group25_hw06;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by rosyazad on 05/11/17.
 */

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
       RealmConfiguration config = new RealmConfiguration.Builder().name("myrealm2.realm").build();
        /*RealmConfiguration config = new RealmConfiguration
                .Builder()
                .deleteRealmIfMigrationNeeded()
                .build();*/

        Realm.setDefaultConfiguration(config);

    }
}
