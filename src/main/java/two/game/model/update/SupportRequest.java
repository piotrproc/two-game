package two.game.model.update;

import two.game.model.Event;

public class SupportRequest implements Event {
    private String user;


    public SupportRequest(String user) {
        this.user = user;
    }

    public SupportRequest() {
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "SupportRequest{" +
                "user=" + user +
                "}";
    }
}
