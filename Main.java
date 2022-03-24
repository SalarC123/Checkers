/*
Names: Jacob, John, Nik, Salar
Date: April 1, 2022
Purpose: Use knowledge from inheritance, team development, agile, scrum, and github to create a functional checkers game
*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main{
    int row = 0;
	int col = 0;
    public ArrayList<String> possibleJumps = new ArrayList<String>();
	public static void main (String[] args)
	{
		try
		{
			Main obj = new Main();
			obj.run();
		}
		catch (Exception e)
		{
			e.printStackTrace ();
		}
	}
	int row = 0;
	int col = 0;
	boolean goodInp = false;
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
		
        // creates checkerboard and paints it with the board colors and default pieces
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

                // draw side labels
                for (int i = 0; i < 8; i++) {
                    g.setColor(Color.GRAY);
                    g.drawString(""+(i), 520, i*64 + 36);
                }

                // draw bottom labels
                for (int i = 0; i < 8; i++) {
                    g.setColor(Color.GRAY);
                    g.drawString(""+(i), i*64 + 32, 530);
                }

                for (int i = 0; i < possibleJumps.size(); i++) {
                    int possibleRow = Integer.parseInt(possibleJumps.get(i).substring(0,1));
                    int possibleCol = Integer.parseInt(possibleJumps.get(i).substring(2));
                    g.setColor(Color.CYAN);
                    g.drawRect(possibleCol*64, possibleRow*64, 64, 64);
                }
            }    
        };

        // listener for mouse clicks on board
        GUI.getContentPane().addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				col = e.getX()/64;
				row = e.getY()/64;
                possibleJumps = gb.getAllPossibleJumps(row, col);		
                checkerBoard.repaint();	
            }
		});

        GUI.add(checkerBoard);
        GUI.setVisible(true);
		
        String name = "";
		name = JOptionPane.showInputDialog("Player 1, enter your name: ");
		if(name==null){
			name = "Player 1";
		}
		else if(name.equals(" ")){
			name = "Player 1";
		}
		else if(name.equals("")){
			name = "Player 1";
		}
        Player p1 = new Player(name, "w", true, false);
        int n = JOptionPane.showConfirmDialog(null,"Would you like to play against the computer?","Computer", JOptionPane.YES_NO_OPTION);
        Player p2;
        if (n == JOptionPane.YES_OPTION) p2 = new Player("computer", "b", false, true);
        else {
			name = JOptionPane.showInputDialog("Player 2, enter your name: ");
			if(name==null){
				name = "Player 2";
			}
			else if(name.equals(" ")){
				name = "Player 2";
			}
			else if(name.equals("")){
				name = "Player 2";
			}
			p2 = new Player(name, "b", false, false);
        }

        // main game loop
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
                
                int t = JOptionPane.showConfirmDialog(null, "Have you chosen a piece yet?", "Choose Piece", JOptionPane.YES_NO_OPTION);
                System.out.println(t);
                //else System.out.println("INVALID PIECE");
            }

            System.out.print("Possible moves: ");
            System.out.println(gb.getAllPossibleJumps(row, col));
			String toIndices = "";
			while(!goodInp){
				toIndices = JOptionPane.showInputDialog(null, "Enter indices of where you would like to move (e.g. 4,2): ", "Make a Move");
				if(toIndices==null){
					goodInp = false;
				}
				else if(toIndices.equals("")){
					goodInp = false;
				}
				else if(toIndices.contains(" ")){
					goodInp = false;
				}
				else{
					goodInp = true;
				}
			}
			goodInp = false;
            int toRow = Integer.parseInt(toIndices.substring(0,1));
            int toCol = Integer.parseInt(toIndices.substring(2));

            while (!gb.updateBoard(row, col, toRow, toCol)) {
					while(!goodInp){
					toIndices = JOptionPane.showInputDialog(null, "Enter indices of where you would like to move (e.g. 4,2): ", "Make a Move");
					if(toIndices==null){
						goodInp = false;
					}
					else if(toIndices.equals("")){
						goodInp = false;
					}
					else if(toIndices.contains(" ")){
						goodInp = false;
					}
					else{
						goodInp = true;
					}
				}
                toRow = Integer.parseInt(toIndices.substring(0,1));
                toCol = Integer.parseInt(toIndices.substring(2));
            }
			goodInp = false;
            checkerBoard.repaint();

            p1.setIsTurn(!p1.getIsTurn());
            p2.setIsTurn(!p2.getIsTurn());

        }

        Player winner = p1.getColor().equals(gb.determineWinner()) ? p1: p2;
        System.out.println(winner.getName() + "IS THE WINNER!!!");

        // TODO: AI when user chooses to play against computer
        // TODO: force jumps and double jumps        
        // TODO: error handling if user does not enter name
        // TODO: make getAllPossibleJumps method take in which player is checking as well
        
    }
}