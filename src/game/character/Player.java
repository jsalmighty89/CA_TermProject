package game.character;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

import engine.base.AEMath;
import engine.base.AEVector;
import engine.framework.AEFramework;
import engine.object.AECamera2D;
import game.DrawOrder;
import game.weapon.Projectile;

public class Player extends Character {
	public Player() {
		setObjectName("Player");
		
		createSprite("res/images/player.png");
		getSprite().setDrawOrder( DrawOrder.CHARACTER.ordinal());
		
		isAlive = true;
		
		acceleratedRatio = 0.25f;
		deacceleratedRatio = 0.1f;
		movementSpeed = 150.0f;
	}
	
	public void update( float deltaTime, GameContainer gc) {
		super.update(deltaTime, gc);
		
		input( gc);
	}
	
	protected void input( GameContainer gc) {
		Input input = gc.getInput();
		
		float x = input.getMouseX();
		float y = input.getMouseY();
		AEVector mousePosition = new AEVector( x, y, 0.0f);
		
		AECamera2D camera = AEFramework.getInstance().getActiveCamera();
		AEVector mouseWorldPos = camera.getWorldFromScreen( mousePosition);
		
		AEVector playerPos = this.getTransform().getPosition();
		
		float dx = mouseWorldPos.x - playerPos.x;
		float dy = mouseWorldPos.y - playerPos.y;
		float rad = (float)Math.atan2( dy, dx);
		
		// rotate player
		transform.setRotation( rad - AEMath.deg2rad( 90.0f));
		
		if( input.isMousePressed( 0)) {
			Projectile projectile = new Projectile();
			projectile.getTransform().setPosition( playerPos);
			projectile.setForward( new AEVector( dx, dy, 0.0f));
			projectile.getTransform().setRotation( rad + AEMath.deg2rad( 180.0f));
			AEFramework.getInstance().addToSceneRoot( projectile);
		}
	}
	
	protected void move(float deltaTime, GameContainer gc) {
		Input input = gc.getInput();
		AEVector position = this.getTransform().getPosition();
		AEVector moveDir = new AEVector();
		
		if( input.isKeyDown( Input.KEY_A))
			moveDir.x -= 1.0f;
		if( input.isKeyDown( Input.KEY_D))
			moveDir.x += 1.0f;
		if( input.isKeyDown( Input.KEY_W))
			moveDir.y -= 1.0f;
		if( input.isKeyDown( Input.KEY_S))
			moveDir.y += 1.0f;
		
		// when pressed movement key
		if( moveDir.x != 0.0f || moveDir.y != 0.0f) {
			moveDir.normalize();
			moveDir = AEVector.multiply( moveDir, movementSpeed);
			currentMovement = AEVector.lerp( currentMovement, moveDir, acceleratedRatio);
		}
		// not pressed
		else {
			currentMovement = AEVector.lerp( currentMovement, moveDir, deacceleratedRatio);
		}
		
		position = AEVector.add( position, AEVector.multiply(currentMovement, deltaTime));
		this.getTransform().setPosition( position);
	}
}
