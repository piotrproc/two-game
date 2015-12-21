/**
 * Created by Piotr Proc on 30.11.15.
 */

function update() {

    if (game.input.mousePointer.isDown) {

        if (game.input.mousePointer.button == Phaser.Mouse.LEFT_BUTTON) {
            handleClick();
            game.input.activePointer.reset();
        }

    }
}

function handleClick(){
    var pointedField = calculatePointedField();
    var pointedSprite = getUnitSprite(pointedField);

    //if we click on sprite
    if (pointedSprite) {

        //if we had one sprite marked before
        if (movingSprite && !spriteIsInMyTeam(pointedSprite)) {
            sendMoveMessage(null, pointedSprite);
        } else {
            if(spriteIsInMyTeam(pointedSprite))
                chooseUnit(pointedSprite);
        }

    } else {

        //if we had one sprite marked before
        if (movingSprite){
            //we move if
            if(spriteIsInMyTeam(movingSprite) && fieldIsOnTheMap(pointedField)) {
                sendMoveMessage(pointedField, null);
            }
        }

    }
}

function chooseUnit(pointedSprite) {
    movingSprite = pointedSprite;
    movingSprite.anchor.setTo(-0.1, 0);
}

function calculatePointedField(){
    var fieldX = Math.floor((game.input.mousePointer.x + game.camera.x) / fieldSize);
    var fieldY = Math.floor((game.input.mousePointer.y + game.camera.y) / fieldSize);

    return new Field(fieldX, fieldY);
}

function fieldIsOnTheMap(field) {
    return field.x >= 0 && field.x * fieldSize < mapSize
        && field.y >= 0 && field.y * fieldSize < mapSize;
}