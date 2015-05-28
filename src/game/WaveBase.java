package game;

import java.util.LinkedList;

import engine.object.AEGameObject;
import game.character.Monster;

public class WaveBase extends AEGameObject {
	
	LinkedList<Class> listMonster;
	
	public WaveBase() {
		
	}
	
	public LinkedList<Class> getMonsterList() {
		return listMonster;
	}
	
	protected void addMonster( Class monsterClass) {
		listMonster.add( monsterClass);
	}
}
