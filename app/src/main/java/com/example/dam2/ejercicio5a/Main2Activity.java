package com.example.dam2.ejercicio5a;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends Activity {

    Button grabar,volver;
    EditText nombre,edad,ciclo,curso,nota;
    TextView opcional;
    private miDBAdaptador m;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Bundle b=getIntent().getExtras();
        m=new miDBAdaptador(this);
        m.open();
        final int op=b.getInt("opcion");
        opcional=(TextView)findViewById(R.id.opcional);
        switch (op){
            case 0: opcional.setText("Nota media");
                break;
            case 1: opcional.setText("Despacho");
                break;
        }
        nombre=(EditText)findViewById(R.id.nombre);
        edad=(EditText)findViewById(R.id.edad);
        ciclo=(EditText)findViewById(R.id.ciclo);
        curso=(EditText)findViewById(R.id.curso);
        nota=(EditText)findViewById(R.id.nota);
        grabar=(Button)findViewById(R.id.grabar);
        volver=(Button)findViewById(R.id.volver);
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Principal();

            }
        });
        grabar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   try {
                       switch (op){
                           case 0:
                               m.insertarAlumno(nombre.getText().toString(), Integer.parseInt(edad.getText().toString()), ciclo.getText().toString(), Integer.parseInt(curso.getText().toString()), Integer.parseInt(nota.getText().toString()));
                               break;
                           case 1:
                               m.insertarProfesor(nombre.getText().toString(), Integer.parseInt(edad.getText().toString()), ciclo.getText().toString(), Integer.parseInt(curso.getText().toString()), nota.getText().toString());
                               break;}
                   }catch(NullPointerException e){
                       opcional.setText("Error");
                   }
                Principal();
                //Principal();
            }
        });
    }
    public void Principal(){
        Intent i=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
