package engine.graphics;

import engine.math.Matrix4f;
import engine.math.Vector4f;

public class Vertex {
    public final Vector4f position;
    public final Vector4f color;

    public Vertex(Vector4f position, Vector4f color) {
        this.position = position;
        this.color = color;
    }

    public Vertex transform(Matrix4f transform) {
        return new Vertex(transform.Transform(position), color);
    }

    public Vertex perspectiveDivide() {
        double x = position.x / position.w;
        double y = position.y / position.w;
        double z = position.z / position.z;
        double w = position.w;
        Vector4f position = new Vector4f(x, y, z, w);

        return new Vertex(position, color);
    }

    public double triangleArea(Vertex b, Vertex c) {
        double x1 = b.position.x - position.x;
        double y1 = b.position.y - position.y;
        double x2 = c.position.x - position.x;
        double y2 = c.position.y - position.y;

        return (x1 * y2 - x2 * y1) / 2.0;
    }
}
