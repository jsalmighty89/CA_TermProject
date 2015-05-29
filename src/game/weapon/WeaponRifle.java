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
		ammoMax = 10;
		ammo = ammoMax;
		
		chargeTime = 0.25f;
	}
	
	
	
	public void onFire() {
		if( ammo <= 0) {
			this.onButtonReloadDown();
		}
		else {
			// get face direction using current transform's rotation matrix
			AEVector position = transform.getPosition();
			float faceRad = transform.getRotation();
			
			AEMatrix2D matTranslation = AEMatrix2D.createTranslateMatrix( 0.0f, 1.0f); // default forward is (0.0f, 1.0f)
			AEMatrix2D matRotation = AEMatrix2D.createRotationMatrix( faceRad);
			matTranslation = AEMatrix2D.multiply( matRotation, matTranslation);
			
			AEVector faceDirection = new AEVector( matTranslation.v[0][2], matTranslation.v[1][2]);

			// create projectile
			Projectile projectile = new Projectile( this);
			projectile.getTransform().setPosition( position);
			projectile.setForward( faceDirection);
			projectile.getTransform().setRotation( faceRad - AEMath.deg2rad( 90.0f));
			AEFramework.getInstance().addToSceneRoot( projectile);
			
			ammo--;
		}
	}
	
	public void onReloadFinished() {
		super.onReloadFinished();
		ammo = ammoMax;
	}
}
