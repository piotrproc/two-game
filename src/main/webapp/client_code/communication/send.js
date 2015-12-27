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

function sendMoveMessage(targetPosition, unitAttack) {
    var message = messageForServer(movingSprite, targetPosition, unitAttack);
    eventBus.send("UserUpdate", message);
}


function messageForServer(movingSprite, targetPosition, unitAttack) {

    var userUpdate = {
        "userId": user,
        "userSequenceId": userSequence
    };

    var unitUpdate = {};
    unitUpdate["unitId"] = movingSprite.id;

    if (targetPosition != null) {
        unitUpdate["moveTarget"] = {
            "x": targetPosition.x * fieldSize,
            "y": targetPosition.y * fieldSize
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

    userSequence += 1;
    userUpdate["unitUpdates"] = [unitUpdate];
    return userUpdate;
}
