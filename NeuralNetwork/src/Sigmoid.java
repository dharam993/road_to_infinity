import java.lang.Math;
class Sigmoid implements Activation{
  
  public Sigmoid(){
  }
 
  public Matrix compute(Matrix input){
    for(int i = 0; i < input.rows; i++){
      for(int j = 0; j < input.cols; j++){
      input.data[i][j] = compute(input.data[i][j]);
      }
    }
    
    return input;
  }
  
  
  private double compute(double input){
  return 1/(1+Math.exp(input));
  }
  
  
}