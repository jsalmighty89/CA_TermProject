

import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Transform;

import engine.framework.AEFramework;
import engine.object.SpriteTest;

public class Test extends BasicGame{
	
	AEFramework framework;
	
	SpriteTest a,b,c;

	public Test(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws SlickException {
		// TODO Auto-generated method stub
		AppGameContainer app = new AppGameContainer( new Test("TestWindow"));
		
		app.setDisplayMode( 1280, 720, false);
		app.start();
	}

	@Override
	public void render(GameContainer gc, Graphics graphic) throws SlickException {		
		framework.render( graphic);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		// TODO Auto-generated method stub

		framework = AEFramework.getInstance();
		
		TestLevel level = new TestLevel();
		framework.addLevel( level);
		framework.setLevel( "TestLevel");
		
		gc.setTargetFrameRate( 60);
		gc.setVSync( true);
	}

	@Override
	public void update(GameContainer gc, int dt) throws SlickException {
		float deltaTime = dt * 0.001f;
		
		framework.update( deltaTime, gc);
	}

}
