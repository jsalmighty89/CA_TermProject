package game;

import game.character.*;

public class WaveTypeE extends WaveBase {
	public WaveTypeE() {
		this.addMonster( Monster_Attack.class, 10);
		this.addMonster( Monster_Boss.class, 2);
	}
}
