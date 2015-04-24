package engine.base;

import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Transform;

import engine.framework.AEFramework;

public class Test extends BasicGame{

	float test=0.0f;
	
	AEFramework framework;
	Image img;

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
	public void render(GameContainer arg0, Graphics arg1) throws SlickException {		
		arg1.drawString( "BCD123", 10, 100);
		arg1.drawString( "Å×½ºÆ®ABCD", 10, 200);
		img.setRotation( test);
		arg1.drawImage( img,  200,  200);
	}

	@Override
	public void init(GameContainer arg0) throws SlickException {
		// TODO Auto-generated method stub
		img = new Image("Test.png");
		
		framework = new AEFramework();
	}

	@Override
	public void update(GameContainer arg0, int arg1) throws SlickException {
		// TODO Auto-generated method stub
		test += 1.0f * arg1*0.01f;
	}

}
