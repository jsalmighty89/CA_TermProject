import org.newdawn.slick.Input;

import engine.base.AEMath;
import engine.base.AETransform;
import engine.base.AEVector;
import engine.framework.AEFramework;
import engine.framework.AELevel;
import engine.object.AECamera2D;
import engine.object.AEGameObject;
import game.GroundTile;
import game.character.Monster;
import game.character.Player;



public class TestLevel extends AELevel {
	
	Player player;
	Monster monster[];
	GroundTile ground;
	
	public TestLevel() {
		this.objectName = "TestLevel";
	}
	
	protected void _initLevel() {
		
		player = new Player();
		AEFramework.getInstance().addToSceneRoot( player);
		
		monster = new Monster[10];
		//AEFramework.getInstance().addToSceneRoot( monster);
		
		for( int i=0; i<monster.length; i++) {
			monster[i] = new Monster();
			monster[i].getTransform().setPosition( new AEVector( AEMath.getRandomRange( 0.0f, 640.0f), AEMath.getRandomRange( 0.0f, 720.0f)));
			//monster[i].getTransform().setRotation( AEMath.deg2rad( AEMath.getRandomRange( 0.0f, 480.0f)));
			AEFramework.getInstance().addToSceneRoot( monster[i]);
		}
		
		ground = new GroundTile();
		AEFramework.getInstance().addToSceneRoot( ground);
	}
	
	protected void _updateGame( float deltaTime, Input input) {
		AECamera2D camera = AEFramework.getInstance().getActiveCamera();
		
		/*
		// fire wepaon
		if( input.isMousePressed( 0)) {
			Projectile projectile = new Projectile();
			projectile.getTransform().setPosition( position);
			projectile.setForward( new AEVector( dx, dy, 0.0f));
			projectile.getTransform().setRotation( rad + AEMath.deg2rad( 180.0f));
			AEFramework.getInstance().addToSceneRoot( projectile);
		}
		*/
		AEVector cameraPos = camera.getTransform().getPosition();
		
		camera.getTransform().setPosition( AEVector.lerp( cameraPos, player.getTransform().getPosition(), 0.05f));
	}
}