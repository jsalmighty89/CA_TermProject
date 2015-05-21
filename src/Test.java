import javax.swing.JFrame;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import engine.base.AEVector;
import engine.framework.AEFramework;
import game.GameLevel;

public class Test extends BasicGame{
	
	AEFramework framework;

	public Test(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws SlickException {
		// TODO Auto-generated method stub		
		
		AppGameContainer app = new AppGameContainer( new Test("TestWindow"));
		
		app.setDisplayMode( 640, 480, false);
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
		
		framework.getWindowInfo().setSize( 640, 480);
		
		GameLevel level = new GameLevel();
		framework.addLevel( level);
		framework.setLevel( "GameLevel");
		
		gc.setTargetFrameRate( 60);
		gc.setVSync( true);
	}

	@Override
	public void update(GameContainer gc, int dt) throws SlickException {
		float deltaTime = dt * 0.001f;
		
		framework.update( deltaTime, gc);
	}

}
