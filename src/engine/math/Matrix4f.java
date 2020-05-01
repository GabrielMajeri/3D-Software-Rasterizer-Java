package engine.math;

public class Matrix4f {
    public double[][] data;

    public Matrix4f() {
        data = new double[4][4];
    }

    public Matrix4f InitIdentity() {
        data[0][0] = 1;
        data[0][1] = 0;
        data[0][2] = 0;
        data[0][3] = 0;
        data[1][0] = 0;
        data[1][1] = 1;
        data[1][2] = 0;
        data[1][3] = 0;
        data[2][0] = 0;
        data[2][1] = 0;
        data[2][2] = 1;
        data[2][3] = 0;
        data[3][0] = 0;
        data[3][1] = 0;
        data[3][2] = 0;
        data[3][3] = 1;

        return this;
    }

    public Matrix4f initScreenSpaceTransform(double halfWidth, double halfHeight) {
        data[0][0] = halfWidth;
        data[0][1] = 0;
        data[0][2] = 0;
        data[0][3] = halfWidth;
        data[1][0] = 0;
        data[1][1] = -halfHeight;
        data[1][2] = 0;
        data[1][3] = halfHeight;
        data[2][0] = 0;
        data[2][1] = 0;
        data[2][2] = 1;
        data[2][3] = 0;
        data[3][0] = 0;
        data[3][1] = 0;
        data[3][2] = 0;
        data[3][3] = 1;

        return this;
    }

    public Matrix4f initTranslation(double x, double y, double z) {
        data[0][0] = 1;
        data[0][1] = 0;
        data[0][2] = 0;
        data[0][3] = x;
        data[1][0] = 0;
        data[1][1] = 1;
        data[1][2] = 0;
        data[1][3] = y;
        data[2][0] = 0;
        data[2][1] = 0;
        data[2][2] = 1;
        data[2][3] = z;
        data[3][0] = 0;
        data[3][1] = 0;
        data[3][2] = 0;
        data[3][3] = 1;

        return this;
    }

    public Matrix4f initRotation(double x, double y, double z) {
        Matrix4f rx = new Matrix4f();
        Matrix4f ry = new Matrix4f();
        Matrix4f rz = new Matrix4f();

        rz.data[0][0] = Math.cos(z);
        rz.data[0][1] = -Math.sin(z);
        rz.data[0][2] = 0;
        rz.data[0][3] = 0;
        rz.data[1][0] = Math.sin(z);
        rz.data[1][1] = Math.cos(z);
        rz.data[1][2] = 0;
        rz.data[1][3] = 0;
        rz.data[2][0] = 0;
        rz.data[2][1] = 0;
        rz.data[2][2] = 1;
        rz.data[2][3] = 0;
        rz.data[3][0] = 0;
        rz.data[3][1] = 0;
        rz.data[3][2] = 0;
        rz.data[3][3] = 1;

        rx.data[0][0] = 1;
        rx.data[0][1] = 0;
        rx.data[0][2] = 0;
        rx.data[0][3] = 0;
        rx.data[1][0] = 0;
        rx.data[1][1] = Math.cos(x);
        rx.data[1][2] = -Math.sin(x);
        rx.data[1][3] = 0;
        rx.data[2][0] = 0;
        rx.data[2][1] = Math.sin(x);
        rx.data[2][2] = Math.cos(x);
        rx.data[2][3] = 0;
        rx.data[3][0] = 0;
        rx.data[3][1] = 0;
        rx.data[3][2] = 0;
        rx.data[3][3] = 1;

        ry.data[0][0] = Math.cos(y);
        ry.data[0][1] = 0;
        ry.data[0][2] = -Math.sin(y);
        ry.data[0][3] = 0;
        ry.data[1][0] = 0;
        ry.data[1][1] = 1;
        ry.data[1][2] = 0;
        ry.data[1][3] = 0;
        ry.data[2][0] = Math.sin(y);
        ry.data[2][1] = 0;
        ry.data[2][2] = Math.cos(y);
        ry.data[2][3] = 0;
        ry.data[3][0] = 0;
        ry.data[3][1] = 0;
        ry.data[3][2] = 0;
        ry.data[3][3] = 1;

        data = rz.Mul(ry.Mul(rx)).data;

        return this;
    }

    public Matrix4f initPerspective(double fov, double aspectRatio, double zNear, double zFar) {
        double tanHalfFOV = Math.tan(fov / 2);
        double zRange = zNear - zFar;

        data[0][0] = 1.0f / (tanHalfFOV * aspectRatio);
        data[0][1] = 0;
        data[0][2] = 0;
        data[0][3] = 0;
        data[1][0] = 0;
        data[1][1] = 1.0f / tanHalfFOV;
        data[1][2] = 0;
        data[1][3] = 0;
        data[2][0] = 0;
        data[2][1] = 0;
        data[2][2] = (-zNear - zFar) / zRange;
        data[2][3] = 2 * zFar * zNear / zRange;
        data[3][0] = 0;
        data[3][1] = 0;
        data[3][2] = 1;
        data[3][3] = 0;


        return this;
    }

    public Matrix4f initRotation(Vector4f forward, Vector4f up, Vector4f right) {
        Vector4f f = forward;
        Vector4f r = right;
        Vector4f u = up;

        data[0][0] = r.x;
        data[0][1] = r.y;
        data[0][2] = r.z;
        data[0][3] = 0;
        data[1][0] = u.x;
        data[1][1] = u.y;
        data[1][2] = u.z;
        data[1][3] = 0;
        data[2][0] = f.x;
        data[2][1] = f.y;
        data[2][2] = f.z;
        data[2][3] = 0;
        data[3][0] = 0;
        data[3][1] = 0;
        data[3][2] = 0;
        data[3][3] = 1;

        return this;
    }

    public Vector4f Transform(Vector4f r) {
        return new Vector4f(data[0][0] * r.x + data[0][1] * r.y + data[0][2] * r.z + data[0][3] * r.w,
                data[1][0] * r.x + data[1][1] * r.y + data[1][2] * r.z + data[1][3] * r.w,
                data[2][0] * r.x + data[2][1] * r.y + data[2][2] * r.z + data[2][3] * r.w,
                data[3][0] * r.x + data[3][1] * r.y + data[3][2] * r.z + data[3][3] * r.w);
    }

    public Matrix4f Mul(Matrix4f r) {
        Matrix4f res = new Matrix4f();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                res.data[i][j] = data[i][0] * r.data[0][j] +
                        data[i][1] * r.data[1][j] +
                        data[i][2] * r.data[2][j] +
                        data[i][3] * r.data[3][j];
            }
        }

        return res;
    }
}