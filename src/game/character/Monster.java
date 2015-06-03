package game.character;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

import engine.base.AEMath;
import engine.base.AEVector;
import engine.framework.AEFramework;
import engine.object.AEGameObject;
import game.DrawOrder;
import game.GameLevel;

public class Monster extends Character {
	//7가지 데미지,체력,스피드
	public int index; //최대 0 ~6
    public float healthMaxlist[] = new float[7];
	public float damagelist[] = new float[7];
	public float minspeedlist[] = new float[7];
	public float maxspeedlist[] = new float[7];
	public Monster() {
		/*
		setObjectName("Monster");
		
		createSprite("res/images/player.png");
		getSprite().setDrawOrder( DrawOrder.CHARACTER.ordinal());
		
		createCollider( 20.0f);
		isAlive = true;
		
		acceleratedRatio = 0.25f;
		deacceleratedRatio = 0.1f;		
		movementSpeed = AEMath.getRandomRange( 50.0f, 100.0f);
		
		// test
		this.healthMax = 1.0f;
		this.health = this.healthMax;
		*/
	}
	
	public void setIndex(int index){
		this.index = index;
	}
	public float getDamage(){
		return damagelist[index];
	}
	public int getIndex(){
		return this.index;
	}
	{
		for(int i=0; i<healthMaxlist.length; i++)
			healthMaxlist[i] = 100*(i+1);
	}
	
	{
	for(int i=0; i<damagelist.length; i++)
		    damagelist[i] = 10*(i+1);	
    }
	{
		for(int i=0; i<minspeedlist.length; i++){
			minspeedlist[i] = 70*(i+1);
		}
	}
	{
		for(int i=0; i<maxspeedlist.length; i++){
			maxspeedlist[i] = 90*(i+1);
		}
	}

	
	public void onCollideEnter( AEGameObject other) {
		if( other.isTypeOf( Player.class)) {
			System.out.println( "Monster attacks Player");
			AEVector monsterPosition = transform.getPosition();
			AEVector playerPosition = other.getTransform().getPosition();
			AEVector direction = AEVector.sub( playerPosition, monsterPosition);
			direction.normalize();
			
			other.addForce( direction, 350.0f);
			
			Player player = (Player)other;
			player.decreaseHealth(getDamage());
		}
		else {
			AEVector monsterPosition = transform.getPosition();
			AEVector otherMonsterPosition = other.getTransform().getPosition();
			AEVector direction = AEVector.sub( otherMonsterPosition, monsterPosition);
			direction.normalize();
			
			other.addForce( direction, 100.0f);
		}
	}
	
	public void onDeath() {
		super.onDeath();
		GameLevel level = GameLevel.getGameLevel();
		level.addScore( 2.0f);
		
		level.getGameLogic().onMonsterDeath( this);
		
		AEFramework.getInstance().removeFromScene( this);
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
