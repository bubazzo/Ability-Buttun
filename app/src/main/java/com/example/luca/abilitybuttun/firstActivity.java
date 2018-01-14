package com.example.luca.abilitybuttun;

import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;
import java.util.*;
/**
 * Created by Luca on 18/11/2017.
 */

public class firstActivity extends AppCompatActivity {
    int count=0, nSwitch=-1, lv=0, fiVerde=0, pasVerde=0, lvOff=0;
    Integer nm[]=new Integer[9];
    private ButtonApp[] buttons = new ButtonApp[9];

    settaggio ps=new settaggio(buttons){};
    private HashMap<String,Integer> colors=new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_activity);
        count =0;
        int i=0, lv=0, maxNb=6, nRosso=-1, nVerde=-1, flagVerde=-1, z=0;
        long tInt=10, tRet=0, tBase=5000;
        Integer lvStamp=0;
        final int nt=9;
        final int lvmax=99; /*1 in meno del livello segnato => i livello internamente partono da 0
        ma vengono visualizzati a partire da 1*/
        final int blue = Color.parseColor("#3F51B5");
        final int red= Color.parseColor("#ae0c00");
        final int green=Color.parseColor("#00ff00");
        colors.put("blue",blue);
        colors.put("red",red);
        colors.put("green",green);




        Random random=new Random();

        Intent intento=getIntent();
        lv=intento.getIntExtra("livello", -1); /*parte da 0*/
        final int mlv=lv;/*variabile final per mettere dentro a funzioni*/
        /*per avere meno di 9 bottoni*/
        lvOff=lv+3;
        if(lvOff>maxNb){
            lvOff=maxNb;
        }

        final Button abut[];
        abut=new Button[9];
        abut[0]=(Button) findViewById(R.id.b1);
        abut[1]=(Button) findViewById(R.id.b2);
        abut[2]=(Button) findViewById(R.id.b3);
        abut[3]=(Button) findViewById(R.id.b4);
        abut[4]=(Button) findViewById(R.id.b5);
        abut[5]=(Button) findViewById(R.id.b6);
        abut[6]=(Button) findViewById(R.id.b7);
        abut[7]=(Button) findViewById(R.id.b8);
        abut[8]=(Button) findViewById(R.id.b9);

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


        if(lv>=14){/*setto il tempo*/
            tBase=3000;
        }
        /*creazione timer*/
        final CountDownTimer timer=new CountDownTimer(tBase, tInt) {

            public void onTick(long tScorr) {
                tTimer.setText(""+tScorr / 1000+"."+tScorr/10%100);/*scrivo il tempo in seconti.decimi di secondo*/
            }

            public void onFinish() {/*ho perso per fine tempo*/
                iv.putExtra("esito", 0);
                iv.putExtra("livello", mlv+1);
                startActivity(iv);
            }
        };


        lvStamp=lv+1;
        tlv.setText("lv: "+ lvStamp.toString());/*stampo il vello*/

        /*inizializzo nm tutto a 0*/
        for(i=0; i<nt; i++){
            nm[i]=0;
        }
        nm=ps.setNumBut(nt, nm);/*setto i numeri casuali*/
        // setto gli id di buttons
        ps.setIdButtons(nt);


        /*scrivo i numeri casuali nei bottoni e il numero di bottoni*/
        /*
        non c'è di bisogno con la nuova classe perchè ogni volta che setto l'id cambio anche il
        testo, aggiungo anche che quando faccio l'estrazione in settaggio vado a mettere il colore
        a blue;
         */
        for(i=0; i<nt; i++){
            abut[i].setText(nm[i].toString());
            abut[i].setBackgroundColor(blue);
                if(nm[i]>lvOff){
                    abut[i].setVisibility(View.INVISIBLE);
                }
                if(buttons[i].getId()>lvOff){
                    buttons[i].setVisible(false);
                }
        }
        /*setto il valore max raggiungibile*/

        final int max=lvOff;

        /*difficoltà 5 livello*/
        if(lv>=4 && lv<19){/*perchè i livelli partono segnati da 0*/
            nRosso=ps.setnRosso(max);

        }
        if(lv>=24){
            nRosso=ps.setnRosso(max);
        }
        /*difficoltà livello 10, verde*/
        if(lv>=9 && lv<19){
            flagVerde=random.nextInt(100)%2;
        }

        if(flagVerde==1){
            nVerde=ps.setnVerde(max);
        }
        if(nVerde==1){
            for(z=0; z<nt; z++) {
                if (nm[z] == nVerde) {
                    abut[z].setBackgroundColor(green);
                }
                if (buttons[z].getId() == nVerde) {
                    buttons[z].setColor("green");
                }
            }
        }
        if(lv>=19){/*difficoltà livello 20, switch numeri*/
            nSwitch=ps.setnSwitch(max);
            while(nSwitch==nRosso || nSwitch==nRosso-1){
                nSwitch=ps.setnSwitch(max);
            }
        }

        /*faccio partire il timer*/

        final int flagRosso=nRosso;
        final int fflagVerde=nVerde;
        final long ftRet=tRet;

        final Intent ilv=new Intent(firstActivity.this, InterLvActivity.class);

        for(i=0; i<nt; i++){
            final int k=i;
            abut[i].setOnClickListener(new View.OnClickListener(){
           //buttons[i].getButton().setonClickListener(new View.OnClickListener()){
                public void onClick(View v) {
                    int ciclo, button1=-1, button2=-1;
                    int j=0;
                    abut[k].setVisibility(View.INVISIBLE);
                    buttons[k].setVisible(false);
                    /*se ho premuto giusto*/
                    if(count==nm[k]-1){
                //    if(count==buttons[k].getId()-1){
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
                        if(flagRosso==nm[k]+1){  /*controllo rosso*/
                       //if(button1!=-1&&buttons[button1].getId()==flagRosso){
                            count++;
                            //parte per vedere qual è il prossimo bottone e quello dopo due
                            if (fflagVerde == nm[k]){/*se il rosso è subito dopo il verde*/
                          //if(fflagVerde==buttons[k].getId()){
                                if(fiVerde==0){
                                    count--;
                                }
                            }
                            if(fflagVerde==nm[k]+2){
                          //if(button2!=-1&&fflagVerde==buttons[button2].getId())
                                for (j = 0; j < nt; j++) {
                                    if (nm[j] == fflagVerde) {
                                  //if(buttons[j].getId()==fflagVerde){
                                      //buttons[j].setColor("green");
                                        abut[j].setBackgroundColor(green);
                                    }
                                }
                            }
                            for(j=0; j<nt; j++){
                                if(nm[j]==flagRosso){
                              //if(buttons[j].getId()==flagRosso)
                                  //buttons[j].setColor("red");
                                    abut[j].setBackgroundColor(red);
                                }
                            }

                        }


                        if (fflagVerde == nm[k] + 1) {/*controllo verde*/
                      //if(button1!=-1&&fflagVerde==buttons[button1].getId()){
                            for (j = 0; j < nt; j++) {
                                if (nm[j] == fflagVerde) {
                              //if(buttons[j].getId()==fflagVerde){
                                  //buttons[j].setColor("green");
                                    abut[j].setBackgroundColor(green);
                                }
                            }
                        }
                        if(fiVerde==0) {
                            if (fflagVerde == nm[k]) {
                          //if(fflagVerde==buttons[k].getId()){
                                if (fiVerde == 0) {
                                    count--;
                                  //buttons[k].getButton().setVisibility(View.VISIBLE);
                                    abut[k].setVisibility(View.VISIBLE);
                                }
                                fiVerde = 1;

                            }
                        }
                        else{/*pulisce il fiVerde*/
                            fiVerde=0;
                        }
                        /*controllo switch*/
                        if(nm[k]==nSwitch){
                      //if(buttons[k].getId()==nSwitch){
                            nm=ps.setNumBut(nt, nm); /*risetto i bottoni*/
                            //ps.setIdButtons(nt);
                            for(j=0; j<nt; j++){
                                //non c'è di bisogno perchè quando setto l'id ristampo
                                abut[j].setText(nm[j].toString());/*faccio ristampare i bottoni*/

                                if(nm[j]>lvOff || nm[j]<=count){/*risetto i bottoni alti invisibili*/
                              //if(buttons[j].getId()>lvOff || buttons[j].getId()<=count){
                                  //buttons[j].setVisible(false);
                                    abut[j].setVisibility(View.INVISIBLE);
                                }
                                else{
                                    abut[j].setVisibility(View.VISIBLE);/*risetto i bottoni giusti visibili*/
                                  //buttons[j].setVisible(true);
                                    abut[j].setBackgroundColor(blue);/*pulisco i bottoni eventualmente colorati di rosso*/
                                  //buttons[j].setColor("blue");
                                }

                            }
                        }


                        if(count==max || count==nt){/*se ho finito il livello*/
                            if(mlv==lvmax){/*se era l'ultimo livello e ho vinto*/
                                timer.cancel();
                                iv.putExtra("esito", 1);
                                startActivity(iv);

                            }
                            else{/*se devo continuare con altri livelli*/
                                timer.cancel();
                                ilv.putExtra("livello", mlv+1);
                                startActivity(ilv);

                            }

                        }
                    }
                    /*se ho premuto sbagliato*/
                    else{
                        timer.cancel();
                        iv.putExtra("esito", 0);
                        iv.putExtra("livello", mlv+1);
                        startActivity(iv);
                    }
                }
            });

        }
        timer.start();

    }
}

