/**
 * Created by Piotr Proc on 28.12.15.
 */

function createPools(){
    //order here is important, control points need to be displayed at the beginning
    controlPointPool = game.add.group();
    myArmyPool = game.add.group();

    myArmyPool.exists = function (foundUnit){
        return this.getIndex(foundUnit) >= 0;
    };

    oppositeArmyPool = game.add.group();
    oppositeArmyPool.enableBody = true;  //it's necessary for interaction with bullets

    oppositeArmyPool.exists = function (foundUnit){
        return this.getIndex(foundUnit) >= 0;
    };

    createBullets();
    missilePool = game.add.group();
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

