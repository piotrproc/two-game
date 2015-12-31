/**
 * Created by Piotr Proc on 31.12.15.
 */

function killSprite(sprite, pool){
    pool.remove(sprite);
    sprite.healthBar.kill();
    sprite.kill();
}
