package com.example.luca.abilitybuttun;

import java.util.Random;

/**
 * Created by Luca on 30/11/2017.
 */

public abstract class settaggio{

    private Integer npresi[]=new Integer[9];
    private int i=0, nVerde=-1, nRosso=-1, nSwitch=-1;
    private ButtonApp[] buttons;

    Random random=new Random();

    public settaggio(ButtonApp[] buttons){
        this.buttons=buttons;
    }

    public Integer[] setNumBut(int nt, Integer nm[]){
        for(i=0; i<nt; i++){/*setto i flag dei numeri presi a 0*/
           npresi[i]=0;
        }
        /*assegno i numeri casuali(nm)*/
        i=0;
        while(i<nt) {
            nm[i] = (random.nextInt(100) % 9) + 1;
            if (npresi[nm[i] - 1] == 0) {
                npresi[nm[i] - 1] = 1;
                i++;
            }
        }
    return nm;
    }

    public int setnVerde(int max){
        nVerde=random.nextInt(100)%max;
        while(nVerde==nRosso){
            nVerde=random.nextInt(100)%(max+1);
        }
        return nVerde;
    }

    public int setnRosso(int max){
        while(nRosso<2 || nRosso>5){
            nRosso=random.nextInt(100)%max;
        }
        return nRosso;
    }
    public int setnSwitch(int max){
        nSwitch=random.nextInt(100)%(max-1);
        return nSwitch;
    }

    /*
        setto gli id di buttons tenendo il vettore dei flag corrispondente ai numeri
     */
    public void setIdButtons(int nt) {
        for(i=0; i<nt; i++){/*setto i flag dei numeri presi a 0*/
            npresi[i]=0;
        }
        /*assegno i numeri casuali(nm)*/
        i=0;
        while(i<nt){
            buttons[i].setId((random.nextInt(100)%9)+1);
            if(npresi[buttons[i].getId()-1]==0) {
                npresi[buttons[i].getId() - 1] = 1;
                i++;
            }
        }
    }
}

