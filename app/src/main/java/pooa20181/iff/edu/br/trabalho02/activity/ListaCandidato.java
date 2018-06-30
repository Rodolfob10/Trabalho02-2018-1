package pooa20181.iff.edu.br.trabalho02.activity;

import android.os.Bundle;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;


import pooa20181.iff.edu.br.trabalho02.R;
import io.realm.Realm;

public class ListaCandidato extends AppCompatActivity {

            private Realm realm;

            @Override
    protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_lista_candidato);
                Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
                setSupportActionBar(toolbar);

                        realm = Realm.getDefaultInstance();
                        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
                        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                                               .setAction("Action", null).show();
                            }
            });
            }

            public void onResume()
    {
                super.onResume();

                    }

        }