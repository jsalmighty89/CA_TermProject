package game.weapon;

import org.newdawn.slick.GameContainer;

import game.character.Character;
import engine.base.AEVector;
import engine.framework.AEFramework;
import engine.object.AEGameObject;

public class Projectile extends AEGameObject {
	protected Weapon firedFrom;
	
	protected AEVector forward;
	protected float lifeTime;
	
	public Projectile( Weapon firedFrom) {
		this.firedFrom = firedFrom;
		
		this.setObjectName( "Projectile");
		this.createSprite( "res/images/bullet.png");
		this.createCollider( 10.0f);
		
		lifeTime = 2.0f;
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
		position.add( AEVector.multiply( forward, 500.0f * deltaTime));
		this.getTransform().setPosition( position);
	}

	@Override
	public void onCollide(AEGameObject collider) {
		super.onCollide(collider);

		if( collider.isTypeOf( Character.class)) {
			((Character)collider).onTakeDamage( firedFrom);
		}
		
		AEFramework.getInstance().removeFromScene( this);
	}
}
