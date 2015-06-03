package game.character;

import game.DrawOrder;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

public class PlayerSHK extends Player {

	static float dashTime;


	protected void dash(float deltaTime, GameContainer gc){
		Input input = gc.getInput();
		
		if (input.isKeyPressed(Input.KEY_V)){
		movementSpeed *= 4;	
		dashTime = 0.1f;
		System.out.println("Dash!!");
		}
		if(dashTime<deltaTime){
			movementSpeed = 150.0f;
		}
	}
	
	
	@Override
	public void update(float deltaTime, GameContainer gc) {
		super.update(deltaTime, gc);
		dashTime-=deltaTime;
		if( isAlive) {
			dash(deltaTime, gc);
			move(deltaTime, gc);
		}
	}

	
	
	
	public PlayerSHK() {
		setObjectName("Nine");
		
		createSprite("res/images/sas.png");
		getSprite().setDrawOrder( DrawOrder.CHARACTER.ordinal());
		
		createCollider( 20.0f);

		//weapon[3] = new WeaponSkill();
		
		isAlive = true;
		
		acceleratedRatio = 0.25f;
		deacceleratedRatio = 0.1f;
		movementSpeed = 150.0f;
		health = 200.0f;


	}
}
