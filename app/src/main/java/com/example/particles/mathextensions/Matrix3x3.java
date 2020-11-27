package com.example.particles.mathextensions;


import static com.example.particles.mathextensions.Extensions.Cos;
import static com.example.particles.mathextensions.Extensions.Sin;


public class Matrix3x3 {
    public float values[][];

    public Matrix3x3() {
        values = new float[3][3];
    }

    public Matrix3x3 Mul(Matrix3x3 matrix3x3) {
        Matrix3x3 result = new Matrix3x3();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                result.values[i][j] = 0;
                for (int k = 0; k < 3; k++) {
                    result.values[i][j] += values[i][k] * matrix3x3.values[k][j];
                }
            }
        }
        return result;
    }

    public Matrix3x3 Mul(float value) {
        Matrix3x3 result = new Matrix3x3();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                result.values[i][j] =values[i][j] * value;
            }
        }
        return result;
    }

    public Matrix3x3 MulSelf(float value) {
        Matrix3x3 result = new Matrix3x3();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                values[i][j] *= value;
            }
        }
        return this;
    }

    public static Matrix3x3 SingleMatrix(float value) {

        Matrix3x3 matrix3x3 = new Matrix3x3();
        matrix3x3.values[0][0] = value;
        matrix3x3.values[1][1] = value;
        matrix3x3.values[2][2] = value;

        return matrix3x3;
    }

    public static Matrix3x3 ScaleMatrix(Vector2D scale) {
        Matrix3x3 matrix3x3 = new Matrix3x3();

        matrix3x3.values[0][0] = scale.x;
        matrix3x3.values[1][1] = scale.y;
        matrix3x3.values[2][2] = 1;

        return matrix3x3;

    }

    public static Matrix3x3 TranslationMatrix(Vector2D translation) {
        Matrix3x3 matrix3x3 = new Matrix3x3();

        matrix3x3.values[2][0] = translation.x;
        matrix3x3.values[2][1] = translation.y;
        matrix3x3.values[2][2] = 1;

        return matrix3x3;
    }

    public Vector2D Mul(Vector2D v){
        float x_new, y_new;

        x_new = v.x* values[0][0] + v.y * values[1][0] + values[2][0];
        y_new = v.x* values[0][1] + v.y * values[1][1] + values[2][1];

        return new Vector2D(x_new,y_new);
    }

    public static Matrix3x3 RotationMatrix(float angle) {
        Matrix3x3 matrix3x3 = new Matrix3x3();

        matrix3x3.values[0][0] = Cos(angle);
        matrix3x3.values[0][1] = -Sin(angle);
        matrix3x3.values[1][0] = Sin(angle);
        matrix3x3.values[1][1] = Cos(angle);
        matrix3x3.values[2][2] = 1;

        return matrix3x3;
    }

    public static Matrix3x3 RegularMatrix(Vector2D position, Vector2D scale, float angle) {
        Matrix3x3 matrix3x3 = new Matrix3x3();

        matrix3x3.values[0][0] = scale.x * Cos(angle);
        matrix3x3.values[0][1] = scale.x * -Sin(angle);
        matrix3x3.values[1][0] = scale.y * Sin(angle);
        matrix3x3.values[1][1] = scale.y * Cos(angle);

        matrix3x3.values[2][0] = position.x;
        matrix3x3.values[2][1] = position.y;

        matrix3x3.values[2][2] = 1;
        return matrix3x3;
    }
}
