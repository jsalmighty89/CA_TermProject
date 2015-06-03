package game.character;

import engine.base.AEMath;
import engine.base.AEVector;
import engine.object.AEGameObject;
import game.DrawOrder;

// monsterÀÇ ºÐ·ù - 

public class Monster_Attack extends Monster {
	
	public Monster_Attack(){
        this.setHard();
	}
	
	public void setEasy(){
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
	public void setNormal(){
		    setObjectName("Monster");
			
		    createSprite("res/images/monster/attack.jpg");
			getSprite().setDrawOrder( DrawOrder.CHARACTER.ordinal());
			
			createCollider( 20.0f);
			isAlive = true;
			
			acceleratedRatio = 0.25f;
			deacceleratedRatio = 0.1f;		
			movementSpeed = AEMath.getRandomRange( minspeedlist[5],maxspeedlist[5]);
			
			// test
			this.healthMax = this.healthMaxlist[5];
			this.health = this.healthMax;
			
			this.setIndex(5);
			
			
		
	};
	public void setHard(){
	        setObjectName("Monster");
			
	        createSprite("res/images/monster/attack.jpg");
			getSprite().setDrawOrder( DrawOrder.CHARACTER.ordinal());
			
			createCollider( 20.0f);
			isAlive = true;
			
			acceleratedRatio = 0.25f;
			deacceleratedRatio = 0.1f;		
			movementSpeed = AEMath.getRandomRange( minspeedlist[6],maxspeedlist[6]);
			
			// test
			this.healthMax = this.healthMaxlist[6];
			this.health = this.healthMax;
			
			this.setIndex(6);
	
		
	};
	

}
