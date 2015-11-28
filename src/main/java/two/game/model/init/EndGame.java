package two.game.model.init;

import two.game.model.Event;

public class EndGame implements Event {
    private String winnerTeamId;

    public EndGame(String winnerTeamId) {
        this.winnerTeamId = winnerTeamId;
    }

    public EndGame() {

    }

    public String getWinnerTeamId() {
        return winnerTeamId;
    }

    public void setWinnerTeamId(String winnerTeamId) {
        this.winnerTeamId = winnerTeamId;
    }

    @Override
    public String toString() {
        return "EndGame{" +
                "winnerTeamId='" + winnerTeamId + '\'' +
                '}';
    }
}
