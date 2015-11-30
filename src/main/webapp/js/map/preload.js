/**
 * Created by Piotr Proc on 30.11.15.
 */

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