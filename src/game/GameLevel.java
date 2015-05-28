package game;

import java.util.LinkedList;

import org.newdawn.slick.Input;

import engine.base.AEMath;
import engine.base.AETransform;
import engine.base.AEVector;
import engine.framework.AEFramework;
import engine.framework.AELevel;
import engine.object.AECamera2D;
import engine.object.AEGameObject;
import engine.object.AEUIObject;
import game.GroundTile;
import game.character.*;


public class GameLevel extends AELevel {
	protected UIManager uiManager;
	
	protected Player player;
	protected GroundTile ground;

	protected float score;
	
	protected GameLogic gameLogic;
	
	public GameLevel() {
		this.objectName = "GameLevel";
	}
	public static GameLevel getGameLevel() {
		// get current 'active' game level
		return (GameLevel)AEFramework.getInstance().getActiveLevel();
	}
	
	protected void _initLevel() {
		// UIManager
		uiManager = new UIManager();
		AEFramework.getInstance().addToSceneRoot( uiManager);
		uiManager.setMainMessage( "GameStart");
		
		
		// Player
		player = new Player();
		AEFramework.getInstance().addToSceneRoot( player);
		
		// Ground
		ground = new GroundTile();
		AEFramework.getInstance().addToSceneRoot( ground);
		

		// Game Logic
		gameLogic = new GameLogic();
		AEFramework.getInstance().addToSceneRoot( gameLogic);
		
		gameLogic.onGameStart();
	}
	
	public Player getPlayer() {
		return player;
	}
	public GameLogic getGameLogic() {
		return gameLogic;
	}
	public UIManager getUIManager() {
		return uiManager;
	}
	
	public void addScore( float score) {
		this.score += score;
		
		System.out.println( "Score : " + (int)this.score);
	}
	
	protected void _updateGame( float deltaTime, Input input) {
		// camera update
		AECamera2D camera = AEFramework.getInstance().getActiveCamera();
		AEVector cameraPos = camera.getTransform().getPosition();		
		camera.getTransform().setPosition( AEVector.lerp( cameraPos, player.getTransform().getPosition(), 0.05f));
	}
}