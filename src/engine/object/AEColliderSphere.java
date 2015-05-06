package engine.object;

import engine.base.AEVector;

public class AEColliderSphere extends AECollider {
	
	protected float radius;
	protected float radiusSq;
	
	public AEColliderSphere( AEGameObject gameObject, float radius) {
		super( gameObject);
		setRadius( radius);
	}
	
	public void setRadius( float radius) {
		this.radius = radius;
		this.radiusSq = radius * radius;
	}
	public float getRadius() { 
		return radius;
	}
	public float getRadiusSq() {
		return radiusSq;
	}
	
	protected boolean _checkCollide( AECollider other) {
		if( other instanceof AEColliderSphere) {
			AEColliderSphere otherSphere = (AEColliderSphere)other;
			AEVector positionA = this.gameObject.getTransform().getPosition();
			AEVector positionB = otherSphere.getGameObject().getTransform().getPosition();
			AEVector diff = AEVector.sub( positionB, positionA);
			float lengthSq = diff.lengthSqrt();
			
			float scaleA = this.gameObject.getTransform().getScale();
			float scaleB = otherSphere.getGameObject().getTransform().getScale();
			float radiusSqA = this.radiusSq * scaleA * scaleA;
			float radiusSqB = otherSphere.getRadiusSq() * scaleB * scaleB;
			if( lengthSq < ( radiusSqA + radiusSqB)) {
				return true;
			}
		}
		return false;
	}
}
