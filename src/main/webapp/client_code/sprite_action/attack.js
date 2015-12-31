/**
 * Created by Piotr Proc on 27.12.15.
 */

function attack(pointedSprite){

    if(this.type == "cannon"){
        this.launchMissile(pointedSprite);
//        this.fireBullet(pointedSprite);
//        Sender.sendUserUpdate(null, pointedSprite, this.position);
    }else{
        this.fireBullet(pointedSprite);
    }

}


function fireBullet(pointedSprite) {
    var bullet = bulletPool.getFirstDead();
    if (bullet === null || bullet === undefined)
        return;

    bullet.revive(); // Revive the bullet, this makes the bullet "alive"
    bullet.reset(
        this.position.x + fieldSize / 2,
        this.position.y + fieldSize / 2
    );

    game.physics.arcade.moveToXY(bullet,
        pointedSprite.position.x + fieldSize / 2,
        pointedSprite.position.y + fieldSize / 2);

    Sender.sendUserUpdate(null, pointedSprite, null);
}

function launchMissile(pointedSprite){
    Sender.sendUserUpdate(null, null, pointedSprite.position);
}

function updateHealthBar(sprite, unitData){
    sprite.healthBar.setPercent(unitData.health);
}