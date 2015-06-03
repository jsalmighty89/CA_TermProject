package game.character;

import engine.base.AEMath;
import game.DrawOrder;

public class Monster_Boom extends Monster {
	
	public Monster_Boom(){
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
			movementSpeed = AEMath.getRandomRange( minspeedlist[4],maxspeedlist[4]);
			
			// test
			this.healthMax = this.healthMaxlist[0];
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
		this.healthMax = this.healthMaxlist[2];
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
		this.healthMax = this.healthMaxlist[4];
		this.health = this.healthMax;
		
		this.setIndex(6);
		
		
	
		
	};

}
