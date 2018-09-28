package com.example.luca.abilitybuttun;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v4.content.res.ConfigurationHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
/*per il bottone indietro*/
import android.app.AlertDialog;
import android.content.DialogInterface;

/**
 * Created by Luca on 18/11/2017.
 */



public class VictoryActivity extends AppCompatActivity {
    Integer lvStamp=0, flagPause=0, tempoPause=20000;
    int lvMax=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_victory);
        TextView testo=(TextView)findViewById(R.id.victory);
        Intent i=getIntent();
        int ris=i.getIntExtra("esito", -1);
        Button restart=(Button) findViewById(R.id.restart);
        TextView testoLv=(TextView) findViewById(R.id.tlvR);
        TextView testoMotivo=(TextView) findViewById(R.id.motivazione);
        String preference_name = "Pref1";

        SharedPreferences prefs = getSharedPreferences(preference_name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        int lvR=i.getIntExtra("livello", -1);

        if(ris==0){
            testo.setText("YOU LOSE");
            testo.setTextColor(Color.RED);

        }
        if(ris==1){
            testo.setText("YOU WIN");
            testoLv.setVisibility(View.INVISIBLE);
        }
        else{
            testo.setText("YOU LOSE");
            testo.setTextColor(Color.RED);
            lvStamp=lvR;
            testoLv.setText("lv: "+ lvStamp.toString());
            if(ris==0){
                testoMotivo.setText("Time out");
            }
            else{
                testoMotivo.setText("Pressed wrong button");
            }
        }

        restart.setBackgroundResource(R.mipmap.free_button);
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(VictoryActivity.this, RegoleActivity.class);
                i.putExtra("livello", 0);
                startActivity(i);
            }
        });

        /*gestione livello massimo*/
        lvMax= prefs.getInt("livello", 0);
        if(lvR>lvMax){
            lvMax=lvR;
            testo.setText("NEW RECORD");
            testo.setTextColor(Color.GREEN);
        }

        editor.putInt("livello", lvMax);
        editor.putInt("isPause", 0);
        editor.commit();
    }

    /*per gestire il bottone indietro*/
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Return to Main Menù?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user pressed "yes", then he is allowed to exit from application
                Intent i=new Intent(VictoryActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user select "No", just cancel this dialog and continue with app
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
    /*fine sezione bottone indietro*/
    @Override
    public void  onPause(){
        super.onPause();
        flagPause=1;
        new CountDownTimer(tempoPause, 1000){
            public void onFinish(){
                if(flagPause==1){
                    finish();
                }
            }
            public void onTick(long t){
            }
        }.start();
    }
    @Override
    public void onResume(){
        super.onResume();
        flagPause=0;
    }
}
