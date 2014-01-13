package net.jcip.examples;

import net.jcip.annotations.*;

/**
 * MutablePoint
 * <p/>
 * Mutable Point class similar to java.awt.Point
 *
 * @author Brian Goetz and Tim Peierls
 */
@NotThreadSafe
public class MutablePoint {
    public int x, y;

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public MutablePoint() {
        x = 0;
        y = 0;
    }

    public MutablePoint(MutablePoint p) {
        this.x = p.x;
        this.y = p.y;
    }

	@Override
	public String toString() {
		return "Pt{" +
				"x=" + x +
				", y=" + y +
				'}';
	}
}
