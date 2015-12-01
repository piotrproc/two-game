
// set a handler to receive a message
var eventBus = new EventBus('http://localhost:8077/eventbus');

eventBus.onopen = function () {

    eventBus.registerHandler('MatchStatus', function (i, message) {
        console.log('received a message: ', message);
        handleTeamStatus(message);
        handleMapUpdate(message);
    });

};

var userSequence = 1;

// map configuration
var fieldSize = 32;
var mapSize = 3200;
var viewSize = mapSize / 10;

var unitNames = ['airforce', 'cannon', 'tank', 'soldier'];
var resource = 10000;
var resourceText;
var followedUnitID = 0; // unit that camera is currently following

var movingSprite = null;
var movingSpriteInfo = null;

var teamA = [];
var teamB = [];

// game modifiers are split into three files
var game = new Phaser.Game(viewSize, viewSize, Phaser.AUTO, '', {
    preload: preload,
    create: create,
    update: update
});

function Field(x, y){
    this.x = x;
    this.y = y;
}

function ArmyElement(x, y, name, id) {
    this.x = x * fieldSize;
    this.y = y * fieldSize;
    this.name = name;       //name specifies PNG for unit
    this.id = id;           //unique id
}

var armySprites = [];
var initialArmy = [];

function getUnitSprite(field) {
    var foundUnit = null;

    armySprites.forEach(function (unit) {
        if (unit.x == field.x * fieldSize && unit.y == field.y * fieldSize) {
            foundUnit = unit;
        }
    });

    return foundUnit;
}

function getUnitSpriteWithId(id) {
    var foundUnit = null;

    armySprites.forEach(function (unit) {
        if (unit.id == id) {
            foundUnit = unit;
        }
    });

    return foundUnit;
}

function checkIfSuchUnitAlreadyExists(newUnit){
    var isFound = false;

    armySprites.forEach(function (unit) {
        if (unit.id == newUnit.id) {
            isFound = true;
        }
    });
    return isFound;
}


