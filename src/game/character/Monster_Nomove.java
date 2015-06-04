package game.character;

import engine.base.AEMath;
import engine.object.AEGameObject;
import game.DrawOrder;

// 안움직임 - 부딪히면 터짐
public class Monster_Nomove extends Monster {
	
	public Monster_Nomove(){
        createSprite("res/images/monster/nomove.png");
		getSprite().setDrawOrder( DrawOrder.CHARACTER.ordinal());
	}
	
	public void initMonsterStatEasy() {
		initMonsterStat( 1, 1, 0);
		movementSpeed = 0.0f;
	}
	public void initMonsterStatNormal() {
		initMonsterStat( 3, 3, 0);
		movementSpeed = 0.0f;
	}
	public void initMonsterStatHard() {
		initMonsterStat( 5, 5, 0);
		movementSpeed = 0.0f;
	}
	
	public void onCollideEnter( AEGameObject other) {
		super.onCollideEnter( other);
		if( other.isTypeOf( Player.class)) {
			onDeath();
		}
   }
}
