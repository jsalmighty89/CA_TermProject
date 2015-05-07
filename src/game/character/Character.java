package game.character;

import org.newdawn.slick.GameContainer;

import engine.base.AEVector;
import engine.object.AEGameObject;

public class Character extends AEGameObject {
	
	protected float healthMax;
	protected float health;
	protected boolean isAlive;
	
	protected AEVector currentMovement;
	protected float acceleratedRatio = 0.25f;
	protected float deacceleratedRatio = 0.1f;
	protected float movementSpeed = 300.0f;
	
	
	public Character() {
		currentMovement = new AEVector();
	}
	
	
	public void addHealth( float health) {
		health = Math.min( healthMax, this.health + health);		
	}
	public void decreaseHealth( float health) {
		this.health -= health;
		if( health <= 0.0f)
			onDeath();
	}

	public void onDeath() {
		isAlive = false;
	}
	
	public void update(float deltaTime, GameContainer gc) {
		super.update(deltaTime, gc);
		
		if( isAlive) {
			move(deltaTime, gc);
		}
	}

	protected void move(float deltaTime, GameContainer gc) {
		
	}
}
