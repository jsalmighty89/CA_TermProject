package game.character;

import game.DrawOrder;
import game.weapon.WeaponRifle;

public class PlayerSHK extends Player {
	
	public PlayerSHK() {
		
		setObjectName("Nine");
		
		createSprite("res/images/sas.png");
		getSprite().setDrawOrder( DrawOrder.CHARACTER.ordinal());
		
		isAlive = true;
		
		acceleratedRatio = 0.25f;
		deacceleratedRatio = 0.1f;
		movementSpeed = 150.0f;
	}

}
