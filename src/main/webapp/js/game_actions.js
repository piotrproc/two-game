var userInfo = {};

$("#show_game").click(function(){
    $("#cover").fadeOut(500, function() {
        $("#main-container").load("team_selection.html");
    });
});

function onGameFinished(gameResult) {
    $("#game-container").fadeOut(500, function() {
        showHighscores(gameResult);
    })
}

function showHighscores(gameResult) {
    $("#highscores").removeClass("invis");
    gameResult.sort(function(a, b) {
        return a[1]-b[1];
    });

    for (var i=0; i<gameResult.length; ++i) {
        $("#hs-table")
            .find("thead")
            .after("<tr><th>"+(gameResult.length-i)+"</th><th>"+gameResult[i][0]+"</th><th>"+gameResult[i][1]+"</th></tr>");
    }
}

function teamSelected(teamType) {
    $("#team-selection").fadeOut(500, function(){
        $("#main-container").load("unit_selection.html");
    });
    userInfo["teamType"] = teamType;
}

function unitSelected(unitType) {
    //$("#unit-selection").fadeOut(500, function(){
    //    $("#main-container").load("map.html");
    //});
    userInfo["unitType"] = unitType;
    Sender.sendPlayerInitData(userInfo);
}

function nicknameChanged() {
    var nick = $("#nickname").val();
    if (nick == "") {
        $("#show_game").prop("disabled", true);
    } else {
        $("#show_game").prop("disabled", false);
        userInfo["nickname"] = nick;
    }
}