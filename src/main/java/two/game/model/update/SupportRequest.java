package two.game.model.update;

public class SupportRequest {
    private Long amount;

    public SupportRequest(Long amount) {
        this.amount = amount;
    }

    public SupportRequest() {
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "SupportRequest{" +
                "amount=" + amount +
                '}';
    }
}
