// set a handler to receive a message
var eventBus = new EventBus('http://' + window.location.hostname + ':8077/eventbus');


eventBus.onopen = function () {

    eventBus.registerHandler('MatchStatus', function (i, message) {
        console.log('received a message: ', message);
        handleTeamStatus(message);

        handleMapUpdate(message);
        handleControlPoints(message);
        handleMissiles(message);
    });

};

// user data configuration
var map;


var user = prompt("Podaj nazwę użytkownika", "user1");
//var user = "user1";
var myTeam;
var userSequenceId;

// map configuration
var fieldSize = 32;
var mapSize = 3200;
var viewSize = mapSize / 10;
var noOfFieldsInView = viewSize / fieldSize;

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


