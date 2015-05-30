package game.character;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

import engine.base.AEMath;
import engine.base.AEVector;
import engine.framework.AEFramework;
import engine.object.AECamera2D;
import game.DrawOrder;
import game.GameDataManager;
import game.GameLevel;
import game.weapon.*;

public class Player extends Character {	
	// weapon
	protected static int weaponCount=4;
	protected int currentWeaponIdx;
	protected Weapon weapon[] = new Weapon[weaponCount];
	
	public Player() {
		setObjectName("Player");
		
		createSprite("res/images/player.png");
		getSprite().setDrawOrder( DrawOrder.CHARACTER.ordinal());
		
		createCollider( 20.0f);
		
		isAlive = true;
		
		acceleratedRatio = 0.25f;
		deacceleratedRatio = 0.1f;
		movementSpeed = 150.0f;
		
		currentWeaponIdx = 0;
	}
	
	public int getCurrentWeaponIdx() {
		return currentWeaponIdx;
	}
	public Weapon getCurrentWeapon() {
		return weapon[currentWeaponIdx];
	}
	public Weapon getWeapon( int idx) {
		if( idx < 0 ||  idx >= weaponCount)
			return null;
		
		return weapon[idx];
	}
	
	public void setWeapon( Weapon weapon, int slot) {
		weapon.setOwner( this);
		this.addChild( weapon);
		this.weapon[slot] = weapon;
	}
	public void changeWeapon( int slot) {
		if( currentWeaponIdx != slot) {
			weapon[currentWeaponIdx].onButtonFireUp();
			currentWeaponIdx = slot;
		}
	}
	
	public void update( float deltaTime, GameContainer gc) {
		super.update(deltaTime, gc);
		
		if( isAlive)
			input( gc);
	}
	
	public void onDeath() {
		super.onDeath();
		GameLevel.getGameLevel().getGameLogic().onGameOver();
	}
	
	protected void input( GameContainer gc) {
		Input input = gc.getInput();
		
		
		// mouse targeting
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

		
		// mouse left button down
		if( input.isMouseButtonDown( 0)) {
			weapon[currentWeaponIdx].onButtonFireDown();
		}
		// up
		else {
			weapon[currentWeaponIdx].onButtonFireUp();
		}
		
		// reload
		if( input.isKeyPressed( Input.KEY_R)) {
			weapon[currentWeaponIdx].onButtonReloadDown();
		}
		
		// weapon change
		if( input.isKeyPressed( Input.KEY_1)) {
			changeWeapon( 0);
		}
		if( input.isKeyPressed( Input.KEY_2)) {
			changeWeapon( 1);
		}
		if( input.isKeyPressed( Input.KEY_3)) {
			changeWeapon( 2);
		}
		if( input.isKeyPressed( Input.KEY_4)) {
			changeWeapon( 3);
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
