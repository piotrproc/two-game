/**
 * Created by Piotr Proc on 30.11.15.
 */

function handleTeamStatus(message){
    var teamStatuses = message.body.teamStatuses;

    teamA = (teamStatuses[0]).userIds;
    teamB = (teamStatuses[1]).userIds;

    if(teamA.indexOf(user) >= 0){
        myTeam = 1;
    }else{
        myTeam = 2;
    }

    userSequenceId = message.body.sequenceId;
    updateResourcesAmount(message, myTeam);
    setTimeout(function(){handleCreatingControlPoints(message)}, 1000); //we need here some delay
    handleTakingControlPoints(message);
}

function updateResourcesAmount(message, team){
    resource = (message.body.teamStatuses[team-1]).resourcesAmount;
    resourceText["text"] = 'Zasoby: ' + resource;
}

