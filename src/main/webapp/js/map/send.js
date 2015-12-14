/**
 * Created by Piotr Proc on 30.11.15.
 */

function sendMoveMessage(targetPosition) {
    var message = messageForServer(movingSprite, targetPosition);
    eventBus.send("UserUpdate", message);
    movingSprite = null;
}

function sendSupportRequest() {
    var message = {
        "amount": 30
    };
    //eventBus.send("SupportRequest", message);
}



function messageForServer(movingSprite, targetPosition){
    userUpdate = {
        "userId": "user1",
        "userSequenceId": userSequence,
        "unitUpdates": [
            {
                "unitId": movingSprite.id,
                "moveTarget": {
                    "x": targetPosition.x * fieldSize,
                    "y": targetPosition.y * fieldSize
                }
            }
        ],
        "missileLaunches": [
            {
                "target": {
                    "x": 45.6,
                    "y": 185.6
                }
            }
        ],
        "attacks": [
            {
                "targetUnitId": 64
            }
        ],
        "request": {
            "amount": 0
        }
    };

    userSequence += 1;

    return userUpdate;
}
