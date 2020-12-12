package com.example.android_abcc;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android_abcc.controlador.AnalizadorJSON;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    Button btnIniciarSesion;
    EditText cajaUser;
    EditText cajaPassword;
    Intent i;

    String usuario[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        cajaUser = findViewById(R.id.cajaUser);
        cajaPassword = findViewById(R.id.cajaPassword);

        btnIniciarSesion = findViewById(R.id.btnIniciarSesion);

        btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ConsultarUsuario().execute(cajaUser.getText().toString(), cajaPassword.getText().toString());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (cajaUser.getText().toString().equals(usuario[0]) && cajaPassword.getText().toString().equals(usuario[1])) {
                    i = new Intent(Login.this, MainActivity.class);
                    startActivity(i);
                } else {
                    Toast.makeText(getApplicationContext(), "Usuario o contraseña incorrecto", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    class ConsultarUsuario extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... strings) {
            AnalizadorJSON analizadorJSON = new AnalizadorJSON();
            Map<String, String> registros = new HashMap<String, String>();

            registros.put("user", strings[0]);
            registros.put("password", strings[1]);

            String url = "http://10.0.2.2/Users/Faustino/AndroidStudioProjects/HTTP_Android/consulta_usuario.php";
            JSONObject jsonObject = analizadorJSON.peticionUsuairoHTTP(url, "POST", registros);
            try {
                JSONArray jsonArray = jsonObject.getJSONArray("usuario");
                usuario[0] = jsonArray.getJSONObject(0).getString("user");
                usuario[1] = jsonArray.getJSONObject(1).getString("password");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }
    }
}
