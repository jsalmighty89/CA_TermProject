package game.character;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

import engine.base.AEVector;
import game.DrawOrder;
import game.weapon.WeaponRifle;

public class PlayerSHK extends Player {
	protected float dashTime = 0.0f;
	
	protected void dash(GameContainer gc){
		Input input = gc.getInput();
	
		if(input.isKeyPressed(Input.KEY_SPACE)){
			System.out.println("DASH !!");
			movementSpeed *= 3.5f;
			dashTime = 0.1f;
		}
		
		if(dashTime < 0.0f)
			movementSpeed = 250.0f;
	}
	
	public void update(float deltaTime, GameContainer gc) {
		super.update(deltaTime, gc);
		dashTime -= deltaTime;
		
		if( isAlive) {
			dash(gc);
			move(deltaTime, gc);
		}
	}
		
	
	
	public PlayerSHK() {
		setObjectName("Player");
		
		createSprite("res/images/player.png");
		getSprite().setDrawOrder( DrawOrder.CHARACTER.ordinal());
		
		createCollider( 1.0f);
		
		isAlive = true;
		
		acceleratedRatio = 1.0f;
		deacceleratedRatio =1.0f;
		movementSpeed = 250.0f;

	}

}
