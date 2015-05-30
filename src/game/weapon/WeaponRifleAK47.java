package game.weapon;

public class WeaponRifleAK47 extends WeaponRifle {
	public WeaponRifleAK47() {
		setObjectName( "AK-47");
		ammoMax = 30;
		ammo = ammoMax;
		
		chargeTime = 0.075f;
	}
}
