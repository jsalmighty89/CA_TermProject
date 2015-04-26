import org.newdawn.slick.Input;

import engine.base.AETransform;
import engine.base.AEVector;
import engine.framework.AEFramework;
import engine.framework.AELevel;
import engine.object.SpriteTest;


public class TestLevel extends AELevel {
	
	SpriteTest testSprite;
	
	public TestLevel() {
		this.objectName = "TestLevel"; 
	}
	
	protected void _initLevel() {
		testSprite = new SpriteTest( "test.png");
		
		testSprite.getTransform().setScale( 1.0f);
		
		AEFramework.getInstance().addToSceneRoot( testSprite);
	}
	
	protected void _updateGame( float deltaTime, Input input) {
		AETransform transform = testSprite.getTransform();
		AEVector position = transform.getPosition();
		
		if( input.isKeyDown( Input.KEY_LEFT) || input.isKeyDown( Input.KEY_A))			
			position.x -= 100.0f * deltaTime;
		if( input.isKeyDown( Input.KEY_RIGHT) || input.isKeyDown( Input.KEY_D))
			position.x += 100.0f * deltaTime;
		if( input.isKeyDown( Input.KEY_UP) || input.isKeyDown( Input.KEY_W))
			position.y -= 100.0f * deltaTime;
		if( input.isKeyDown( Input.KEY_DOWN) || input.isKeyDown( Input.KEY_S))
			position.y += 100.0f * deltaTime;
		
		transform.setPosition( position);
	}
}
