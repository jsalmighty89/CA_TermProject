import org.newdawn.slick.GameContainer;

import engine.base.AEVector;
import engine.framework.AEFramework;
import engine.object.SpriteTest;


public class Projectile extends SpriteTest {

	protected AEVector forward;
	protected float lifeTime;
	
	public Projectile(String file) {
		super(file);
		// TODO Auto-generated constructor stub
		
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
	
}
