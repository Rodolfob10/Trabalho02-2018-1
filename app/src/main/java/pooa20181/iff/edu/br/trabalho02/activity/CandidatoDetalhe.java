package pooa20181.iff.edu.br.trabalho02.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import  pooa20181.iff.edu.br.trabalho02.R;
import  pooa20181.iff.edu.br.trabalho02.model.Candidato;
import io.realm.Realm;

public class CandidatoDetalhe extends AppCompatActivity {

    EditText edtNome, edtPartido, edtCargo, edtNumeroVotos, edtNumeroUrna, edtEstado, edtMunicipio;
    Button btnAdicionar, btnAlterar, btnExcluir;


    Candidato candidato;
    int id;
    private Realm realm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidato_detalhe);

                edtNome = (EditText) findViewById(R.id.edtNomeEleitor);
                edtPartido = (EditText)findViewById(R.id.edtNomeMae);
                edtCargo = (EditText) findViewById(R.id.edtNumeroTitulo);
                edtNumeroVotos = (EditText) findViewById(R.id.edtZona);
                edtNumeroUrna = (EditText) findViewById(R.id.edtDataNascimento);
                edtEstado = (EditText) findViewById(R.id.edtSecao);
                edtMunicipio = (EditText) findViewById(R.id.edtMunicipio);
                btnAdicionar = (Button) findViewById(R.id.btnAdicionar);
                btnAlterar = (Button) findViewById(R.id.btnAltera);
                btnExcluir = (Button) findViewById(R.id.btnExclui);

                Intent intent = getIntent();
                id = (int) intent.getSerializableExtra("id");
                realm = Realm.getDefaultInstance();

                       if(id != 0) {

                           btnAdicionar.setEnabled(false);
                           btnAdicionar.setClickable(false);
                           btnAdicionar.setVisibility(View.INVISIBLE);
                           candidato = realm.where(Candidato.class).equalTo("id", id).findFirst();
                           edtNome.setText(candidato.getNome());
                           edtPartido.setText(candidato.getPartido());
                           edtNumeroVotos.setText(candidato.getNumeroVotos());
                           edtCargo.setText(candidato.getCargo());
                           edtNumeroUrna.setText(candidato.getNumeroUrna());
                           edtEstado.setText(candidato.getEstado());
                           edtMunicipio.setText(candidato.getMunicipio());
                }
                else{
                        btnAlterar.setEnabled(false);
                        btnAlterar.setClickable(false);
                        btnAlterar.setVisibility(View.INVISIBLE);
                        btnExcluir.setEnabled(false);
                        btnExcluir.setClickable(false);
                        btnExcluir.setVisibility(View.INVISIBLE);
                    }

                        btnAdicionar.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                salvar();
                            }
                        });

                       btnExcluir.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                       excluir();
                            }
                       });

                       btnAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View view) {
                                        alterar();
                                    }
        });

    }
        public void excluir()
            {
                    realm.beginTransaction();
                    candidato.deleteFromRealm();
                    realm.commitTransaction();
                    realm.close();
                    Toast.makeText(this, "Candidato Excluido", Toast.LENGTH_LONG).show();
                    this.finish();

                        }

            public void salvar()
    {
                int proximoID = 1;
                if(realm.where(Candidato.class).max("id") != null)
                {
                               proximoID = realm.where(Candidato.class).max("id").intValue()+1;
                }

                realm.beginTransaction();
                Candidato candidato = new Candidato();
                candidato.setId(proximoID);

                setEgrava(candidato);

                realm.copyToRealm(candidato);
                realm.commitTransaction();
                realm.close();
                Toast.makeText(this, "Candidato Cadastrado", Toast.LENGTH_LONG).show();
                this.finish();
            }

            public void alterar()
            {
                realm.beginTransaction();
                setEgrava(candidato);
                realm.copyFromRealm(candidato);
                realm.commitTransaction();
                realm.close();

                        Toast.makeText(this, "Candidato Alterado", Toast.LENGTH_LONG).show();
                this.finish();
            }

            public void setEgrava(Candidato candidato)
            {
                candidato.setNome(edtNome.getText().toString());
                candidato.setPartido(edtPartido.getText().toString());
                candidato.setNumeroVotos(edtNumeroVotos.getText().toString());
                candidato.setNumeroUrna(edtNumeroUrna.getText().toString());
                candidato.setCargo(edtCargo.getText().toString());
                candidato.setEstado(edtEstado.getText().toString());
                candidato.setMunicipio(edtMunicipio.getText().toString());
            }




}