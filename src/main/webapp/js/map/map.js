
// set a handler to receive a message
var eventBus = new EventBus('http://localhost:8077/eventbus');

eventBus.onopen = function () {

    eventBus.registerHandler('MatchStatus', function (i, message) {
        //console.log('received a message: ', message);
        handleTeamStatus(message);
        handleMapUpdate(message);
    });

};

var user = "user1";
var myTeam;
var myTeamList = [];
var userSequence;

// map configuration
var fieldSize = 32;
var mapSize = 3200;
var viewSize = mapSize / 10;

var unitNames = ['airforce', 'cannon', 'tank', 'soldier'];
var resource = 10000;
var resourceText;
var followedUnitID = 0; // unit that camera is currently following

var movingSprite = null;

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

var armySprites = [];



