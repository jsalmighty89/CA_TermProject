package game.weapon;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

import engine.base.AEMath;
import engine.base.AEVector;
import engine.framework.AEFramework;
import engine.object.AECamera2D;
import engine.object.AEGameObject;
import game.DrawOrder;
import game.GameLevel;
import game.character.Player;

public class WeaponSkillWarp extends WeaponSkill {
	
	protected AEGameObject player2;
	
	public WeaponSkillWarp() {
		setObjectName( "Skill Warp");
	}
	
	public void onFire() {
		super.onFire();
		
		Player player = GameLevel.getGameLevel().getPlayer();
		if(player != null){
		GameContainer gc = null;
		float deltaTime;
	    Input input = gc.getInput();
	   
	    AEVector position = player.getTransform().getPosition();
		AEVector moveDir = new AEVector();
		
		// mouse targeting
		
		moveDir.x = input.getMouseX();
		moveDir.y = input.getMouseY();
		
		}

     }
}
