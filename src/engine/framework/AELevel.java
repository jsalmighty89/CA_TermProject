package engine.framework;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import engine.object.AEGameObject;
import engine.object.AEObject;
import engine.render.AESceneGraph;


public class AELevel extends AEObject {
	protected AESceneGraph sceneGraph;
	
	public AELevel() {
		sceneGraph = new AESceneGraph();
	}
	
	// SceneGraph-related
	public AESceneGraph getSceneGraph() {
		return sceneGraph;
	}
	public void addToSceneRoot( AEGameObject object) {
		sceneGraph.getRoot().addChild( object);
	}
	public void render( Graphics graphic) {
		sceneGraph.renderSceneGraph( graphic);
	}
	public void update(float deltaTime, GameContainer gc) {
		sceneGraph.updateSceneGraph();
		sceneGraph.update( deltaTime, gc);
		
		_updateGame( deltaTime, gc.getInput());
	}
	
	
	
	
	public void initLevel() {
		_initLevel();
	}
	
	public void releaseLevel() {
		_releaseLevel();
	}
	
	// for overriding
	protected void _initLevel() {
		
	}
	protected void _releaseLevel() {
		
	}
	protected void _updateGame( float deltaTime, Input input) {
		
	}
}
