package game.weapon;

import engine.base.AEVector;
import engine.framework.AEFramework;
import engine.object.AEGameObject;
import engine.render.AESprite;
import game.DrawOrder;
import game.GameLevel;
import game.character.Monster;
import org.newdawn.slick.GameContainer;

import java.util.LinkedList;

/**
 * Created by daehyun on 15. 6. 4..
 */
public class ProjectileHulk extends ProjectileGrenade {

    protected static final float EFFECT_RATE = 0.035f;
    protected float effectElapsed;
    int imgChangeIndex;

    AEGameObject effect;


    public ProjectileHulk( Weapon firedFrom) {
        super(firedFrom);

        effect = new AEGameObject();

        AEFramework.getInstance().addToSceneRoot(effect);


        effect.createSprite("res/images/BDHEffect1.png");
        effect.getSprite().loadImage("res/images/BDHEffect2.png");
        effect.getSprite().loadImage("res/images/BDHEffect3.png");
        effect.getSprite().loadImage("res/images/BDHEffect4.png");
        effect.getSprite().loadImage("res/images/BDHEffect5.png");
        effect.getSprite().loadImage("res/images/BDHEffect6.png");
        effect.getSprite().loadImage("res/images/BDHEffect7.png");

        effect.getSprite().setDrawOrder(DrawOrder.EFFECT.ordinal());
        effect.setVisible(false);

        targetPosition = new AEVector();
        elapsedTime = 0.0f;
        targetTime = lifeTime * 0.5f;

        effectElapsed = -0.05f;
        imgChangeIndex = 0;

        explosionRange = 20.0f;
        lifeTime = 0.00000005f;
    }


    public void onExplosion() {
        LinkedList<Monster> listMonsterHit = GameLevel.getGameLevel().getMonsterAround( getTransform().getPosition(),
                explosionRange);

        for( Monster monster : listMonsterHit) {
            AEVector direction = AEVector.sub( monster.getTransform().getPosition(), getTransform().getPosition());
            direction.normalize();
            monster.onTakeDamage(firedFrom);
        }






    }

    public void update( float deltaTime, GameContainer gc) {
        lifeTime -= deltaTime;
        getTransform().setPosition( AEVector.lerp( startPosition, targetPosition, 0.2f));
        if( lifeTime < 0.0f) {
            onExplosion();
            effectUpdate(deltaTime);


        }

    }


    public void effectUpdate(float deltaTime) {
            effect.getTransform().setPosition(this.getTransform().getPosition());
            effect.setVisible(true);

        if (effectElapsed >= EFFECT_RATE) {
            if (imgChangeIndex == 0) {
                effect.getSprite().setSpriteIndex(1);
                imgChangeIndex++;
                effectElapsed += deltaTime;
            }
            else if (imgChangeIndex == 1) {
                    effect.getSprite().setSpriteIndex(2);
                    imgChangeIndex++;
                    effectElapsed = 0.0f;
            }
            else if (imgChangeIndex == 2) {
                effect.getSprite().setSpriteIndex(3);
                imgChangeIndex++;
                effectElapsed = 0.0f;
            }
            else if (imgChangeIndex == 3) {
                effect.getSprite().setSpriteIndex(4);
                imgChangeIndex++;
                effectElapsed = 0.0f;
            }
            else if (imgChangeIndex == 4) {
                effect.getSprite().setSpriteIndex(5);
                imgChangeIndex++;
                effectElapsed = 0.0f;
            }
            else if (imgChangeIndex == 5) {
                effect.getSprite().setSpriteIndex(6);
                effectElapsed = 0.0f;

                AEFramework.getInstance().removeFromScene(effect);
                AEFramework.getInstance().removeFromScene( this);
            }

        }
        else {
            effectElapsed += deltaTime;
        }
    }

}
