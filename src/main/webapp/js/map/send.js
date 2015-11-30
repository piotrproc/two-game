/**
 * Created by Piotr Proc on 30.11.15.
 */


function messageForServer(movingSprite, targetPosition){
    userUpdate = {
        "userId": "user1",
        "unitUpdates": [
            {
                "unitId": movingSprite.id,
                "moveTarget": {
                    "x": targetPosition.x * fieldSize,
                    "y": targetPosition.y * fieldSize
                }
            }
        ]
    };

    return userUpdate;
}
