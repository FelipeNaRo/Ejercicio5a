package com.example.dam2.ejercicio5a;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    Button botonAA,botonAP,botonBA,botonBP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        botonAA=(Button)findViewById(R.id.botonAA);
        botonAP=(Button)findViewById(R.id.botonAP);
        botonBA=(Button)findViewById(R.id.botonBA);
        botonBP=(Button)findViewById(R.id.botonBP);

        botonAA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recogerDatos(0);
            }
        });

        botonAP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recogerDatos(1);
            }
        });
    }

    public void recogerDatos(int op){

        Intent i= new Intent(getApplicationContext(),Main2Activity.class);
        Bundle b= new Bundle();
        b.putInt("opcion",op);
        i.putExtras(b);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
