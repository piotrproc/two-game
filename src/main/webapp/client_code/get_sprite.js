/**
 * Created by Piotr Proc on 01.12.15.
 */

function getUnitSprite(field) {
    return existsInGroups([myArmyPool, oppositeArmyPool], field, onTheSameField);
}

function getUnitSpriteWithId(unitData) {
    return existsInGroups([myArmyPool, oppositeArmyPool], unitData, haveTheSameId);
}

function getControlPoint(field){
    return existsInGroup(controlPointPool, field, onTheSameField);
}

function spriteIsInMyTeam(foundUnit){
    return myArmyPool.getIndex(foundUnit) >= 0;
}

function haveTheSameId(objectA, objectB){
    return objectA.id == objectB.id;
}

function onTheSameField(objectA, objectB){
    return objectA.x == objectB.x && objectA.y == objectB.y;
}