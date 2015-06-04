package game.character;

import game.DrawOrder;
import game.weapon.WeaponSkillDash;

public class PlayerSHK extends Player {
	

	
	public PlayerSHK() {
		
		setObjectName("Nine");
		
		createSprite("res/images/sas.png");
		getSprite().setDrawOrder( DrawOrder.CHARACTER.ordinal());
		
		health = 500.0f;
		
		
		setSkill( new WeaponSkillDash());
	}
	
	

}
