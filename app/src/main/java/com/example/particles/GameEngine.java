package com.example.particles;

import android.graphics.Canvas;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.view.MotionEvent;
import android.view.View;

import com.example.particles.mathextensions.Vector2D;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

public class GameEngine extends Thread implements View.OnTouchListener {

    public static GameEngine gameEngine = null;

    public static GameEngine ins() {
        return gameEngine;
    }

    public boolean isRunning;

    float timeOffset;
    static float time;
    float timePerFrame;

    public Sensor sensor;
    public SensorManager sensorManager;

    static float deviceRotationX;
    static float deviceRotationY;
    static float deviceRotationZ;

    public float getDeltaTime() {
        return deltaTime;
    }

    static float deltaTime;

    Thread thread;
    public List<Entity> entities = new ArrayList<>();
    public Queue<Entity> entitiesToAdd = new LinkedBlockingDeque<>();

    public View targetWindow;

    public GameEngine() {
        isRunning = true;
    }

    public GameEngine(float framesPerSecond) {
        gameEngine = this;

        isRunning = true;
        thread = Thread.currentThread();

        time = 0;
        timeOffset = GetTime();

        timePerFrame = 1f / framesPerSecond;
        deltaTime = timePerFrame;

        System.out.println("GameEngineInit timePerFrame " + timePerFrame);
    }

    public float GetTime() {
        return (float) System.nanoTime() / 1000000000f - timeOffset;
    }


    @Override
    public void run() {

        while (isRunning) {
            try {
                float frameStartTime = GetTime();
                float frameExpectedEndTime = frameStartTime + timePerFrame;

                while (!entitiesToAdd.isEmpty()) {
                    entities.add(entitiesToAdd.remove());
                }

                for (Entity entity : entities) {
                    if (entity.active)
                        entity.Update();
                }

                targetWindow.postInvalidate();
                float frameEndTime = GetTime();
                long sleepTime = (long) ((frameExpectedEndTime - frameEndTime) * 1000f);
                if (sleepTime > 0)
                    thread.join(sleepTime);

                deltaTime = frameExpectedEndTime - frameStartTime;

                time += deltaTime;

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }


    public void DrawEntities(Canvas canvas) {
        for (Entity entity : entities) {
            if (entity.active)
                entity.drawSelf(canvas);
        }
    }

    public Entity Instantiate(Entity prefab, Vector2D position, Vector2D scale, float rotation) {
        prefab.position = position;
        prefab.scale = scale;
        prefab.rotation = rotation;
        entitiesToAdd.add(prefab);
        return prefab;
    }

    public void setTargetWindow(TargetWindow _targetWindow){
        targetWindow = _targetWindow;
        targetWindow.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        float x = event.getX();
        float y = event.getY();
        float pressure = event.getPressure();
        String motion = "";
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                motion = "down";
                break;
            case MotionEvent.ACTION_MOVE:
                motion = "move";
                break;
            case MotionEvent.ACTION_UP:
                motion = "up";
                break;
            case MotionEvent.ACTION_CANCEL:
                motion = "cancel";
                break;
        }

        System.out.println(motion + " [" + x + ";" + y + "] pressure" + pressure);
        return false;
    }
}
