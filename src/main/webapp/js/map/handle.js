/**
 * Created by Piotr Proc on 30.11.15.
 */

function handleMapUpdate(message) {
    var unitStatuses = message.body.unitStatuses;

    unitStatuses.forEach(function (unit) {

        var position = new Field(
            unit.position.x / fieldSize,
            unit.position.y / fieldSize
        );
        var sprite = getUnitSpriteWithId(position, unit.unitId);

        if (sprite) {
            var targetPosition = new Field(
                unit.targetPosition.x / fieldSize,
                unit.targetPosition.y / fieldSize
            );

            var destinationSprite = getUnitSprite(targetPosition);

            if (destinationSprite == null) {
                moveUnitOnServerOrder(sprite, targetPosition);
            }

        } else {

            newUnit = new ArmyElement(
                position.x,
                position.y,
                armyType + '_' + teamNumber,
                unit.unitId
            );

            if (checkIfSuchUnitAlreadyExists(newUnit) == false) {
                var armySprite = game.add.sprite(newUnit.x, newUnit.y, newUnit.name);
                armySprite.id = newUnit.id;
                armySprites.push(armySprite);
            }

        }

    });
}

function moveUnitOnServerOrder(sprite, targetPosition) {
    sprite.x = targetPosition.x * fieldSize;
    sprite.y = targetPosition.y * fieldSize;
    sprite.anchor.setTo(0, 0);
}