/**
 * Created by Piotr Proc on 01.12.15.
 */

function onTheSameField(objectA, objectB){
    return objectA.x == objectB.x && objectA.y == objectB.y;
}

function getUnitSprite(field) {
    var foundUnit = null;

    allSprites.forEach(function (unit) {
        if (onTheSameField(field, unit)) {
            foundUnit = unit;
        }
    });

    return foundUnit;
}

function getUnitSpriteWithId(id) {
    var foundUnit = null;

    allSprites.forEach(function (unit) {
        if (unit.id == id) {
            foundUnit = unit;
        }
    });

    return foundUnit;
}

function getControlPoint(field){
    var foundControlPoint = null;

    controlPointSprites.forEach(function (controlPoint) {
        if (onTheSameField(field, controlPoint)) {
            foundControlPoint = controlPoint;
        }
    });

    return foundControlPoint;
}

function spriteIsInMyTeam(foundUnit){
    return oppositeArmyPool.getIndex(foundUnit) == -1;
}