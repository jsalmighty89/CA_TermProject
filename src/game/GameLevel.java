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

	// game play stage radius
	protected float stageRadius = 750.0f;
	
	// temp score
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
		
		GameDataManager gdManager = GameDataManager.getInstance();
		
		// Player
		int selectedPlayerIdx = gdManager.getSelectedPlayerIdx();
		if( selectedPlayerIdx == -1) {
			System.out.println( "[WARN] NO PLAYER SELECTED!");
			selectedPlayerIdx = 0;
		}
		player = gdManager.getPlayer( selectedPlayerIdx);
		AEFramework.getInstance().addToSceneRoot( player);
		
		// Weapon Setting
		// temp select
		gdManager.setSelectedWeaponIdx( 0, 1);
		// bind weapon
		player.setWeapon( gdManager.getSelectedWeapon( 0), 0);
		player.setWeapon( gdManager.getSelectedWeapon( 1), 1);
		player.setWeapon( gdManager.getSelectedWeapon( 2), 2);
		player.setWeapon( gdManager.getSelectedWeapon( 3), 3);
		
		
		// Ground
		ground = new GroundTile();
		AEFramework.getInstance().addToSceneRoot( ground);
		
		// border line
		AEGameObject border = new AEGameObject();
		border.setObjectName( "Border");
		border.createSprite( "res/images/border.png");
		border.getSprite().setDrawOrder( DrawOrder.GROUND.ordinal());
		border.getTransform().setScale( stageRadius/(256.0f-15.0f));	// 256 is half-size of sprite 
		AEFramework.getInstance().addToSceneRoot( border);

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
	
	public float getStageRadius() { 
		return stageRadius;
	}
	public AEVector getStagePositionRandom() {
		float rad = AEMath.getRandomRange( 0.0f, 6.28f);
		return new AEVector( (float)Math.sin(rad) * stageRadius, (float)Math.cos(rad) * stageRadius);
	}
	
	protected void _updateGame( float deltaTime, Input input) {
		// camera update
		AECamera2D camera = AEFramework.getInstance().getActiveCamera();
		AEVector cameraPos = camera.getTransform().getPosition();		
		camera.getTransform().setPosition( AEVector.lerp( cameraPos, player.getTransform().getPosition(), 0.05f));
	}
}