package com.example.a1514290069.contatos;

import android.content.DialogInterface;
import android.content.Intent;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        Button btn = (Button) findViewById(R.id.button2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Conteiner para transportar valores
                Intent it = new Intent(LoginActivity.this, MainActivity.class);
                startActivityForResult(it, 9999);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        String email = data.getExtras().getString("email");

        EditText txtEmailLogin = (EditText) findViewById(R.id.txtEmailLogin);
        txtEmailLogin.setText(email);
    }

}
