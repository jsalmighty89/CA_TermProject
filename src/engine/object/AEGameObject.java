package engine.object;

import java.util.LinkedList;

import engine.base.*;
import engine.render.AESprite;


public class AEGameObject extends AEObject {
	
	protected AEGameObject parent;
	protected LinkedList<AEGameObject> childs;
	
	protected AETransform transform;
	
	// collider
	protected AECollider collider;
	
	// sprite
	protected AESprite sprite;
	protected boolean isVisible;
	
	// physics
	// additional force
	protected AEVector forceMomentum;
	
	
	public AEGameObject() {
		transform = new AETransform();
		childs = new LinkedList<AEGameObject>();
		collider = null;
		sprite = null;
		isVisible = false;
		
		forceMomentum = new AEVector( 0.0f, 0.0f);
	}
	
	public AETransform getTransform() {
		return transform;
	}
	
	// parent
	public void setParent( AEGameObject parent) {
		setParent( parent, false);
	}
	protected void setParent( AEGameObject parent, boolean internalCall) {
		this.parent = parent;
		if( internalCall == false && parent != null)
			this.parent.addChild( this, true);
	}
	public AEGameObject getParent() {
		return parent;
	}
	// child
	public void addChild( AEGameObject object) {
		addChild( object, false);
	}
	protected void addChild( AEGameObject object, boolean internalCall) {
		if( childs.contains( object))
			return;		
		childs.add( object);
		
		if( internalCall == false && object != null)
			object.setParent( this, true);
	}
	public void removeChild( AEGameObject object) {
		if( childs.contains( object) == false)
			return;
		childs.remove( object);
	}
	public LinkedList<AEGameObject> getChildList() {
		return childs;
	}
	
	
	// collision
	public boolean hasCollider() {
		return ( this.collider != null);
	}
	public AECollider getCollider() {
		return collider;
	}
	public void onCollide( AEGameObject collider) {
		
	}
	public void onCollideEnter( AEGameObject collider) {
		
	}
	public void onCollideLeave( AEGameObject collider) {
		
	}
	final public void createCollider( float radius) {
		collider = new AEColliderSphere( this, radius);
	}
	
	// sprite
	public boolean hasSprite() {
		return( this.sprite != null);
	}
	public AESprite getSprite() {
		return sprite;
	}
	public boolean isVisible() {
		return isVisible;
	}
	public void setVisible( boolean visible) {
		isVisible = visible;
	}
	public void createSprite( String fileName) {
		createSprite( fileName, true);
	}
	public void createSprite( String fileName, boolean visible) {
		sprite = new AESprite( fileName);
		isVisible = visible;
	}
	
	// force
	public void addForce( AEVector force) {
		forceMomentum.add( force);
	}
	public void addForce( AEVector direction, float power) {
		addForce( AEVector.multiply( direction, power));
	}
	public boolean forceMomentumRemain() {
		if( forceMomentum.x == 0.0f && forceMomentum.y == 0.0f && forceMomentum.z == 0.0f)
			return false;
		
		return true;
	}
	public void updateForce( float deltaTime) {
		if( forceMomentumRemain()) {
			AEVector zero = new AEVector( 0.0f, 0.0f);
			forceMomentum = AEVector.lerp( forceMomentum, zero, deltaTime * 3.0f);
			final float EPS = 500.0f;
			if( forceMomentum.lengthSqrt() <= EPS) {
				forceMomentum = zero;
			}
			transform.setPosition( AEVector.add( transform.getLocalPosition(), AEVector.multiply( forceMomentum, deltaTime)));
		}
	}
}