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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final long time=2000;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regole);
        Button button= (Button) findViewById(R.id.bRegole);
        Intent i=getIntent();
        final Integer lv=i.getIntExtra("livello", -1);
        Integer lvSucc=lv+1;

        switch(lvSucc){
            case 1:
                button.setText("Premi i numeri in ordine crescente\n\nTempo 3s");
                break;
            case 5:
                button.setText("Don't click the red button");
                break;
            case 10:
                button.setText("Clicca 2 volte il bottone verde");
                break;
            case 15:
                button.setText("Scambio posizione");
                break;
            case 20:
                button.setText("Scambio posizione + rosso");
                break;
            case 25:
                button.setText("Tempo 2,5 secondi\nNon premere il rosso");
                break;
            case 30:
                button.setText("Clicca 2 volte il bottone verde");
                break;
            case 35:
                button.setText("Scambio posizione");
                break;
            case 40:
                button.setText("Scambio posizione + rosso");
                break;
            default:
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iLv=new Intent(RegoleActivity.this, firstActivity.class);
                iLv.putExtra("livello", lv);
                startActivity(iLv);
            }
        });

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
        finish();
    }
}
