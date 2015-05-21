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
import game.GroundTile;
import game.character.Monster;
import game.character.Player;
import game.character.PlayerKJS;


public class GameLevel extends AELevel {
	
	protected PlayerKJS player;
	protected GroundTile ground;
	
	protected LinkedList<Monster> listMonsterRespawned;
	protected float monsterRespawnTime = 5.0f;
	protected float monsterRespawnTimeElapsed = 4.0f;
	
	
	public GameLevel() {
		this.objectName = "GameLevel";
	}
	
	protected void _initLevel() {
		player = new PlayerKJS();
		AEFramework.getInstance().addToSceneRoot( player);
		
		listMonsterRespawned = new LinkedList<Monster>();
		
		
		ground = new GroundTile();
		AEFramework.getInstance().addToSceneRoot( ground);
	}
	
	public void onGameOver() {
		
	}
	
	protected void _updateGame( float deltaTime, Input input) {
		AECamera2D camera = AEFramework.getInstance().getActiveCamera();

		AEVector cameraPos = camera.getTransform().getPosition();
		
		camera.getTransform().setPosition( AEVector.lerp( cameraPos, player.getTransform().getPosition(), 0.05f));
		
		
		// monster respawn
		monsterRespawnTimeElapsed += deltaTime;
		if( monsterRespawnTimeElapsed >= monsterRespawnTime) {
			int respawnCount = (int)AEMath.getRandomRange( 10.0f, 20.0f);
			for( int i=0; i<respawnCount; i++)
				respawnMonster();
			monsterRespawnTimeElapsed = 0.0f;
		}
	}
	
	protected void respawnMonster() {
		float rad = AEMath.getRandomRange( 0.0f, 6.28f);
		float distance = AEMath.getRandomRange( 300.0f, 500.0f);
		AEVector respawnPosition = new AEVector( (float)Math.sin( rad)*distance, (float)Math.cos( rad)*distance);
		
		Monster monster = new Monster();
		monster.getTransform().setPosition( respawnPosition);
		AEFramework.getInstance().addToSceneRoot( monster);
		
		listMonsterRespawned.add( monster);
	}
}