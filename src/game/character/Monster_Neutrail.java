package game.character;

import engine.base.AEMath;
import game.DrawOrder;

public class Monster_Neutrail extends Monster {
	
	public Monster_Neutrail(){
		createSprite("res/images/monster/attack.jpg");
		getSprite().setDrawOrder( DrawOrder.CHARACTER.ordinal());
	}
	
	public void initMonsterStatEasy() {
		initMonsterStat( 2, 2, 2);
	}
	public void initMonsterStatNormal() {
		initMonsterStat( 3, 3, 3);
	}
	public void initMonsterStatHard() {
		initMonsterStat( 4, 4, 4);
	}
}
