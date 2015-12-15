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

function sendMoveMessage(targetPosition) {
    var message = messageForServer(movingSprite, targetPosition);
    eventBus.send("UserUpdate", message);
    movingSprite = null;
}

function messageForServer(movingSprite, targetPosition){
    userUpdate = {
        "userId": user,
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
        ]
    };

    userSequence += 1;

    return userUpdate;
}
