package game.character;

import engine.base.AEMath;
import engine.base.AEVector;
import engine.object.AEGameObject;
import game.DrawOrder;

// monster ¹«Á¶°Ç

public class Monster_Attack extends Monster {
	
	public Monster_Attack(){
		createSprite("res/images/monster/attack.png");
		getSprite().setDrawOrder( DrawOrder.CHARACTER.ordinal());
	}
	
	public void initMonsterStatEasy() {
		initMonsterStat( 4, 4, 4);
	}
	public void initMonsterStatNormal() {
		initMonsterStat( 5, 5, 5);
	}
	public void initMonsterStatHard() {
		initMonsterStat( 6, 6, 6);
	}
}
