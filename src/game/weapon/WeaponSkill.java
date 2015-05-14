package game.weapon;

import org.newdawn.slick.GameContainer;

public class WeaponSkill extends Weapon {
	// chargeTime : casting time
	// reloadingTime : cool time
	public WeaponSkill() {
		chargeTimeElapsed = 0.0f;
	}
	
	
	public void onButtonFireDown() {
		if( isReloading)
			return;
		
		isFireDown = true;
	}
	public void onButtonFireUp() {
		// after start casting, 
		//isFireDown = false;
	}
	public void onFire() {
		// skill effect implementation..		
		this.onButtonReloadDown();
	}
	
	public void onReloadFinished() {
		super.onReloadFinished();
	}
	
	public void update( float deltaTime, GameContainer gc) {
		if( isFireDown) {
			chargeTimeElapsed += deltaTime;
			if( chargeTimeElapsed >= chargeTime) {
				chargeTimeElapsed = 0.0f;
				onFire();
			}
		}
		else {
			chargeTimeElapsed = 0.0f;
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
