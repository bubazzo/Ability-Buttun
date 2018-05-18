package com.example.luca.abilitybuttun;

import android.graphics.Color;
import android.view.View;
import android.widget.Button;

import java.util.*;
/**
 * Created by regis on 13/01/2018.
 */

public class ButtonApp {
    
    private Button button;
    private int id=-1;
    private HashMap<String,Integer> colors = new HashMap<>();
    private String currentColor;

    public ButtonApp(HashMap colors,Button button){
        this.colors=colors;
        this.button=button;
    }
    public void setId(int id){
        this.id=id;
        button.setText(Integer.toString(id));
    }
    public int getId(){
        return id;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    public void setColor(String color) {
        button.setBackgroundColor(colors.get(color));
        currentColor=color;
    }
    public String getColor(){
        return currentColor;
    }

    public void setVisible(boolean visible) {
        if(visible)
            button.setVisibility(View.VISIBLE);
        else
            button.setVisibility(View.INVISIBLE);
    }

    public void setBackImage(){
        button.setBackgroundResource(R.mipmap.free_button);
    }

    public void setColorText(String color){
        button.setTextColor(colors.get(color));
    }
}
