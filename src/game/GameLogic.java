package game;

import java.util.LinkedList;

import org.newdawn.slick.GameContainer;

import engine.base.AEMath;
import engine.base.AEVector;
import engine.framework.AEFramework;
import engine.object.AEGameObject;
import game.Item.Item;
import game.character.Monster;
import game.character.Player;

public class GameLogic extends AEGameObject {
	protected int currentWave;
	protected LinkedList<Class> listMonsterRespawn;

	protected float monsterRespawnTime = 1.2f;
	protected float monsterRespawnTimeElapsed = 4.0f;

	protected float itemRespawnTime = 10.0f;
	protected float itemRespawnTimeElapsed = 6.0f;

	protected LinkedList<WaveBase> listWave;
	protected int monsterMax;
	protected int monsterRemain;

	public GameLogic() {
		// wave list
		listWave = new LinkedList<WaveBase>();
		listWave.add( new WaveTypeA());
		listWave.add( new WaveTypeB());
		listWave.add( new WaveTypeC());
		listWave.add( new WaveTypeD());
		listWave.add( new WaveTypeE());
		
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
		player.getTransform().setPosition(new AEVector(0.0f, 0.0f, 0.0f));

		onWaveBegin();
	}

	public void onGameOver() {
		UIManager.getUIManager().setMainMessage("Game Over!");
		AEFramework.getInstance().setUpdateTimeRatio(0.25f);
	}

	public void onWaveBegin() {
		pushNextWave();
		currentWave++;
		UIManager.getUIManager().setMainMessage(
				"Wave " + currentWave + " Start!", 2.0f);
	}

	public void onWaveEnd() {
		UIManager.getUIManager().setMainMessage("Wave Clear!!", 2.0f);
		GameLevel.getGameLevel().flushMonsterRespawned();
		onWaveBegin();
	}

	public void onMonsterDeath(Monster monster) {
		boolean isRemoved = GameLevel.getGameLevel().removeMonsterRespawned(monster);

		// avoid duplicate death
		if( isRemoved) {
			monsterRemain--;
			if (monsterRemain <= 0) {
				monsterRemain = 0;
				onWaveEnd();
			}
		}
	}

	public void pushNextWave() {
		WaveBase wave = listWave.get( currentWave % listWave.size());
		LinkedList<Class> listNextWave = wave.getMonsterList();
		int monsterCount = listNextWave.size();
		for (int i = 0; i < monsterCount; i++) {
			listMonsterRespawn.add(listNextWave.get(i));
		}
		monsterMax = monsterCount;
		monsterRemain = monsterMax;
	}

	public void update(float deltaTime, GameContainer gc) {
		// update monster wave respawn
		if (listMonsterRespawn.size() > 0) {
			updateRespawn(deltaTime);
		}

		// update item respawn
		itemRespawnTimeElapsed += deltaTime;
		if (itemRespawnTimeElapsed >= itemRespawnTime) {
			itemRespawnTimeElapsed = 0.0f;
			int respawnCount = AEMath.getRandomRange(0, 3);
			for (int i = 0; i < respawnCount; i++) {
				respawnItem();
			}
		}

		// make sure player in stage radius
		GameLevel level = GameLevel.getGameLevel();
		Player player = level.getPlayer();
		float radius = level.getStageRadius();
		AEVector playerPosition = player.getTransform().getPosition();
		float distFromZero = playerPosition.length();
		if (distFromZero >= radius) {
			playerPosition.normalize();
			// reverse position vector
			player.addForce(AEVector.sub(new AEVector(), playerPosition),
					500.0f);
		}
	}

	public void updateRespawn(float deltaTime) {
		monsterRespawnTimeElapsed += deltaTime;
		if (monsterRespawnTimeElapsed >= monsterRespawnTime) {
			monsterRespawnTimeElapsed = 0.0f;
			int respawnCount = AEMath.getRandomRange(5, 10);
			respawnCount = Math.min(respawnCount, listMonsterRespawn.size());
			for (int i = 0; i < respawnCount; i++) {
				int index = AEMath.getRandomRange(0,
						listMonsterRespawn.size() - 1);
				Class cls = listMonsterRespawn.get(index);
				respawnMonster(cls);
				listMonsterRespawn.remove(cls);
			}
		}
	}

	protected void respawnMonster(Class cls) {
		float rad = AEMath.getRandomRange(0.0f, 6.28f);
		float distance = AEMath.getRandomRange(300.0f, 500.0f);
		AEVector respawnPosition = new AEVector((float) Math.sin(rad)
				* distance, (float) Math.cos(rad) * distance);

		try {
			Monster monster = (Monster) cls.newInstance();
			monster.getTransform().setPosition(respawnPosition);
			AEFramework.getInstance().addToSceneRoot(monster);

			// Add to respawned monster list at GameLevel
			GameLevel.getGameLevel().addMonsterRespawned(monster);

			if (currentWave <= 10)
				monster.initMonsterStatEasy();
			else if (currentWave <= 20)
				monster.initMonsterStatNormal();
			else
				monster.initMonsterStatHard();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	protected void respawnItem() {
		float rad = AEMath.getRandomRange(0.0f, 6.28f);
		float distance = AEMath.getRandomRange(300.0f, 500.0f);
		AEVector respawnPosition = new AEVector((float) Math.sin(rad)
				* distance, (float) Math.cos(rad) * distance);

		Item item = new Item();
		item.getTransform().setPosition(respawnPosition);
		AEFramework.getInstance().addToSceneRoot(item);

	}

}
