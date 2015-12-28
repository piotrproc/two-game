/**
 * Created by Piotr Proc on 27.12.15.
 */

function addNewSprite(unitData, position){
    var armySprite = game.add.sprite(position.x, position.y, unitData.name);

    armySprite.id = unitData.id;
    armySprite.type = unitData.type;
    armySprite.team = unitData.team;

    if(myTeam == unitData.team){
        game.camera.follow(armySprite);
        myArmyPool.add(armySprite);
    }else{
        oppositeArmyPool.add(armySprite);
    }

}