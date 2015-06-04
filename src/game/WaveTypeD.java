package game;

import game.character.*;

public class WaveTypeD extends WaveBase {
	public WaveTypeD() {
		this.addMonster( Monster_Standard.class, 10);
		this.addMonster( Monster_Attack.class, 10);
		this.addMonster( Monster_Bomb.class, 5);
		this.addMonster( Monster_Nomove.class, 5);
	}
}
