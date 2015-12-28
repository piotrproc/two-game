/**
 * Created by Piotr Proc on 27.12.15.
 */

function attack(pointedSprite){

    if(movingSprite.type == "cannon"){
//        launchMissile(pointedSprite);
        fireBullet(pointedSprite);
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

    game.physics.arcade.moveToXY(bullet,
        pointedSprite.position.x + fieldSize / 2,
        pointedSprite.position.y + fieldSize / 2);

    sendUserUpdate(null, pointedSprite, null);
}

function bulletReachedTarget(bullet, armySprite){
    bullet.kill();
}

function launchMissile(pointedSprite){
    //todo
}