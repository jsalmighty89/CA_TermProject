package game.weapon;

import engine.base.AEVector;
import engine.framework.AEFramework;
import game.GameLevel;

public class WeaponFreeze extends WeaponRifle {

	public WeaponFreeze() {
		setObjectName("Freeze_ALL");
		ammoMax = 3;
		ammo = ammoMax;
		damage = 20.0f;
		reloadingTime = 20.0f;
		chargeTime = 5.0f;
	}

	@Override
	public void onFire() {
		if (ammo <= 0) {
			this.onButtonReloadDown();
		}

		else {
			AEVector mousePosition = GameLevel.getGameLevel().getPlayer()
					.getMouseTargetPosition();

			if (mousePosition != null) {
				// create projectile
				ProjectileFreeze freeze = new ProjectileFreeze(this);
				freeze.setTargetPosition(transform.getPosition(), mousePosition);
				AEFramework.getInstance().addToSceneRoot(freeze);
			}

			ammo--;
		}
	}

}
