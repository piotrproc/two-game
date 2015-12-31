/**
 * Created by Piotr Proc on 30.11.15.
 */

function create() {

    game.world.setBounds(0, 0, mapSize, mapSize);
    game.physics.startSystem(Phaser.Physics.ARCADE);
    map = game.add.sprite(0, 0, 'map');

    createMainBar();
    createShortcuts();
    createPools();
}

function createMainBar() {

    var textFont = {
        font: '32 px Arial',
        fill: '#ff0044'
    };

    resourceText = game.add.text(0, 0, 'Zasoby: ' + resource, textFont);
    resourceText.fixedToCamera = true;
    resourceText.cameraOffset.setTo(viewSize - 3.75 * fieldSize, 0.25 * fieldSize);

    var button = game.add.button(viewSize - 1.45 * fieldSize, 0.25 * fieldSize,
        'change_player_button', changeCameraToOtherPlayer, this, 2, 1, 0);
    var button2 = game.add.button(viewSize - 0.75 * fieldSize, 0.25 * fieldSize,
        'reinforcement_button', Sender.sendSupportRequest, this, 2, 1, 0);
    button.fixedToCamera = true;
    button2.fixedToCamera = true;
}

function createShortcuts(){
    game.input.keyboard.onDownCallback = function (e) {
        if (e.keyCode == 32) { //code for space key
            changeCameraToOtherPlayer();
        }
    };
}

function changeCameraToOtherPlayer() {
    followedUnitID = (followedUnitID + 1) % myArmyPool.countLiving();//myTeamList.length;
    game.camera.follow(myArmyPool.getAt(followedUnitID));//(myTeamList[followedUnitID]);
}

//    game.input.keyboard.onDownCallback = function(e) {
//        if(e.keyCode == 13){ //code for enter key
//            alert(user);
//            if(user){
//                if(user == "user1")
//                    user = "user2";
//                else
//                    user = "user1";
//            }
//        }
//    };