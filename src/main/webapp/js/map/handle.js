/**
 * Created by Piotr Proc on 30.11.15.
 */

function handleTeamStatus(message){
    var teamStatuses = message.body.teamStatuses;

    teamA = (teamStatuses[0]).userIds;
    teamB = (teamStatuses[1]).userIds;
}

function handleMapUpdate(message) {
    var unitStatuses = message.body.unitStatuses;


    unitStatuses.forEach(function (unit) {

        var unitType = unitNames[unit.unitType];
        var team;

        if(teamA.indexOf(unit.user) >= 0)
            team = 1;
        else
            team = 2;

        var unitName = unitType + "_" + team;
        var sprite = getUnitSpriteWithId(unit.unitId);

        var position = new Field(
            unit.position.x / fieldSize,
            unit.position.y / fieldSize
        );

        if (sprite) {
            var destinationSprite = getUnitSprite(position);

            if (destinationSprite == null) {
                moveUnitOnServerOrder(sprite, position);
            }

        } else {

            var newUnit = new ArmyElement(position.x, position.y, unitName, unit.unitId);

            if (checkIfSuchUnitAlreadyExists(newUnit) == false) {
                createNewSprite(newUnit);
            }

        }

    });
}

function createNewSprite(newUnit){
    var armySprite = game.add.sprite(newUnit.x, newUnit.y, newUnit.name);
    armySprite.id = newUnit.id;
    armySprites.push(armySprite);
}

function moveUnitOnServerOrder(sprite, targetPosition) {
    sprite.x = targetPosition.x * fieldSize;
    sprite.y = targetPosition.y * fieldSize;
    sprite.anchor.setTo(0, 0);
}