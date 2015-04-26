package engine.object;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


// TODO Test Class : DO NOT USE

public class SpriteTest extends AEGameObject {
	protected Image sprite;	
	
	public SpriteTest( String file) {
		try {
			sprite = new Image( file);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public Image getSprite() {
		return sprite;
	}
	
	public void update( float deltaTime, GameContainer gc) {
		
	}
}
