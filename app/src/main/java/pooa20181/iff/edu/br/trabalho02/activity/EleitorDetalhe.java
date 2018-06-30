package pooa20181.iff.edu.br.trabalho02.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.realm.Realm;
import pooa20181.iff.edu.br.trabalho02.R;
import pooa20181.iff.edu.br.trabalho02.model.Eleitor;

public class EleitorDetalhe extends AppCompatActivity {

    EditText edtNomeEleitor, edtNomeMae, edtDataNascimento, edtNumeroTitulo, edtZona, edtSecao, edMunicipio;
    Button btnAdiciona, btnAltera, btnExclui;

    Eleitor eleitor;
    int id;
    private Realm realm;

    SimpleDateFormat form = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eleitor_detalhe);

        edtNomeEleitor = (EditText) findViewById(R.id.edtNomeEleitor);
        edtNomeMae = (EditText) findViewById(R.id.edtNomeMae);
        edtDataNascimento = (EditText) findViewById(R.id.edtDataNascimento);
        edtNumeroTitulo = (EditText) findViewById(R.id.edtNumeroTitulo);
        edtZona = (EditText) findViewById(R.id.edtZona);
        edtSecao = (EditText) findViewById(R.id.edtSecao);
        edMunicipio = (EditText) findViewById(R.id.edMunicipio);
        btnAdiciona = (Button) findViewById(R.id.btnAdiciona);
        btnAltera = (Button) findViewById(R.id.btnAltera);
        btnExclui = (Button) findViewById(R.id.btnExclui);


        Intent intent = getIntent();
        id = (int) intent.getSerializableExtra("id");
        realm = Realm.getDefaultInstance();

        if(id != 0) {

            btnAdiciona.setEnabled(false);
            btnAdiciona.setClickable(false);
            btnAdiciona.setVisibility(View.INVISIBLE);
            eleitor = realm.where(Eleitor.class).equalTo("id", id).findFirst();
            edtNomeEleitor.setText(eleitor.getNome());
            edtNomeMae.setText(eleitor.getNomeMae());
            edtDataNascimento.setText(form.format((Date) eleitor.getDataNascimento()));
            edtNumeroTitulo.setText(eleitor.getNumeroTitulo());
            edtZona.setText(eleitor.getZona());
            edMunicipio.setText(eleitor.getMunicipio());
        }
        else{
            btnAltera.setEnabled(false);
            btnAltera.setClickable(false);
            btnAltera.setVisibility(View.INVISIBLE);
            btnExclui.setEnabled(false);
            btnExclui.setClickable(false);
            btnExclui.setVisibility(View.INVISIBLE);
        }

        btnAdiciona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvar();
            }
        });

        btnExclui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                excluir();
            }
        });

        btnAltera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alterar();
            }
        });

    }

    public void excluir()
    {
        realm.beginTransaction();
        eleitor.deleteFromRealm();
        realm.commitTransaction();
        realm.close();
        Toast.makeText(this, "Eleitor Excluido", Toast.LENGTH_LONG).show();
        this.finish();

    }

    public void salvar()
    {
        int proximoID = 1;
        if(realm.where(Eleitor.class).max("id") != null)
        {
            proximoID = realm.where(Eleitor.class).max("id").intValue()+1;
        }

        realm.beginTransaction();
        Eleitor eleitor = new Eleitor();
        eleitor.setId(proximoID);

        setEgrava(eleitor);

        realm.copyToRealm(eleitor);
        realm.commitTransaction();
        realm.close();
        Toast.makeText(this, "Eleitor Cadastrado", Toast.LENGTH_LONG).show();
        this.finish();
    }

    public void alterar()
    {
        realm.beginTransaction();
        setEgrava(eleitor);
        realm.copyFromRealm(eleitor);
        realm.commitTransaction();
        realm.close();

        Toast.makeText(this, "Eleitor Alterado", Toast.LENGTH_LONG).show();
        this.finish();
    }

    public void setEgrava(Eleitor eleitor)
    {
        eleitor.setNome(edtNomeEleitor.getText().toString());
        eleitor.setNomeMae(edtNomeMae.getText().toString());

        try {
            eleitor.setDataNascimento((Date) form.parse(edtDataNascimento.getText().toString()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        eleitor.setNumeroTitulo(edtNumeroTitulo.getText().toString());
        eleitor.setZona(edtZona.getText().toString());
        eleitor.setSecao(edtSecao.getText().toString());
        eleitor.setMunicipio(edMunicipio.getText().toString());
    }
}
