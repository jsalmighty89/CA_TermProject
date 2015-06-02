package game.weapon;

import engine.framework.AEFramework;
import engine.object.AEGameObject;
import game.character.*;
import game.character.Character;

/**
 * Created by daehyun on 15. 6. 2..
 */
public class ProjectileBeam extends Projectile {

    public ProjectileBeam(Weapon firedFrom) {

        super(firedFrom);

        this.createCollider(3.0f);
        this.setObjectName("Projectile");
        this.createSprite("res/images/beam.png");

        lifeTime = 2.0f;
        bulletSpeed = 1000.0f;
    }

    public void onCollide(AEGameObject collider) {
        super.onCollide(collider);

        if (firedFrom.getOwner() == collider)
            return;

        if (collider.isTypeOf(game.character.Character.class)) {
            ((Character) collider).onTakeDamage(firedFrom);
            AEFramework.getInstance().removeFromScene(this);
        }
    }
}
