package com.example.android_abcc;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android_abcc.controlador.AnalizadorJSON;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Eliminar extends AppCompatActivity {
    EditText cajaNumControl;
    ListView listaEliminar;
    ArrayList<String> listaDatos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar);

        cajaNumControl = (EditText) findViewById(R.id.cajaNumControlEliminar);
        listaEliminar = (ListView) findViewById(R.id.listaEliminar);

        new ConsultarAlumnos().execute();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaDatos);
        listaEliminar.setAdapter(adapter);
    }

    public void eliminarRegistro(View v) {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();

        if (ni != null && ni.isConnected()) {
            String nc = cajaNumControl.getText().toString();

            //usar una clase interna para que realice la inserción
            new EliminarAlumnos().execute(nc);
            Toast.makeText(this, "ELIMINADO", Toast.LENGTH_SHORT).show();
        }
    }

    class EliminarAlumnos extends AsyncTask<String, String, String> {
        @Override                       //VARARGS
        protected String doInBackground(String... datos) {
            Map<String, String> registros = new HashMap<>();

            registros.put("nc", datos[0]);
            registros.put("n", "");
            registros.put("pa", "");
            registros.put("sa", "");
            registros.put("e", "");
            registros.put("s", "");
            registros.put("c", "");

            AnalizadorJSON aJSON = new AnalizadorJSON();

            String url = "http://10.0.2.2/Users/Faustino/AndroidStudioProjects/HTTP_Android/bajas_alumnos.php";
            String metodoEnvio = "POST";

            //String url3 = "http://www.tecjerez.edu.mx/potros03/atas_alumnos.php";

            JSONObject jsonObject = aJSON.peticionHTTP(url, metodoEnvio, registros);

            Log.i("MSJ JSON", jsonObject.toString());

            //enviar a GUI el mensaje
            //PantallaInicio pi = new PantallaInicio();
            //pi.mostrarErrores(jsonObject);

            return null;
        }
    }

    class ConsultarAlumnos extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            AnalizadorJSON analizadorJSON = new AnalizadorJSON();

            String url = "http://10.0.2.2/Practicas/CRUD-MySQL-PHP/HTTP_Android/consultas_alumnos.php";
            String metodoEnvio = "POST";

            JSONObject jsonObject = analizadorJSON.peticionHTTP(url, metodoEnvio);
            try {
                JSONArray jsonArray = jsonObject.getJSONArray("alumnos");


                for (int i=0; i<jsonArray.length(); i++) {
                    String datos = jsonArray.getJSONObject(i).getString("nc") + " - " +
                            jsonArray.getJSONObject(i).getString("n") + " - " +
                            jsonArray.getJSONObject(i).getString("pa") + " - " +
                            jsonArray.getJSONObject(i).getString("sa") + " - " +
                            jsonArray.getJSONObject(i).getString("e") + " - " +
                            jsonArray.getJSONObject(i).getString("s") + " - " +
                            jsonArray.getJSONObject(i).getString("c") + " - ";

                    listaDatos.add(datos);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }
    }
}
