package engine.object;

import engine.base.AERoot;

public class AEObject extends AERoot {
	protected String objectName;
	
	public AEObject() {
		
	}
	public AEObject( String name) {
		objectName = name;
	}
	
	public void setObjectName( String objectName) {
		this.objectName = objectName;
	}
	public String getObjectName() {
		return objectName;
	}
	
	public void Update() {
		
	}
}