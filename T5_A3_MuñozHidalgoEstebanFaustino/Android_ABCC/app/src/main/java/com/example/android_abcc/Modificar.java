package com.example.android_abcc;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android_abcc.controlador.AnalizadorJSON;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Modificar extends AppCompatActivity {
    EditText cajaNumControl, cajaNombre, cajaPrimerApellido, cajaSegundoApellido, cajaEdad, cajaSemestre, cajaCarrera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);

        cajaNumControl = (EditText) findViewById(R.id.cajaNumControl);
        cajaNombre = (EditText) findViewById(R.id.cajaNombre);
        cajaPrimerApellido = (EditText) findViewById(R.id.cajaPrimerApellido);
        cajaSegundoApellido = (EditText) findViewById(R.id.cajaSegundoApellido);
        cajaEdad = (EditText) findViewById(R.id.cajaEdad);
        cajaSemestre = (EditText) findViewById(R.id.cajaSemestre);
        cajaCarrera = (EditText) findViewById(R.id.cajaCarrera);
    }

    public void modificarRegistro(View v) {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();

        if (ni != null && ni.isConnected()) {
            String nc = cajaNumControl.getText().toString();
            String n = cajaNombre.getText().toString();
            String pa = cajaPrimerApellido.getText().toString();
            String sa = cajaSegundoApellido.getText().toString();
            String e = cajaEdad.getText().toString();
            String s = cajaSemestre.getText().toString();
            String c = cajaCarrera.getText().toString();

            //usar una clase interna para que realice la inserción
            new ModificarAlumnos().execute(nc, n, pa, sa, e, s, c);
            Toast.makeText(this, "MODIFICADO", Toast.LENGTH_SHORT).show();
        }
    }

    class ModificarAlumnos extends AsyncTask<String, String, String> {
        @Override                       //VARARGS
        protected String doInBackground(String... datos) {
            AnalizadorJSON aJSON = new AnalizadorJSON();
            Map<String, String> registros = new HashMap<String, String>();

            registros.put("nc", datos[0]);
            registros.put("n", datos[1]);
            registros.put("pa", datos[2]);
            registros.put("sa", datos[3]);
            registros.put("e", datos[4]);
            registros.put("s", datos[5]);
            registros.put("c", datos[6]);

            String url = "http://10.0.2.2/Users/Faustino/AndroidStudioProjects/HTTP_Android/cambios_alumnos.php";
            String metodoEnvio = "POST";

            JSONObject jsonObject = aJSON.peticionHTTP(url, metodoEnvio, registros);

            Log.i("MSJ JSON", jsonObject.toString());

            //enviar a GUI el mensaje
            //PantallaInicio pi = new PantallaInicio();
            //pi.mostrarErrores(jsonObject);

            return null;
        }
    }
}
