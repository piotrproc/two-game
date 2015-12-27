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

    userSequence = message.body.sequenceId;
    setTimeout(function(){handleCreatingControlPoints(message)}, 1000); //we need here some delay
    handleTakingControlPoints(message);
}

