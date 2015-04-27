package engine.framework;

import java.util.LinkedList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import engine.object.AEGameObject;
import engine.object.AEObject;
import engine.render.AESceneGraph;

public class AEFramework extends AEObject{
	// level
	protected LinkedList<AELevel> listLevel;
	protected AELevel currentActiveLevel;

	// singleton instance
	private static AEFramework _instance = null;
	public static AEFramework getInstance() {
		if( _instance == null)
			_instance = new AEFramework();
		return _instance;
	}
	
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
	public void setLevel( AELevel level) {
		if( listLevel.contains( level) == false) {
			// critical error. not registered level.
			return;
		}
		
		if( currentActiveLevel != null) {
			currentActiveLevel.releaseLevel();
		}
		
		currentActiveLevel = level;
		level.initLevel();
	}
	public void setLevel( String levelName) {
		AELevel level = getLevel( levelName);
		setLevel( level);
	}
	
	// SceneGraph-related
	public void addToSceneRoot( AEGameObject object) {
		if( currentActiveLevel != null)
			currentActiveLevel.addToSceneRoot( object);
	}
	public void removeFromScene( AEGameObject object) {
		// TODO is this right?
		object.getParent().removeChild( object);
		object.setParent( null);
	}
	
	
	public void update(float deltaTime, GameContainer gc) {
		if( currentActiveLevel != null) {
			currentActiveLevel.update( deltaTime, gc);
		}
	}
	
	public void render( Graphics graphic) {
		if( currentActiveLevel != null) {
			currentActiveLevel.getSceneGraph().renderSceneGraph( graphic);
		}
	}
}
