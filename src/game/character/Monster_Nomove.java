package game.character;

import org.newdawn.slick.GameContainer;

import engine.base.AEMath;
import engine.base.AEVector;
import engine.object.AEGameObject;
import game.DrawOrder;
import game.GameLevel;

// �ȿ����� - �ε����� ����
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
    protected void move(float deltaTime, GameContainer gc) {
	        float rad2 = AEMath.getRandomRange(0.0f, 0.0f);
	        float distance = AEMath.getRandomRange(0.0f, 0.0f);
         	AEVector respawnPosition = new AEVector(rad2,distance);
		//Player player = (Player)AEFramework.getInstance().findGameObject( "Player");
				
			
			AEVector moveDir = respawnPosition;
			moveDir.normalize();
			
			
		}
	}	

	

