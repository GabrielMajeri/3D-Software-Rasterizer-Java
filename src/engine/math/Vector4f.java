package engine.math;

import org.jetbrains.annotations.NotNull;

public class Vector4f {
    public final double x;
    public final double y;
    public final double z;
    public final double w;

    public Vector4f(double x, double y, double z, double w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

	/**
	 * Returns the length of this vector.
	 * @return
	 */
	public double getLength() {
        return Math.sqrt(x * x + y * y + z * z + w * w);
    }

	/**
	 * Returns a vector of length one with the same direction as this one.
	 */
	public Vector4f normalized() {
		double length = getLength();

		return new Vector4f(x / length, y / length, z / length, w / length);
	}

	/**
	 * Computes the dot product of this vector with another one
	 */
	public double dot(@NotNull Vector4f other) {
        return (this.x * other.x) + (this.y * other.y) + (this.z * other.z) + (this.w * other.w);
    }

	/**
	 * Computes the cross product of this vector with another one.
	 */
	public Vector4f cross(@NotNull Vector4f other) {
        double x = this.y * other.z - this.z * other.y;
        double y = z * other.x - this.x * other.z;
        double z = x * other.y - this.y * other.x;

        return new Vector4f(x, y, z, 0);
    }
}