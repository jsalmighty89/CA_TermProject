package game;

import java.util.LinkedList;

import engine.object.AEGameObject;
import game.character.Monster;

public class WaveBase extends AEGameObject {
	
	LinkedList<Class> listMonster;
	
	public WaveBase() {
		listMonster = new LinkedList<Class>();
		addMonster( Monster.class, 10);
	}
	
	public LinkedList<Class> getMonsterList() {
		return listMonster;
	}
	
	protected void addMonster( Class monsterClass) {
		addMonster( monsterClass, 1);
	}
	protected void addMonster( Class monsterClass, int count) {
		for( int i=0; i<count; i++)
			listMonster.add( monsterClass);
	}
}
