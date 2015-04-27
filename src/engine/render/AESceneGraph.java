package engine.render;

import java.util.LinkedList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import engine.base.AEMath;
import engine.base.AETransform;
import engine.object.AEGameObject;
import engine.object.AEObject;
import engine.object.SpriteTest;

public class AESceneGraph extends AEObject{
	protected AEGameObject rootObject;
	
	// internal use only
	protected LinkedList<AEGameObject> listUpdateObject;
	protected LinkedList<AEGameObject> listRenderObject;
	
	public AESceneGraph() {
		rootObject = new AEGameObject();
		listUpdateObject = new LinkedList<AEGameObject>();
		listRenderObject = new LinkedList<AEGameObject>();
	}
	
	
	public AEGameObject getRoot() {
		return rootObject;
	}
	
	
	public void updateSceneGraph() {
		listRenderObject.clear();
		listUpdateObject.clear();
		
		_updateTransformTraversal( rootObject);
	}
	
	public void update( float deltaTime, GameContainer gc) {
		for( int i=0; i<listUpdateObject.size(); i++) {
			AEGameObject object = listUpdateObject.get( i);
			object.update( deltaTime, null);
		}
	}
	
	public void renderSceneGraph( Graphics graphic) {
		
		//_updateRenderTraversal( rootObject);
		
		for( int i=0; i<listRenderObject.size(); i++) {
			AEGameObject object = listRenderObject.get( i);
			// TODO test codes :  need to be delete
			
			if( object instanceof SpriteTest) {
				SpriteTest sprite = (SpriteTest)object;
				AETransform transform = sprite.getTransform();
				//graphic.drawImage( sprite.getSprite(), transform.getPosition().x, transform.getPosition().y, 0.5f);
				float scale = transform.getScale().x;
				float positionX = transform.getPosition().x;
				float positionY = transform.getPosition().y;
				float centerWidth = sprite.getSprite().getWidth() * 0.5f;
				float centerHeight = sprite.getSprite().getWidth() * 0.5f;
				float centerWidthScaled = centerWidth * scale;
				float centerHeightScaled = centerHeight * scale;
				
				sprite.getSprite().setCenterOfRotation( centerWidthScaled, centerHeightScaled);
				sprite.getSprite().setRotation( AEMath.rad2deg(transform.getRotation()));
				sprite.getSprite().draw( positionX - centerWidthScaled, positionY - centerHeightScaled, scale);
				//sprite.getSprite().drawSheared( transform.getPosition().x, transform.getPosition().y, transform.getScale().x, );
			}
		}
	}
	
	
	private void _updateTransformTraversal( AEGameObject parent) {
		LinkedList<AEGameObject> listChild = parent.getChildList();
		int count = listChild.size();
		if( count > 0) {
			for( int i=0; i<count; i++) {
				AEGameObject child = listChild.get( i);
				// Transform Update
				child.getTransform().updateMatrix();
				child.getTransform().updateFromParentMatrix( parent.getTransform());
				
				// if need to be update
				listUpdateObject.add( child);
				
				// if need to be rendered
				listRenderObject.add( child);
				
				_updateTransformTraversal( child);
			}
		}
	}
	
	private void _updateRenderTraversal( AEGameObject parent) {
		/*
		listRenderObject.add( parent);
		LinkedList<AEGameObject> listChild = parent.getChildList();
		int count = listChild.size();
		if( count > 0) {
			for( int i=0; i<count; i++) {
				AEGameObject child = listChild.get( i);
				_updateRenderTraversal( child);
			}
		}
		*/
	}
}
