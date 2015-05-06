import org.newdawn.slick.Input;

import engine.base.AEMath;
import engine.base.AETransform;
import engine.base.AEVector;
import engine.framework.AEFramework;
import engine.framework.AELevel;
import engine.object.AECamera2D;
import engine.object.AEGameObject;


public class TestLevel extends AELevel {
	
	AEGameObject testSprite;
	AEGameObject testSpriteNPC[];
	
	public TestLevel() {
		this.objectName = "TestLevel";
	}
	
	protected void _initLevel() {
		testSprite = new AEGameObject();
		testSprite.createSprite("res/images/player.png");
		testSprite.createCollider( 0.0f);
		testSprite.setObjectName( "Player");
		
		testSprite.getTransform().setScale( 1.0f);
		
		AEFramework.getInstance().addToSceneRoot( testSprite);
		
		
		testSpriteNPC = new AEGameObject[20];
		for( int i=0; i<20; i++) {
			AEGameObject newSprite = new AEGameObject();
			newSprite.createSprite("res/images/player.png");
			newSprite.createCollider( 20.0f);
			newSprite.setObjectName( "Monster");
			newSprite.getTransform().setPosition( new AEVector( AEMath.getRandomRange( 0.0f, 640.0f), AEMath.getRandomRange( 0.0f, 720.0f)));
			newSprite.getTransform().setRotation( AEMath.deg2rad( AEMath.getRandomRange( 0.0f, 480.0f)));
			newSprite.getTransform().setScale( 0.5f);
			AEFramework.getInstance().addToSceneRoot( newSprite);
			testSpriteNPC[i] = newSprite;
		}
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
		
		float x = input.getMouseX();
		float y = input.getMouseY();
		AEVector mousePosition = new AEVector( x, y, 0.0f);
		
		AECamera2D camera = AEFramework.getInstance().getActiveCamera();
		AEVector mouseWorldPos = camera.getWorldFromScreen( mousePosition);
		
		float dx = mouseWorldPos.x - position.x;
		float dy = mouseWorldPos.y - position.y;
		float rad = (float)Math.atan2( dy, dx);
		
		
		transform.setPosition( position);
		transform.setRotation( rad - AEMath.deg2rad( 90.0f));
		
		
		if( input.isMousePressed( 0)) {
			Projectile projectile = new Projectile();
			projectile.getTransform().setPosition( position);
			projectile.setForward( new AEVector( dx, dy, 0.0f));
			projectile.getTransform().setRotation( rad + AEMath.deg2rad( 180.0f));
			AEFramework.getInstance().addToSceneRoot( projectile);
		}
		
		camera.getTransform().setPosition( position);
	}
}
