package com.example.particles;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.particles.mathextensions.Vector2D;


public class MainActivity extends AppCompatActivity {
    GameEngine gameEngine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        gameEngine = new GameEngine(60);
        setContentView(R.layout.activity_main_with_start_vectors);

        TargetWindow targetWindow = findViewById(R.id.TargetWindow);
        targetWindow.gameEngine = gameEngine;
        gameEngine.setTargetWindow(targetWindow);

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);

        Mesh mesh = new Mesh(new Vector2D[]{
                new Vector2D(-50f, -50f),
                new Vector2D(50f, -50f),
                new Vector2D(50f, 50f),
                new Vector2D(-50f, 50f),
        }, new int[]{0, 1, 2, 0, 2, 3});

        RigidBody rigidBody1 = (RigidBody) GameEngine.ins().Instantiate(new RigidBody(paint), new Vector2D(300f, 300f), new Vector2D(1f, 1f), 0);
        rigidBody1.mesh = mesh;
        rigidBody1.active = false;
        ParticleSystem particleSystem = (ParticleSystem) GameEngine.ins().Instantiate(new ParticleSystem(paint, new Vector2D(0, 5)), new Vector2D(300f, 300f), new Vector2D(1, 1), 0);
        particleSystem.mesh = mesh;

        EditText accelerationText = findViewById(R.id.AccelerationEditText);
        EditText rotationText = findViewById(R.id.RotationEditText);
        EditText startSpeedText = findViewById(R.id.StartSpeed);

        Button set = findViewById(R.id.SetValuesButton);
        set.setOnClickListener(v -> {

            try {
                float rotation = Float.parseFloat(rotationText.getText().toString());
                Vector2D acceleration = Vector2D.Parse(accelerationText.getText().toString(), " ");
                Vector2D startSpeed = Vector2D.Parse(startSpeedText.getText().toString(), " ");
                particleSystem.SetParticles(10, new Vector2D(1, 1), startSpeed, acceleration, rotation);
            } catch (Exception e) {

            }

        });

        Button rotateLeft = findViewById(R.id.rotateLeftButton);
        Button accelerateRight = findViewById(R.id.TranslateRightButton);
        Button accelerateLeft = findViewById(R.id.TranslateLeftButton);
        Button rotateRight = findViewById(R.id.rotateRightButton);
        Button accelerateUp = findViewById(R.id.TranslateUpButton);
        Button accelerateDown = findViewById(R.id.TranslateDownButton);

        gameEngine.start();
    }

    @Override
    protected void onStop() {
        if (gameEngine.isRunning)
            gameEngine.isRunning = false;
        super.onStop();
    }

    @Override
    protected void onRestart() {
        if (!gameEngine.isRunning) {
            gameEngine.isRunning = true;
            gameEngine.start();
        }
        super.onRestart();
    }
}