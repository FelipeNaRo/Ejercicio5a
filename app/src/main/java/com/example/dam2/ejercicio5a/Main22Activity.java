package com.example.dam2.ejercicio5a;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Main22Activity extends Activity {


    private Button borrar,volver;
    private RadioGroup optativo;
    private EditText elegir;
    private TextView titulo;
    int numero;
    private miDBAdaptador m;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main22);
        Bundle b=getIntent().getExtras();
        final int op=b.getInt("opcion");
        m=new miDBAdaptador(this);
        m.open();
        titulo=(TextView)findViewById(R.id.titulo);
        switch (op){
            case 0:titulo.setText("BORRADO DE ALUMNO");
                break;
            case 1:titulo.setText("BORRADO DE PROFESOR");
        }
        borrar=(Button)findViewById(R.id.borrar);
        volver=(Button)findViewById(R.id.volver);
        optativo=(RadioGroup)findViewById(R.id.optativo);
        elegir=(EditText)findViewById(R.id.numero);
        elegir.setVisibility(View.INVISIBLE);
        optativo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.uno:
                        elegir.setVisibility(View.VISIBLE);
                        break;
                    case R.id.todos:
                        elegir.setVisibility(View.INVISIBLE);
                        elegir.setText("0");
                        break;
                }
            }
        });

        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Principal();
            }
        });
        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numero= Integer.parseInt(elegir.getText().toString());
                switch (op){
                    case 0: if (numero!=0){m.borrarElemento(0, numero);
                    }else{
                        m.borrarElemento(0,0);
                    }
                        break;
                    case 1: if (numero!=0){m.borrarElemento(1, numero);
                    }else{
                        m.borrarElemento(1,0);
                    }
                        break;
                }
                Principal();
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
        getMenuInflater().inflate(R.menu.menu_main22, menu);
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
