package engine.object;

import java.util.LinkedList;

import engine.base.*;


public class AEGameObject extends AEObject {
	
	protected AEGameObject parent;
	protected LinkedList<AEGameObject> childs;
	
	protected AETransform transform;
	
	public AEGameObject() {
		transform = new AETransform();
	}
	
	// parent
	public void setParent( AEGameObject parent) {
		this.parent = parent;
	}
	public AEGameObject getParent() {
		return parent;
	}
	// child
	public void addChild( AEGameObject object) {
		if( childs.contains( object))
			return;
		
		childs.add( object);
	}
	public void removeChild( AEGameObject object) {
		if( childs.contains( object) == false)
			return;
		
		childs.remove( object);
	}
}