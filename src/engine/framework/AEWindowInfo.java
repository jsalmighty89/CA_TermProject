package engine.framework;

import engine.object.AEObject;

public class AEWindowInfo extends AEObject {
	protected float width;
	protected float height;
	
	public AEWindowInfo() {
		
	}
	
	public void setSize( float width, float height) {
		this.width = width;
		this.height = height;
	}
	
	public float getWidth() {
		return width;
	}
	public float getHeight() {
		return height;
	}
	
}
