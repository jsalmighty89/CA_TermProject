package game.character;

import org.newdawn.slick.GameContainer;

import engine.base.AEMath;
import engine.base.AEVector;
import engine.framework.AEFramework;
import engine.object.AEGameObject;
import game.DrawOrder;
import game.GameLevel;

// µ¹¾Æ´Ù´Ô
public class Monster_Standard extends Monster {
	
	public Monster_Standard(){
		createSprite("res/images/monster/attack.png");
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
	
	protected void move(float deltaTime, GameContainer gc) {
		///// after
		AEVector moveDir = new AEVector( 0.0f, 1.0f, 0.0f);
		moveDir = AEVector.multiply( moveDir, movementSpeed);
		AEVector position = getTransform().getPosition();
		
		currentMovement = AEVector.lerp( currentMovement, moveDir, acceleratedRatio);
		
		position = AEVector.add( position, AEVector.multiply( currentMovement, deltaTime));
		this.getTransform().setPosition( position);
		
		
		//// before
		/*
		float rad2 = AEMath.getRandomRange(0.0f, 0.0f);
		float distance = AEMath.getRandomRange(300.0f, 500.0f);
		AEVector respawnPosition = new AEVector((float) Math.sin(rad2)
				* distance, (float) Math.cos(rad2) * distance);
		
		Player player = (Player)AEFramework.getInstance().findGameObject( "Player");
		if( player != null) {		
			AEVector position = this.getTransform().getPosition();	
			AEVector playerPos = player.getTransform().getPosition();
			AEVector moveDir = respawnPosition;
			
			
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
		*/
	}
	/*
	public void onCollideEnter( AEGameObject other) {
		super.onCollideEnter( other);
		if( other.isTypeOf( Player.class)) {
			onDeath();
		}
}
*/
	public void update(float deltaTime, GameContainer gc) {
		super.update( deltaTime, gc);
		
		GameLevel level = GameLevel.getGameLevel();
		float radius = level.getStageRadius();
		float radius2 = radius * radius;
		AEVector monsterPosition = getTransform().getPosition();
		float distFromZero = monsterPosition.lengthSqrt();
		if (distFromZero >= radius2) {
			onDeath();
		}
	}
}
