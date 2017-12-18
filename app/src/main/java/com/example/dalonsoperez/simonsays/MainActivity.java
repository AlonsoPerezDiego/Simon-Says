package com.example.dalonsoperez.simonsays;

import android.os.Bundle;
import android.os.HandlerThread;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import static com.example.dalonsoperez.simonsays.R.id.green;
import static com.example.dalonsoperez.simonsays.R.id.space;
import static com.example.dalonsoperez.simonsays.R.id.text;
import static com.example.dalonsoperez.simonsays.R.id.time;

public class MainActivity extends AppCompatActivity {

    Button botones[];
    TextView puntos;
    TextView nivel;
    Button inicio;
    ArrayList <Integer> acertados = new ArrayList<>();
    ArrayList<Integer> combinacion = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        nivel = (TextView)findViewById(R.id.lvl);
        puntos = (TextView)findViewById(R.id.score);
        botones = new Button[]{
                (Button)findViewById(R.id.pink),
                (Button)findViewById(R.id.brown),
                (Button)findViewById(R.id.green),
                (Button)findViewById(R.id.orange)
        };


        inicio = (Button)findViewById(R.id.newGame);

        botones[0].setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){comprobar(0);}
        });
        botones[1].setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){comprobar(1);}
        });
        botones[2].setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){comprobar(2);}
        });
        botones[3].setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){comprobar(3);}
        });
        inicio.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){comboColores();}
        });
    }

    public void comprobar(int color){
        final Toast derrota = Toast.makeText(getApplicationContext(), "DERROTA", Toast.LENGTH_SHORT);
        if(color==0){
            acertados.add(0);
        }else if(color==1){
            acertados.add(1);
        }else if(color==2){
            acertados.add(2);
        }else{
            acertados.add(3);
        }

        if(combinacion.size()==acertados.size()){
            for (int i = 0; i < acertados.size(); i++){
                if(!combinacion.get(i).equals(acertados.get(i))) {

                    derrota.show();
                    acertados.clear();
                    combinacion.clear();
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.action_settings) {
            return true;
        }
        //

        return super.onOptionsItemSelected(item);
    }

    public void comboColores() {
        android.os.Handler h1 = new android.os.Handler();
        final android.os.Handler h2 = new android.os.Handler();
        final int result = (int) (Math.random() * 4);
        int time = 1000;
        combinacion.add(result);
        for (int i = 0; i < combinacion.size(); i++) {
            final int fin = i;
            h1.postDelayed(new Runnable() {
                @Override
                public void run() {
                    botones[combinacion.get(fin)].setPressed(true);

                    h2.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            botones[combinacion.get(fin)].setPressed(false);
                        }
                    }, 500);
                }
            }, time * i + 500);
        }
        nivel.setText(String.valueOf(combinacion.size()));
        puntos.setText(String.valueOf(combinacion.size()));
    }

}
