/**
 * Created by Piotr Proc on 31.12.15.
 */

function kill(){
    this.pool.remove(this);
    this.healthBar.kill();
    this.kill();
}