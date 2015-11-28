package two.game.model.init;

public class EndGame {
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
