package game.character;

import game.DrawOrder;
import game.weapon.WeaponSkillDash;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

public class PlayerSHK extends Player {
	
	public PlayerSHK() {
		setObjectName("Nine");
		
		createSprite("res/images/sas.png");
		getSprite().setDrawOrder( DrawOrder.CHARACTER.ordinal());
		
		health = 200.0f;
		
		setSkill( new WeaponSkillDash());
	}
}
