/**
 * Created by Piotr Proc on 27.12.15.
 */

function Helper(){}

Helper.selectSprite = function (pointedSprite) {
    movingSprite = Picker.getUnitSprite(pointedSprite.position);
    movingSprite.anchor.setTo(-0.1, 0);
};

Helper.deselectSprite = function (){
    movingSprite.anchor.setTo(0, 0);
    movingSprite = null;
};

Helper.calculatePointedField = function calculatePointedField() {
    var fieldX = Math.floor((game.input.mousePointer.x + game.camera.x) / fieldSize) * fieldSize;
    var fieldY = Math.floor((game.input.mousePointer.y + game.camera.y) / fieldSize) * fieldSize;

    return new Field(fieldX, fieldY);
};

Helper.existsInGroup = function (group, element, func){
    var foundElement = null;

    group.forEach(function (groupElement) {
        if (func(element, groupElement)) {
            foundElement = groupElement;
        }
    }, this);

    return foundElement;
};

Helper.existsInGroups = function (groups, element, func){
    var inGroup = null;

    groups.forEach(function(group){
        if(inGroup == null)
            inGroup = Helper.existsInGroup(group, element, func);
    });

    return inGroup;
};

Helper.checkAllAliveUnits = function (unitStatuses){

    myArmyPool.forEach(function(sprite){

        var foundStatus = null;

        unitStatuses.forEach(function (unitStatus) {
            if(sprite.id == unitStatus.unitId)
                foundStatus = unitStatus;
        });

        if(foundStatus == null){
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
            sprite.kill();
        }

    }, this);

};