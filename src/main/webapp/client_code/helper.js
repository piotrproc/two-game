/**
 * Created by Piotr Proc on 27.12.15.
 */

function Helper(){}

Helper.selectSprite = function (pointedSprite) {
    movingSprite = Picker.getUnitSprite(pointedSprite.position);
    movingSprite.anchor.setTo(-0.1, 0);
};

Helper.deselectSprite = function (){
    if(movingSprite){
        movingSprite.anchor.setTo(0, 0);
        movingSprite = null;
    }
};

Helper.getShiftedField = function () {
    var fieldX = Math.floor((game.input.mousePointer.x + game.camera.x) / fieldSize) * fieldSize;
    var fieldY = Math.floor((game.input.mousePointer.y + game.camera.y) / fieldSize) * fieldSize;

    return new Field(fieldX, fieldY);
};

Helper.getPointedField = function () {
    var fieldX = Math.floor((game.input.mousePointer.x) / fieldSize) * fieldSize;
    var fieldY = Math.floor((game.input.mousePointer.y) / fieldSize) * fieldSize;

    return new Field(fieldX, fieldY);
};

Helper.getFromGroup = function (group, element, func){
    var foundElement = null;

    group.forEach(function (groupElement) {
        if (func(element, groupElement)) {
            foundElement = groupElement;
        }
    }, this);

    return foundElement;
};

Helper.getFromGroups = function (groups, element, func){
    var inGroup = null;

    groups.forEach(function(group){
        if(inGroup == null)
            inGroup = Helper.getFromGroup(group, element, func);
    });

    return inGroup;
};

Helper.checkAllAliveUnitsInPool = function (unitStatuses, pool){

    pool.forEach(function(sprite){

        var foundStatus = null;

        unitStatuses.forEach(function (unitStatus) {
            if(sprite.id == unitStatus.unitId)
                foundStatus = unitStatus;
        });

        if(foundStatus == null){
            sprite.kill();
        }

    }, this);

};

Helper.removeAllDeadUnits = function (unitStatuses){
    Helper.checkAllAliveUnitsInPool(unitStatuses, myArmyPool);
    Helper.checkAllAliveUnitsInPool(unitStatuses, oppositeArmyPool);
};

Helper.changeCameraToOtherPlayer = function () {
    Helper.deselectSprite();

    followedUnitID = (followedUnitID + 1) % myArmyPool.countLiving();
    game.camera.follow(myArmyPool.getAt(followedUnitID));
};

Helper.buttonsWereClicked = function() {
    var position = Helper.getPointedField();

    return (position.x == fieldSize * (noOfFieldsInView-2) && position.y == 0) ||
        (position.x == fieldSize * (noOfFieldsInView-1) && position.y == 0)
};

Helper.buttonsWereNotClicked = function(){
    return !Helper.buttonsWereClicked();
};


Helper.checkAllMissiles = function (missiles, pool){

    pool.forEach(function(sprite){

        var foundMissile = null;

        missiles.forEach(function (missile) {
            if(sprite.id == missile.missileId)
                foundMissile = missile;
        });

        if(foundMissile == null){
            sprite.kill();
        }

    }, this);

};