package game.character;

import game.DrawOrder;
import game.weapon.WeaponSkillPush;

public class PlayerKJS extends Player {
	public PlayerKJS() {
		//this.createSprite( "res/images/bullet.png");
		//getSprite().setDrawOrder( DrawOrder.CHARACTER.ordinal());
		
		setSkill( new WeaponSkillPush());
	}
	
	
}
