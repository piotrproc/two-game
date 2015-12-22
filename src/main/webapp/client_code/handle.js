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
    setTimeout(function(){handleCreatingControlPoints(message)}, 100); //we need here some delay
}

function handleMapUpdate(message) {
    var unitStatuses = message.body.unitStatuses;

    unitStatuses.forEach(function (unit) {

        var unitType = unit.type.toLowerCase();
        var team;

        if(teamA.indexOf(unit.user) >= 0)
            team = 1;
        else
            team = 2;

        if(team == myTeam){
            resource = (message.body.teamStatuses[team-1]).resourcesAmount;
            resourceText["text"] = 'Zasoby: ' + resource;
        }

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

function handleCreatingControlPoints(message){
    var controlPoints = message.body.controlPoints;

    controlPoints.forEach(function(controlPoint){
        if(getControlPoint(controlPoint.location) == null){
            var controlPointSprite = game.add.sprite(controlPoint.location.x, controlPoint.location.y, "control_point");
            controlPointSprites.push(controlPointSprite);
        }
    });
}

function handleTakingControlPoints(message){
    var teamStatuses = message.body.teamStatuses;
    var colors = [
        "0x3D3D3B", //dark grey
        "0xA61C2E"  //dark red
    ];
    var index = 0;

    teamStatuses.forEach(function (teamStatus) {
        //console.log(teamStatus);
         var controlPoints = teamStatus.controlPoints;

        controlPoints.forEach(function(controlPoint){
            var controlPointSprite = getControlPoint(controlPoint.location);
            var spriteOnTheField = getUnitSprite(new Field(controlPoint.location.x/fieldSize, controlPoint.location.y/fieldSize));
            if(controlPointSprite && spriteOnTheField){
                controlPointSprite.tint = colors[index];
            }
        });
        index = index + 1;
    });
}

function createNewSprite(position, unitName, id, team){
    var armySprite = game.add.sprite(position.x*fieldSize, position.y*fieldSize, unitName);
    armySprite.id = id;

//    var bul = game.make.sprite(0, 0, 'bullet');
//    game.physics.arcade.enable(bul);
//    armySprite.addChild(bul);

    if(myTeam == team){
        game.camera.follow(armySprite);
        myTeamList.push(armySprite);
    }else{
        game.physics.arcade.enable(armySprite);
    }

    armySprites.push(armySprite);

}

function moveUnitOnServerOrder(sprite, targetPosition) {
    sprite.x = targetPosition.x * fieldSize;
    sprite.y = targetPosition.y * fieldSize;
    sprite.anchor.setTo(0, 0);
}