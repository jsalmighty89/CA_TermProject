package engine.object;

public class AECollider extends AEObject {
	
	protected int collisionMask;
	
	protected AEGameObject gameObject;		// linked gameobject (parent)
	
	public AECollider( AEGameObject gameObject) {
		this.gameObject = gameObject;
	}
	
	public AEGameObject getGameObject() {
		return gameObject;
	}
	
	public boolean performCollision( AECollider other) {
		if( _checkCollide( other)) {
			this.gameObject.onCollide( other.getGameObject());
		}
		return false;
	}
	
	protected boolean _checkCollide( AECollider other) {
		return false;
	}
}
