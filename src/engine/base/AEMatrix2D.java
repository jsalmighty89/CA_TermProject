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
	
	public static AEMatrix2D createRotationMatrix( float theta) {
		AEMatrix2D matReturn = new AEMatrix2D();		
		matReturn.v[0][0] = (float)Math.cos( theta);
		matReturn.v[0][1] = (float)Math.sin( theta);
		matReturn.v[1][0] = -(float)Math.sin( theta);
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
		matReturn.v[2][0] = x;
		matReturn.v[2][1] = y;
		return matReturn;
	}
	
	public AEMatrix2D multiply( AEMatrix2D a, AEMatrix2D b) {
		AEMatrix2D matReturn = new AEMatrix2D();
		for( int i=0; i<rows; i++)
			for( int j=0; j<cols; j++)
				for( int k=0; k<cols; k++)
					matReturn.v[i][j] = a.v[i][k] * b.v[j][k];
		return matReturn;
	}
}
