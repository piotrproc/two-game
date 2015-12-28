/**
 * Created by Piotr Proc on 27.12.15.
 */

function UnitData(id, name, type, team, health) {
    this.id = id;
    this.name = name;
    this.type = type;
    this.team = team;
    this.health = health;
}

function handleMapUpdate(message) {
    var unitStatuses = message.body.unitStatuses;

    unitStatuses.forEach(function (unit) {

        var unitData = handleUnitData(unit);
        var position = unit.position;

        var sprite = getUnitSpriteWithId(unitData);
        if(sprite)
            updateHealthBar(sprite, unitData);

        var destinationSprite = getUnitSprite(position);
        if (destinationSprite != null)
            return;

        if (sprite) {
            updateUnitOnServerOrder(sprite, unitData, position);
        } else {
            addNewSprite(unitData, position);
        }

    });
}

function handleUnitData(unit) {
    var unitType = unit.type.toLowerCase();
    var unitTeam;

    if (teamA.indexOf(unit.user) >= 0)
        unitTeam = 1;
    else
        unitTeam = 2;

    var unitName = unitType + "_" + unitTeam;

    return new UnitData(unit.unitId, unitName, unitType, unitTeam, unit.health);
}

