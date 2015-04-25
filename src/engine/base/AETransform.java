package engine.base;

public class AETransform extends AERoot {
	
	// local
	protected AEMatrix2D matrixLocal;
	protected AEVector scale;
	protected float rotation;
	protected AEVector position;
	
	protected AEMatrix2D matrix;
	
	protected boolean needUpdateBuffer;
	
	public AETransform() {
		matrixLocal = new AEMatrix2D();
		matrix = new AEMatrix2D();
		
		scale = new AEVector( 1.0f, 1.0f);
		rotation = 0.0f;
		position = new AEVector( 0.0f, 0.0f);
		updateMatrix();
		
		needUpdateBuffer = true;
	}
	
	public AEMatrix2D getLocalMatrix() {
		return matrixLocal;
	}
	public AEMatrix2D getMatrix() {
		return matrix;
	}
	
	public void setScale( float scale) {
		setScale( new AEVector( scale, scale));
	}
	public void setScale( AEVector scale) {
		this.scale = scale;
	}
	public void setRotation( float rotation) {
		this.rotation = rotation;
	}
	public void setPosition( AEVector position) {
		this.position = position;
	}
	public AEVector getLocalScale() {
		return scale;
	}
	public float getLocalRotation() {
		return rotation;
	}
	public AEVector getLocalPosition() {
		return position;
	}
	
	
	public AEVector getScale() {
		float sx = (float)Math.sqrt( matrix.v[0][0] * matrix.v[0][0] + matrix.v[1][0] * matrix.v[1][0]);
		float sy = (float)Math.sqrt( matrix.v[0][1] * matrix.v[0][1] + matrix.v[1][1] * matrix.v[1][1]);		
		return new AEVector( sx, sy);
	}
	public float getRotation() {
		//return (float)Math.atan( matrix.v[1][0] / matrix.v[1][1]);
		return (float)Math.atan2( matrix.v[1][0], matrix.v[1][1]);
	}
	public AEVector getPosition() {
		return new AEVector( matrix.v[0][2], matrix.v[1][2]);
	}
	
	
	public void updateMatrix() {
		AEMatrix2D matrixScale = AEMatrix2D.createScaleMatrix( scale.x, scale.y);
		AEMatrix2D matrixRotation = AEMatrix2D.createRotationMatrix( rotation);
		//AEMatrix2D matrixTranslate = AEMatrix2D.createTranslateMatrix( position.x, position.y);
		
		matrixLocal.copyFrom( matrixScale);
		matrixLocal.multiply( matrixRotation);
		matrixLocal.setTranslate( position.x, position.y);
		
		needUpdateBuffer = false;
	}
	
	public void updateParentMatrix( AETransform parentTransform) {
		if( parentTransform == null)
			matrix.copyFrom( matrixLocal);
		else
			matrix = AEMatrix2D.multiply( parentTransform.getMatrix(), matrixLocal);
	}
}
