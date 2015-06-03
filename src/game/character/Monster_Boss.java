package game.character;

import engine.base.AEMath;
import game.DrawOrder;

public class Monster_Boss extends Monster {
	
	public Monster_Boss(){
        setEasy();
	}
	
    public void setEasy(){
	    setObjectName("Monster");
		
		createSprite("res/images/monster/attack_boss.jpg");
		getSprite().setDrawOrder( DrawOrder.CHARACTER.ordinal());
		
		createCollider( 20.0f);
		isAlive = true;
		
		acceleratedRatio = 0.25f;
		deacceleratedRatio = 0.1f;		
		movementSpeed = AEMath.getRandomRange( minspeedlist[1],maxspeedlist[1]);
		
		// test
		this.healthMax = this.healthMaxlist[1]*10.0f;
		this.health = this.healthMax;
		
		this.setIndex(4);
	};
	public void setNormal(){
	    setObjectName("Monster");
		
	    createSprite("res/images/monster/attack_boss.jpg");
		getSprite().setDrawOrder( DrawOrder.CHARACTER.ordinal());
		
		createCollider( 20.0f);
		isAlive = true;
		
		acceleratedRatio = 0.25f;
		deacceleratedRatio = 0.1f;		
		movementSpeed = AEMath.getRandomRange( minspeedlist[3],maxspeedlist[3]);
		
		// test
		this.healthMax = this.healthMaxlist[3]*10.0f;
		this.health = this.healthMax;
		
		this.setIndex(5);
	};
	public void setHard(){
	    setObjectName("Monster");
		
	    createSprite("res/images/monster/attack_boss.jpg");
		getSprite().setDrawOrder( DrawOrder.CHARACTER.ordinal());
		
		createCollider(25.0f);
		isAlive = true;
		
		acceleratedRatio = 0.25f;
		deacceleratedRatio = 0.1f;		
		movementSpeed = AEMath.getRandomRange( minspeedlist[5],maxspeedlist[5]);
		
		// test
		this.healthMax = this.healthMaxlist[5]*10.0f;
		this.health = this.healthMax;
		
		this.setIndex(6);
		
		
		
	};
}
