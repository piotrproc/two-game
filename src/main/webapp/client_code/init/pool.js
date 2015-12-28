/**
 * Created by Piotr Proc on 28.12.15.
 */

function createPools(){
    myArmyPool = game.add.group();
    oppositeArmyPool = game.add.group();
    oppositeArmyPool.enableBody = true;  //it's necessary for interaction with bullets
    controlPointPool = game.add.group();

    createBullets();
    createMissiles();
}

function createBullets() {
    bulletPool = game.add.group();
    bulletPool.enableBody = true;
    for (var i = 0; i < 20; i++) {

        var bullet = game.add.sprite(0, 0, 'bullet');
        game.physics.arcade.enable(bullet);

        bulletPool.add(bullet);
        bullet.anchor.setTo(0.5, 0.5);
        bullet.kill(); // Set its initial state to "dead".
    }
}

function createMissiles(){
    missilePool = game.add.group();
    for (var i = 0; i < 20; i++) {

        var missile = game.add.sprite(0, 0, 'missile');
        missilePool.add(missile);

        missile.anchor.setTo(0.5, 0.5);
        missile.kill(); // Set its initial state to "dead".
    }
}