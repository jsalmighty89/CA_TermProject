package engine.render;

import java.util.LinkedList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import engine.base.AEMath;
import engine.base.AETransform;
import engine.framework.AEFramework;
import engine.object.AECamera2D;
import engine.object.AECollider;
import engine.object.AEGameObject;
import engine.object.AEObject;

public class AESceneGraph extends AEObject{
	protected AEGameObject rootObject;
	
	// internal use only
	protected LinkedList<AEGameObject> listUpdateObject;
	protected LinkedList<AEGameObject> listRenderObject;
	protected LinkedList<AEGameObject> listPhysicsObject;
	
	public AESceneGraph() {
		rootObject = new AEGameObject();
		listUpdateObject = new LinkedList<AEGameObject>();
		listRenderObject = new LinkedList<AEGameObject>();
		listPhysicsObject = new LinkedList<AEGameObject>();
	}
	
	
	public AEGameObject getRoot() {
		return rootObject;
	}
	public AEGameObject getObject( String objectName) {		
		// not traversing scenegraph. searching on update object list
		for( int i=0; i<listUpdateObject.size(); i++) {
			AEGameObject object = listUpdateObject.get( i);
			if( object.getObjectName() != null  && object.getObjectName().equals( objectName)) {
				return object;
			}
		}
		
		return null;
	}
	
	
	public void updateSceneGraph() {
		listRenderObject.clear();
		listUpdateObject.clear();
		listPhysicsObject.clear();
		
		_updateTransformTraversal( rootObject);
		_updateCollision();
	}
	
	public void update( float deltaTime, GameContainer gc) {
		for( int i=0; i<listUpdateObject.size(); i++) {
			AEGameObject object = listUpdateObject.get( i);
			object.update( deltaTime, gc);
		}
	}
	
	public void renderSceneGraph( Graphics graphic) {
		
		AECamera2D activeCam = AEFramework.getInstance().getActiveCamera();
		AETransform transformCam = activeCam.getTransform();
		float cameraZoom = activeCam.getZoom();
		float cameraPosX = transformCam.getPosition().x;
		float cameraPosY = transformCam.getPosition().y;
		
		float offsetX = AEFramework.getInstance().getWindowInfo().getWidth();
		float offsetY = AEFramework.getInstance().getWindowInfo().getHeight();
		cameraPosX -= offsetX * 0.5f;
		cameraPosY -= offsetY * 0.5f;
		
		for( int i=0; i<listRenderObject.size(); i++) {
			AEGameObject object = listRenderObject.get( i);
		
			AETransform transform = object.getTransform();
			Image image = object.getSprite().getImage();
			
			float scale = transform.getScale();
			float positionX = transform.getPosition().x;
			float positionY = transform.getPosition().y;
			float centerWidth = image.getWidth() * 0.5f;
			float centerHeight = image.getWidth() * 0.5f;
			float centerWidthScaled = centerWidth * scale;
			float centerHeightScaled = centerHeight * scale;
			
			image.setCenterOfRotation( centerWidthScaled, centerHeightScaled);
			image.setRotation( AEMath.rad2deg(transform.getRotation()));
			image.draw( positionX - centerWidthScaled - cameraPosX,
						positionY - centerHeightScaled - cameraPosY, scale);
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
				if( child.hasSprite() && child.isVisible())
					listRenderObject.add( child);
				
				// if need to be perform physics
				if( child.hasCollider())
					listPhysicsObject.add( child);
				
				_updateTransformTraversal( child);
			}
		}
	}
	
	private void _updateCollision() {
		for( int i=0; i<listPhysicsObject.size(); i++) {
			AEGameObject object = listPhysicsObject.get( i);
			for( int j=0; j<listPhysicsObject.size(); j++) {
				AEGameObject other = listPhysicsObject.get( j);
				if( object == other) continue;
				AECollider colliderA = object.getCollider();
				AECollider colliderB = other.getCollider();
				colliderA.performCollision( colliderB);
			}
		}
	}
}
