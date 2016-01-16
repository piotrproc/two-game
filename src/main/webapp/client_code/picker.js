/**
 * Created by Piotr Proc on 01.12.15.
 */

function Picker(){}

Picker.getUnitSprite = function (field) {
    return Helper.getFromGroups([myArmyPool, oppositeArmyPool], field, onTheSameField);
};

Picker.getMovingUnitSprite = function (field) {
    return Helper.getFromGroups([myArmyPool, oppositeArmyPool], field, onTheSameSquare);
};

Picker.getUnitSpriteWithId = function (unitData) {
    return Helper.getFromGroups([myArmyPool, oppositeArmyPool], unitData, haveTheSameId);
};

Picker.getControlPointSprite = function (field){
    return Helper.getFromGroup(controlPointPool, field, onTheSameField);
};

Picker.getMissileSprite = function (field){
    return Helper.getFromGroup(missilePool, field, onTheSameField);
};

Picker.getMissileSpriteWithId = function (missileData){
    return Helper.getFromGroup(missilePool, missileData, haveTheSameId);
};

function haveTheSameId(objectA, objectB){
    return objectA.id == objectB.id;
}

function onTheSameSquare(place, sprite){
    return sprite.x <= place.x && place.x <= sprite.x + fieldSize
        && sprite.y <= place.y && place.y <= sprite.y + fieldSize;
}

function onTheSameField(objectA, objectB){
    return objectA.x == objectB.x && objectA.y == objectB.y;
}