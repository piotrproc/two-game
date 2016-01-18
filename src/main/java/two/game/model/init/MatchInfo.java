package two.game.model.init;

import two.game.logic.GameState;
import two.game.model.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Contains info about which cannot change during the match
 * i.e. map description, game mode, list of players
 * todo
 */
public class MatchInfo implements Event {


    public static final String PLAYER_EXISTS_TEMPLATE = "Player %s exists in team %s";
    public static final String PLAYER_DOES_NOT_EXIST_TEMPLATE = "Player %s does not exist";
    public static final String TEAM_DOES_NOT_EXIST_TEMPLATE = "Team %s does not exist";

    private final String mapDescription;
    private final String gameMode;
    private final String teamOneId;
    private final String teamTwoId;
    private final int requiredPlayers;
    private GameState state;


    private Map<String,String> playerToTeam = new ConcurrentHashMap<>();
    private Map<String,List<String>> teamToPlayers = new ConcurrentHashMap<>();

    MatchInfo(String mapDescription, String gameMode, String firstTeamId, String secondTeamId, int requiredPlayers, GameState state) {

        this.mapDescription = mapDescription;
        this.gameMode = gameMode;
        this.teamOneId = firstTeamId;
        this.teamTwoId = secondTeamId;
        this.requiredPlayers = requiredPlayers;
        this.state = state;

        this.teamToPlayers.put(firstTeamId,new ArrayList<>());
    }

    public synchronized void addPlayer(String teamId, String playerId) throws PlayerExistsException, TeamException {

        if (this.state.isStarted()) return;

        boolean teamOk = checkTeam(teamId);
        if (!teamOk) {
            throw new TeamException(String.format(TEAM_DOES_NOT_EXIST_TEMPLATE,teamId));
        }

        String id = checkAdd(teamId,playerId);
        if (id != null) {
            throw new PlayerExistsException(String.format(PLAYER_EXISTS_TEMPLATE,playerId,id));
        }

        this.teamToPlayers.get(teamId).add(playerId);
        this.playerToTeam.put(playerId,teamId);

        if (this.playerToTeam.size() == this.requiredPlayers) {
            this.state.start();
        }

    }

    private boolean checkTeam(String teamId) {
        return teamId == this.teamOneId || teamId == this.teamTwoId;
    }

    private String checkAdd(String teamId, String playerId) {
        if (playerToTeam.containsKey(playerId)) {
            return playerToTeam.get(playerId);
        }
        return null;
    }

    public Map<String, List<String>> getTeamToPlayers() {
        return this.teamToPlayers;
    }

    public static MatchInfo getDefault() {
        return new MatchInfo("Assault on Warsaw", "Domination", "NATO", "USSR", 10, null);
    }

    public void setState(GameState state) {
        this.state = state;
    }


    public static class PlayerException extends Exception {

        public PlayerException(String s) {
            super(s);
        }

    }

    public static class PlayerExistsException extends PlayerException {

        public PlayerExistsException(String s) {
            super(s);
        }
    }

    public static class PlayerDoesNotExistException extends PlayerException {

        public PlayerDoesNotExistException(String s) {
            super(s);
        }

    }

    public static class TeamException extends Exception {

        public TeamException(String s) { super(s); }

    }



}
