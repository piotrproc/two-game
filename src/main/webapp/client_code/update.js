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

function handleClick() {
    var pointedField = calculatePointedField();
    var pointedSprite = getUnitSprite(pointedField);

    //if we click on sprite
    if (pointedSprite) {

        //if we had one sprite marked before
        if (movingSprite && !spriteIsInMyTeam(pointedSprite)) {

            attack(pointedField, pointedSprite);

        } else {
            if (spriteIsInMyTeam(pointedSprite))
                chooseUnit(pointedSprite);
        }

    } else {

        //if we had one sprite marked before
        if (movingSprite) {
            //we move if
            if (spriteIsInMyTeam(movingSprite) && fieldIsOnTheMap(pointedField)) {
                sendMoveMessage(pointedField, null);
            }
        }

    }
}


function attack(pointedField, pointedSprite) {

    bullet = bulletPool.getFirstDead();
    // If there aren't any bullets available then don't shoot
    if (bullet === null || bullet === undefined) return;

    // Revive the bullet
    // This makes the bullet "alive"
    bullet.revive();

    bullet.checkWorldBounds = true;
    bullet.outOfBoundsKill = true;

    // Set the bullet position to the gun position.
    bullet.reset(movingSprite.position.x, movingSprite.position.y);

    // Shoot it
    //bullet.body.velocity.x = 20;
    //bullet.body.velocity.y = 0;
    game.physics.arcade.moveToObject(bullet, pointedSprite);


//    myBullet = getBullet(movingSprite.position);
//    myBullet.visible = true;
//    console.log(myBullet);
//    console.log(pointedSprite);
//
//    secondBullet = getBullet(new Field(movingSprite.position.x-32, movingSprite.position.y-32));
//    secondBullet.visible = true;
//    game.physics.arcade.collide(myBullet, secondBullet, collisionHandler, null, this);
//    game.physics.arcade.moveToObject(myBullet, pointedSprite);
//    sendMoveMessage(null, pointedSprite);
//    //myBullet.reset();
}

function collisionHandler(){
    console.log("Alleluja");
}

function chooseUnit(pointedSprite) {
    movingSprite = pointedSprite;
    movingSprite.anchor.setTo(-0.1, 0);
}

function calculatePointedField() {
    var fieldX = Math.floor((game.input.mousePointer.x + game.camera.x) / fieldSize);
    var fieldY = Math.floor((game.input.mousePointer.y + game.camera.y) / fieldSize);

    return new Field(fieldX, fieldY);
}

function fieldIsOnTheMap(field) {
    return field.x >= 0 && field.x * fieldSize < mapSize
        && field.y >= 0 && field.y * fieldSize < mapSize;
}