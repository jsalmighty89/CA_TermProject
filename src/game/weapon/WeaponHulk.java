package game.weapon;

import engine.base.AEMath;
import engine.base.AEVector;
import engine.framework.AEFramework;
import game.GameLevel;
import org.newdawn.slick.GameContainer;

/**
 * Created by daehyun on 15. 6. 4..
 */
public class WeaponHulk extends Weapon {

    protected static final float MOTION_RATE = 0.005f;
    protected float motionElapsed;
    int imgChangeIndex;
    protected boolean isFiring = false;


    public WeaponHulk() {
        setObjectName("HulkAttack");


        motionElapsed = 0.0f;
        imgChangeIndex = 0;
        chargeTime = 0.00000000008f;
        damage = 30.0f;
    }

    public void onFire() {
        AEVector mousePosition = GameLevel.getGameLevel().getPlayer().getMouseTargetPosition();

        if( mousePosition != null) {



            // create projectile
            ProjectileHulk ph = new ProjectileHulk( this);
            ph.setTargetPosition(transform.getPosition(), mousePosition);
            AEFramework.getInstance().addToSceneRoot(ph);
            ph.setVisible(false);
        }
    }



    //공격 모션 업데이트 추가
    public void update(float deltaTime, GameContainer gc) {

        if (chargeTimeElapsed >= chargeTime) {
            if (isFireDown) {
                onMotionChange();
            }
            if (isFiring) {
                atkMotionUpdate(deltaTime);  // 공격시 모션 변경
            }
        } else {
            chargeTimeElapsed += deltaTime;
        }
    }

    public void atkMotionUpdate(float deltaTime) {
        if(imgChangeIndex == 0) {
            getOwner().getSprite().setSpriteIndex(1);
            imgChangeIndex++;
        }
        if (motionElapsed >= MOTION_RATE) {
            if (imgChangeIndex == 1) {
                getOwner().getSprite().setSpriteIndex(2);
                imgChangeIndex++;
                motionElapsed = 0.0f;
                return ;
            }
            if (imgChangeIndex == 2) {
                getOwner().getSprite().setSpriteIndex(0);
                motionElapsed = 0.0f;
                chargeTimeElapsed = 0.0f;
                isFiring = false;
                imgChangeIndex = 0;
                onFire();
            }
        } else {
            motionElapsed += deltaTime;
        }

    }

    public void onMotionChange() {
        isFiring = true;
    }


}
