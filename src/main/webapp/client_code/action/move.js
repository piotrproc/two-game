/**
 * Created by Piotr Proc on 27.12.15.
 */

function updateUnitOnServerOrder(sprite, unitData, targetPosition) {
    updateHealthBar(sprite, unitData);
    moveUnitOnServerOrder(sprite, targetPosition);
}

function moveUnitOnServerOrder(sprite, targetPosition){
    sprite.x = targetPosition.x;
    sprite.y = targetPosition.y;

    sprite.healthBar.setPosition(
        sprite.x + fieldSize/2,
        sprite.y + fieldSize
    );

    sprite.anchor.setTo(0, 0);
}

function updateHealthBar(sprite, unitData){
    sprite.healthBar.setPercent(unitData.health);
}