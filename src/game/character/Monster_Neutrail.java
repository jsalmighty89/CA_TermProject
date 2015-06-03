package game.character;

import engine.base.AEMath;
import game.DrawOrder;

public class Monster_Neutrail extends Monster {
	
	public Monster_Neutrail(){
        setEasy();
	}
	
	public void setEasy(){
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
	public void setNormal(){
		    setObjectName("Monster");
			
		    createSprite("res/images/monster/attack.jpg");
			getSprite().setDrawOrder( DrawOrder.CHARACTER.ordinal());
			
			createCollider( 20.0f);
			isAlive = true;
			
			acceleratedRatio = 0.25f;
			deacceleratedRatio = 0.1f;		
			movementSpeed = AEMath.getRandomRange( minspeedlist[3],maxspeedlist[3]);
			
			// test
			this.healthMax = this.healthMaxlist[3];
			this.health = this.healthMax;
			
			this.setIndex(3);
		
		
	};
	public void setHard(){
	        setObjectName("Monster");
			
	        createSprite("res/images/monster/attack.jpg");
			getSprite().setDrawOrder( DrawOrder.CHARACTER.ordinal());
			
			createCollider( 20.0f);
			isAlive = true;
			
			acceleratedRatio = 0.25f;
			deacceleratedRatio = 0.1f;		
			movementSpeed = AEMath.getRandomRange( minspeedlist[4],maxspeedlist[4]);
			
			// test
			this.healthMax = this.healthMaxlist[4];
			this.health = this.healthMax;
			
			this.setIndex(4);
		
	};

}
