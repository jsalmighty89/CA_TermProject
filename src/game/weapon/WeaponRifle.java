package game.weapon;

import engine.base.AEMath;
import engine.base.AEMatrix2D;
import engine.base.AEVector;
import engine.framework.AEFramework;

public class WeaponRifle extends Weapon {
	protected int ammoMax;
	protected int ammo;
	
	// chargeTime : fire rate
	
	public WeaponRifle() {
		setObjectName( "Default Rifle");
		ammoMax = 10;
		ammo = ammoMax;
		
		chargeTime = 0.25f;
	}
	
	public int getAmmo() {
		return ammo;
	}
	public int getAmmoMax() {
		return ammoMax;
	}
	
	public void onFire() {
		if( ammo <= 0) {
			this.onButtonReloadDown();
		}
		else {
			// get face direction using current transform's rotation matrix
			AEVector position = transform.getPosition();
			float recoil = AEMath.getRandomRange( -0.1f, 0.1f);
			float faceRad = transform.getRotation() + recoil;
			AEVector faceDirection = getTransform().getForward( AEMath.deg2rad( 90.0f) + recoil);

			// create projectile
			Projectile projectile = new ProjectileRifleBullet( this);
			projectile.getTransform().setPosition( position);
			projectile.setForward( faceDirection);
			projectile.getTransform().setRotation( faceRad - AEMath.deg2rad( 0.0f));
			AEFramework.getInstance().addToSceneRoot( projectile);
			
			ammo--;
		}
	}
	
	public void onReloadFinished() {
		super.onReloadFinished();
		ammo = ammoMax;
	}
}
