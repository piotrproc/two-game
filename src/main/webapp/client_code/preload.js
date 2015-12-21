/**
 * Created by Piotr Proc on 30.11.15.
 */

function preload() {
    game.load.image('map', 'map/my_map.png', null, Phaser.Tilemap.TILED_JSON);

    var icons = [
        'change_player_button',
        'reinforcement_button',
        'control_point',
        'bullet'
    ];

    icons.forEach(function (icon){
        game.load.image(icon, 'img/icons/'+icon+'.png', null, Phaser.Tilemap.TILED_JSON);
    });

    unitNames.forEach(function (unitName) {
        for (var i = 1; i <= 3; i++) {
            game.load.image(unitName + '_' + i, 'img/units/' + unitName + '_' + i + '.png', null, Phaser.Tilemap.TILED_JSON);
        }
    });
}