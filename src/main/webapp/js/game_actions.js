$("#show_game").click(function(){
    $("#cover").fadeOut(500, function() {
    //    $("#main-container").load("unit_selection.html");
        $.getScript("map.js");
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

function unitSelected(unitType) {
    $("#unit-selection").fadeOut(500, function(){
  //      $.getScript("js/map.js");
    });
}