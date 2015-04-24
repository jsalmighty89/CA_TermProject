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
	public AEVector( float x, float z) {
		this.set( x, z);
	}
	
	public void set( float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	public void set( float x, float z) {
		this.x = x;
		this.z = z;
	}
	
	public AEVector add( AEVector a, AEVector b) {
		return new AEVector( a.x + b.x, a.y + b.y, a.z + b.z);
	}
	public AEVector sub( AEVector a, AEVector b) {
		return new AEVector( a.x - b.x, a.y - b.y, a.z - b.z);
	}
	public AEVector cross( ) {
		return new AEVector();
	}
	public AEVector mul() {
		return new AEVector();
	}
	public float length() {
		return 0.0f;
	}
	public float lengthSqrt() {
		return 0.0f;
	}
}
