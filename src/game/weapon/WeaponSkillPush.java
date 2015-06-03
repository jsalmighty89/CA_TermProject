package game.weapon;

import java.util.LinkedList;

import engine.base.AEMath;
import engine.base.AEVector;
import game.GameLevel;
import game.character.Monster;

public class WeaponSkillPush extends WeaponSkill {
	
	protected float pushRange = 300.0f;
	protected float pushPower = 500.0f;
	
	public WeaponSkillPush() {
		setObjectName( "Push Skill");
	}
	
	public void onFire() {
		super.onFire();
		
		System.out.println( "Skill");
		
		AEVector position = this.getOwner().getTransform().getPosition();
		
		LinkedList<Monster> listMonsterHit = GameLevel.getGameLevel().getMonsterAround( position, pushRange);
		for( Monster monster : listMonsterHit) {
			AEVector direction = AEVector.sub( monster.getTransform().getPosition(), position);
			direction.normalize();
			float additionalForce = AEMath.getRandomRange( 0.0f, pushPower*0.5f);
			monster.addForce( direction, pushPower + additionalForce);
		}
	}
}
