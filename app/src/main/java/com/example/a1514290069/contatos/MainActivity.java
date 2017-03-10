package com.example.a1514290069.contatos;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.btnOK).setOnClickListener(new View.OnClickListener(){

            @Override

            public void onClick(View v){

                Intent it = new Intent();

                EditText txtNome = (EditText) findViewById(R.id.CampoNome);
                String nome = txtNome.getText().toString();
                it.putExtra("Nome", nome);

                EditText txtEndereco = (EditText) findViewById(R.id.CampoEndereco);
                String endereco = txtEndereco.getText().toString();
                it.putExtra("endereco", endereco);

                EditText txtTel = (EditText) findViewById(R.id.CampoTel);
                String telefone = txtTel.getText().toString();
                it.putExtra("telefone", telefone);


                EditText txtEmail = (EditText) findViewById(R.id.CampoEmail);
                String email = txtEmail.getText().toString();
                it.putExtra("email", email);

                EditText txtSenha = (EditText) findViewById(R.id.CampoSenha);
                String senha = txtSenha.getText().toString();
                it.putExtra("email", email);


                setResult(RESULT_OK, it);
                finish();
            }
        });

        Button btn = (Button) findViewById(R.id.btnCANCELAR);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void salvarCadastro (Intent intent){
        dbHelper = new DatabaseHelper(this);

        //tipo de uma variavel
        //cria uma nova isntancia
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put("nome", intent.getStringExtra("campoNome"));
        content.put("endereco", intent.getStringExtra("campoEndereco"));
        content.put("telefone", intent.getStringExtra("campoTel"));
        content.put("email", intent.getStringExtra("campoEmail"));
        content.put("senha", intent.getStringExtra("campoSenha"));

        db.insert("usuario", null, content);

    }


}
