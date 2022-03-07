class CheckerPiece{
  private String color;//Color
  //private int Orientation = 0//0 = Down, 1 = Up, 2 = King
  //Above is Alternative Code for Kings, includes the Orientation of the Peice in it if it isn't going to be inherent to the Color.
  private String status;//Status
  //Object Constructor (Below) Requires Color.
  public void Checkerpiece(int oriColor){
    color = oriColor;//Sets Color
    status = "n";//Default status
  }
  //Object Constructor (Below) Requires Color & status
  public void Checkerpiece(int oriColor, int oristatus){
    color = oriColor;//Sets  Color
    status = oriStatus;//Sets status
  }
  public void KingMe(){
    status = "k";
  }
  public String getColor(){
    return color;
  }
  public String getStatus(){
    return status;
  }
  public String toString(){//overriding the toString() method  
  return (status + color);
 }
}