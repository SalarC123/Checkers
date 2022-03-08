class CheckerPiece{
  private String color;//Color
  private String status;//Status
  //Object Constructor (Below) Requires Color.
  public CheckerPiece(String oriColor){
    color = oriColor;//Sets Color
    status = "n";//Default status
  }
  //Object Constructor (Below) Requires Color & status
  public CheckerPiece(String oriColor, String oriStatus){
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
  return (status + " " + color);
 }
}