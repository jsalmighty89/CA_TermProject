package game.character;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

import engine.base.AEVector;
import engine.object.AEGameObject;
import game.DrawOrder;
import game.weapon.WeaponSkillDash;
import game.weapon.WeaponSkillPush;
import game.weapon.WeaponSkillWarp;

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
		
		//setSkill( new WeaponSkillPush());
		setSkill( new WeaponSkillWarp());
		
		laserSight = new AEGameObject();
		laserSight.setObjectName( "LaserSight");
		laserSight.createSprite( "res/images/laser.png");
		laserSight.getSprite().setDrawOrder( DrawOrder.CHARACTER.ordinal());
		laserSight.getTransform().setPosition( new AEVector( 0.0f, 40.0f));
		this.addChild( laserSight);
	}

	
}
    
	
	


