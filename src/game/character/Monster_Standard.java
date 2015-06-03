package game.character;

import engine.base.AEMath;
import game.DrawOrder;

public class Monster_Standard extends Monster {
	
	public Monster_Standard(){
		createSprite("res/images/monster/attack.jpg");
		getSprite().setDrawOrder( DrawOrder.CHARACTER.ordinal());
	}
	
	public void initMonsterStatEasy() {
		initMonsterStat( 1, 1, 1);
	}
	public void initMonsterStatNormal() {
		initMonsterStat( 2, 2, 2);
	}
	public void initMonsterStatHard() {
		initMonsterStat( 3, 3, 3);
	}
}
