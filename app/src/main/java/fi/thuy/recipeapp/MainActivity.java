package fi.thuy.recipeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import java.util.Objects;

/**
 * This is a main activity for the program.
 * This is the first screen the user sees.
 */
public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MAINACTIVITY";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();

        move();

        Button btnStart = findViewById(R.id.buttonGetStarted);

        btnStart.setOnClickListener((view) -> handleStartClick());

    }

    /**
     *When the user clicks the  start button
     * it will redirect the user to next activity.
     */
    private void handleStartClick(){
        Log.d(TAG,"Get Started clicked");
        Intent intent = new Intent(this, StartActivity.class);
        startActivity(intent);
    }

    /**
     *When the user opens the app for the first time,
     * match recipe will do some animation.
     */
    public void move(){
        TextView tv = findViewById(R.id.textViewMain);

        Animation animation1 =
                AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.move);
        tv.startAnimation(animation1);
    }
}