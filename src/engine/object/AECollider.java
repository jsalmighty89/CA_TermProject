package engine.object;

import java.util.LinkedList;

public class AECollider extends AEObject {
	
	protected int collisionMask;
	
	protected AEGameObject gameObject;		// linked gameobject (parent)
	protected LinkedList<AEGameObject> listCollide;
	
	public AECollider( AEGameObject gameObject) {
		this.gameObject = gameObject;
		listCollide = new LinkedList<AEGameObject>();
	}
	
	public AEGameObject getGameObject() {
		return gameObject;
	}
	
	public boolean performCollision( AECollider other) {
		if( _checkCollide( other)) {
			AEGameObject otherObject = other.getGameObject();
			this.gameObject.onCollide( otherObject);
			
			// trigger collideEnter
			if( listCollide.contains( otherObject) == false) {
				this.gameObject.onCollideEnter( otherObject);
				listCollide.add( otherObject);
			}
		}
		else {
			AEGameObject otherObject = other.getGameObject();
			if( listCollide.contains( otherObject)) {
				this.gameObject.onCollideLeave( otherObject);
				listCollide.remove( otherObject);
			}
		}
		return false;
	}
	
	protected boolean _checkCollide( AECollider other) {
		return false;
	}
}
