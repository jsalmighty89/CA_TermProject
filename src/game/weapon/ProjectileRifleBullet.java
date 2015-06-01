package game.weapon;

import engine.base.AEMath;
import engine.framework.AEFramework;
import engine.object.AEGameObject;
import game.character.Character;

public class ProjectileRifleBullet extends Projectile {
	public ProjectileRifleBullet( Weapon firedFrom) {
		super( firedFrom);
		
		this.createCollider( 3.0f);
		
		bulletSpeed = AEMath.getRandomRange( 500.0f, 600.0f);
	}
	
	public void onCollide(AEGameObject collider) {
		super.onCollide(collider);
		
		if( firedFrom.getOwner() == collider)
			return;
		
		if( collider.isTypeOf( Character.class)) {
			((Character)collider).onTakeDamage( firedFrom);
			AEFramework.getInstance().removeFromScene( this);
		}
	}
}
