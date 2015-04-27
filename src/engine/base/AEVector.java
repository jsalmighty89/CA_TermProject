package engine.base;

public class AEVector extends AERoot {
	public float x;
	public float y;
	public float z;
	
	public AEVector() {
		this.set( 0.0f, 0.0f, 0.0f);
	}
	public AEVector( float x, float y, float z) {
		this.set( x, y, z);
	}
	public AEVector( float x, float y) {
		this.set( x, y);
	}
	
	public void set( float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	public void set( float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public static AEVector add( AEVector u, AEVector v) {
		return new AEVector( u.x + v.x, u.y + v.y, u.z + v.z);
	}
	public void add( AEVector v) {
		x += v.x;
		y += v.y;
		z += v.z;
	}
	public static AEVector sub( AEVector u, AEVector v) {
		return new AEVector( u.x - v.x, u.y - v.y, u.z - v.z);
	}
	public void sub( AEVector v) {
		x -= v.x;
		y -= v.y;
		z -= v.z;
	}
	public AEVector cross() {
		return new AEVector();
	}
	public static AEVector multiply( AEVector u, float scalar) {
		AEVector returnVector = new AEVector();
		returnVector.x = u.x * scalar;
		returnVector.y = u.y * scalar;
		returnVector.z = u.z * scalar;
		return returnVector;
	}
	public void multiply( float scalar) {
		x *= scalar;
		y *= scalar;
		z *= scalar;
	}
	public float length() {
		return (float)Math.sqrt( lengthSqrt());
	}
	public float lengthSqrt() {
		return (x*x + y*y + z*z);
	}
	
	public static AEVector normalize( AEVector u) {
		AEVector returnVector = new AEVector();
		float length = u.length();
		returnVector.x = u.x / length;
		returnVector.y = u.y / length;
		returnVector.z = u.z / length;
		return returnVector;
	}
	public void normalize() {
		float length = this.length();
		x = x / length;
		y = y / length;
		z = z / length;
	}
}
