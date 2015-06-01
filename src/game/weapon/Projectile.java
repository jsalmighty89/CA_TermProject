package game.weapon;

import org.newdawn.slick.GameContainer;

import game.character.Character;
import game.character.Player;
import engine.base.AEMath;
import engine.base.AEVector;
import engine.framework.AEFramework;
import engine.object.AEGameObject;

public class Projectile extends AEGameObject {
	protected Weapon firedFrom;
	
	protected AEVector forward;
	protected float lifeTime;
	protected float bulletSpeed;
	
	public Projectile( Weapon firedFrom) {
		this.firedFrom = firedFrom;
		
		this.setObjectName( "Projectile");
		this.createSprite( "res/images/bullet.png");
		
		lifeTime = 2.0f;
		bulletSpeed = 300.0f;
	}
	
	
	public void setForward( AEVector forward) {
		this.forward = forward;		
		this.forward.normalize();
	}
	
	public void update( float deltaTime, GameContainer gc) {
		lifeTime -= deltaTime;
		if( lifeTime < 0.0f) {
			AEFramework.getInstance().removeFromScene( this);
		}
		
		AEVector position = this.getTransform().getPosition();
		position.add( AEVector.multiply( forward, bulletSpeed * deltaTime));
		this.getTransform().setPosition( position);
	}

	@Override
	public void onCollide(AEGameObject collider) {
		super.onCollide(collider);
	}
}
