package game;

import game.character.*;

public class WaveTypeB extends WaveBase {
	public WaveTypeB() {
		this.addMonster( Monster_Standard.class, 5);
		this.addMonster( Monster_Attack.class, 10);
	}
}
