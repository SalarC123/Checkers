public class Player {
    private String name;
    private String color;
    private boolean isTurn;
    public boolean isAI;
    
    public Player(String n, String c, boolean t, boolean a) {// Constructor
        name = n;
        color = c;
        isTurn = t;
        isAI = a;
    }

    public String getName() {//Get
        return name;
    }

    public String getColor() {//Get
        return color;
    }

    public boolean getIsTurn() {//Get
        return isTurn;
    }

    public boolean getIsAI() {//Get
        return isAI;
    }

    public void setIsTurn(boolean t) {//Set
        isTurn = t;
    }
}