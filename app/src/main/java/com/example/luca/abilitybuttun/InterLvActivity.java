package com.example.luca.abilitybuttun;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
/*per il bottone indietro*/
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

/**
 * Created by Luca on 19/11/2017.
 */

public class InterLvActivity extends AppCompatActivity {
    Integer lv=0, lvSucc=0, flagPause=0, tempoPause=20000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_interlv);
        Intent i=getIntent();
        lv=i.getIntExtra("livello", -1);
        lvSucc=lv+1;
        TextView testoBase=(TextView) findViewById(R.id.testoBase);
        testoBase.setText("lv"+lv.toString()+" completed\nTap to start lv"+lvSucc.toString());

        //parte per gestire il tocco delle schermo
        ViewGroup viewGroup = (ViewGroup) ((ViewGroup) this
                .findViewById(android.R.id.content)).getChildAt(0);
        viewGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if (lvSucc == 5 || lvSucc == 10 || lvSucc == 15 || lvSucc == 20 || lvSucc == 25 || lvSucc == 30
                            || lvSucc == 35 || lvSucc == 40 || lvSucc == 45 || lvSucc == 50 || lvSucc == 55
                            || lvSucc == 60) {
                        Intent iR = new Intent(InterLvActivity.this, RegoleActivity.class);
                        iR.putExtra("livello", lv);
                        startActivity(iR);
                    } else {
                        Intent iLv = new Intent(InterLvActivity.this, firstActivity.class);
                        iLv.putExtra("livello", lv);
                        startActivity(iLv);
                    }


            }

        });//fine gestione tocco schermo



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
                Intent i=new Intent(InterLvActivity.this, MainActivity.class);
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

    @Override
    public void onStop(){
        String preference_name = "Pref1";

        SharedPreferences prefs = getSharedPreferences(preference_name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putInt("lvPause", lv);
        editor.putInt("isPause", 1);
        editor.commit();
        super.onStop();
    }

}
