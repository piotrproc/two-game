/**
 * Created by Piotr Proc on 31.12.15.
 */



function handleMissiles(message) {

    var missileStatuses = message.body.missileStatuses;
    Helper.checkAllMissiles(missileStatuses, missilePool);

    missileStatuses.forEach(function (missileStatus) {
        var missileData = {"id": missileStatus.missileId};
        var missileSprite = Picker.getMissileSpriteWithId(missileData);

        if (missileSprite == null) {
            missileSprite = game.add.sprite(missileStatus.currentPosition.x, missileStatus.currentPosition.y, "missile");
            missileSprite.id = missileStatus.missileId;
            missilePool.add(missileSprite);

        } else {
            missileSprite.x = missileStatus.currentPosition.x;
            missileSprite.y = missileStatus.currentPosition.y;

            console.log(missileStatus);

            if(//missileStatus.currentPosition.x == missileStatus.targetPosition.x &&
                missileStatus.currentPosition.y == missileStatus.targetPosition.y){
                console.log("KIIIIIIIIIILL");
                missileSprite.kill();

            }
        }

    });
}