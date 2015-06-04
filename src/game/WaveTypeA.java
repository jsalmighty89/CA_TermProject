package game;

import game.character.*;

public class WaveTypeA extends WaveBase {
	public WaveTypeA() {
		/*
		this.addMonster( Monster_Standard.class, 10);
		this.addMonster( Monster_Attack.class, 5);
		*/
		//this.addMonster( Monster_Standard.class, 10);
		//this.addMonster( Monster_Attack.class, 10);
		this.addMonster( Monster_Bomb.class, 10);
		this.addMonster( Monster_Nomove.class, 10);
	}
}
