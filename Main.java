import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;

public class Main{
	public static void main (String[] args)
	{
		try
		{
			Main obj = new Main();
			obj.run (args);
		}
		catch (Exception e)
		{
			e.printStackTrace ();
		}
	}
	int row = 0;
	int col = 0;
  public String InputIndices(Scanner sc){//Input Checker Function
    String Indices = sc.nextLine();//Input Line
    if(Indices.length() == 3 && Indices.charAt(0) >= 48 && Indices.charAt(0) <= 57 && Indices.charAt(2) >=48 && Indices.charAt(2) <= 57 && Indices.charAt(1) == 44){//Checks if everything meets Criteria
      /*
      if(Indices.charAt(1) == 45)
        Indices = Character.toString((Indices.toCharArray())[1]-1);
      */
    return Indices;
    }
    else{
      System.out.println("\nInvalid Input, Please Try Again");
      InputIndices(sc);//Recursive Function that forces user to put input over & over again until it is correct.
    }
    return null;
  }
	public void run(String[] args){
        Scanner sc = new Scanner(System.in);
        GameBoard gb = new GameBoard();
        JOptionPane.showMessageDialog(null, "Thanks for playing our checkers game! \nFirst, each player will enter their name. \nThen, you will take turns selecting the pieces you want to move, and then where you want to move that piece. \nGood Luck!");
        JFrame GUI = new JFrame(); 
        GUI.setSize(527, 550); 
		GUI.setResizable(false);
        GUI.setTitle("CheckerBoard"); 
        GUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		GUI.getContentPane().addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				col = e.getX()/64;
				row = e.getY()/64;
			}
		});
		
        JPanel checkerBoard = new JPanel() {
            @Override
            public void paint(Graphics g) {
                boolean white = true;
                for (int i = 0; i < gb.getGameBoard().length; i++) {
                    for (int j = 0; j < gb.getGameBoard()[0].length; j++) {
                        CheckerPiece piece = gb.getGameBoard()[j][i];

                        if (white) g.setColor(Color.GRAY);
                        else g.setColor(Color.RED);
                        g.fillRect(i*64, j*64, 64, 64);

                        if (piece == null) {
                            white = !white;
                            continue;
                        }

                        String pieceType = piece.toString().substring(0,1);
                        String pieceColor = piece.getColor();

                        if (pieceColor.equals("b")) g.setColor(Color.BLACK);
                        else g.setColor(Color.WHITE);

                        g.fillOval(i*64, j*64, 64, 64);
                        if (pieceType.equals("k")) {
                            g.setColor(Color.RED);
                            g.fillOval(i*64+4, j*64+4, 56, 56);
                        }

                        white = !white;
                    }
                    white = !white;
                }
            }    
        };

        GUI.add(checkerBoard);
        GUI.setVisible(true);

        String name = JOptionPane.showInputDialog("Player 1, enter your name: ");
        Player p1 = new Player(name, "w", true, false);
        int n = JOptionPane.showConfirmDialog(null,"Would you like to play against the computer?","Computer", JOptionPane.YES_NO_OPTION);
        Player p2;
        if (n == JOptionPane.YES_OPTION) p2 = new Player("computer", "b", false, true);
        else {
            p2 = new Player(JOptionPane.showInputDialog("Player 2, enter your name: "), "b", false, false);
        }

        boolean win = false;
        while (!win) {
            if (!gb.determineWinner().equals("none")) win = true;
            Player currentPlayer = p1.getIsTurn() ? p1: p2;
            String currentPlayerColor = currentPlayer.getColor();
            System.out.println(currentPlayer.getName() + "'s turn");

            boolean validPieceChosen = false;
            while (!validPieceChosen) {
				System.out.println(row+","+col);
                CheckerPiece currentPiece = gb.getGameBoard()[row][col];
                if (currentPiece != null && 
                    currentPiece.getColor().equals(currentPlayerColor) &&
                    gb.getAllPossibleJumps(row, col).size() > 0) validPieceChosen = true;
                //else System.out.println("INVALID PIECE");
            }

            System.out.print("Possible moves: ");
            System.out.println(gb.getAllPossibleJumps(row, col));
            System.out.print("Enter indices of where you would like to move (e.g. 4,2): ");
            String toIndices = InputIndices(sc);
            int toRow = Integer.parseInt(toIndices.substring(0,1));
            int toCol = Integer.parseInt(toIndices.substring(2));

            while (!gb.updateBoard(row, col, toRow, toCol)) {
                System.out.println("INVALID MOVE, TRY AGAIN");
                System.out.print("Enter indices of where you would like to move (e.g. 4,2): ");
                toIndices = sc.nextLine();
                toRow = Integer.parseInt(toIndices.substring(0,1));
                toCol = Integer.parseInt(toIndices.substring(2));
            }

            checkerBoard.repaint();

            p1.setIsTurn(!p1.getIsTurn());
            p2.setIsTurn(!p2.getIsTurn());

        }

        Player winner = p1.getColor().equals(gb.determineWinner()) ? p1: p2;
        System.out.println(winner.getName() + "IS THE WINNER!!!");

        // TODO: AI when user chooses to play against computer
        // TODO: force jumps and double jumps        
        
    }
}