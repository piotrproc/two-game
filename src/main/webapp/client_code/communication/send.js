/**
 * Created by Piotr Proc on 30.11.15.
 */

var user = "user1";

//Sender sends message through WebSocket to Server
function Sender(){}

Sender.sendSupportRequest = function () {
    Helper.deselectSprite();

    var message = {
        "user": user
    };
    eventBus.send("SupportRequest", message);
};

Sender.sendUserUpdate = function (targetPosition, unitAttack, missileLaunch) {
    var message = Sender.getUserUpdateMessage(targetPosition, unitAttack, missileLaunch);
    eventBus.send("UserUpdate", message);
};

/**
 * We prepare message for UserUpdate consumer here
 */
Sender.getUserUpdateMessage = function (targetPosition, unitAttack, missileLaunch) {

    userSequenceId += 1;

    var userUpdate = {
        "userId": user,
        "userSequenceId": userSequenceId
    };

    var unitUpdate = {};
    unitUpdate["unitId"] = movingSprite.id;

    if (targetPosition != null) {
        unitUpdate["moveTarget"] = {
            "x": targetPosition.x,
            "y": targetPosition.y
        };
    }



    if (unitAttack != null) {
        unitUpdate["attacks"] = [
            {
                "targetUnitId": unitAttack.id
            }
        ];
    }


    if (missileLaunch != null) {
        unitUpdate["missileLaunches"] = [missileLaunch];
    }

    userUpdate["unitUpdates"] = [unitUpdate];
//    console.log(userUpdate);
    return userUpdate;
};


Sender.sendPlayerInitData = function (userInfo) {
    //return 5;
    window.location.replace('http://' + window.location.hostname + ':8080/map.html');
};