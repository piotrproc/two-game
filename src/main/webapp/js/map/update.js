/**
 * Created by Piotr Proc on 30.11.15.
 */

function update() {

    // You can poll mouse status
    if (game.input.mousePointer.isDown) {

        // In the case of a mouse, you can check mouse button status
        if (game.input.mousePointer.button == Phaser.Mouse.LEFT_BUTTON) {

            var pointedField = calculatePointedField();
            var pointedSprite = getUnitSprite(pointedField);

            if (pointedSprite) {

                if (movingSprite) {
                    attack(pointedSprite);
                } else {
                    chooseUnit(pointedSprite);
                }

            } else {

                if (movingSprite && fieldIsOnTheMap(pointedField)) {
                    move(pointedField);
                }

            }

            // We just want to clear it, so this doesn't fire over and over, don't do this in production
            game.input.activePointer.reset();
        }

    }
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

function move(field) {
//    var move = new Move(movingSpriteInfo,
//    new ArmyElement(fieldX, fieldY, movingSprite.name));

    movingSprite.x = field.x * fieldSize;
    movingSprite.y = field.y * fieldSize;
    movingSprite.anchor.setTo(0, 0);

//    send('UserUpdate', messageForServer(movingSprite));

    movingSprite = null;
}

function chooseUnit(pointedSprite) {
    movingSprite = pointedSprite;
    movingSpriteInfo = new ArmyElement(
        movingSprite.x * fieldSize,
        movingSprite.y * fieldSize,
        movingSprite.name,
        movingSprite.id
    );
    movingSprite.anchor.setTo(-0.1, 0);
}

function attack(pointedSprite){
    //currently it is not implemented
    //so we just leave unit alone
    movingSprite.anchor.setTo(0, 0);
    movingSprite = null;
}