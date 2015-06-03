package game.character;

import engine.base.AEMath;
import game.DrawOrder;

public class Monster_Standard extends Monster {
	
	public Monster_Standard(){
        setHard();
	}
	
	public void setEasy(){
		    setObjectName("Monster");
			
		    createSprite("res/images/monster/attack.jpg");
			getSprite().setDrawOrder( DrawOrder.CHARACTER.ordinal());
			
			createCollider( 20.0f);
			isAlive = true;
			
			acceleratedRatio = 0.25f;
			deacceleratedRatio = 0.1f;		
			movementSpeed = AEMath.getRandomRange( minspeedlist[0],maxspeedlist[0]);
			
			// test
			this.healthMax = this.healthMaxlist[0];
			this.health = this.healthMax;
			
			this.setIndex(0);
			
		
	};
	public void setNormal(){
		    setObjectName("Monster");
			
		    createSprite("res/images/monster/attack.jpg");
			getSprite().setDrawOrder( DrawOrder.CHARACTER.ordinal());
			
			createCollider( 20.0f);
			isAlive = true;
			
			acceleratedRatio = 0.25f;
			deacceleratedRatio = 0.1f;		
			movementSpeed = AEMath.getRandomRange( minspeedlist[1],maxspeedlist[1]);
			
			// test
			this.healthMax = this.healthMaxlist[1];
			this.health = this.healthMax;
			
			this.setIndex(1);			
			
		
	};
	public void setHard(){
	        setObjectName("Monster");
			
	        createSprite("res/images/monster/attack.jpg");
			getSprite().setDrawOrder( DrawOrder.CHARACTER.ordinal());
			
			createCollider( 20.0f);
			isAlive = true;
			
			acceleratedRatio = 0.25f;
			deacceleratedRatio = 0.1f;		
			movementSpeed = AEMath.getRandomRange( minspeedlist[2],maxspeedlist[2]);
			
			// test
			this.healthMax = this.healthMaxlist[2];
			this.health = this.healthMax;
			
			this.setIndex(2);
			
			
		
	};
}
