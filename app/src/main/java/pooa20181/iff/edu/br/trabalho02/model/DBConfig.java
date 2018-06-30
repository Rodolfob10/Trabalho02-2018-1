package pooa20181.iff.edu.br.trabalho02.model;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class DBConfig extends Application{

            public void onCreate(){
                super.onCreate();

                       Realm.init(getApplicationContext());

                        RealmConfiguration.Builder builder = new RealmConfiguration.Builder();
                        builder.name("eleicao.realm");
                        builder.schemaVersion(0);
                        builder.deleteRealmIfMigrationNeeded();
                        RealmConfiguration realmConfiguration = builder.build();
                        Realm.setDefaultConfiguration(realmConfiguration);


                }
}