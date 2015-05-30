package game;

import java.util.LinkedList;

import org.newdawn.slick.GameContainer;

import engine.base.AEMath;
import engine.base.AEVector;
import engine.framework.AEFramework;
import engine.object.AEGameObject;
import game.character.Monster;
import game.character.Player;

public class GameLogic extends AEGameObject {
	protected int currentWave;
	protected LinkedList<Class> listMonsterRespawn;
	
	protected float monsterRespawnTime = 5.0f;
	protected float monsterRespawnTimeElapsed = 4.0f;
	
	protected WaveBase wave;
	protected int monsterMax;
	protected int monsterRemain;
	
	public GameLogic() {
		wave = new WaveBase();
		listMonsterRespawn = new LinkedList<Class>();
	}
	
	public int getCurrentWave() {
		return currentWave;
	}
	public int getMonsterMax() {
		return monsterMax;
	}
	public int getMonsterRemain() {
		return monsterRemain;
	}
	
	public void onGameStart() {
		currentWave = 0;
		Player player = GameLevel.getGameLevel().getPlayer();
		player.getTransform().setPosition( new AEVector( 0.0f, 0.0f, 0.0f));
		
		onWaveBegin();
	}
	public void onGameOver() {
		UIManager.getUIManager().setMainMessage( "Game Over!");
		AEFramework.getInstance().setUpdateTimeRatio( 0.25f);
	}
	public void onWaveBegin() {
		currentWave++;
		pushNextWave();
		UIManager.getUIManager().setMainMessage( "Wave " + currentWave + " Start!", 2.0f);
	}
	public void onWaveEnd() {
		UIManager.getUIManager().setMainMessage( "Wave Clear!!", 2.0f);
		onWaveBegin();
	}
	
	public void onMonsterDeath( Monster monster) {
		monsterRemain--;
		if( monsterRemain <= 0) {
			monsterRemain = 0;
			onWaveEnd();
		}
	}
	
	public void pushNextWave() {
		LinkedList<Class> listNextWave = wave.getMonsterList();
		int monsterCount = listNextWave.size();
		for( int i=0; i<monsterCount; i++) {
			listMonsterRespawn.add( listNextWave.get( i));
		}
		monsterMax = monsterCount;
		monsterRemain = monsterMax;
	}
	
	protected void respawnMonster( Class cls) {
		float rad = AEMath.getRandomRange( 0.0f, 6.28f);
		float distance = AEMath.getRandomRange( 300.0f, 500.0f);
		AEVector respawnPosition = new AEVector( (float)Math.sin( rad)*distance, (float)Math.cos( rad)*distance);
		
		try {
			Monster monster = (Monster)cls.newInstance();
			monster.getTransform().setPosition( respawnPosition);
			AEFramework.getInstance().addToSceneRoot( monster);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}	
	}
	
	public void update(float deltaTime, GameContainer gc) {
		if( listMonsterRespawn.size() > 0) {
			updateRespawn( deltaTime);
		}
	}
	public void updateRespawn( float deltaTime) {
		monsterRespawnTimeElapsed += deltaTime;
		if( monsterRespawnTimeElapsed >= monsterRespawnTime) {
			monsterRespawnTimeElapsed = 0.0f;
			int respawnCount = AEMath.getRandomRange( 5, 10);
			respawnCount = Math.min( respawnCount, listMonsterRespawn.size());
			for( int i=0; i<respawnCount; i++) {
				int index = AEMath.getRandomRange( 0, listMonsterRespawn.size()-1);
				Class cls = listMonsterRespawn.get( index);
				respawnMonster( cls);
				listMonsterRespawn.remove( cls);
			}
		}
	}
}
