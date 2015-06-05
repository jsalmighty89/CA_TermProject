package game.character;

import engine.base.AEMath;
import engine.base.AEVector;
import engine.framework.AEFramework;
import engine.object.AECamera2D;
import engine.object.AEGameObject;
import game.DrawOrder;
import game.GameLevel;
import game.weapon.Weapon;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

public class PlayerBDH extends Player {
    // weapon
    protected static int weaponCount=4;
    protected int currentWeaponIdx;
    protected Weapon weapon[] = new Weapon[weaponCount];

    protected AEGameObject laserSight;
    protected float laserSightBlink;

    protected AEVector mouseWorldPos;

    public PlayerBDH() {
        setObjectName("Player");

        createSprite("res/images/BDHhulk.png");
        this.getSprite().loadImage("res/images/BDHatk1.png");  // 공격 모션 두개
        this.getSprite().loadImage("res/images/BDHatk2.png");
        getSprite().setDrawOrder( DrawOrder.CHARACTER.ordinal());

        createCollider( 20.0f);

        isAlive = true;

        acceleratedRatio = 0.25f;
        deacceleratedRatio = 0.1f;
        movementSpeed = 150.0f;

        currentWeaponIdx = 0;


    }












}
