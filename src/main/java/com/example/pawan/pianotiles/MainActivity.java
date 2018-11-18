package com.example.pawan.pianotiles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;




public class MainActivity extends AppCompatActivity {

    Button play,play2;

    private FirebaseAuth mAuth;
  public static int s=0;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        play =(Button) findViewById(R.id.play);
        play2 =(Button) findViewById(R.id.play2);
       s=0;

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s=1;
                openActivity2();

            }
        });

        play2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openActivity3();

            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();
        mAuth= FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        s=0;
        if(currentUser == null)
        {
            SendUserToLoginActivity();
        }
    }




    private void SendUserToLoginActivity() {
        Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
        loginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(loginIntent);
        finish();
    }



    public void openActivity2(){

        Intent i = new Intent( this,startpage.class);
        startActivity(i);


    }

    public void openActivity3(){

        Intent i = new Intent( this,startup2.class);
        startActivity(i);


    }
}

