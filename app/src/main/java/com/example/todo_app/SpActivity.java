package com.example.todo_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import java.util.Objects;

public class SpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sp);
        Handler handler= new Handler();
       Objects.requireNonNull(getSupportActionBar()).hide();
        handler.postDelayed(new Runnable(){
            public void run(){
                startActivity(new Intent(SpActivity.this,MainActivity.class));
                finish();
            }
        },3000);


    }
}

// new Handler().postDelayed(new Runnable(){
//@Override
//public void run() {
//        /* Create an Intent that will start the Menu-Activity. */
//        Intent mainIntent = new Intent(Splash.this,Menu.class);
//        Splash.this.startActivity(mainIntent);
//        Splash.this.finish();
//        }
//        }, SPLASH_DISPLAY_LENGTH);
//        }