package engine.render;

import java.util.LinkedList;

import org.newdawn.slick.Graphics;

import engine.base.AEMath;
import engine.base.AETransform;
import engine.object.AEGameObject;
import engine.object.AEObject;
import engine.object.SpriteTest;

public class AESceneGraph extends AEObject{
	protected AEGameObject rootObject;
	
	// internal use only
	protected LinkedList<AEGameObject> listRenderObject;
	
	public AESceneGraph() {
		rootObject = new AEGameObject();
		listRenderObject = new LinkedList<AEGameObject>();
	}
	
	
	public AEGameObject getRoot() {
		return rootObject;
	}
	
	
	public void updateSceneGraph() {
		_updateTransformTraversal( rootObject);
	}
	
	public void renderSceneGraph( Graphics graphic) {
		listRenderObject.clear();
		_updateRenderTraversal( rootObject);
		
		for( int i=0; i<listRenderObject.size(); i++) {
			AEGameObject object = listRenderObject.get( i);
			// TODO test codes :  need to be delete
			/*
			if( object instanceof SpriteTest) {
				SpriteTest sprite = (SpriteTest)object;
				AETransform transform = sprite.getTransform();
				sprite.getSprite().setRotation( AEMath.rad2deg(transform.getRotation()));
				graphic.drawImage( sprite.getSprite(), transform.getPosition().x, transform.getPosition().y);
			}
			*/			
		}
	}
	
	
	private void _updateTransformTraversal( AEGameObject parent) {
		LinkedList<AEGameObject> listChild = parent.getChildList();
		int count = listChild.size();
		if( count > 0) {
			for( int i=0; i<count; i++) {
				AEGameObject child = listChild.get( i);
				child.getTransform().updateMatrix();
				child.getTransform().updateFromParentMatrix( parent.getTransform());
				_updateTransformTraversal( child);
			}
		}
	}
	
	private void _updateRenderTraversal( AEGameObject parent) {
		listRenderObject.add( parent);
		LinkedList<AEGameObject> listChild = parent.getChildList();
		int count = listChild.size();
		if( count > 0) {
			for( int i=0; i<count; i++) {
				AEGameObject child = listChild.get( i);
				_updateRenderTraversal( child);
			}
		}
	}
}
