/**
 * Created by Piotr Proc on 30.11.15.
 */

var user = "user1";

function sendSupportRequest() {
    var message = {
        "user": user
    };
    eventBus.send("SupportRequest", message);
}

function sendUserUpdate(targetPosition, unitAttack, missileLaunch) {
    var message = getUserUpdateMessage(movingSprite, targetPosition, unitAttack, missileLaunch);
    eventBus.send("UserUpdate", message);
}

/**
 * We prepare message for UserUpdate consumer here
 */
function getUserUpdateMessage(movingSprite, targetPosition, unitAttack, missileLaunch) {

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


    if (true == false) {
        unitUpdate["missileLaunches"] = [
            {
                "target": {
                    "x": 45.6,
                    "y": 185.6
                }
            }
        ];
    }

    userUpdate["unitUpdates"] = [unitUpdate];
    return userUpdate;
}
