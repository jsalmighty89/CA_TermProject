package game;

import game.character.*;

public class WaveTypeC extends WaveBase {
	public WaveTypeC() {
		this.addMonster( Monster_Attack.class, 10);
		this.addMonster( Monster_Bomb.class, 5);
		this.addMonster( Monster_Nomove.class, 5);
	}
}
