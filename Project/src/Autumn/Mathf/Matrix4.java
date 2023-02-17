package Autumn.Mathf;

import java.nio.FloatBuffer;

public class Matrix4 {

    private float[][] m = new float[4][4];

    public Matrix4() {
        set();
    }

    public final void set() {

        m[0][0] = 1; m[0][1] = 0; m[0][2] = 0; m[0][3] = 0;
        m[1][0] = 0; m[1][1] = 1; m[1][2] = 0; m[1][3] = 0;
        m[2][0] = 0; m[2][1] = 0; m[2][2] = 1; m[2][3] = 0;
        m[3][0] = 0; m[3][1] = 0; m[3][2] = 0; m[3][3] = 1;

    }

    public void get(FloatBuffer b) {

        b.put(m[0][0]).put(m[0][1]).put(m[0][2]).put(m[0][3]);
        b.put(m[1][0]).put(m[1][1]).put(m[1][2]).put(m[1][3]);
        b.put(m[2][0]).put(m[2][1]).put(m[2][2]).put(m[2][3]);
        b.put(m[3][0]).put(m[3][1]).put(m[3][2]).put(m[3][3]);

        b.flip();
    }

    public static Matrix4 Ortho(float l, float r, float b, float t, float n, float f) {
        Matrix4 m = new Matrix4();

        m.m[0][0] = 2f / (r-l);
        m.m[1][1] = 2f / (t-b);
        m.m[2][2] = 2f / (f-n);

        m.m[3][0] = -(r+l) / (r-l);
        m.m[3][1] = -(t+b) / (t-b);
        m.m[3][2] = -(f+n) / (f-n);

        return m;
    }
}
