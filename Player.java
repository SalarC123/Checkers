public class Player {
    private String name;
    private String color;
    private boolean isTurn;
    public boolean isAI;
    
    public Player(String n, String c, boolean t, boolean a) {
        name = n;
        color = c;
        isTurn = t;
        isAI = a;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public boolean getIsTurn() {
        return isTurn;
    }

    public boolean getIsAI() {
        return isAI;
    }

    public void setIsTurn(boolean t) {
        isTurn = t;
    }
}
