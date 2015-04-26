package engine.framework;

import java.util.LinkedList;

import org.newdawn.slick.Graphics;

import engine.object.AEGameObject;
import engine.object.AEObject;
import engine.render.AESceneGraph;

public class AEFramework extends AEObject{
	// level
	protected LinkedList<AELevel> listLevel;
	protected AELevel currentActiveLevel;
	// scenegraph
	protected AESceneGraph sceneGraph;
	
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
		sceneGraph = new AESceneGraph();
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
	
	// SceneGraph-related
	public void addToSceneRoot( AEGameObject object) {
		sceneGraph.getRoot().addChild( object);
	}
	
	
	public void update() {
		sceneGraph.updateSceneGraph();
	}
	
	public void render( Graphics graphic) {
		sceneGraph.renderSceneGraph( graphic);
	}
}
