package com.example.particles;

import android.graphics.Canvas;

import com.example.particles.mathextensions.Vector2D;


public class Entity {
    public Vector2D position = new Vector2D(0,0);
    public Vector2D scale = new Vector2D(1,1);
    public float rotation = 0;

    public Mesh mesh;

    public boolean active = true;

    public void Start(){

    }

    public void drawSelf(Canvas canvas)
    {

    }

    public void Update()
    {

    }
}
