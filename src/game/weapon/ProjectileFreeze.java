package game.weapon;

import java.util.LinkedList;

import org.newdawn.slick.GameContainer;

import engine.base.AEVector;
import engine.framework.AEFramework;
import engine.object.AEGameObject;
import game.GameLevel;
import game.character.Character;
import game.character.Monster;

public class ProjectileFreeze extends Projectile {

	protected float elapsedTime;
	protected float targetTime;
	protected AEVector startPosition;
	protected AEVector targetPosition;

	protected float maximumRange = 500.0f;
	protected float explosionRange = 200.0f;

	public ProjectileFreeze(Weapon firedFrom) {
		super(firedFrom);
		this.createSprite("res/images/bullet2.png");

		targetPosition = new AEVector();
		
		this.createCollider( 3.0f);
		elapsedTime = 0.0f;
		lifeTime = 2.0f;
		targetTime = lifeTime * 0.5f;
	}

	public void setTargetPosition(AEVector startPosition,
			AEVector targetPosition) {
		this.startPosition = startPosition;
		this.targetPosition = targetPosition;
		getTransform().setPosition(this.startPosition);
	}

	public void onExplosion() {
		LinkedList<Monster> listMonsterHit = GameLevel.getGameLevel()
				.getMonsterAround(getTransform().getPosition(), explosionRange);

		for (Monster monster : listMonsterHit) {
			AEVector direction = AEVector.sub(monster.getTransform()
					.getPosition(), getTransform().getPosition());
			direction.normalize();
			monster.onFreeze();
			monster.onTakeDamage(firedFrom);
		}
	}

	@Override
	public void onCollide(AEGameObject collider) {
		super.onCollide(collider);

		if (firedFrom.getOwner() == collider)
			return;

		if (collider.isTypeOf(Monster.class)) {
			AEFramework.getInstance().removeFromScene(this);
			onExplosion();
		}
	}

	public void update(float deltaTime, GameContainer gc) {
		lifeTime -= deltaTime;
		if (lifeTime < 0.0f) {
			onExplosion();
			AEFramework.getInstance().removeFromScene(this);
		}

		elapsedTime += deltaTime;
		getTransform().setPosition(
				AEVector.lerp(startPosition, targetPosition, elapsedTime
						/ targetTime));
	}
}
