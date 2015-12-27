/**
 * Created by Piotr Proc on 27.12.15.
 */

function moveUnitOnServerOrder(sprite, targetPosition) {
    sprite.x = targetPosition.x;
    sprite.y = targetPosition.y;
    sprite.anchor.setTo(0, 0);
}