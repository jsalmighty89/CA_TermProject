package game.character;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

import engine.base.AEVector;
import engine.object.AEGameObject;
import game.DrawOrder;

public class PlayerJH extends Player {
	
	static float dashTime;
	
	public PlayerJH() {
        setObjectName("Player");
		
		createSprite("res/images/playerhun.png");
		getSprite().setDrawOrder( DrawOrder.CHARACTER.ordinal());
		
		createCollider( 20.0f);
		
		isAlive = true;
		
		acceleratedRatio = 0.25f;
		deacceleratedRatio = 0.1f;
		movementSpeed = 300.0f;
		
		currentWeaponIdx = 0;
		
		laserSight = new AEGameObject();
		laserSight.setObjectName( "LaserSight");
		laserSight.createSprite( "res/images/laser.png");
		laserSight.getSprite().setDrawOrder( DrawOrder.CHARACTER.ordinal());
		laserSight.getTransform().setPosition( new AEVector( 0.0f, 40.0f));
		this.addChild( laserSight);
	}

	protected void dash(float deltaTime, GameContainer gc){
		Input input = gc.getInput();
		
		if (input.isKeyPressed(Input.KEY_SPACE)){
		movementSpeed *= 4;	
		dashTime = 0.1f;
		System.out.println("Dash!!");
		}
		if(dashTime<deltaTime){
			movementSpeed = 150.0f;
		}
	}
    }
	
	


