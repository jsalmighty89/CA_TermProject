package game.weapon;

import java.util.LinkedList;

import engine.base.AEMath;
import engine.base.AEVector;
import game.GameLevel;
import game.character.Monster;
import game.character.Player;

public class WeaponMelee extends Weapon {

	protected float range = 100.0f;
	protected float angle = 90.0f;
	protected float pushForce = 300.0f;
	
	public WeaponMelee() {
		setObjectName( "Default Melee");
		chargeTime = 0.3f;
		reloadingTime = 1.0f;
	}
	
	public void onFire() {
		onButtonReloadDown();
		
		GameLevel gameLevel = GameLevel.getGameLevel();
		Player player = GameLevel.getGameLevel().getPlayer();
		AEVector playerPosition = player.getTransform().getPosition();
		AEVector playerForward = player.getTransform().getForward( AEMath.deg2rad( 90.0f));	//player forward's offset		
		
		LinkedList<Monster> listMonsterHit = gameLevel.getMonsterAround( playerPosition, range);
		
		for( Monster monster : listMonsterHit) {
			AEVector direction = AEVector.sub( monster.getTransform().getPosition(), playerPosition);
			direction.normalize();
			// angle check
			float dot = AEVector.dot( playerForward, direction);
			if( (float)Math.acos( dot) <= AEMath.deg2rad( angle * 0.5f)) {
				monster.onTakeDamage( this);
				monster.addForce( direction, pushForce);
			}			
		}
	}
}
