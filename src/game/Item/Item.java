package game.Item;

import org.newdawn.slick.GameContainer;

import engine.framework.AEFramework;
import engine.object.AEGameObject;
import game.character.Player;
import game.character.PlayerSHK;

public class Item extends AEGameObject {
	
protected float lifeTime;
protected float healthUp;	
	public Item() {
		 
		this.setObjectName( "Item");
		this.createSprite( "res/images/rainbow.png");
		this.createCollider( 10.0f);
		healthUp = 50.0f;
		lifeTime = 20.0f;
	}
	
	public void update( float deltaTime, GameContainer gc) {
		lifeTime -= deltaTime;
		if( lifeTime < 0.0f) {
			AEFramework.getInstance().removeFromScene( this);
		}
		
	}
	
	public void onCollide(AEGameObject collider) {
		super.onCollide(collider);
		
		if( collider.isTypeOf( Player.class)) {
			System.out.println("Health UP ++");
			((Player)collider).addHealth( healthUp);
			
			AEFramework.getInstance().removeFromScene( this);
		}
	}

}
