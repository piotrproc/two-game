/**
 * Created by Piotr Proc on 27.12.15.
 */

function attack(pointedSprite) {

    var bullet = bulletPool.getFirstDead();
    if (bullet === null || bullet === undefined)
        return;

    bullet.revive(); // Revive the bullet, this makes the bullet "alive"
    bullet.reset(
        movingSprite.position.x + fieldSize / 2,
        movingSprite.position.y + fieldSize / 2
    );

    var bulletMove = game.add.tween(bullet).to(
        {
            x: pointedSprite.position.x + fieldSize / 2,
            y: pointedSprite.position.y + fieldSize / 2
        }, 2000, Phaser.Easing.Linear.None, true);

    bulletMove.onComplete.addOnce(bulletReachedTarget, bullet);
    sendUserUpdate(null, pointedSprite);
}

function bulletReachedTarget() {
    this.kill();
}