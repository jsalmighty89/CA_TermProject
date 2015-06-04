package game.character;

import engine.base.AEMath;
import engine.base.AEVector;
import engine.object.AEGameObject;
import game.DrawOrder;

public class Monster_Bomb extends Monster {
	
	public Monster_Bomb(){
		createSprite("res/images/monster/boom.png");
		getSprite().setDrawOrder( DrawOrder.CHARACTER.ordinal());
	}
	
	public void initMonsterStatEasy() {
		initMonsterStat( 0, 4, 4);
	}
	
	public void initMonsterStatNormal() {
		initMonsterStat( 2, 5, 5);
	}
	public void initMonsterStatHard() {
		initMonsterStat( 4, 6, 6);
	}
	
	public void onCollideEnter( AEGameObject other) {
		super.onCollideEnter( other);
		if( other.isTypeOf( Player.class)) {
			onDeath();
		}
	}

}
