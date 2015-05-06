package engine.object;

import engine.base.AEVector;
import engine.framework.AEFramework;

public class AECamera2D extends AEGameObject {
	
	protected float zoom = 1.0f;
	
	public AECamera2D() {
		
	}
	
	public void setZoom( float size) {
		this.zoom = zoom;
	}
	public float getZoom() {
		return zoom;
	}
	
	public AEVector getWorldFromScreen( AEVector screenPos) {
		float offsetX = AEFramework.getInstance().getWindowInfo().getWidth();
		float offsetY = AEFramework.getInstance().getWindowInfo().getHeight();
		
		AEVector cameraPos = this.getTransform().getPosition();
		AEVector worldPos = AEVector.add( screenPos, cameraPos);
		worldPos.x -= offsetX * 0.5f;
		worldPos.y -= offsetY * 0.5f;
		return worldPos;
	}
	
}
