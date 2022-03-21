import java.awt.*;
import java.awt.event.*;

public class MouseFunctions extends MouseAdapter{
	private int row = 0;
	private int col = 0;
	public void mouseClicked(MouseEvent e){
		int newcol = e.getX()/64;
		setCol(newcol);
        int newrow = e.getY()/64;
		setRow(newrow);
	}
	public void setCol(int newCol){
		col = newCol;
	}
	public void setRow(int newRow){
		row = newRow;
	}
	public int getCol(){
		return col;
	}
	public int getRow(){
		return row;
	}
	
	
}