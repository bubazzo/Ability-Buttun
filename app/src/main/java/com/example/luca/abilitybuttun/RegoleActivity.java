package com.example.luca.abilitybuttun;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
/*per il bottone indietro*/
import android.app.AlertDialog;
import android.content.DialogInterface;

/**
 * Created by Luca on 20/11/2017.
 */

public class RegoleActivity extends AppCompatActivity {
    Integer lv=0, lvSucc=0;

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
                testoRegole.setText("Premi i numeri in ordine crescente\n\nTempo 3s");
                break;
            case 5:
                testoRegole.setText("Don't click the red button");
                break;
            case 10:
                testoRegole.setText("Clicca 2 volte il bottone verde");
                break;
            case 15:
                testoRegole.setText("Scambio posizione");
                break;
            case 20:
                testoRegole.setText("Scambio posizione + rosso");
                break;
            case 25:
                testoRegole.setText("Tempo 2,5 secondi\nNon premere il rosso");
                break;
            case 30:
                testoRegole.setText("Clicca 2 volte il bottone verde");
                break;
            case 35:
                testoRegole.setText("Scambio posizione");
                break;
            case 40:
                testoRegole.setText("Scambio posizione + rosso");
                break;
            case 45:
                testoRegole.setText("Tempo 2 secondi\nNon premere il rosso");
                break;
            case 50:
                testoRegole.setText("Clicca 2 volte il bottone verde");
                break;
            case 55:
                testoRegole.setText("Scambio posizione");
                break;
            case 60:
                testoRegole.setText("Scambio posizione + rosso");
                break;
            default:
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
        finish();
    }

    public void onUserInteraction(){
        super.onUserInteraction();
        Intent iLv=new Intent(RegoleActivity.this, firstActivity.class);
        iLv.putExtra("livello", lv);
        startActivity(iLv);
    }
}
