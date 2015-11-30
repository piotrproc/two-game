/**
 * Created by Piotr Proc on 30.11.15.
 */

function handleMapUpdate(message){
    var unitStatuses = message.body.unitStatuses;

    unitStatuses.forEach(function (unit) {

        var sprite = getUnitSpriteWithId(unit.position.x / fieldSize, unit.position.y / fieldSize, unit.unitId);

        if(sprite){

            destinationSprite = getUnitSprite(unit.targetPosition.x / fieldSize, unit.targetPosition.y / fieldSize);

            if(destinationSprite == null){
                sprite.x = unit.targetPosition.x;
                sprite.y = unit.targetPosition.y;
                sprite.anchor.setTo(0, 0);
            }

        }else{

            armyElement = new ArmyElement(
                unit.position.x / fieldSize,
                unit.position.y / fieldSize,
                armyType + '_' + teamNumber,
                unit.unitId
            );

            if(checkIfUnitExist(armyElement) == false){
                var unitImage = game.add.sprite(armyElement.x, armyElement.y, armyElement.name);
                unitImage.id = armyElement.id;
                armySprites.push(unitImage);
            }

        }

    });
}