/**
 * Created by Piotr Proc on 30.11.15.
 */

function update() {
    game.physics.arcade.overlap(bulletPool, oppositeArmyPool, bulletReachedTarget);

    if (game.input.mousePointer.isDown) {

        if (game.input.mousePointer.button == Phaser.Mouse.LEFT_BUTTON) {
            handleClick();
            game.input.activePointer.reset();
        }

    }
}

function handleClick() {
    var pointedField = calculatePointedField();
    var pointedSprite = getUnitSprite(pointedField);

    //if we click on sprite
    if (pointedSprite) {

        //if we had one sprite marked before
        if (movingSprite && !spriteIsInMyTeam(pointedSprite)) {
            attack(pointedSprite);
        } else {
            if (spriteIsInMyTeam(pointedSprite))
                selectSprite(pointedSprite);
        }

    } else {

        //if we had one sprite marked before
        if (movingSprite) {
            //we move if
            if (spriteIsInMyTeam(movingSprite) && fieldIsOnTheMap(pointedField)) {
                sendUserUpdate(pointedField, null, null);
                deselectSprite();
            }
        }

    }
}