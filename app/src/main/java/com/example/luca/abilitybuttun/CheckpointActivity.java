package com.example.luca.abilitybuttun;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
/*per il bottone indietro*/
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.TextView;

/**
 * Created by Luca on 25/11/2017.
 */

public class CheckpointActivity extends AppCompatActivity {
    Button arrBut[];
    int i=0, ncp=6, flagPause=0, tempoPause=20000;
    Integer lvRaggiunto=0;
    Intent intento;
    int blue = Color.parseColor("#3F51B5");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkpoint);
        arrBut=new Button[6];/*alloco i bottoni*/
        arrBut[0]=findViewById(R.id.cpButton1);
        arrBut[1]=findViewById(R.id.cpButton2);
        arrBut[2]=findViewById(R.id.cpButton3);
        arrBut[3]=findViewById(R.id.cpButton4);
        arrBut[4]=findViewById(R.id.cpButton5);
        arrBut[5]=findViewById(R.id.cpButton6);
        TextView testoLv=(TextView) findViewById(R.id.textLivello);

        String preference_name = "Pref1";

        SharedPreferences prefs = getSharedPreferences(preference_name, Context.MODE_PRIVATE);

        /*gestione livello*/
        lvRaggiunto=prefs.getInt("livello", 0);
        testoLv.setText("Record: lv"+lvRaggiunto.toString());
        /*fine gestione livello*/

        for(i=0; i<ncp; i++){
            arrBut[i].setBackgroundColor(blue);
            switch (i){
                case 0:
                    arrBut[i].setText("Lv1");
                    break;
                case 1:
                    arrBut[i].setText("Lv5");
                    break;
                case 2:
                    arrBut[i].setText("Lv10");
                    break;
                case 3:
                    arrBut[i].setText("Lv15");
                    break;
                case 4:
                    arrBut[i].setText("Lv20");
                    break;
                case 5:
                    arrBut[i].setText("Lv25");
                    break;
            }
        }

        for(i=0; i<ncp; i++){
            final int k=i;
            arrBut[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    intento=new Intent(CheckpointActivity.this, RegoleActivity.class);
                    if(k==0){
                        intento.putExtra("livello", 0);
                    }
                    if(k==1){
                        intento.putExtra("livello", 4);
                    }
                    if(k==2){
                        intento.putExtra("livello", 9);
                    }
                    if(k==3){
                        intento.putExtra("livello", 14);
                    }
                    if(k==4){
                        intento.putExtra("livello", 19);
                    }
                    if(k==5){
                        intento.putExtra("livello", 24);
                    }

                    startActivity(intento);
                }
            });
        }

    }

    /*per gestire il bottone indietro*/
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Tornare al menù principale?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user pressed "yes", then he is allowed to exit from application
                Intent i=new Intent(CheckpointActivity.this, MainActivity.class);
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
