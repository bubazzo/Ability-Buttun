package com.example.luca.abilitybuttun;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
/*per il bottone indietro*/
import android.app.AlertDialog;
import android.content.DialogInterface;


/**
 * Created by Luca on 19/11/2017.
 */

public class InterLvActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interlv);
        Button button=(Button) findViewById(R.id.bInter);
        Intent i=getIntent();
        final Integer lv=i.getIntExtra("livello", -1);
        final Integer lvSucc=lv+1;
        button.setText("lv"+lv.toString()+" completed\nTap to start lv"+lvSucc.toString());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(lvSucc==5 || lvSucc==10 || lvSucc==15 || lvSucc==20 || lvSucc==25 || lvSucc==30
                        || lvSucc==35 || lvSucc==40){
                    Intent iR=new Intent(InterLvActivity.this, RegoleActivity.class);
                    iR.putExtra("livello", lv);
                    startActivity(iR);
                }
                else{
                    Intent iLv=new Intent(InterLvActivity.this, firstActivity.class);
                    iLv.putExtra("livello", lv);
                    startActivity(iLv);
                }

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
        finish();
    }
}
