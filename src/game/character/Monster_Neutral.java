package game.character;

import org.newdawn.slick.GameContainer;

import engine.base.AEMath;
import engine.base.AEVector;
import engine.framework.AEFramework;
import game.DrawOrder;

// 공격 or 방황
public class Monster_Neutral extends Monster {
	
	public Monster_Neutral(){
		createSprite("res/images/monster/good.png");
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
	
	protected void move(float deltaTime, GameContainer gc) {
		
		Player player = (Player)AEFramework.getInstance().findGameObject( "Player");
		if( player != null) {		
			AEVector position = this.getTransform().getPosition();			
			AEVector playerPos = player.getTransform().getPosition();
			AEVector moveDir = AEVector.sub( playerPos, position);
			moveDir.normalize();
			
			if( moveDir.x != 0.0f || moveDir.y != 0.0f) {
				moveDir.normalize();
				moveDir = AEVector.multiply( moveDir, movementSpeed);
				currentMovement = AEVector.lerp( currentMovement, moveDir, acceleratedRatio);
			}
			else {
				currentMovement = AEVector.lerp( currentMovement, moveDir, deacceleratedRatio);
			}
			
			position = AEVector.add( position, AEVector.multiply(currentMovement, deltaTime));
			this.getTransform().setPosition( position);
			
			float dx = moveDir.x;
			float dy = moveDir.y;
			float rad = (float)Math.atan2( dy, dx);
			
			// rotate monster
			this.getTransform().setRotation( rad - AEMath.deg2rad( 90.0f));
		}
	}
}
