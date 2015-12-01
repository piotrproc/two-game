/**
 * Created by Piotr Proc on 30.11.15.
 */

function handleTeamStatus(message){
    var teamStatuses = message.body.teamStatuses;

    teamA = (teamStatuses[0]).userIds;
    teamB = (teamStatuses[1]).userIds;

    if(teamA.indexOf(user) >= 0){
        myTeam = 1;
    }else{
        myTeam = 2;
    }

    userSequence = message.body.sequenceId;
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

        resource = (message.body.teamStatuses[team-1]).resourcesAmount;
        resourceText.text = 'Zasoby: ' + resource;

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
            var placeIsEmpty = (getUnitSprite(position) == null);

            if (placeIsEmpty) {
                createNewSprite(position, unitName, unit.unitId, team);
            }

        }

    });
}

function createNewSprite(position, unitName, id, team){
    var armySprite = game.add.sprite(position.x*fieldSize, position.y*fieldSize, unitName);
    armySprite.id = id;
    armySprites.push(armySprite);

    if(myTeam == team){
        game.camera.follow(armySprite);
        myTeamList.push(armySprite);
    }

}

function moveUnitOnServerOrder(sprite, targetPosition) {
    sprite.x = targetPosition.x * fieldSize;
    sprite.y = targetPosition.y * fieldSize;
    sprite.anchor.setTo(0, 0);
}