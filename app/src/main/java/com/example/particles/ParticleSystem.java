package com.example.particles;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.particles.mathextensions.Matrix3x3;
import com.example.particles.mathextensions.Vector2D;

import java.util.Arrays;
import java.util.Random;

import static com.example.particles.mathextensions.Extensions.Lerp;


public class ParticleSystem extends Entity {

    float[] angle;
    float[] rotSpeedAngle;
    Vector2D[] scale;
    Vector2D[] x;       //текущее положение
    Vector2D[] oldx;    //предыдущее положение
    Vector2D[] a;       //суммарная сила(ускорение)
    Vector2D gravity;

    public int NUM_PARTICLES = 0;

    public Mesh mesh;

    public Paint paint;

    public ParticleSystem(Paint paint, Vector2D _gravity) {
        this.paint = paint;
        gravity = _gravity;
    }

    void SetParticles(int particles, Vector2D _scale, Vector2D startSpeed, Vector2D acceleration, float maxRotation) {
        NUM_PARTICLES = particles;
        gravity = acceleration;
        Random random = new Random();


        scale = new Vector2D[particles];
        for (int i = 0; i < scale.length; i++) {
            scale[i] = new Vector2D(_scale);
        }

        x = new Vector2D[particles];
        Vector2D inverseStartSpeed = new Vector2D(-startSpeed.getX(), startSpeed.getY());
        for (int i = 0; i < x.length; i++) {
            x[i] = Lerp(startSpeed, inverseStartSpeed, random.nextFloat());
        }

        oldx = new Vector2D[particles];
        for (int i = 0; i < oldx.length; i++) {
            oldx[i] = new Vector2D(0, 0);
        }

        a = new Vector2D[particles];
        for (int i = 0; i < a.length; i++) {
            a[i] = new Vector2D(gravity);
        }
        angle = new float[particles];
        Arrays.fill(angle, 0);
        rotSpeedAngle = new float[particles];
        for (int i = 0; i < rotSpeedAngle.length; i++) {
            rotSpeedAngle[i] = Lerp(0, maxRotation, random.nextFloat());
        }
    }

    @Override
    public void Update() {
        AccumulateForces();
        Verlet();
        super.Update();
    }

    @Override
    public void drawSelf(Canvas canvas) {
        if (x != null) {

            for (int i = 0; i < x.length; i++) {
                mesh.Draw(canvas, paint, Matrix3x3.RegularMatrix(position.Plus(x[i]), scale[i], angle[i]));
            }
        }

        super.drawSelf(canvas);
    }

    public void Verlet() {
        for (int i = 0; i < NUM_PARTICLES; i++) {
            Vector2D _x = x[i];
            Vector2D temp = new Vector2D(_x);
            Vector2D _oldx = oldx[i];
            Vector2D _a = a[i];

            Vector2D at2 = _a.Mul(GameEngine.deltaTime * GameEngine.deltaTime);
            Vector2D dx = _x.Minus(_oldx);

            _x.PlusEquals(dx.Plus(at2));
            oldx[i] = temp;

            angle[i] += rotSpeedAngle[i] * GameEngine.deltaTime;
        }
    }

    public void AccumulateForces() {
        if (a != null) {

            for (int i = 0; i < a.length; i++) {
                a[i].EqualsSelf(gravity);
            }
        }

    }

    public void SatisfyConstrains() {
    }
}

