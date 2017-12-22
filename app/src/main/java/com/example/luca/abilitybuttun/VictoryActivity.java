package com.example.luca.abilitybuttun;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.res.ConfigurationHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Luca on 18/11/2017.
 */

public class VictoryActivity extends AppCompatActivity {
    Integer lvStamp=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_victory);
        TextView testo=(TextView)findViewById(R.id.victory);
        Intent i=getIntent();
        int ris=i.getIntExtra("esito", -1);
        Button restart=(Button) findViewById(R.id.restart);
        TextView testoLv=(TextView) findViewById(R.id.tlvR);

        if(ris==0){
            testo.setText("YOU LOSE");
            testo.setTextColor(Color.RED);
            int lvR=i.getIntExtra("livello", -1);
            lvStamp=lvR;
            testoLv.setText("lv: "+ lvStamp.toString());
        }
        if(ris==1){
            testo.setText("YOU WIN");
            testoLv.setVisibility(View.INVISIBLE);
        }
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(VictoryActivity.this, CheckpointActivity.class);
                startActivity(i);
            }
        });
    }
}
