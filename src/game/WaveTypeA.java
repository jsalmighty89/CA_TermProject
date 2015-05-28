package game;

import game.character.Monster;

public class WaveTypeA extends WaveBase {
	public WaveTypeA() {
		this.addMonster( Monster.class);
	}
}
