package com.example.luca.abilitybuttun;

import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
/*per il bottone indietro*/
import android.app.AlertDialog;
import android.content.DialogInterface;

import java.util.Random;
import java.util.*;


import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
/**
 * Created by Luca on 18/11/2017.
 */

public class firstActivity extends AppCompatActivity {
    private int count=0, nSwitch=-1, lv=0, fiVerde=0, lvOff=0, nt=9, max=0,
            nRosso=-1, nVerde=-1, flagVerde=-1, maxNb=6, flagPause=0, tempoPause=20000;
    private int lvmax=64;  /*1 in meno del livello segnato => i livello internamente partono da 0
        ma vengono visualizzati a partire da 1*/
    private ButtonApp[] buttons = new ButtonApp[9];

    Intent ilv;

    settaggio ps=new settaggio(buttons){};
    private HashMap<String,Integer> colors=new HashMap<>();
    private InterstitialAd mInterstitial;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_activity);


        mInterstitial = new InterstitialAd(this);
        //test ca-app-pub-3940256099942544/1033173712
        //nostro ca-app-pub-6641542231203215/9862852588
        mInterstitial.setAdUnitId("ca-app-pub-6641542231203215/9862852588");
        AdRequest request = new AdRequest.Builder().build();
        mInterstitial.loadAd(request);
        mInterstitial.setAdListener(new AdListener()
                                    {
                                        @Override
                                        public void onAdClosed(){
                                            ilv.putExtra("livello", lv + 1);
                                            startActivity(ilv);
                                        }
                                    }
        );

        count =0;
        int i=0, z=0;
        long tInt=10, tBase=3000;
        Integer lvStamp=0;
        final int blue = Color.parseColor("#3F51B5");
        final int red= Color.parseColor("#ae0c00");
        final int green=Color.parseColor("#00ff00");
        final int azzurro=Color.parseColor("#00FFFF");
        colors.put("blue",blue);
        colors.put("red",red);
        colors.put("green",green);
        colors.put("azzurro", azzurro);




        Random random=new Random();

        Intent intento=getIntent();
        lv=intento.getIntExtra("livello", -1); /*parte da 0*/
        /*per avere meno di 9 bottoni*/
        /*lvOff=lv+3;
        if(lvOff>maxNb){
            lvOff=maxNb;
        }*/

        lvOff=maxNb;

        //utilizzo del vettore buttons
        buttons[0]=new ButtonApp(colors,(Button) findViewById(R.id.b1));
        buttons[1]=new ButtonApp(colors,(Button) findViewById(R.id.b2));
        buttons[2]=new ButtonApp(colors,(Button) findViewById(R.id.b3));
        buttons[3]=new ButtonApp(colors,(Button) findViewById(R.id.b4));
        buttons[4]=new ButtonApp(colors,(Button) findViewById(R.id.b5));
        buttons[5]=new ButtonApp(colors,(Button) findViewById(R.id.b6));
        buttons[6]=new ButtonApp(colors,(Button) findViewById(R.id.b7));
        buttons[7]=new ButtonApp(colors,(Button) findViewById(R.id.b8));
        buttons[8]=new ButtonApp(colors,(Button) findViewById(R.id.b9));


        TextView tlv= (TextView) findViewById(R.id.tlv);
        final TextView tTimer=(TextView) findViewById(R.id.tTimer);
        /*intent per fine gioco=>vittoria o sconfitta*/
        final Intent iv=new Intent(firstActivity.this, VictoryActivity.class);


        if(lv>=24){/*setto il tempo*/
            tBase=2500;
        }
        if(lv>=44){
            tBase=2000;
        }
        /*creazione timer*/
        final CountDownTimer timer=new CountDownTimer(tBase, tInt) {

            public void onTick(long tScorr) {
                tTimer.setText(""+tScorr / 1000+"."+tScorr/10%100);/*scrivo il tempo in seconti.decimi di secondo*/
            }

            public void onFinish() {/*ho perso per fine tempo*/
                if(flagPause==0){
                    tTimer.setText("0");//faccio scrivere 0 prima di chiudere il timer
                    iv.putExtra("esito", 0);
                    iv.putExtra("livello", lv+1);
                    startActivity(iv);
                }
                else{
                    finish();
                }
            }
        };


        lvStamp=lv+1;
        tlv.setText("lv: "+ lvStamp.toString());/*stampo il vello*/
        // setto gli id di buttons
        ps.setIdButtons(nt);


        /*scrivo i numeri casuali nei bottoni e il numero di bottoni*/
        /*
        non c'è di bisogno con la nuova classe perchè ogni volta che setto l'id cambio anche il
        testo, aggiungo anche che quando faccio l'estrazione in settaggio vado a mettere il colore
        a blue;
         */
        for(i=0; i<nt; i++){
            //buttons[i].setColor("blue");
            buttons[i].setBackImage();
            buttons[i].setColorText("azzurro");
                if(buttons[i].getId()>lvOff){
                    buttons[i].setVisible(false);
                }
        }
        /*setto il valore max raggiungibile*/

        max=lvOff;

        /*difficoltà 5 livello*/
        if(lv>=4 && lv<14){/*perchè i livelli partono segnati da 0*/
            nRosso=ps.setnRosso(max);
        }
        if(lv>=19 && lv<34){
            nRosso=ps.setnRosso(max);
        }
        if(lv>=39 && lv<54){
            nRosso=ps.setnRosso(max);
        }
        if(lv>=59){
            nRosso=ps.setnRosso(max);
        }
        /*difficoltà livello 10, verde*/
        if(lv>=9 && lv<14){
            flagVerde=random.nextInt(100)%2;
        }
        if(lv>=29 && lv<34){
            flagVerde=random.nextInt(100)%2;
        }
        if(lv>=49 && lv<54){
            flagVerde=random.nextInt(100)%2;
        }

        if(flagVerde==1){
            nVerde=ps.setnVerde(max);
        }
        if(nVerde==1){
            for(z=0; z<nt; z++) {
                if (buttons[z].getId() == nVerde) {
                    buttons[z].setGreenButton();
                }
            }
        }
        if(lv>=14 && lv<24) {/*difficoltà livello 15, switch numeri*/
            nSwitch = ps.setnSwitch(max);
            while (nSwitch == nRosso || nSwitch == nRosso - 1) {
                nSwitch = ps.setnSwitch(max);
            }
        }
        if(lv>=34 && lv<44){
            nSwitch = ps.setnSwitch(max);
            while (nSwitch == nRosso || nSwitch == nRosso - 1) {
                nSwitch = ps.setnSwitch(max);
            }
        }
        if(lv>=54){
            nSwitch = ps.setnSwitch(max);
            while (nSwitch == nRosso || nSwitch == nRosso - 1) {
                nSwitch = ps.setnSwitch(max);
            }
        }

        ilv=new Intent(firstActivity.this, InterLvActivity.class);

        for(i=0; i<nt; i++){
            final int k=i;
          //  abut[i].setOnClickListener(new View.OnClickListener(){
           buttons[i].getButton().setOnClickListener(new View.OnClickListener(){
                public void onClick(View v) {
                    int ciclo, button1=-1, button2=-1;
                    int j=0;
                    buttons[k].setVisible(false);
                    /*se ho premuto giusto*/
                    if(count==buttons[k].getId()-1){
                        count++;
                        //da continuare perchè vogliamo fare che id e indice non corrispondono
                        //seleziono i bottoni che mi servono, successivo ecc.
                        for(ciclo = 0; ciclo < nt; ciclo++){

                            if(buttons[ciclo].getId()==buttons[k].getId()+1){
                                button1=ciclo;
                            }
                            if(buttons[ciclo].getId()==buttons[k].getId()+2){
                                button2=ciclo;
                            }
                        }
                        //scrivo così perchè i valori nei flag partono da 1
                        if(button1!=-1&&buttons[button1].getId()==nRosso){ /*controllo rosso*/
                            count++;
                            //parte per vedere qual è il prossimo bottone e quello dopo due
                            if(nVerde==buttons[k].getId()){/*se il rosso è subito dopo il verde*/
                                if(fiVerde==0){
                                    count--;
                                }
                            }
                            if(button2!=-1&&nVerde==buttons[button2].getId()){
                                for (j = 0; j < nt; j++) {
                                    if(buttons[j].getId()==nVerde){
                                        buttons[j].setGreenButton();
                                    }
                                }
                            }
                            for(j=0; j<nt; j++){
                                if(buttons[j].getId()==nRosso){
                                    buttons[j].setRedButton();
                                }
                            }

                        }


                        if(button1!=-1&&nVerde==buttons[button1].getId()){/*controllo verde*/
                            for (j = 0; j < nt; j++) {
                                if(buttons[j].getId()==nVerde){
                                    buttons[j].setGreenButton();
                                }
                            }
                        }
                        if(fiVerde==0) {
                            if(nVerde==buttons[k].getId()){
                                if (fiVerde == 0) {
                                    count--;
                                    buttons[k].getButton().setVisibility(View.VISIBLE);
                                }
                                fiVerde = 1;

                            }
                        }
                        else{/*pulisce il fiVerde*/
                            fiVerde=0;
                        }
                        /*controllo switch*/
                        if(buttons[k].getId()==nSwitch){
                            ps.setIdButtons(nt);/*risetto i bottoni*/
                            for(j=0; j<nt; j++){

                                if(buttons[j].getId()>lvOff || buttons[j].getId()<=count){/*risetto i bottoni alti invisibili*/
                                    buttons[j].setVisible(false);
                                }
                                else{
                                    buttons[j].setVisible(true);/*risetto i bottoni giusti visibili*/
                                    buttons[j].setBackImage();
                                    buttons[j].setColorText("azzurro");/*pulisco i bottoni eventualmente colorati di rosso*/
                                }

                            }
                        }


                        if(count==max || count==nt){/*se ho finito il livello*/
                            if(lv==lvmax){/*se era l'ultimo livello e ho vinto*/
                                timer.cancel();
                                iv.putExtra("esito", 1);
                                startActivity(iv);

                            }
                            else{/*se devo continuare con altri livelli*/
                                timer.cancel();
                                if(mInterstitial.isLoaded()){
                                    //test
                                    //endtest
                                    mInterstitial.show();
                                }else {
                                    ilv.putExtra("livello", lv + 1);
                                    startActivity(ilv);
                                }
                            }

                        }
                    }
                    /*se ho premuto sbagliato*/
                    else{
                        timer.cancel();
                        iv.putExtra("esito", 2);
                        iv.putExtra("livello", lv+1);
                        startActivity(iv);
                    }
                }
            });

        }
        timer.start();

    }

    /*per gestire il bottone indietro*/
    @Override
    public void onBackPressed() {
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

