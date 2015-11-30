

// set a handler to receive a message
var eventBus = new EventBus('http://localhost:8077/eventbus');

eventBus.onopen = function () {

    eventBus.registerHandler('MatchStatus', function (i, message) {
        console.log('received a message: ', message);
        handleMapUpdate(message);
    });

};

// map configuration
var fieldSize = 32;
var mapSize = 3200;
var viewSize = mapSize / 10;

var unitNames = ['airforce', 'cannon', 'tank', 'soldier'];
var resource = 10000;
var resourceText;
var followedUnitID = 0; // unit that camera is currently following
var teamNumber = 1;
var armyTypeIndex = 3;
var armyType = unitNames[armyTypeIndex];

// game modifiers are split into three files
var game = new Phaser.Game(viewSize, viewSize, Phaser.AUTO, '', {
    preload: preload,
    create: create,
    update: update
});



function ArmyElement(x, y, name, id) {
    this.x = x * fieldSize;
    this.y = y * fieldSize;
    this.name = name;       //name specifies PNG for unit
    this.id = id;           //unique id
}

var armySprites = [];
var initialArmy = [
    new ArmyElement(Math.floor(armyTypeIndex / 2), armyTypeIndex % 2, armyType + '_' + teamNumber, 111),
    new ArmyElement(0, 0, armyType + '_' + teamNumber, 222)
];

function Move(moveFrom, moveTo) {
    this.moveFrom = moveFrom;
    this.moveTo = moveTo;
}

var movingSprite = null;
var movingSpriteInfo = null;

function getUnitSprite(field) {
    var foundUnit = null;

    armySprites.forEach(function (unit) {
        if (unit.x == field.x * fieldSize && unit.y == field.y * fieldSize) {
            foundUnit = unit;
        }
    });

    return foundUnit;
}

function getUnitSpriteWithId(field, id) {
    var foundUnit = getUnitSprite(field);

    if(foundUnit){
        if(foundUnit.id == id)
            return foundUnit
    }
    return null;
}

function checkIfUnitExist(armyElement){
    var isFound = false;

    armySprites.forEach(function (unit) {
        if (unit.id == armyElement.id) {
            isFound = true;
        }
    });
    return isFound;
}


