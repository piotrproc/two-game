/**
 * Created by Piotr Proc on 27.12.15.
 */

function handleControlPoints(message){
    handleAddingControlPoints(message);
    handleTakingControlPoints(message);
}

function handleAddingControlPoints(message){
    var controlPoints = message.body.controlPoints;

    controlPoints.forEach(function(controlPoint){
        if(getControlPoint(controlPoint.location) == null){
            var controlPointSprite = game.add.sprite(controlPoint.location.x, controlPoint.location.y, "control_point");
            controlPointSprite.tint = "0xffffff"; //white color
            controlPointPool.add(controlPointSprite);
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

            if(controlPointSprite){
                controlPointSprite.tint = colors[index];
            }
        });
        index = index + 1;
    });
}