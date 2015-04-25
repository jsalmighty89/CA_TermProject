package engine.base;

public class AEMatrix2D extends AERoot {
	public static final int cols = 3;
	public static final int rows = 3;
	public float[][] v;
	
	public AEMatrix2D() {
		v = new float[rows][cols];
		
		this.setIdentity();
	}
	
	protected void _fillZero() {
		for( int i=0; i<rows; i++) 
			for( int j=0; j<cols; j++)
				v[i][j] = 0.0f;
	}
	
	public void setIdentity() {
		_fillZero();
		v[0][0] = 1.0f;
		v[1][1] = 1.0f;
		v[2][2] = 1.0f;
	}
	public void copyFrom( AEMatrix2D from) {
		for( int i=0; i<rows; i++)
			for( int j=0; j<cols; j++)
				this.v[i][j] = from.v[i][j];
	}
	
	public static AEMatrix2D createRotationMatrix( float theta) {
		AEMatrix2D matReturn = new AEMatrix2D();		
		matReturn.v[0][0] = (float)Math.cos( theta);
		matReturn.v[0][1] = -(float)Math.sin( theta);
		matReturn.v[1][0] = (float)Math.sin( theta);
		matReturn.v[1][1] = (float)Math.cos( theta);		
		return matReturn;
	}
	public static AEMatrix2D createScaleMatrix( float scale) {
		AEMatrix2D matReturn = new AEMatrix2D();		
		matReturn.v[0][0] = scale;
		matReturn.v[1][1] = scale;		
		return matReturn;
	}
	public static AEMatrix2D createScaleMatrix( float x, float y) {
		AEMatrix2D matReturn = new AEMatrix2D();		
		matReturn.v[0][0] = x;
		matReturn.v[1][1] = y;		
		return matReturn;
	}
	public static AEMatrix2D createTranslateMatrix( float x, float y) {
		AEMatrix2D matReturn = new AEMatrix2D();
		matReturn.v[0][2] = x;
		matReturn.v[1][2] = y;
		return matReturn;
	}
	
	// just add translate
	public void setTranslate( float x, float y) {
		v[0][2] = x;
		v[1][2] = y;
	}
	
	// multiply
	public static AEMatrix2D multiply( AEMatrix2D a, AEMatrix2D b) {
		AEMatrix2D matReturn = new AEMatrix2D();
		
		for( int i=0; i<rows; i++) {
			for( int j=0; j<cols; j++) {
				float value = 0;
				for( int k=0; k<cols; k++) {
					 value += a.v[i][k] * b.v[k][j]; 
				}
				matReturn.v[i][j] = value;
			}
		}
		
		return matReturn;
	}	
	public void multiply( AEMatrix2D matrix) {
		AEMatrix2D matReturn = multiply( this, matrix);		
		this.copyFrom( matReturn);
	}
	
	// add
	public static AEMatrix2D add( AEMatrix2D a, AEMatrix2D b) {
		AEMatrix2D matReturn = new AEMatrix2D();		
		for( int i=0; i<rows; i++)
			for( int j=0; j<cols; j++)
				matReturn.v[i][j] = a.v[i][j] + b.v[i][j];		
		return matReturn;
	}
	public void add( AEMatrix2D matrix) {
		AEMatrix2D matReturn = add( this, matrix);
		this.copyFrom( matrix);		
	}
}
