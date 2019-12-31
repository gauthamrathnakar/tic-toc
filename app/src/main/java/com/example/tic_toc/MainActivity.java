package com.example.tic_toc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button[][] buttons=new Button[3][3];
    private boolean player1Turn=true;
    private int roundCounts=0;
    private int player1points=0;
    private int player2points=0;
    private TextView textViewPlayer1;
    private TextView textViewPlayer2;
    private Button resetbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewPlayer1=findViewById(R.id.player1text);
        textViewPlayer2=findViewById(R.id.player2text);

        resetbutton=findViewById(R.id.resetbutton);

        buttons[0][0]=findViewById(R.id.button_00);
        buttons[0][1]=findViewById(R.id.button_01);
        buttons[0][2]=findViewById(R.id.button_02);
        buttons[1][0]=findViewById(R.id.button_10);
        buttons[1][1]=findViewById(R.id.button_11);
        buttons[1][2]=findViewById(R.id.button_12);
        buttons[2][0]=findViewById(R.id.button_20);
        buttons[2][1]=findViewById(R.id.button_21);
        buttons[2][2]=findViewById(R.id.button_22);

        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++)
                buttons[i][j].setOnClickListener(this);

         resetbutton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 resetgame();
             }
         });





    }

    @Override
    public void onClick(View v) {
        Button b=(Button)v;
        if(!b.getText().toString().equals(""))
        {
            return;
        }
        if(player1Turn)
        {
            b.setText("X");
        }
        else
        {
            b.setText("O");
        }
        roundCounts++;

        if(checkForWin())
        {
            if(player1Turn)
            {
                player1Wins();
            }
            else
            {
                player2Wins();
            }
        }
        else
            if(roundCounts==9)
            {
                draw();
            }
            else
            {
                player1Turn=!player1Turn;
            }
    }
    private void player1Wins()
    {
       player1points++;
        Toast.makeText(this,"players 1 wins!",Toast.LENGTH_LONG).show();
        updatePointText();
        resetBoard();
    }
    private void player2Wins()
    {
        player2points++;
        Toast.makeText(this,"players 2 win!",Toast.LENGTH_LONG).show();
        updatePointText();
        resetBoard();
    }
    private void draw()
    {
        Toast.makeText(this, "Draw!!", Toast.LENGTH_SHORT).show();
        resetBoard();
    }
    private void updatePointText()
    {
        textViewPlayer1.setText("Player1:"+player1points);
        textViewPlayer2.setText("Player2:"+player2points);
    }
    private void resetBoard()
    {
        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++)
                buttons[i][j].setText("");
    roundCounts=0;
    player1Turn=true;
    }
    private void resetgame()
    {
        player2points=0;
        player1points=0;
        updatePointText();
        resetBoard();
    }
    private boolean checkForWin()
    {
        String[][] field=new String[3][3];
        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++)
                field[i][j]=buttons[i][j].getText().toString();

         for(int i=0;i<3;i++)
         {
             if(field[i][0].equals(field[i][1]) && field[i][0].equals(field[i][2]) && !field[i][0].equals(""))
             {
                 return true;
             }
         }

        for(int i=0;i<3;i++)
        {
            if(field[0][i].equals(field[1][i]) && field[0][i].equals(field[2][i]) && !field[0][i].equals(""))
            {
                return true;
            }
        }
        if(field[0][0].equals(field[1][1]) && field[0][0].equals(field[2][2]) & !field[0][0].equals(""))
        {
            return true;
        }
        if(field[0][2].equals(field[1][1]) && field[0][2].equals(field[2][0]) && !field[0][2].equals(""))
        {
            return true;
        }
         return false;
    }
}
