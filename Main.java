
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
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        try {
            Main obj = new Main();
            obj.run(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    int row = 0;
    int col = 0;
    String boardPlayerInfo = "";
    // boolean goodInp = false;

    public void run(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        GameBoard gb = new GameBoard();
        JOptionPane.showMessageDialog(null,
                "Thanks for playing our checkers game! \nFirst, each player will enter their name. \nThen, you will take turns selecting the pieces you want to move, and then where you want to move that piece. \nGood Luck!");
        JFrame GUI = new JFrame();
        GUI.setSize(547, 600);
        GUI.setResizable(false);
        GUI.setTitle("CheckerBoard");
        GUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // GUI.getContentPane().addMouseListener(new MouseAdapter() {
        //     public void mouseClicked(MouseEvent e) {
        //         col = e.getX() / 64;
        //         row = e.getY() / 64;
        //     }
        // });

        JPanel checkerBoard = new JPanel() {
            @Override
            public void paint(Graphics g) {
                boolean white = true;
                for (int i = 0; i < gb.getGameBoard().length; i++) {
                    for (int j = 0; j < gb.getGameBoard()[0].length; j++) {
                        CheckerPiece piece = gb.getGameBoard()[j][i];

                        if (white) g.setColor(Color.GRAY);
                        else g.setColor(Color.RED);
                        g.fillRect(i * 64, j * 64, 64, 64);

                        if (piece == null) {
                            white = !white;
                            continue;
                        }

                        String pieceType = piece.toString().substring(0, 1);
                        String pieceColor = piece.getColor();

                        if (pieceColor.equals("b")) g.setColor(Color.BLACK);
                        else g.setColor(Color.WHITE);

                        g.fillOval(i * 64, j * 64, 64, 64);
                        if (pieceType.equals("k")) {
                            g.setColor(Color.RED);
                            g.fillOval(i * 64 + 4, j * 64 + 4, 56, 56);
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

                g.setColor(Color.RED);
                g.drawString(boardPlayerInfo, 100, 555);
            }
        };

        GUI.add(checkerBoard);
        GUI.setVisible(true);

        String name = "";
        name = JOptionPane.showInputDialog("Player 1, enter your name: ");
        if (name == null) {
            name = "Player 1";
        } else if (name.equals(" ")) {
            name = "Player 1";
        } else if (name.equals("")) {
            name = "Player 1";
        }
        Player p1 = new Player(name, "w", true, false);
        int n = JOptionPane.showConfirmDialog(null, "Would you like to play against the computer?", "Computer",
                JOptionPane.YES_NO_OPTION);
        Player p2;
        if (n == JOptionPane.YES_OPTION)
            p2 = new Player("computer", "b", false, true);
        else {
            name = JOptionPane.showInputDialog("Player 2, enter your name: ");
            if (name == null) {
                name = "Player 2";
            } else if (name.equals(" ")) {
                name = "Player 2";
            } else if (name.equals("")) {
                name = "Player 2";
            }
            p2 = new Player(name, "b", false, false);
        }

        while (true) {
            if (!gb.determineWinner().equals("none"))
                break;
            Player currentPlayer = p1.getIsTurn() ? p1 : p2;
            String currentPlayerColor = currentPlayer.getColor();
            boardPlayerInfo = currentPlayer.getName() + "'s turn - " + currentPlayerColor;
            checkerBoard.repaint();

            ArrayList<String> forceJumpSpot = gb.canForceJump(currentPlayer);
            boolean goodInput = false;
            if (forceJumpSpot.isEmpty()) {
                String indices="";
                while (!goodInput) {
                    indices = JOptionPane.showInputDialog(null, "Enter indices of piece you want to move (e.g. 1,3): ", "Make a Move");
                
                    if (indices == null) {
                        goodInput = false;
                    } else if (indices.equals("")) {
                        goodInput = false;
                    } else if (indices.contains(" ")) {
                        goodInput = false;
                    } else if (indices.length() != 3) {
                        goodInput = false;
                    } else {
                        row = Integer.parseInt(indices.substring(0,1));
                        col = Integer.parseInt(indices.substring(2));

                        CheckerPiece currentPiece = gb.getGameBoard()[row][col];
                        if (currentPiece != null &&
                                currentPiece.getColor().equals(currentPlayerColor) &&
                                gb.getAllPossibleJumps(row, col).size() > 0) goodInput = true;
                        else goodInput = false;
                    }
                }

                System.out.print("Possible moves: ");
                System.out.println(gb.getAllPossibleJumps(row, col));
                
                String toIndices = "";
                boolean goodInp = false;
                while (!goodInp) {
                    toIndices = JOptionPane.showInputDialog(null,
                            "Enter indices of where you would like to move (e.g. 4,2): ", "Make a Move");
                    if (toIndices == null) {
                        goodInp = false;
                    } else if (toIndices.equals("")) {
                        goodInp = false;
                    } else if (toIndices.contains(" ")) {
                        goodInp = false;
                    } else if (toIndices.length() != 3) {
                        goodInp = false;
                    } else {
                        goodInp = true;
                    }
                }
                goodInp = false;
                int toRow = Integer.parseInt(toIndices.substring(0, 1));
                int toCol = Integer.parseInt(toIndices.substring(2));

                while (!gb.updateBoard(currentPlayer, row, col, toRow, toCol)) {
                    while (!goodInp) {
                        toIndices = JOptionPane.showInputDialog(null,
                                "Enter indices of where you would like to move (e.g. 4,2): ", "Make a Move");
                        if (toIndices == null) {
                            goodInp = false;
                        } else if (toIndices.equals("")) {
                            goodInp = false;
                        } else if (toIndices.contains(" ")) {
                            goodInp = false;
                        } else if (toIndices.length() != 3) {
                            goodInp = false;
                        } else {

                            toRow = Integer.parseInt(toIndices.substring(0, 1));
                            toCol = Integer.parseInt(toIndices.substring(2));

                            if (gb.getAllPossibleJumps(row, col).contains(toRow+"-"+toCol)) goodInp = true;
                            else goodInp = false;
                        }
                    }
                }
                checkerBoard.repaint();
            } else {
                boolean doubleJump = false;
                // does force jump
                do {
                    int fromRow = Integer.parseInt(forceJumpSpot.get(0).substring(0,1));
                    int fromCol = Integer.parseInt(forceJumpSpot.get(0).substring(2));
                    int toRow = Integer.parseInt(forceJumpSpot.get(1).substring(0,1));
                    int toCol = Integer.parseInt(forceJumpSpot.get(1).substring(2));

                    TimeUnit.SECONDS.sleep(1);
                    gb.updateBoard(currentPlayer, fromRow, fromCol, toRow, toCol);
                    TimeUnit.SECONDS.sleep(1);

                    // checks if double jump is possible
                    ArrayList<String> newPossibleJumps = gb.getAllPossibleJumps(toRow, toCol);
                    for (String jump: newPossibleJumps) {
                        int newToRow = Integer.parseInt(jump.substring(0,1));
                        if (Math.abs(newToRow-toRow) == 2) {
                            forceJumpSpot.set(0, toRow+","+toCol);
                            forceJumpSpot.set(1, jump);
                            doubleJump = true;
                        } else {
                            doubleJump = false;
                            break;
                        }

                    }
                } while (doubleJump);
            }

            p1.setIsTurn(!p1.getIsTurn());
            p2.setIsTurn(!p2.getIsTurn());

        }

        Player winner = p1.getColor().equals(gb.determineWinner()) ? p1 : p2;
        System.out.println(winner.getName() + " IS THE WINNER!!!");

        // TODO: AI when user chooses to play against computer
        // TODO: change piece that you chose to move if you change your mind
        // TODO: error checking (make sure first and third spot are digits and are 0-7)
        // TODO: Display physical win screen
        // TODO: fix error with checkWin

    }
}