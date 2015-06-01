package game.weapon;

import engine.base.AEMath;
import engine.base.AEVector;
import engine.framework.AEFramework;
import game.GameLevel;

public class WeaponGrenade extends WeaponRifle {
	public WeaponGrenade() {
		setObjectName( "Default Grenade");
	}
	
	public void onFire() {
		if( ammo <= 0) {
			this.onButtonReloadDown();
		}
		else {
			AEVector mousePosition = GameLevel.getGameLevel().getPlayer().getMouseTargetPosition();

			// create projectile
			ProjectileGrenade grenade = new ProjectileGrenade( this);
			grenade.setTargetPosition( transform.getPosition(), mousePosition);
			AEFramework.getInstance().addToSceneRoot( grenade);
			
			ammo--;
		}
	}
}
