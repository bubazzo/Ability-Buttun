package com.example.luca.abilitybuttun;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
/*per il bottone indietro*/
import android.app.AlertDialog;
import android.content.DialogInterface;

/**
 * Created by Luca on 20/11/2017.
 */

public class RegoleActivity extends AppCompatActivity {
    Integer lv=0, lvSucc=0, flagPause=0, tempoPause=20000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final long time=2000;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regole);
        Intent i=getIntent();
        lv=i.getIntExtra("livello", -1);
        lvSucc=lv+1;
        TextView testoRegole=(TextView) findViewById(R.id.testoRegole);

        switch(lvSucc){
            case 1:
                testoRegole.setText("Press the numbers in ascending order\n\nTime 3s");
                break;
            case 5:
                testoRegole.setText("Don't press the red button");
                break;
            case 10:
                testoRegole.setText("Press the green button twice");
                break;
            case 15:
                testoRegole.setText("Numbers change position");
                break;
            case 20:
                testoRegole.setText("Numbers change position + red button");
                break;
            case 25:
                testoRegole.setText("2.5 seconds\nDon't press the red button");
                break;
            case 30:
                testoRegole.setText("Press the green button twice");
                break;
            case 35:
                testoRegole.setText("Numbers change position");
                break;
            case 40:
                testoRegole.setText("Numbers change position + red button");
                break;
            case 45:
                testoRegole.setText("2.5 seconds\nDon't press the red button");
                break;
            case 50:
                testoRegole.setText("Press the green button twice");
                break;
            case 55:
                testoRegole.setText("Numbers change position");
                break;
            case 60:
                testoRegole.setText("Numbers change position + red button");
                break;
            default:
        }

        //parte per gestire il tocco delle schermo
        ViewGroup viewGroup = (ViewGroup) ((ViewGroup) this
                .findViewById(android.R.id.content)).getChildAt(0);
        viewGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iLv=new Intent(RegoleActivity.this, firstActivity.class);
                iLv.putExtra("livello", lv);
                startActivity(iLv);
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
                Intent i=new Intent(RegoleActivity.this, MainActivity.class);
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
