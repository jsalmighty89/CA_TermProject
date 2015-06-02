package game.weapon;

import engine.base.AEMath;
import engine.base.AEVector;
import engine.framework.AEFramework;

/**
 * Created by daehyun on 15. 6. 2..
 */
public class WeaponBeam extends Weapon{
    protected int ammoMax;
    protected int ammo;

    // chargeTime : fire rate

    public WeaponBeam() {
        setObjectName( "Beam");
        ammoMax = 15;
        ammo = ammoMax;

        chargeTime = 0.1f;
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
            float recoil = AEMath.getRandomRange(-0.1f, 0.1f);
            float faceRad = transform.getRotation() + recoil;
            AEVector faceDirection = getTransform().getForward( AEMath.deg2rad( 90.0f) + recoil);

            // create projectile
            Projectile projectile = new ProjectileBeam( this);
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
