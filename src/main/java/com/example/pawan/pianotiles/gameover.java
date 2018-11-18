package com.example.pawan.pianotiles;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.example.pawan.pianotiles.MainActivity.s;


public class gameover extends AppCompatActivity {


     Button replay, exit,logout;
    public MediaPlayer bg;
    TextView finalscore,Bestscore,huser,highest;
    int bestscore2;
    private static final String TAG = MainActivity.class.getSimpleName();
    private FirebaseAuth mAuth;
    private DatabaseReference UsersRef;
    private StorageReference UserProfileImageRef;

    String currentUserID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameover);

        bg = MediaPlayer.create(gameover.this,R.raw.bg);

        replay = (Button) findViewById(R.id.replay);
        exit = (Button) findViewById(R.id.btn_exit);
        logout = (Button) findViewById(R.id.btn_logout);

        startpage start =new startpage();
        startup2 start2 =new startup2();

        finalscore = (TextView) findViewById(R.id.finalscore);
        Bestscore = (TextView) findViewById(R.id.bestscore);

        huser = (TextView) findViewById(R.id.huser);
        highest = (TextView) findViewById(R.id.highest);


        if(s==1)
        finalscore.setText(""+start.getScore(start.currentscore));
        else
        finalscore.setText(""+start2.getScore(start2.currentscore1));


       SharedPreferences preference = getSharedPreferences("PREFS", 0);
       bestscore2 = preference.getInt("highscore", 0);
       Bestscore.setText("Best Score: " +bestscore2);






        mAuth = FirebaseAuth.getInstance();
        currentUserID = mAuth.getCurrentUser().getUid();
        UsersRef = FirebaseDatabase.getInstance().getReference().child("Users").child(currentUserID);

        HashMap userMap = new HashMap();
        userMap.put("Best Score",bestscore2);

        UsersRef.updateChildren(userMap).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(gameover.this, "Your information is saved Successfully..", Toast.LENGTH_LONG).show();

                }
                else{
                    String message = task.getException().getMessage();
                    Toast.makeText(gameover.this, "Error Occured: "+message, Toast.LENGTH_SHORT).show();

                }
            }
        });







      final  DatabaseReference Players = FirebaseDatabase.getInstance().getReference();

        Query HighestPlayer = Players.child("Users").orderByChild("Best Score").limitToLast(1);
        HighestPlayer.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot){
                for (DataSnapshot childSnapshot: dataSnapshot.getChildren()) {

                   // String Key = childSnapshot.getKey();

                    //Log.i(TAG,Key);
                     //  highest.setText("F"+Key);

                        String Username = childSnapshot.child("username").getValue().toString();
                       String Hscore = childSnapshot.child("Best Score").getValue().toString();
                        highest.setText(Hscore);
                        huser.setText(Username);
                    }
                }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                throw databaseError.toException(); // don't swallow errors
            }
        });





        getbestscoreofapp();



        replay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                bg.start();

               openMain();

            }
        });



        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int reset =0;
                SharedPreferences p = getSharedPreferences("PREFS", 0);
                SharedPreferences.Editor editor = p.edit();
                editor.putInt("highscore",reset);
                editor.commit();

                    mAuth.signOut();
                    Intent i=new Intent(gameover.this, LoginActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
                finish();

            }
            });




        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                bg.release();

                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);

            }
        });
    }

    private void getbestscoreofapp() {

    }

    public void REPLAY(View v){
        bg.start();
    }


    @Override
    protected void onPause() {
        super.onPause();
       bg.release();
    }

    private void openMain() {
        Intent i = new Intent( this,MainActivity.class);
        startActivity(i);
    }


}
