/**
 * Created by Piotr Proc on 31.12.15.
 */

var missileId = 1;

function handleMissiles(message){
    var missileStatuses = message.body.missileStatuses;

    missileStatuses.forEach(function(missileStatus){
        var missileSprite = Picker.getMissileSprite(missileStatus.currentPosition);

        if(missileSprite == null){
            missileSprite = game.add.sprite(missileStatus.currentPosition.x, missileStatus.currentPosition.y, "missile");
            missileSprite.id = missileId;
            missileId = missileId + 1;
            missilePool.add(missileSprite);
        }else{

            thatMissileSprite = Picker.getMissileSpriteWithId(missileSprite);

            if( thatMissileSprite != null){
                thatMissileSprite.x = missileStatus.currentPosition.x;
                thatMissileSprite.y = missileStatus.currentPosition.y;
            }

        }

    });
}