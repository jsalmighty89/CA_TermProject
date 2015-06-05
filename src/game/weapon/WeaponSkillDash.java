package game.weapon;

import game.GameLevel;
import game.character.Player;

public class WeaponSkillDash extends WeaponSkill {
	public WeaponSkillDash() {
		setObjectName( "Skill Dash");
		reloadingTime = 0.1f;
	}
	
	public void onFire() {
		super.onFire();
		
		Player player = GameLevel.getGameLevel().getPlayer();
		if( player != null) {
			player.startDash( 0.1f, 6.0f);
		}
	}
}
