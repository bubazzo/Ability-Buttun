package com.example.luca.abilitybuttun;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


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
                if(lvSucc==5 || lvSucc==10 || lvSucc==15 || lvSucc==20 || lvSucc==25){
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
}
