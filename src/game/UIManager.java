package game;

import org.newdawn.slick.GameContainer;

import engine.base.AEVector;
import engine.framework.AEFramework;
import engine.object.AEGameObject;
import engine.object.AEUIObject;

public class UIManager extends AEGameObject {
	
	protected float windowRight;
	protected float windowBottom;
	
	protected AEUIObject textMainMessage;
	protected float remainMainMessage;
	
	protected AEUIObject textWeaponName;
	
	
	public UIManager() {
		windowRight = AEFramework.getInstance().getWindowInfo().getWidth();
		windowBottom = AEFramework.getInstance().getWindowInfo().getHeight();
		
		textMainMessage = new AEUIObject();
		textMainMessage.createText( "Default", "");
		AEFramework.getInstance().addToUIRoot( textMainMessage);
		textMainMessage.getTransform().setPosition( new AEVector( getCenterX(), getCenterY()));
		remainMainMessage = 0.0f;
	}

	
	public float getCenterX() {
		return windowRight * 0.5f;
	}
	public float getCenterY() {
		return windowBottom * 0.5f;
	}
	
	public void setMainMessage( String message) {
		setMainMessage( message, 2.0f);
	}
	public void setMainMessage( String message, float time) {
		textMainMessage.getText().setText( message);
		remainMainMessage = time;
	}
	
	public void update( float deltaTime, GameContainer gc) {
		if( remainMainMessage <= 0.0f) {
			textMainMessage.getText().setText( "");
		}
		else {
			remainMainMessage -= deltaTime;
		}
	}
}
