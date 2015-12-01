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

function checkIfSuchUnitAlreadyExists(newUnit){
    var isFound = false;

    armySprites.forEach(function (unit) {
        if (unit.id == newUnit.id) {
            isFound = true;
        }
    });
    return isFound;
}
