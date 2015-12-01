/**
 * Created by Piotr Proc on 30.11.15.
 */

var textFont = {
    font: '32 px Arial',
    fill: '#ff0044'
};

function create() {

    game.world.setBounds(0, 0, mapSize, mapSize);
    //  We're going to be using physics, so enable the Arcade Physics system
    game.physics.startSystem(Phaser.Physics.ARCADE);

    var map = game.add.sprite(0, 0, 'map'); //  A simple background for our game

    resourceText = game.add.text(0, 0, 'Zasoby: ' + resource, textFont);
    resourceText.fixedToCamera = true;
    resourceText.cameraOffset.setTo(viewSize - 3.25 * fieldSize, 0.25 * fieldSize);

    var button = game.add.button(viewSize - 0.75 * fieldSize, 0.25 * fieldSize, 'button', changeCameraToOtherPlayer, this, 2, 1, 0);
    button.fixedToCamera = true;

    game.camera.follow(armySprites[followedUnitID]);

    game.input.keyboard.onDownCallback = function(e) {
        if(e.keyCode == 32){ //code for space key
            changeCameraToOtherPlayer();
        }
    }
}

function changeCameraToOtherPlayer() {
    followedUnitID = (followedUnitID + 1) % myTeamList.length;
    game.camera.follow(myTeamList[followedUnitID]);
}