package com.example.particles.mathextensions;


public class Extensions {
    public static float Cos(float value) {
        return (float) Math.cos((double) value);
    }

    public static float Sin(float value) {
        return (float) Math.sin((double) value);
    }

    public static float Lerp(float v0, float v1, float t) {
        return (1 - t) * v0 + t * v1;
    }

    public static Vector2D Lerp(Vector2D v0, Vector2D v1, float t) {
        return new Vector2D(Lerp(v0.x, v1.x, t), Lerp(v0.y, v1.y, t), 1);
    }
}
