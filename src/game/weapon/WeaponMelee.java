package game.weapon;

import java.util.LinkedList;

import engine.base.AEVector;
import game.GameLevel;
import game.character.Monster;
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
		
		GameLevel gameLevel = GameLevel.getGameLevel();
		Player player = GameLevel.getGameLevel().getPlayer();
		AEVector playerPosition = player.getTransform().getPosition();
		AEVector playerForward = player.getTransform().getForward();
		
		
		LinkedList<Monster> listMonsterHit = gameLevel.getMonsterAround( playerPosition, 75.0f);
		
		for( Monster monster : listMonsterHit) {
			AEVector direction = AEVector.sub( monster.getTransform().getPosition(), playerPosition);
			direction.normalize();
			// angle check
			monster.onTakeDamage( this);
		}
	}
}
