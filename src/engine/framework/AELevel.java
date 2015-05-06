package engine.framework;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import engine.base.AEVector;
import engine.object.AECamera2D;
import engine.object.AEGameObject;
import engine.object.AEObject;
import engine.render.AESceneGraph;


public class AELevel extends AEObject {
	protected AESceneGraph sceneGraph;
	protected AECamera2D activeCamera;
	
	public AELevel() {
		sceneGraph = new AESceneGraph();
		
		AECamera2D defaultCamera = new AECamera2D();
		this.addToSceneRoot( defaultCamera);
		this.setActiveCamera( defaultCamera);
		
		defaultCamera.getTransform().setPosition( new AEVector( 100.0f, 0.0f));
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
	
	public AECamera2D getActiveCamera() {
		return activeCamera;
	}
	public void setActiveCamera( AECamera2D camera) {
		activeCamera = camera;
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
