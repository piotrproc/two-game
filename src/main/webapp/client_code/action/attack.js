/**
 * Created by Piotr Proc on 27.12.15.
 */

function attack(pointedSprite){

    if(movingSprite.type == "cannon"){
//        launchMissile(pointedSprite);
        fireBullet(pointedSprite);
        console.log("@@@@@@@@");
        sendUserUpdate(null, pointedSprite, movingSprite.position);
    }else{
        fireBullet(pointedSprite);
    }

}

function fireBullet(pointedSprite) {

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

    sendUserUpdate(null, pointedSprite, movingSprite.position);
//    sendUserUpdate(null, pointedSprite, null);
}

function bulletReachedTarget() {
    this.kill();
}

function launchMissile(pointedSprite){
//    var missile = missilePool.getFirstDead();
//    if (missile === null || missile === undefined)
//        return;
//
//    missile.revive(); // Revive the missile, this makes the missile "alive"
//    missile.reset(
//        movingSprite.position.x + fieldSize / 2,
//        movingSprite.position.y + fieldSize / 2
//    );
//    console.log(movingSprite.position);
}