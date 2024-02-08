package com.example.appli3voiture;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
//import com.bumptech.glide.Glide;
import com.example.appli3voiture.Logic.GameManager;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.widget.Toast;

import java.util.Random;


public class MainActivity extends AppCompatActivity {

    final int ROWS = 6;
    final int COLS = 3;
    private int speed = 1000;


    private ShapeableImageView main_IMG_background;
    private ShapeableImageView main_IMG_hearts[];
    private ImageView main_IMG_1, main_IMG_2, main_IMG_3, main_IMG_4, main_IMG_5, main_IMG_6, main_IMG_7, main_IMG_8, main_IMG_9, main_IMG_10,
            main_IMG_11, main_IMG_12, main_IMG_13, main_IMG_14, main_IMG_15,main_IMG_16,main_IMG_17,main_IMG_18;

    private Button main_BTN_left;
    private ShapeableImageView main_IMG_cars[];
    private Button main_BTN_right;
    private ImageView mat[][] = new ImageView[COLS][ROWS];


    private GameManager gameManager;
    private MaterialTextView main_LBL_score;
    private Random random;
    private CountDownTimer timer;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        random = new Random();

        gameManager = new GameManager(main_IMG_hearts.length);
        timerStart();

        main_BTN_right.setOnClickListener(view -> buttonActivity("right"));
        main_BTN_left.setOnClickListener(view -> buttonActivity("left"));

    }

    private void timerStart() {
            timer = new CountDownTimer(1000000,speed) {
                @Override
                public void onTick(long l) {
                    goOnUI();
                }
                @Override
                public void onFinish() {
                    timer.cancel();
                    changeActivity("YOU WON!!", gameManager.getScore());

                }
            };
            timer.start();
    }
    public void goOnUI(){

        //bottom - en bas
        if(gameManager.getRow() == ROWS-1){

            mat[gameManager.getRow()][gameManager.getCol()].setImageResource(0);
            gameManager.setRow(0);
            gameManager.setCol(random.nextInt(3));
        }

        //game over
        if(gameManager.isGameEnded()){
            toastAndVibrate("Touched");
            timer.cancel();
            changeActivity("GAME OVER", gameManager.getScore());

        }

        mat[gameManager.getRow()][gameManager.getCol()].setImageResource(0);
        gameManager.setRow(gameManager.getRow()+1);
        mat[gameManager.getRow()][gameManager.getCol()].setImageResource(R.drawable.man);

        //loose
        if(gameManager.isMeet()) {
            main_IMG_hearts[main_IMG_hearts.length - gameManager.getMeet()].setVisibility(View.INVISIBLE);
            toastAndVibrate("Touched");
        }
        main_LBL_score.setText(gameManager.getScore() + "");
        gameManager.setScore(gameManager.getScore()+1);
        main_LBL_score.setText(String.valueOf(gameManager.getScore()));


    }


    private void toastAndVibrate(String text) {
        vibrate();
        toast(text);
    }
    private void toast(String text) {
        Toast.makeText(this,text, Toast.LENGTH_LONG).show();
    }
    private void vibrate() {
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        // Vibrate for 500 milliseconds
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            //deprecated in API 26
            v.vibrate(500);
        }
    }

    private void changeActivity(String status, int score) {
        Intent scoreIntent = new Intent(this, ScoreActivity.class);
        scoreIntent.putExtra(ScoreActivity.KEY_STATUS, status);
        scoreIntent.putExtra(ScoreActivity.KEY_SCORE, score);
        startActivity(scoreIntent);
        finish();
    }
    private void buttonActivity(String answere) {
       if(answere == "right"){
           if(gameManager.getPosCar() !=2){
               main_IMG_cars[gameManager.getPosCar()].setImageResource(0);
               main_IMG_cars[gameManager.getPosCar()  +1].setImageResource(R.drawable.car);
               gameManager.setPosCar(gameManager.getPosCar()+1);
           }

       }
       if(answere == "left") {

           if (gameManager.getPosCar()  != 0) {
               main_IMG_cars[gameManager.getPosCar()].setImageResource(0);
               main_IMG_cars[gameManager.getPosCar() - 1].setImageResource(R.drawable.car);
               gameManager.setPosCar(gameManager.getPosCar()-1);

           }
       }
   }

    private void findViews() {
        main_BTN_right = findViewById(R.id.main_BTN_right);
        main_BTN_left = findViewById(R.id.main_BTN_left);
        main_LBL_score= findViewById(R.id.main_LBL_score);
        main_IMG_background = findViewById(R.id.main_IMG_background);

        main_IMG_hearts = new ShapeableImageView[]{
                findViewById(R.id.main_IMG_heart1),
                findViewById(R.id.main_IMG_heart2),
                findViewById(R.id.main_IMG_heart3)
        };
        main_IMG_cars = new ShapeableImageView[]{
                findViewById(R.id.main_IMG_car1),
                findViewById(R.id.main_IMG_car2),
                findViewById(R.id.main_IMG_car3)
        };
        main_IMG_1 = findViewById(R.id.main_IMG_1);
        main_IMG_2 = findViewById(R.id.main_IMG_2);
        main_IMG_3 = findViewById(R.id.main_IMG_3);
        main_IMG_4 = findViewById(R.id.main_IMG_4);
        main_IMG_5 = findViewById(R.id.main_IMG_5);
        main_IMG_6 = findViewById(R.id.main_IMG_6);
        main_IMG_7 = findViewById(R.id.main_IMG_7);
        main_IMG_8 = findViewById(R.id.main_IMG_8);
        main_IMG_9 = findViewById(R.id.main_IMG_9);
        main_IMG_10 = findViewById(R.id.main_IMG_10);
        main_IMG_11 = findViewById(R.id.main_IMG_11);
        main_IMG_12 = findViewById(R.id.main_IMG_12);
        main_IMG_13 = findViewById(R.id.main_IMG_13);
        main_IMG_14 = findViewById(R.id.main_IMG_14);
        main_IMG_15 = findViewById(R.id.main_IMG_15);
        main_IMG_16 = findViewById(R.id.main_IMG_16);
        main_IMG_17 = findViewById(R.id.main_IMG_17);
        main_IMG_18 = findViewById(R.id.main_IMG_18);

        ImageView t[][] = {{main_IMG_1, main_IMG_2, main_IMG_3},
                {main_IMG_4, main_IMG_5, main_IMG_6},
                {main_IMG_7, main_IMG_8, main_IMG_9},
                {main_IMG_10, main_IMG_11, main_IMG_12},
                {main_IMG_13, main_IMG_14, main_IMG_15},
                {main_IMG_16,main_IMG_17,main_IMG_18}};
        mat = t;

    }






}