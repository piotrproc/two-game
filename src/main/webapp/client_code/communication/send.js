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

    console.log(userInfo);
    var o = {
        userId : userInfo.nickname,
        selectedTeamId : userInfo.teamType == 0 ? "NATO" : "USSR"
    };

    var eventBus = new EventBus('http://localhost:8077/eventbus');

    eventBus.onopen = function() {
        eventBus.publish("JoinMatchRequest",o);
        eventBus.registerHandler("StartGame", function() {
            //window.location = window.location + "/map.js" + ?
        })
    };

};