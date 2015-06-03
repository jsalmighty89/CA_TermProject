package game.character;

import engine.base.AEMath;
import game.DrawOrder;

public class Monster_Nomove extends Monster {
	
	public Monster_Nomove(){
        setEasy();
	}
	
	public void setEasy(){
		    setObjectName("Monster");
			
		    createSprite("res/images/monster/nomove.jpg");
			getSprite().setDrawOrder( DrawOrder.CHARACTER.ordinal());
			
			createCollider( 20.0f);
			isAlive = true;
			
			acceleratedRatio = 0.25f;
			deacceleratedRatio = 0.1f;		
			movementSpeed = 0;
			
			// test
			this.healthMax = this.healthMaxlist[1];
			this.health = this.healthMax;
			
			this.setIndex(1);
		
		
	};
	public void setNormal(){
		    setObjectName("Monster");
			
		    createSprite("res/images/monster/nomove.jpg");
			getSprite().setDrawOrder( DrawOrder.CHARACTER.ordinal());
			
			createCollider( 20.0f);
			isAlive = true;
			
			acceleratedRatio = 0.25f;
			deacceleratedRatio = 0.1f;		
			movementSpeed = 0;
			
			// test
			this.healthMax = this.healthMaxlist[3];
			this.health = this.healthMax;
			
			this.setIndex(3);
			
	};
	public void setHard(){
	        setObjectName("Monster");
			
	        createSprite("res/images/monster/nomove.jpg");
			getSprite().setDrawOrder( DrawOrder.CHARACTER.ordinal());
			
			createCollider( 20.0f);
			isAlive = true;
			
			acceleratedRatio = 0.25f;
			deacceleratedRatio = 0.1f;		
			movementSpeed = 0;
			
			// test
			this.healthMax = this.healthMaxlist[5];
			this.health = this.healthMax;
			
			this.setIndex(5);
			
		
		
	};

}
