/**
 * Created by Piotr Proc on 30.11.15.
 */


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
