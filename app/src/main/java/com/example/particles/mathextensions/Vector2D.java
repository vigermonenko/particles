package com.example.particles.mathextensions;


public class Vector2D {
    float x, y, n;

    Vector2D() {

    }

    public Vector2D(float _x, float _y) {
        x = _x;
        y = _y;
        n = 1;
    }

    public Vector2D(float _x, float _y, float _n) {
        x = _x;
        y = _y;
        n = _n;
    }

    public Vector2D EqualsSelf(Vector2D vector2D) {
        x = vector2D.x;
        y = vector2D.y;
        n = vector2D.n;
        return this;
    }

    public static Vector2D Parse(String string, String regex) {
        Vector2D vector2D = new Vector2D(0,0);
        try {

            String[] values = string.split(regex);
            vector2D.x = Float.parseFloat(values[0]);
            vector2D.y = Float.parseFloat(values[1]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vector2D;
    }

    public Vector2D Mul(float value) {
        return new Vector2D(x * value, y * value, 1);
    }

    public Vector2D(Vector2D vector2D) {
        x = vector2D.x;
        y = vector2D.y;
        n = vector2D.n;
    }

    public String Print() {
        return "[" + x + ";" + y + ";" + n + "]";
    }

    public void PlusEquals(Vector2D vector2D) {
        x += vector2D.x;
        y += vector2D.y;
    }

    public void MinusEquals(Vector2D vector2D) {
        x -= vector2D.x;
        y -= vector2D.y;
    }

    public Vector2D Plus(Vector2D vector2D) {
        Vector2D _vector2D = new Vector2D(vector2D.x + x, vector2D.y + y);
        return _vector2D;
    }

    public Vector2D Minus(Vector2D vector2D) {
        Vector2D _vector2D = new Vector2D(x - vector2D.x, y - vector2D.y);
        return _vector2D;
    }

    public boolean IsEquals(Vector2D vector2D){
        return (x == vector2D.x && y == vector2D.y);
    }

    public float lengthSqr() {
        return x * x + y * y;
    }

    public void MulSelf(float value) {
        x *= value;
        y *= value;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
