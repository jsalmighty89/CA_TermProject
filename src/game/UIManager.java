package game;

import org.newdawn.slick.GameContainer;

import engine.base.AEVector;
import engine.framework.AEFramework;
import engine.object.AEGameObject;
import engine.object.AEUIObject;
import game.character.Player;
import game.weapon.*;

public class UIManager extends AEGameObject {
	
	protected float windowRight;
	protected float windowBottom;
	
	protected AEUIObject textMainMessage;
	protected float remainMainMessage;
	
	protected AEUIObject textCurrentWave;
	protected AEUIObject textMonsterCount;
	
	protected AEUIObject textPlayerHP;
	
	//weapon
	protected AEUIObject textWeaponName;
	protected AEUIObject textWeaponAmmo;
	protected AEUIObject textWeaponSelector;
	
	public UIManager() {
		windowRight = AEFramework.getInstance().getWindowInfo().getWidth();
		windowBottom = AEFramework.getInstance().getWindowInfo().getHeight();
		
		textMainMessage = createTextObject( "Default");
		textMainMessage.getTransform().setPosition( new AEVector( getCenterX(), getCenterY()));
		remainMainMessage = 0.0f;
		
		textCurrentWave = createTextObject( "Default");
		textCurrentWave.getTransform().setPosition( new AEVector( getCenterX(), 5.0f));
		textMonsterCount = createTextObject( "Default");
		textMonsterCount.getTransform().setPosition( new AEVector( getCenterX(), 35.0f));
		
		textPlayerHP = createTextObject( "Default");
		textPlayerHP.getTransform().setPosition( new AEVector( 60.0f, windowBottom - 40.0f));
		
		textWeaponName = createTextObject( "Default");
		textWeaponName.getTransform().setPosition( new AEVector( windowRight - 150.0f, windowBottom - 80.0f));
		
		textWeaponAmmo = createTextObject( "Default");
		textWeaponAmmo.getTransform().setPosition( new AEVector( windowRight - 150.0f, windowBottom - 40.0f));
		
		textWeaponSelector = createTextObject( "Default");
		textWeaponSelector.getTransform().setPosition( new AEVector( windowRight - 150.0f, windowBottom - 120.0f));
	}
	public static UIManager getUIManager() {
		return GameLevel.getGameLevel().getUIManager();
	}
	protected AEUIObject createTextObject( String fontType) {
		AEUIObject object = new AEUIObject();
		object.createText( fontType, "");
		AEFramework.getInstance().addToUIRoot( object);
		return object;
	}

	
	public float getCenterX() {
		return windowRight * 0.5f;
	}
	public float getCenterY() {
		return windowBottom * 0.5f;
	}
	
	public void setMainMessage( String message) {
		setMainMessage( message, 2.0f);
	}
	public void setMainMessage( String message, float time) {
		textMainMessage.getText().setText( message);
		remainMainMessage = time;
	}
	
	public void update( float deltaTime, GameContainer gc) {
		if( remainMainMessage <= 0.0f) {
			textMainMessage.getText().setText( "");
		}
		else {
			remainMainMessage -= deltaTime;
		}
		
		GameLogic gameLogic = GameLevel.getGameLevel().getGameLogic();
		
		// update wave
		String buffer = "Wave " + gameLogic.getCurrentWave();
		textCurrentWave.getText().setText( buffer);
		
		// update hp
		Player player = GameLevel.getGameLevel().getPlayer();
		if( player != null) {
			float hp = player.getHealth();
			textPlayerHP.getText().setText( "HP:" + (int)hp);
		}
		
		// update monster
		buffer = gameLogic.getMonsterRemain() + "/" + gameLogic.getMonsterMax();
		textMonsterCount.getText().setText( buffer);
		
		// update weapon info
		updateWeaponInfo();
	}
	
	protected void updateWeaponInfo() {
		Player player = GameLevel.getGameLevel().getPlayer();
		if( player == null) return;
		
		Weapon weapon = player.getCurrentWeapon();
		
		// name
		textWeaponName.getText().setText( weapon.getObjectName());
		
		// ammo
		if( weapon.isTypeOf( WeaponRifle.class)) {
			WeaponRifle rifle = (WeaponRifle)weapon;
			textWeaponAmmo.getText().setText( rifle.getAmmo() + "/" + rifle.getAmmoMax());
		}
		else if( weapon.isTypeOf( WeaponMelee.class)) {
			textWeaponAmmo.getText().setText( "-");
		}
		
		// weapon selector
		int currentWeaponIdx = player.getCurrentWeaponIdx();
		String buffer = "";
		for( int i=0; i<4; i++) {
			if( currentWeaponIdx == i)
				buffer += String.format( "[%d]", i+1);
			else
				buffer += String.format( " %d ", i+1);
		}
		textWeaponSelector.getText().setText( buffer);
	}
}
