package com.example.android_abcc;

import android.content.Context;
import android.os.AsyncTask;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android_abcc.controlador.AnalizadorJSON;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Consultar extends AppCompatActivity {

    EditText cajaNombreAlumno;
    EditText cajaNumControl;
    EditText cajaPrimerAp;
    ListView listViewAlumnos;
    ArrayList<ArrayList> listaDatos2 = new ArrayList<ArrayList>();
    ArrayList<String> listaDatos = new ArrayList<String>();
    ArrayAdapter<String> adapter;

    String datos = "";
    int textlength = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar);

        cajaNombreAlumno = findViewById(R.id.cajaNombreAlumno);
        cajaNumControl = findViewById(R.id.cajaNC);
        cajaPrimerAp = findViewById(R.id.cajaPrimerAp);

        listViewAlumnos = findViewById(R.id.listaAlumnosConsultas);

        new ConsultarAlumnos().execute();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaDatos);
        listViewAlumnos.setAdapter(adapter);

        cajaNumControl.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                textlength = cajaNombreAlumno.getText().length();
                listaDatos.clear();

                for (int i = 0; i < listaDatos2.size(); i++) {
                    if (textlength <= listaDatos2.get(i).get(0).toString().length()) {
                        if (cajaNombreAlumno.getText().toString().equalsIgnoreCase(
                                listaDatos2.get(i).get(0).toString().subSequence(0, textlength).toString() )) {
                            datos =  listaDatos2.get(i).get(0).toString() + " - " +
                                    listaDatos2.get(i).get(1).toString() + " - " +
                                    listaDatos2.get(i).get(2).toString() + " - " +
                                    listaDatos2.get(i).get(3).toString() + " - " +
                                    listaDatos2.get(i).get(4).toString() + " - " +
                                    listaDatos2.get(i).get(5).toString() + " - " +
                                    listaDatos2.get(i).get(6).toString();
                            listaDatos.add(datos);
                        }
                    }
                }
                adapter = new ArrayAdapter<String>(Consultar.this, android.R.layout.simple_list_item_1, listaDatos);
                listViewAlumnos.setAdapter(adapter);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        cajaNombreAlumno.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                textlength = cajaNombreAlumno.getText().length();
                listaDatos.clear();

                for (int i = 0; i < listaDatos2.size(); i++) {
                    if (textlength <= listaDatos2.get(i).get(1).toString().length()) {
                        if (cajaNombreAlumno.getText().toString().equalsIgnoreCase(
                                listaDatos2.get(i).get(1).toString().subSequence(0, textlength).toString() )) {
                            datos =  listaDatos2.get(i).get(0).toString() + " - " +
                                            listaDatos2.get(i).get(1).toString() + " - " +
                                            listaDatos2.get(i).get(2).toString() + " - " +
                                            listaDatos2.get(i).get(3).toString() + " - " +
                                            listaDatos2.get(i).get(4).toString() + " - " +
                                            listaDatos2.get(i).get(5).toString() + " - " +
                                            listaDatos2.get(i).get(6).toString();
                            listaDatos.add(datos);
                        }
                    }
                }
                adapter = new ArrayAdapter<String>(Consultar.this, android.R.layout.simple_list_item_1, listaDatos);
                listViewAlumnos.setAdapter(adapter);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        cajaPrimerAp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                textlength = cajaNombreAlumno.getText().length();
                listaDatos.clear();

                for (int i = 0; i < listaDatos2.size(); i++) {
                    if (textlength <= listaDatos2.get(i).get(2).toString().length()) {
                        if (cajaNombreAlumno.getText().toString().equalsIgnoreCase(
                                listaDatos2.get(i).get(2).toString().subSequence(0, textlength).toString() )) {
                            datos =  listaDatos2.get(i).get(0).toString() + " - " +
                                    listaDatos2.get(i).get(1).toString() + " - " +
                                    listaDatos2.get(i).get(2).toString() + " - " +
                                    listaDatos2.get(i).get(3).toString() + " - " +
                                    listaDatos2.get(i).get(4).toString() + " - " +
                                    listaDatos2.get(i).get(5).toString() + " - " +
                                    listaDatos2.get(i).get(6).toString();
                            listaDatos.add(datos);
                        }
                    }
                }
                adapter = new ArrayAdapter<String>(Consultar.this, android.R.layout.simple_list_item_1, listaDatos);
                listViewAlumnos.setAdapter(adapter);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    class ConsultarAlumnos extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... strings) {
            AnalizadorJSON analizadorJSON = new AnalizadorJSON();

            String url = "http://10.0.2.2/Users/Faustino/AndroidStudioProjects/HTTP_Android/consultas_alumnos.php";
            JSONObject jsonObject = analizadorJSON.peticionHTTP(url, "POST");
            try {
                JSONArray jsonArray = jsonObject.getJSONArray("alumnos");

                for (int i=0; i<jsonArray.length(); i++) {
                    ArrayList<String> listaDatosAlumno = new ArrayList<>();

                    listaDatosAlumno.add(jsonArray.getJSONObject(i).getString("nc"));
                    listaDatosAlumno.add(jsonArray.getJSONObject(i).getString("n"));
                    listaDatosAlumno.add(jsonArray.getJSONObject(i).getString("pa"));
                    listaDatosAlumno.add(jsonArray.getJSONObject(i).getString("sa"));
                    listaDatosAlumno.add(jsonArray.getJSONObject(i).getString("e"));
                    listaDatosAlumno.add(jsonArray.getJSONObject(i).getString("s"));
                    listaDatosAlumno.add(jsonArray.getJSONObject(i).getString("c"));

                    listaDatos2.add(listaDatosAlumno);

                    datos = jsonArray.getJSONObject(i).getString("nc") + " - " +
                            jsonArray.getJSONObject(i).getString("n") + " - " +
                            jsonArray.getJSONObject(i).getString("pa") + " - " +
                            jsonArray.getJSONObject(i).getString("sa") + " - " +
                            jsonArray.getJSONObject(i).getString("e") + " - " +
                            jsonArray.getJSONObject(i).getString("s") + " - " +
                            jsonArray.getJSONObject(i).getString("c");

                    listaDatos.add(datos);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }
    }
}
