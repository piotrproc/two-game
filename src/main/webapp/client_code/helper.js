/**
 * Created by Piotr Proc on 27.12.15.
 */

function selectSprite(pointedSprite) {
    movingSprite = pointedSprite;
    movingSprite.anchor.setTo(-0.1, 0);
}

function deselectSprite(){
    movingSprite.anchor.setTo(0, 0);
    movingSprite = null;
}

function calculatePointedField() {
    var fieldX = Math.floor((game.input.mousePointer.x + game.camera.x) / fieldSize) * fieldSize;
    var fieldY = Math.floor((game.input.mousePointer.y + game.camera.y) / fieldSize) * fieldSize;

    return new Field(fieldX, fieldY);
}

function fieldIsOnTheMap(field) {
    return field.x >= 0 && field.x < mapSize
        && field.y >= 0 && field.y < mapSize;
}