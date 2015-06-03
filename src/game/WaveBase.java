package game;

import java.util.LinkedList;

import engine.object.AEGameObject;
import game.character.Monster;
import game.character.Monster_Attack;
import game.character.Monster_Boss;
import game.character.Monster_Nomove;
import game.character.Monster_Standard;

public class WaveBase extends AEGameObject {
	
	LinkedList<Class> listMonster;
	
	public WaveBase() {
		listMonster = new LinkedList<Class>();
		addMonster(Monster_Attack.class, 70);
		addMonster(Monster_Boss.class, 15);
		addMonster(Monster_Nomove.class,15);
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
