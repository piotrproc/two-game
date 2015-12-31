/**
 * Created by Piotr Proc on 27.12.15.
 */

function addNewSprite(unitData, position){
    var armySprite = game.add.sprite(position.x, position.y, unitData.name);

    var barConfig = getBarConfig(position);

    armySprite.id = unitData.id;
    armySprite.type = unitData.type;
    armySprite.team = unitData.team;

    armySprite.healthBar = new HealthBar(game, barConfig);
    armySprite.healthBar.setPercent(80);

    //we add functions from other files
    armySprite.move = move;
    armySprite.fireBullet = fireBullet;
    armySprite.attack = attack;
    armySprite.kill = kill;

    if(myTeam == unitData.team){
        game.camera.follow(armySprite);
        armySprite.pool = myArmyPool;
        myArmyPool.add(armySprite);
    }else{
        armySprite.pool = oppositeArmyPool;
        oppositeArmyPool.add(armySprite);
    }

}

function getBarConfig(position){
    return {
        width: fieldSize * 0.9,
        height: fieldSize / 5,
        x: position.x + fieldSize/2, //center of the bar should be in field width center
        y: position.y + fieldSize    //bar should be below unit
    };
}
