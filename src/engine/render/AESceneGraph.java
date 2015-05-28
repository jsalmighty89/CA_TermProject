package engine.render;

import java.awt.Font;
import java.util.LinkedList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.util.FontUtils;

import engine.base.AEMath;
import engine.base.AETransform;
import engine.base.AEVector;
import engine.framework.AEFramework;
import engine.object.AECamera2D;
import engine.object.AECollider;
import engine.object.AEGameObject;
import engine.object.AEObject;
import engine.object.AEUIObject;

public class AESceneGraph extends AEObject{	
	protected AEGameObject rootObject;
	protected AEUIObject rootUIObject;
	
	// internal use only
	protected LinkedList<AEGameObject> listUpdateObject;
	protected LinkedList<AEGameObject> listPhysicsObject;
	protected LinkedList<AEGameObject>[] listRenderObjectLayer;
	
	protected LinkedList<AEUIObject> listRenderUIObject;
	
	
	public AESceneGraph() {
		rootObject = new AEGameObject();
		listUpdateObject = new LinkedList<AEGameObject>();
		listPhysicsObject = new LinkedList<AEGameObject>();
		
		listRenderObjectLayer = new LinkedList[10];
		for( int i=0; i<listRenderObjectLayer.length; i++) {
			listRenderObjectLayer[i] = new LinkedList<AEGameObject>();
		}
		
		listRenderUIObject = new LinkedList<AEUIObject>();
		rootUIObject = new AEUIObject();
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
	
	public AEUIObject getUIRoot() {
		return rootUIObject;
	}
	
	
	public void updateSceneGraph() {
		////////// GameObject		
		listUpdateObject.clear();
		listPhysicsObject.clear();
		
		// clear render object layer list
		for( int i=0; i<listRenderObjectLayer.length; i++) {
			listRenderObjectLayer[i].clear();
		}
		
		_updateTransformTraversal( rootObject, false);
		_updateCollision();
		
		
		///////// UIObject
		listRenderUIObject.clear();
		_updateTransformTraversal( rootUIObject, true);
	}
	
	public void update( float deltaTime, GameContainer gc) {
		for( int i=0; i<listUpdateObject.size(); i++) {
			AEGameObject object = listUpdateObject.get( i);
			// force momentum update
			
			// update own routine
			object.update( deltaTime, gc);
			object.updateForce( deltaTime);
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
		
		
		for( int i=0; i<listRenderObjectLayer.length; i++) {
			for( int j=0; j<listRenderObjectLayer[i].size(); j++) {
				AEGameObject object = listRenderObjectLayer[i].get( j);
			
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
		
		AEFontTable fontTable = AEFramework.getInstance().getFontTable();
		
		for( int i=0; i<listRenderUIObject.size(); i++) {
			AEUIObject object = listRenderUIObject.get(i);
			AETransform transform = object.getTransform();
			AEVector position = transform.getPosition();
			
			if( object.hasText()) {
				String fontType = object.getText().getFont();
				String text = object.getText().getText();
				
				
				TrueTypeFont font = fontTable.getFont( fontType);
				//font.drawString( position.x, position.y, text);
				//graphic.drawString( text, position.x, position.y);
				FontUtils.drawCenter( font, text, (int)position.x, (int)position.y, 0);
			}
		}
	}
	
	
	private void _updateTransformTraversal( AEGameObject parent, boolean isUIObject) {
		LinkedList<AEGameObject> listChild = parent.getChildList();
		int count = listChild.size();
		if( count > 0) {
			for( int i=0; i<count; i++) {
				AEGameObject child = listChild.get( i);
				// Transform Update
				child.getTransform().updateMatrix();
				child.getTransform().updateFromParentMatrix( parent.getTransform());
				
				if( isUIObject == false) {
					// if need to be update
					listUpdateObject.add( child);
					
					// if need to be rendered
					if( child.hasSprite() && child.isVisible()) {
						//listRenderObject.add( child);
						AESprite sprite = child.getSprite();
						int order = sprite.getDrawOrder();
						listRenderObjectLayer[order].add( child);
					}
						
					
					// if need to be perform physics
					if( child.hasCollider())
						listPhysicsObject.add( child);
				}
				else {
					listRenderUIObject.add( (AEUIObject)child);
				}
				
				_updateTransformTraversal( child, isUIObject);
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
