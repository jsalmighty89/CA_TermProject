package engine.framework;

import java.util.LinkedList;

import engine.object.AEObject;

public class AEFramework extends AEObject{
	protected LinkedList<AELevel> listLevel;
	protected AELevel currentActiveLevel;
	
	public AEFramework() {
		listLevel = new LinkedList<AELevel>();
		currentActiveLevel = null;
	}
	
	public void addLevel( AELevel level) {
		if( listLevel.contains( level) == false)
			listLevel.add( level);
	}
	public void removeLevel( AELevel level) {
		if( listLevel.contains( level))
			listLevel.remove( level);
	}
	public AELevel getLevel( String name) {
		for( AELevel level : listLevel) {
			if( level.getObjectName() == name)
				return level; 
		}
		
		return null;
	}
	
	
}
