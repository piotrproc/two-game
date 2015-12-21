/**
 * Created by Piotr Proc on 30.11.15.
 */

function preload() {
    game.load.image('map', 'map/my_map.png', null, Phaser.Tilemap.TILED_JSON);
    game.load.image('change_player_button', 'img/icons/change_player.png', null, Phaser.Tilemap.TILED_JSON);
    game.load.image('reinforcement_button', 'img/icons/reinforcement_button.png', null, Phaser.Tilemap.TILED_JSON);
    game.load.image('control_point', 'img/icons/control_point.png', null, Phaser.Tilemap.TILED_JSON);


    unitNames.forEach(function (unitName) {
        var unitNameA = unitName + '_1';
        var unitNameB = unitName + '_2';
        var unitNameC = unitName + '_3';
        game.load.image(unitNameA, 'img/units/' + unitNameA + '.png', null, Phaser.Tilemap.TILED_JSON);
        game.load.image(unitNameB, 'img/units/' + unitNameB + '.png', null, Phaser.Tilemap.TILED_JSON);
        game.load.image(unitNameC, 'img/units/' + unitNameC + '.png', null, Phaser.Tilemap.TILED_JSON);
    });

}