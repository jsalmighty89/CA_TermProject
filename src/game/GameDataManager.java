package game;

import game.character.*;
import game.weapon.*;

import java.util.LinkedList;

public class GameDataManager {	
	
	protected LinkedList<Player> listPlayer;
	protected int selectedPlayerIdx;
	
	protected LinkedList<Weapon> listWeapon;	
	protected int selectedWeaponIdx[] = new int[3];
	
	private static GameDataManager _inst;
	public static GameDataManager getInstance() {
		if( _inst == null) {
			_inst = new GameDataManager();
		}
		return _inst;
	}
	
	public GameDataManager() {
		listPlayer = new LinkedList<Player>();
		listPlayer.add( new PlayerKJS());
		listPlayer.add( new PlayerBDH());
		listPlayer.add( new PlayerHEY());
		listPlayer.add( new PlayerJH());
		listPlayer.add( new PlayerSHK());
		
		selectedPlayerIdx = -1;
		
		listWeapon = new LinkedList<Weapon>();
		listWeapon.add( new WeaponRifle());
		listWeapon.add( new WeaponRifleAK47());
		listWeapon.add( new WeaponMelee()); // melee test

		for( int i=0; i<selectedWeaponIdx.length; i++) {
			selectedWeaponIdx[i] = -1;
		}
	}
	
	
	//////// Player
	public int getPlayerCount() {
		return listPlayer.size();
	}	
	public Player getPlayer( int idx) {
		return listPlayer.get( idx);
	}	
	public int getSelectedPlayerIdx() {
		return selectedPlayerIdx;
	}
	public void setSelectedPlayerIdx( int idx) {
		selectedPlayerIdx = idx;
	}
	
	////// Weapon
	public int getWeaponCount() {
		return listWeapon.size();
	}
	public void setSelectedWeaponIdx( int slot, int idx) {
		if( slot < 0 || slot >= selectedWeaponIdx.length)
			return;
		selectedWeaponIdx[slot] = idx;
	}
	public int getSelectedWeaponIdx( int slot) {
		if( slot < 0 || slot >= selectedWeaponIdx.length)
			return -1;
		return selectedWeaponIdx[slot];
	}
	public Weapon getSelectedWeapon( int slot) {
		// create new instance of weapon
		int selectedWeaponIdx = getSelectedWeaponIdx( slot);
		if( selectedWeaponIdx == -1) {
			return createInstance( listWeapon.get( 0));
		}		
		return createInstance( listWeapon.get( selectedWeaponIdx));
	}
	public Weapon createInstance( Weapon weapon) {
		try {
			return weapon.getClass().newInstance();			
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}		
		return null;
	}
}