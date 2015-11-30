/**
 * Created by Piotr Proc on 30.11.15.
 */



function send(channel, movingSprite) {
    eventBus.send(channel, JSON.parse(messageForServer(movingSprite)));
}

function messageForServer(movingSprite){
    userUpdate = {
        "userId": "kolo34",
        "userSequenceId": 345,
        "unitUpdates": [
            {
                "unitId": 65,
                "moveTarget": {
                    "x": movingSprite.x,
                    "y": movingSprite.y
                },
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
            }
        ],
        "request": {
            "amount": 0
        }
    }

    return userUpdate;
}
