/**
 * Created by Piotr Proc on 31.12.15.
 */

function move(targetPosition){
    console.log(Helper.calculatePointedFieldTwo());
//    if(Helper.buttonsWereClicked(Helper.calculatePointedFieldTwo()))
//        return;

    this.x = targetPosition.x;
    this.y = targetPosition.y;

    this.healthBar.setPosition(
        this.x + fieldSize/2,
        this.y + fieldSize
    );

    this.anchor.setTo(0, 0);
}