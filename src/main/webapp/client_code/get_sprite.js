/**
 * Created by Piotr Proc on 01.12.15.
 */

function onTheSameField(objectA, objectB){
    return objectA.x == objectB.x && objectA.y == objectB.y;
}

function getUnitSprite(field) {
    var foundUnit = null;

    myArmyPool.forEach(function (unit) {
        if (onTheSameField(field, unit)) {
            foundUnit = unit;
        }
    }, this);

    oppositeArmyPool.forEach(function (unit) {
        if (onTheSameField(field, unit)) {
            foundUnit = unit;
        }
    }, this);

    return foundUnit;
}

function getUnitSpriteWithId(id) {
    var foundUnit = null;

    myArmyPool.forEach(function (unit) {
        if (unit.id == id) {
            foundUnit = unit;
        }
    }, this);

    oppositeArmyPool.forEach(function (unit) {
        if (unit.id == id) {
            foundUnit = unit;
        }
    }, this);

    return foundUnit;
}

function getControlPoint(field){
    var foundControlPoint = null;

    controlPointPool.forEach(function (controlPoint) {
        if (onTheSameField(field, controlPoint)) {
            foundControlPoint = controlPoint;
        }
    }, this);

    return foundControlPoint;
}

function spriteIsInMyTeam(foundUnit){
    return oppositeArmyPool.getIndex(foundUnit) == -1;
}