var fieldSize = 32;
var mapSize = 3200;
var viewSize = mapSize / 10;
var shift = -0 / 2;

var game = new Phaser.Game(viewSize, viewSize, Phaser.AUTO, 'game-container', {
    preload: preload, create: create, update: update
});

var unitNames = ['airforce', 'cannon', 'tank', 'soldier'];

function preload() {
    game.load.image('map', 'map/my_map.png', null, Phaser.Tilemap.TILED_JSON);
    game.load.image('button', 'img/refresh.png', null, Phaser.Tilemap.TILED_JSON);

    unitNames.forEach(function (unitName) {
        var unitNameA = unitName + '_1';
        var unitNameB = unitName + '_2';
        var unitNameC = unitName + '_3';
        game.load.image(unitNameA, 'map/units/' + unitNameA + '.png', null, Phaser.Tilemap.TILED_JSON);
        game.load.image(unitNameB, 'map/units/' + unitNameB + '.png', null, Phaser.Tilemap.TILED_JSON);
        game.load.image(unitNameC, 'map/units/' + unitNameC + '.png', null, Phaser.Tilemap.TILED_JSON);
    });

}

function ArmyElement(x, y, name) {
    this.x = x * fieldSize;
    this.y = y * fieldSize;
    this.name = name;
}

var resource = 10000;
var resourceText;
var followedUnitID = 0;
var teamNumber = 1;
var armyTypeIndex = 3;
var armyType = unitNames[armyTypeIndex];

var armySprites = [];
var initialArmy = [
    new ArmyElement(Math.floor(armyTypeIndex / 2), armyTypeIndex % 2, armyType + '_' + teamNumber),
    new ArmyElement(0, 0, armyType + '_' + teamNumber)
];

function Move(moveFrom, moveTo) {
    this.moveFrom = moveFrom;
    this.moveTo = moveTo;
}

var movingSprite = null;
var movingSpriteInfo = null;

function getUnitSprite(x, y) {
    var foundUnit = null;

    armySprites.forEach(function (unit) {
        if (unit.x == x * fieldSize && unit.y == y * fieldSize) {
            foundUnit = unit;
        }
    });

    return foundUnit;
}

function create() {

    game.world.setBounds(0, 0, mapSize, mapSize);
    //  We're going to be using physics, so enable the Arcade Physics system
    game.physics.startSystem(Phaser.Physics.ARCADE);

    var map = game.add.sprite(0, 0, 'map'); //  A simple background for our game

    resourceText = game.add.text(0, 0, 'Zasoby: ' + resource, { font: '32 px Arial', fill: '#ff0044' });
    resourceText.fixedToCamera = true;
    resourceText.cameraOffset.setTo(viewSize - 3.25 * fieldSize, 0.25 * fieldSize);

    button = game.add.button(viewSize - 0.75 * fieldSize, 0.25 * fieldSize, 'button', changeCameraToOtherPlayer, this, 2, 1, 0);
    button.fixedToCamera = true;

    initialArmy.forEach(function (unit) {
        var unitImage = game.add.sprite(unit.x, unit.y, unit.name);
        armySprites.push(unitImage);
    });

    game.camera.follow(armySprites[followedUnitID]);

    game.input.keyboard.onDownCallback = function(e) {
        if(e.keyCode == 32){ //code for space key
            changeCameraToOtherPlayer();
        }
    }
}

function changeCameraToOtherPlayer() {
    followedUnitID = (followedUnitID + 1) % armySprites.length;
    game.camera.follow(armySprites[followedUnitID]);
}

function update() {

    // You can poll mouse status
    if (game.input.mousePointer.isDown) {

        // In the case of a mouse, you can check mouse button status
        if (game.input.mousePointer.button == Phaser.Mouse.LEFT_BUTTON) {

            var fieldX = Math.floor((game.input.mousePointer.x + game.camera.x) / fieldSize);
            var fieldY = Math.floor((game.input.mousePointer.y + game.camera.y) / fieldSize);

            var pointedSprite = getUnitSprite(fieldX, fieldY);

            if (pointedSprite) {

                if (movingSprite) {
                    //here should be attack, if we meet enemy
                    movingSprite.anchor.setTo(0, 0);
                    movingSprite = null;

                } else {
                    movingSprite = pointedSprite;
                    movingSpriteInfo = new ArmyElement(fieldX, fieldY, movingSprite.name);
                    movingSprite.anchor.setTo(-0.1, 0);
                }

            } else {

                if (movingSprite
                    && fieldX >= 0 && fieldX * fieldSize < mapSize
                    && fieldY >= 0 && fieldY * fieldSize < mapSize) {

                    var move = new Move(movingSpriteInfo,
                        new ArmyElement(fieldX, fieldY, movingSprite.name));

                    movingSprite.x = fieldX * fieldSize;
                    movingSprite.y = fieldY * fieldSize;
                    movingSprite.anchor.setTo(0, 0);

                    movingSprite = null;
                }

            }

            // We just want to clear it, so this doesn't fire over and over, don't do this in production
            game.input.activePointer.reset();
        }

    }
}