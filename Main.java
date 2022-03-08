public class Main {
    public static void main(String[] args) {
        GameBoard gb = new GameBoard();
        System.out.println(gb);
        gb.updateBoard(5,0,4,1);
        gb.updateBoard(2,1,3,2);
        gb.updateBoard(2,3,3,4);
        gb.updateBoard(4,1,2,3);
        System.out.println(gb);
    }
}
