package com.example.luca.abilitybuttun;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Binder;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
/*per il bottone indietro*/
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Integer lvRaggiunto=0, flagPause=0, tempoPause=20000, controlloPause=0, lvPause=0;
    Intent i;
    Context cont;

    /*private boolean mIsBound = false;
    private MusicService mServ;
    private ServiceConnection Scon =new ServiceConnection(){

        public void onServiceConnected(ComponentName name, IBinder
                binder) {
            mServ = ((MusicService.ServiceBinder)binder).getService();
        }

        public void onServiceDisconnected(ComponentName name) {
            mServ = null;
        }
    };

    void doBindService(){
        bindService(new Intent(this,MusicService.class),
                Scon,Context.BIND_AUTO_CREATE);
        mIsBound = true;
    }

    void doUnbindService()
    {
        if(mIsBound)
        {
            unbindService(Scon);
            mIsBound = false;
        }
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cont=this;

        /*MusicService mServ = new MusicService();
        Intent music = new Intent();
        music.setClass(this,MusicService.class);
        startService(music);*/
        TextView testoLv=(TextView) findViewById(R.id.textLivello);

        String preference_name = "Pref1";
        SharedPreferences prefs = getSharedPreferences(preference_name, Context.MODE_PRIVATE);

        /*gestione livello*/
        lvRaggiunto=prefs.getInt("livello", 0);
        testoLv.setText("Record: lv"+lvRaggiunto.toString());
        /*fine gestione livello*/
        /*per riprendere partita*/
        controlloPause=prefs.getInt("isPause", 0);
        lvPause=prefs.getInt("lvPause", 0);
        /*fine per riprendere partita*/

        Button start=(Button) findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button start=(Button) findViewById(R.id.start);
                start.setBackgroundResource(R.mipmap.botton_start_pressed);
                //Intent i=new Intent(MainActivity.this, CheckpointActivity.class);
                i=new Intent(MainActivity.this, RegoleActivity.class);
                i.putExtra("livello", 0);
                if(controlloPause==1){
                    /*gestione alert*/
                    AlertDialog.Builder builder = new AlertDialog.Builder(cont);
                    builder.setCancelable(false);
                    builder.setMessage("Vuoi continuare la partita precendente?");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //if user pressed "yes", then he is allowed to exit from application
                            i=new Intent(MainActivity.this, InterLvActivity.class);
                            i.putExtra("livello", lvPause);
                            startActivity(i);
                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //if user select "No", just cancel this dialog and continue with app
                            dialog.cancel();
                            startActivity(i);
                        }
                    });
                    AlertDialog alert = builder.create();
                    alert.show();
            /*fine gestione alert*/
                }
                else{
                    startActivity(i);
                }
            }
        });
        start.setBackgroundResource(R.mipmap.b_start);


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
                Intent i=new Intent(MainActivity.this, MainActivity.class);
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

    /*@Override
    protected void onPause() {
        mServ.pauseMusic();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mServ.stopMusic();
        stopService(new Intent(this,MusicService.class));
        doUnbindService();
        super.onDestroy();
    };

    @Override
    protected void onResume() {
        super.onResume();
        if(mServ!=null)
            mServ.resumeMusic();
    };*/


    /*@Override
    public void onDestroy(){
        super.onDestroy();
        stopService(new Intent(this,MusicService.class));//chiude muscia, da controllare se giusto
    }*/

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