package com.example.particles;


import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

import com.example.particles.mathextensions.Matrix3x3;
import com.example.particles.mathextensions.Vector2D;

public class Mesh {
    public Vector2D[] vertices;
    public int[] indices;


    Path path = new Path();

    public Mesh() {

    }

    public Mesh(Vector2D[] _vertices, int[] _indices) {
        vertices = _vertices;
        indices = _indices;

        if (indices.length % 3 != 0) {
            System.out.println("indices should divide by 3");
        }
    }

    public void Draw(Canvas canvas, Paint paint, Matrix3x3 matrix3x3) {
        int polygonCount = indices.length / 3;

        for (int i = 0; i < polygonCount; i++) {
            Vector2D verticy1 = matrix3x3.Mul(vertices[indices[i * 3]]);
            Vector2D verticy2 = matrix3x3.Mul(vertices[indices[i * 3 + 1]]);
            Vector2D verticy3 = matrix3x3.Mul(vertices[indices[i * 3 + 2]]);

            path.reset();
            path.moveTo(verticy1.getX(), verticy1.getY());
            path.lineTo(verticy2.getX(), verticy2.getY());
            path.lineTo(verticy3.getX(), verticy3.getY());
            path.lineTo(verticy1.getX(), verticy1.getY());
            path.close();

            canvas.drawPath(path, paint);
        }
    }
}
