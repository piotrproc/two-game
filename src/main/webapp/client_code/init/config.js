// set a handler to receive a message
var eventBus = new EventBus('http://localhost:8077/eventbus');

eventBus.onopen = function () {

    eventBus.registerHandler('MatchStatus', function (i, message) {
        //console.log('received a message: ', message);
        handleTeamStatus(message);

        handleAddingControlPoints(message); //we need here some delay
        handleMapUpdate(message);
        handleTakingControlPoints(message);
    });

};

// user data configuration
var map;
var user = "user1";
var myTeam;
var userSequenceId;

// map configuration
var fieldSize = 32;
var mapSize = 3200;
var viewSize = mapSize / 10;

// game configuration
var unitNames = ['airforce', 'cannon', 'tank', 'soldier'];
var resource = 10000;
var resourceText;
var followedUnitID = 0; // unit that camera is currently following

// game objects configuration
var myArmyPool;
var bulletPool;
var oppositeArmyPool;
var missilePool;
var allSpritesPool;
var controlPointPool;

var movingSprite = null;

var teamA = [];
var teamB = [];

// game modifiers are split into three files
var game = new Phaser.Game(viewSize, viewSize, Phaser.AUTO, 'game', {
    preload: preload,
    create: create,
    update: update
});


function Field(x, y) {
    this.x = x;
    this.y = y;
}


