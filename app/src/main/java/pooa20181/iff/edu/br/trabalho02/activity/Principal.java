package pooa20181.iff.edu.br.trabalho02.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import pooa20181.iff.edu.br.trabalho02.R;

        public class PrincipalActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

            private String[] activities = {"ListaEleitorActivity"};
             @Override
                protected void onCreate(Bundle savedInstanceState) {
                    super.onCreate(savedInstanceState);
                    setContentView(R.layout.activity_principal);

                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, activities);

                        ListView listView = (ListView)findViewById(R.id.listaMenu);
                        listView.setAdapter(adapter);
                        listView.setOnItemClickListener(this);

                    }


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = null;

                        try {
                        Class obj =    Class.forName("pooa20181.iff.edu.br.trabalho02.activity." + activities[position]);
                        intent = new Intent(this, obj);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                startActivity(intent);
            }
}