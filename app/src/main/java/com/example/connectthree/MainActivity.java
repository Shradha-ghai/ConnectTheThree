package com.example.connectthree;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //red:1,green:0
    int gamestate[]={2,2,2,2,2,2,2,2,2};
    int winState[][]={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean gameActive=true;

    //x is current player
    int x=0;
    public void func(View view){
        ImageView counter=(ImageView) view;
        int position=Integer.parseInt(counter.getTag().toString());
        if(gamestate[position]==2 && gameActive==true) {
            gamestate[position] = x;
            if (x == 0) {
                counter.setImageResource(R.drawable.green);
                counter.animate().rotation(3600).setDuration(300);
                x = 1;
            } else if (x == 1) {
                counter.setImageResource(R.drawable.red);
                counter.animate().rotation(3600).setDuration(300);
                x = 0;
            }
            for (int[] win : winState) {
                if (gamestate[win[0]] == gamestate[win[1]] && gamestate[win[2]] == gamestate[win[1]] && gamestate[win[1]] != 2) {
                    String mess = "";
                    if (gamestate[win[1]] == 1) {
                        mess = "Red";
                    } else if (gamestate[win[1]] == 0) {
                        mess = "Green";
                    }
                    //Toast.makeText(this, mess + " has won!", Toast.LENGTH_SHORT).show();
                    gameActive=false;
                    Button button=(Button) findViewById(R.id.button);
                    TextView textView=(TextView) findViewById(R.id.textView);
                    textView.setText( mess+ " has won!");
                    button.setVisibility(View.VISIBLE);
                    textView.setVisibility(View.VISIBLE);
                }
            }
        }
    }
    public void again(View view){
        Button button=(Button) findViewById(R.id.button);
        TextView textView=(TextView) findViewById(R.id.textView);

        button.setVisibility(View.INVISIBLE);
        textView.setVisibility(View.INVISIBLE);
        GridLayout gridLayout=(GridLayout) findViewById(R.id.gridLayout);
        for(int i=0;i<gridLayout.getChildCount();i++){
            ImageView count=(ImageView) gridLayout.getChildAt(i);
            count.setImageDrawable(null);

        }
        for(int j=0;j<gamestate.length;j++){
            gamestate[j]=2;
        }
        gameActive=true;
        //x is current player
        x=0;
        ImageView imageView=(ImageView) findViewById(R.id.imageView3);
        imageView.setImageResource(R.drawable.tictac);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}