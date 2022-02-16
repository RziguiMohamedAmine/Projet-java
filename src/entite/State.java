package entite;

public enum State {
    inCart("pending"),
    placed("Placed");

    private final String state;
    private State(final String state){
        this.state=state;
    }

    @Override
    public String toString() {
        return state;
    }
}
