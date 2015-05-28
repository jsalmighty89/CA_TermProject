package game;

import engine.object.AEGameObject;

public class GameLogic extends AEGameObject {
	
	protected int currentWave;
	
	protected WaveBase wave;
	
	public GameLogic() {
		wave = new WaveBase();
	}
	
	public void PushNextWave() {
		
	}
}
