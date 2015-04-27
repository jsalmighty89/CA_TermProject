package engine.object;

import java.util.LinkedList;

import engine.base.*;


public class AEGameObject extends AEObject {
	
	protected AEGameObject parent;
	protected LinkedList<AEGameObject> childs;
	
	protected AETransform transform;
	
	public AEGameObject() {
		transform = new AETransform();
		childs = new LinkedList<AEGameObject>();
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
}