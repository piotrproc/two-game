/**
 * Created by Piotr Proc on 27.12.15.
 */

function selectSprite(pointedSprite) {
    movingSprite = pointedSprite;
    movingSprite.anchor.setTo(-0.1, 0);
}

function deselectSprite(){
    movingSprite.anchor.setTo(0, 0);
    movingSprite = null;
}

function calculatePointedField() {
    var fieldX = Math.floor((game.input.mousePointer.x + game.camera.x) / fieldSize) * fieldSize;
    var fieldY = Math.floor((game.input.mousePointer.y + game.camera.y) / fieldSize) * fieldSize;

    return new Field(fieldX, fieldY);
}

function fieldIsOnTheMap(field) {
    return field.x >= 0 && field.x < mapSize
        && field.y >= 0 && field.y < mapSize;
}

function existsInGroup(group, element, func){
    var foundElement = null;

    group.forEach(function (groupElement) {
        if (func(element, groupElement)) {
            foundElement = groupElement;
        }
    }, this);

    return foundElement;
}

function existsInGroups(groups, element, func){
    var inGroup = null;

    groups.forEach(function(group){
        if(inGroup == null)
            inGroup = existsInGroup(group, element, func);
    });

    return inGroup;
}

function checkAllAliveUnits(unitStatuses){

    myArmyPool.forEach(function(sprite){

        var foundStatus = null;

        unitStatuses.forEach(function (unitStatus) {
            if(sprite.id == unitStatus.unitId)
                foundStatus = unitStatus;
        });

        if(foundStatus == null){
            console.log("aaa");
            myArmyPool.remove(sprite);
            sprite.healthBar.kill();
            sprite.kill();

        }

    }, this);

    oppositeArmyPool.forEach(function(sprite){

        var foundStatus = null;

        unitStatuses.forEach(function (unitStatus) {
            if(sprite.id == unitStatus.unitId)
                foundStatus = unitStatus;
        });

        if(foundStatus == null){
            oppositeArmyPool.remove(sprite);
            sprite.healthBar.kill();
            sprite.kill();

        }

    }, this);


}