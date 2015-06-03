package game.character;

import engine.base.AEVector;
import engine.object.AEGameObject;
import game.DrawOrder;

public class PlayerJH extends Player {
	
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

    }
	
	


