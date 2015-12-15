/**
 * Created by Piotr Proc on 01.12.15.
 */

function getUnitSprite(field) {
    var foundUnit = null;

    armySprites.forEach(function (unit) {
        if (unit.x == field.x * fieldSize && unit.y == field.y * fieldSize) {
            foundUnit = unit;
        }
    });

    return foundUnit;
}

function spriteIsInMyTeam(foundUnit){
    return myTeamList.indexOf(foundUnit) >= 0;
}


function getUnitSpriteWithId(id) {
    var foundUnit = null;

    armySprites.forEach(function (unit) {
        if (unit.id == id) {
            foundUnit = unit;
        }
    });

    return foundUnit;
}

function getControlPoint(field){
    var foundControlPoint = null;

    controlPoints.forEach(function (controlPoint) {
        if (controlPoint.x == field.x && controlPoint.y == field.y) {
            foundControlPoint = controlPoint;
        }
    });

    return foundControlPoint;
}