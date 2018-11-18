


package com.example.pawan.pianotiles;

        import android.content.Context;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.media.AudioManager;
        import android.media.MediaPlayer;
        import android.os.CountDownTimer;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.SoundEffectConstants;
        import android.view.View;
        import android.widget.Button;
        import android.widget.ImageButton;
        import android.widget.ImageView;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;

        import java.util.Random;



public class startpage extends AppCompatActivity {

    // Write a message to the database


    ImageView iv_11, iv_12, iv_13,
            iv_21, iv_22, iv_23,
            iv_31, iv_32, iv_33,
            iv_41, iv_42, iv_43,
            iv_51, iv_52, iv_53;

    int wallpaper;

    public MediaPlayer song,gameover,bgsong;

   // Button b_play;

    TextView tv_time, tv_score, tv_best;

    Random r;

    int locrow1, locrow2, locrow3, locrow4, locrow5;
    int blackimage, frameimage, tapimage, emptyimage;

    public static int currentscore = 0;
    public static int bestscore;






    CountDownTimer timer;

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startpage);

         song = MediaPlayer.create(startpage.this,R.raw.tap);
         gameover = MediaPlayer.create(startpage.this,R.raw.gameover);
         bgsong = MediaPlayer.create(startpage.this,R.raw.bg);

        SharedPreferences preferences = getSharedPreferences("PREFS", 0);
        bestscore = preferences.getInt("highscore", 0);

        iv_11 = (ImageView) findViewById(R.id.iv_11);
        iv_12 = (ImageView) findViewById(R.id.iv_12);
        iv_13 = (ImageView) findViewById(R.id.iv_13);

        iv_21 = (ImageView) findViewById(R.id.iv_21);
        iv_22 = (ImageView) findViewById(R.id.iv_22);
        iv_23 = (ImageView) findViewById(R.id.iv_23);

        iv_31 = (ImageView) findViewById(R.id.iv_31);
        iv_32 = (ImageView) findViewById(R.id.iv_32);
        iv_33 = (ImageView) findViewById(R.id.iv_33);

        iv_41 = (ImageView) findViewById(R.id.iv_41);
        iv_42 = (ImageView) findViewById(R.id.iv_42);
        iv_43 = (ImageView) findViewById(R.id.iv_43);

        iv_51 = (ImageView) findViewById(R.id.iv_51);
        iv_52 = (ImageView) findViewById(R.id.iv_52);
        iv_53 = (ImageView) findViewById(R.id.iv_53);

        // b_play = (Button) findViewById(R.id.b_play);

        tv_score = (TextView) findViewById(R.id.tv_score);
        tv_score.setText("SCORE: " + currentscore);

        tv_best = (TextView) findViewById(R.id.tv_best);
        tv_best.setText("BEST: " + bestscore);


        tv_time = (TextView) findViewById(R.id.tv_time);
      //  tv_time.setText("TIME: " + milliToTime(14000));


        r = new Random();
        loadImages();


        timer = new CountDownTimer(15000, 500) {
            @Override
            public void onTick(long l) {
                tv_time.setText("TIME: " + l/500);
            }

            @Override
            public void onFinish() {
                tv_time.setText("DONE!");

                iv_31.setEnabled(false);
                iv_32.setEnabled(false);
                iv_33.setEnabled(false);

                 // b_play.setVisibility(View.VISIBLE);


                iv_11.setImageResource(wallpaper);
                iv_12.setImageResource(wallpaper);
                iv_13.setImageResource(wallpaper);

                iv_21.setImageResource(wallpaper);
                iv_22.setImageResource(wallpaper);
                iv_23.setImageResource(wallpaper);

                iv_31.setImageResource(wallpaper);
                iv_32.setImageResource(wallpaper);
                iv_33.setImageResource(wallpaper);

                iv_41.setImageResource(wallpaper);
                iv_42.setImageResource(wallpaper);
                iv_43.setImageResource(wallpaper);

                iv_51.setImageResource(wallpaper);
                iv_52.setImageResource(wallpaper);
                iv_53.setImageResource(wallpaper);


                Toast.makeText(startpage.this, "TIME UP!", Toast.LENGTH_SHORT).show();

                if (currentscore > bestscore) {
                    bestscore = currentscore;

                    SharedPreferences preferences1 = getSharedPreferences("PREFS", 0);
                    SharedPreferences.Editor editor = preferences1.edit();
                    editor.putInt("highscore", bestscore);

                    editor.apply();
                    editor.commit();


                }

                tv_best.setText("BEST: " + bestscore);




                SendUserToGameOver();
            }
        };

        iv_31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (locrow3 == 1) {
                    continueGame();
                } else {
                    endGame();
                }
            }
        });

        iv_32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (locrow3 == 2) {
                    continueGame();
                } else {
                    endGame();
                }
            }
        });

        iv_33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (locrow3 == 3) {
                    continueGame();
                } else {
                    endGame();
                }
            }
        });


         iv_21.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 if (locrow3 == 1) {
                     continueGame();
                 } else {
                     endGame();
                 }
             }
         });

         iv_22.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 if (locrow3 == 2) {
                     continueGame();
                 } else {
                     endGame();
                 }
             }
         });

         iv_23.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 if (locrow3 == 3) {
                     continueGame();
                 } else {
                     endGame();
                 }
             }
         });



        initGame();
         getScore(currentscore);
      //  b_play.setOnClickListener(new View.OnClickListener() {
       //     @Override
       //     public void onClick(View view) {
       //         initGame();
       //     }
      //  });

    }
    public void PLAYIT(View v){
        song.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        song.release();
    }

    private void SendUserToGameOver() {
        Intent i = new Intent( this,gameover.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
        finish();
    }
    public int getScore(int s)
    {
        s = currentscore;

        return s;
    }


    private void continueGame() {
        //row5
        locrow5 = locrow4;
        setLoc(locrow5, 5);

        //row4
        locrow4 = locrow3;
        setLoc(locrow4, 4);

        //row3
        locrow3 = locrow2;
        setLoc(locrow3, 3);

        //row2
        locrow2 = locrow1;
        setLoc(locrow2, 2);

        //row1
        locrow1 = r.nextInt(3) + 1;
        setLoc(locrow1, 1);

        currentscore++;
        tv_score.setText("SCORE: " + currentscore);

    }

    public void initGame() {
        iv_31.setEnabled(true);
        iv_32.setEnabled(true);
        iv_33.setEnabled(true);
        iv_21.setEnabled(true);
        iv_22.setEnabled(true);
        iv_23.setEnabled(true);

       // b_play.setVisibility(View.INVISIBLE);  //edited

        currentscore = 0;
        tv_score.setText("SCORE: " + currentscore);

        timer.start();
        bgsong.start();

        //row5 nothing

        //row4
        locrow4 = 2;
        iv_42.setImageResource(blackimage);

        //row3
        locrow3 = 2;
        iv_32.setImageResource(tapimage);

        locrow3 = 2;
        iv_22.setImageResource(tapimage);

        //row2
        locrow2 = r.nextInt(3) + 1;
        setLoc(locrow2, 2);

        //row1
        locrow1 = r.nextInt(3) + 1;
        setLoc(locrow1, 1);

    }

    private void endGame() {
        timer.cancel();

        iv_31.setEnabled(false);
        iv_32.setEnabled(false);
        iv_33.setEnabled(false);
        iv_21.setEnabled(false);
        iv_22.setEnabled(false);
        iv_23.setEnabled(false);

      //  b_play.setVisibility(View.VISIBLE);

        iv_11.setImageResource(wallpaper);
        iv_12.setImageResource(wallpaper);
        iv_13.setImageResource(wallpaper);

        iv_21.setImageResource(wallpaper);
        iv_22.setImageResource(wallpaper);
        iv_23.setImageResource(wallpaper);

        iv_31.setImageResource(wallpaper);
        iv_32.setImageResource(wallpaper);
        iv_33.setImageResource(wallpaper);

        iv_41.setImageResource(wallpaper);
        iv_42.setImageResource(wallpaper);
        iv_43.setImageResource(wallpaper);

        iv_51.setImageResource(wallpaper);
        iv_52.setImageResource(wallpaper);
        iv_53.setImageResource(wallpaper);

        Toast.makeText(startpage.this, "GAME OVER!", Toast.LENGTH_SHORT).show();
       // gameover.stop();
        gameover.start();
        bgsong.stop();
        SendUserToGameOver();
    }

    public void setLoc(int place, int row) {
        if (row == 1) {
            iv_11.setImageResource(wallpaper);
            iv_12.setImageResource(wallpaper);
            iv_13.setImageResource(wallpaper);

            switch (place) {
                case 1:
                    iv_11.setImageResource(emptyimage);
                    break;
                case 2:
                    iv_12.setImageResource(emptyimage);
                    break;
                case 3:
                    iv_13.setImageResource(emptyimage);
                    break;

            }
        }

        if (row == 2) {
            iv_21.setImageResource(wallpaper);
            iv_22.setImageResource(wallpaper);
            iv_23.setImageResource(wallpaper);

            switch (place) {
                case 1:
                    iv_21.setImageResource(tapimage);

                    break;
                case 2:
                    iv_22.setImageResource(tapimage);
                    break;
                case 3:
                    iv_23.setImageResource(tapimage);
                    break;

            }
        }

        if (row == 3) {
            iv_31.setImageResource(wallpaper);
            iv_32.setImageResource(wallpaper);
            iv_33.setImageResource(wallpaper);

            switch (place) {
                case 1:
                    iv_31.setImageResource(tapimage);
                    loadsong(row,place);
                    //song.start();
                    break;
                case 2:
                    iv_32.setImageResource(tapimage);
                    loadsong(row,place);
                   // song.start();
                    break;
                case 3:
                    iv_33.setImageResource(tapimage);
                    loadsong(row,place);
                   // song.start();
                    break;

            }
        }

        if (row == 4) {
            iv_41.setImageResource(wallpaper);
            iv_42.setImageResource(wallpaper);
            iv_43.setImageResource(wallpaper);

            switch (place) {
                case 1:
                    iv_41.setImageResource(blackimage);
                    break;
                case 2:
                    iv_42.setImageResource(blackimage);
                    break;
                case 3:
                    iv_43.setImageResource(blackimage);
                    break;

            }
        }

        if (row == 5) {
            iv_51.setImageResource(wallpaper);
            iv_52.setImageResource(wallpaper);
            iv_53.setImageResource(wallpaper);

            switch (place) {
                case 1:
                    iv_51.setImageResource(emptyimage);
                    break;
                case 2:
                    iv_52.setImageResource(emptyimage);
                    break;
                case 3:
                    iv_53.setImageResource(emptyimage);
                    break;

            }
        }


    }



    public void loadsong(int row,int place){

             if((row ==3 || row == 2) && place==3)
                 song.start();
             else if((row ==3 || row == 2) && place==2)
                 song.start();
             else if ((row ==3 || row == 2) && place==1)
                 song.start();

    }

    private void loadImages() {
        frameimage = R.drawable.frame;
        blackimage = R.drawable.green;
        tapimage = R.drawable.blue;
        emptyimage = R.drawable.green;
    }
}

