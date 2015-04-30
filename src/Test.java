
/*
 			Run에 실패하고 다음 메시지가 뜹니다.

Exception in thread "main" java.lang.UnsatisfiedLinkError: no lwjgl in java.library.path
	at java.lang.ClassLoader.loadLibrary(ClassLoader.java:1865)
	at java.lang.Runtime.loadLibrary0(Runtime.java:870)
	at java.lang.System.loadLibrary(System.java:1119)
	at org.lwjgl.Sys$1.run(Sys.java:72)
	at java.security.AccessController.doPrivileged(Native Method)
	at org.lwjgl.Sys.doLoadLibrary(Sys.java:66)
	at org.lwjgl.Sys.loadLibrary(Sys.java:96)
	at org.lwjgl.Sys.<clinit>(Sys.java:117)
	at org.lwjgl.opengl.Display.<clinit>(Display.java:135)
	at org.newdawn.slick.AppGameContainer$1.run(AppGameContainer.java:39)
	at java.security.AccessController.doPrivileged(Native Method)
	at org.newdawn.slick.AppGameContainer.<clinit>(AppGameContainer.java:36)
	at Test.main(Test.java:28)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:483)
	at com.intellij.rt.execution.application.AppMain.main(AppMain.java:140)

Process finished with exit code 1
 */



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
