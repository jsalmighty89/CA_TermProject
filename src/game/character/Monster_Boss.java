package game.character;

import engine.base.AEMath;
import game.DrawOrder;

public class Monster_Boss extends Monster {
	
	public Monster_Boss(){
        createSprite("res/images/monster/attack_boss.jpg");
		getSprite().setDrawOrder( DrawOrder.CHARACTER.ordinal());
	}
	
	public void initMonsterStatEasy() {
		initMonsterStat( 1, 4, 1);
		healthMax = healthMax * 10.0f;
		health = healthMax;
	}
	public void initMonsterStatNormal() {
		initMonsterStat( 3, 5, 3);
		healthMax = healthMax * 10.0f;
		health = healthMax;
	}
	public void initMonsterStatHard() {
		initMonsterStat( 5, 6, 5);
		healthMax = healthMax * 10.0f;
		health = healthMax;
	}
}
