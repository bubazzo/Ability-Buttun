package com.example.luca.abilitybuttun;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Luca on 20/11/2017.
 */

public class RegoleActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final long time=2000;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regole);
        TextView testo=(TextView) findViewById(R.id.testoRegole);
        Intent i=getIntent();
        final Integer lv=i.getIntExtra("livello", -1);
        Integer lvSucc=lv+1;
        /*
        if(lvSucc==1){
            testo.setText("Premi i numeri in ordine crescente\n\nTempo 5s");
        }
        if(lvSucc==5){
            testo.setText("Don't click on the red button");
        }
        if(lvSucc==10){
            testo.setText("Clicca 2 volte il bottone verde");
        }
        if(lvSucc==15) {
            testo.setText("Tempo 3s");
        }
        if(lvSucc==20){
            testo.setText("Scambio posizione");
        }
        if(lvSucc==25){
            testo.setText("Scambio posizione + rosso o verde");
        }
        */
        switch(lvSucc){
            case 1:
                testo.setText("Premi i numeri in ordine crescente\n\nTempo 5s");
                break;
            case 5:
                testo.setText("Don't click on the red button");
                break;
            case 10:
                testo.setText("Clicca 2 volte il bottone verde");
                break;
            case 15:
                testo.setText("Tempo 3s");
                break;
            case 20:
                testo.setText("Scambio posizione");
                break;
            case 25:
                testo.setText("Scambio posizione + rosso");
                break;
            default:
        }

        final CountDownTimer timer=new CountDownTimer(time, 1000) {

            public void onTick(long tScorr) {
            }

            public void onFinish() {/*ho perso per fine tempo*/
                Intent iLv=new Intent(RegoleActivity.this, firstActivity.class);
                iLv.putExtra("livello", lv);
                startActivity(iLv);
            }
        }.start();
    }
}
