package game.weapon;

import game.GameLevel;
import game.character.Player;

public class WeaponMelee extends Weapon {

	public WeaponMelee() {
		setObjectName( "Default Melee");
		chargeTime = 0.3f;
		reloadingTime = 1.0f;
	}
	
	public void onFire() {
		System.out.println("Melee Fire");
		onButtonReloadDown();
		
		Player player = GameLevel.getGameLevel().getPlayer();
	}
}
