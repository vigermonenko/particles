package com.example.particles;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.particles.mathextensions.Matrix3x3;
import com.example.particles.mathextensions.Vector2D;

public class RigidBody extends Entity {
    float mass = 1f;
    Vector2D velocity = new Vector2D(0, 0);
    Vector2D acceleration = new Vector2D(0,0);
    float rotationVelocity = 0;

    Paint paint;

    public RigidBody(Paint paint) {
        this.paint = paint;
    }

    @Override
    public void drawSelf(Canvas canvas) {

        super.drawSelf(canvas);
        mesh.Draw(canvas, paint, Matrix3x3.RegularMatrix(position, scale, rotation));
        System.out.println("drawSelf");
    }

    public void Rotate(float angle){
        this.rotation += angle;
    }

    @Override
    public void Update() {

        velocity.PlusEquals(acceleration.Mul(GameEngine.deltaTime));
        position.PlusEquals(velocity.Mul(GameEngine.deltaTime));

        rotation += rotationVelocity * GameEngine.deltaTime;

        System.out.println("Update position " + position.Print());
        super.Update();
    }

    public void AddVelocity(Vector2D _velocity){
        velocity.PlusEquals(_velocity);
    }

    public void AddAcceleration(Vector2D _acceleration){
        acceleration.PlusEquals(_acceleration);
    }

    public void AddRotationalVelocity(float _velocity){
        rotationVelocity += _velocity;
    }
}
