package game.weapon;

import org.newdawn.slick.GameContainer;

import engine.base.AEMath;
import engine.base.AEMatrix2D;
import engine.base.AEVector;
import engine.framework.AEFramework;
import engine.object.AEGameObject;
import game.character.*;
import game.character.Character;

public class Weapon extends AEGameObject {
	protected Character owner;
	
	// weapon's information
	protected float damage;
	protected float chargeTime;
	protected float reloadingTime;
	
	
	// for internal using
	protected boolean isFireDown;
	protected boolean isReloading;
	
	protected float chargeTimeElapsed;
	protected float reloadingTimeElapsed;	
	
	public Weapon() {		
		chargeTime = 0.2f;
		reloadingTime = 1.0f;
		
		chargeTimeElapsed = chargeTime;		// weapon is charged at the beginning
		reloadingTimeElapsed = 0.0f;
		
		damage = 400.0f;
	}
	
	public void setOwner( Character owner) {
		this.owner = owner;
	}
	public Character getOwner() {
		return owner;
	}
	
	public float getDamage() {
		return damage;
	}
	
	public void onButtonReloadDown() {
		isReloading = true;
		isFireDown = false;
	}
	public void onButtonFireDown() {
		if( isReloading)
			return;
		
		isFireDown = true;
	}
	public void onButtonFireUp() {
		isFireDown = false;
	}
	
	public void onFire() {
		//if( owner == null)
		//	return;
	}
	public void onReloadFinished() {
		isReloading = false;
	}
	
	public void update( float deltaTime, GameContainer gc) {
		
		if( chargeTimeElapsed >= chargeTime) {
			if( isFireDown) {
				chargeTimeElapsed = 0.0f;
				onFire();
			}
		}
		else {
			chargeTimeElapsed += deltaTime;
		}
		
		if( isReloading) {
			reloadingTimeElapsed += deltaTime;
			if( reloadingTimeElapsed >= reloadingTime) {
				reloadingTimeElapsed = 0.0f;
				onReloadFinished();
			}
		}
	}
}
