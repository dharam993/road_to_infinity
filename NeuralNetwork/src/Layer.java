import java.util.Arrays;

class Layer{
  
  int neurons;
  Matrix weight_matrix;
  Matrix bias;
  Layer from_layer;
  Layer to_layer;
  Matrix input;
  Matrix output;
  String name;
  
   //initialize Layer with number of neurons
  public Layer(int neurons, String name){
   this.neurons = neurons;
   this.name = name;
  }
  
  //initialize weight matrix
  public void initWeightMatrix(Layer to, Layer from){
    this.from_layer = from;
    this.to_layer = to;
    to.from_layer =from;
    this.weight_matrix = new Matrix(to.neurons, this.neurons);
    this.weight_matrix.randomize();
    this.bias = new Matrix(to.neurons, 1);
    this.bias.randomize();
  }
  
  public Matrix getWeightMatrix(){
    return weight_matrix;
  }
  
  public Matrix getOutput(){
    return output;
  }
  
  public Matrix getBias(){
    return bias;
  }
  
	public void feedForward(Matrix input, Activation activation) throws Exception {
		Matrix result;
		this.input = input;

		if (this.weight_matrix != null) {
			Matrix out = Matrix.mul(this.weight_matrix, input);
			out.add(this.bias);
			result = activation.compute(out);
			this.output = result;
			System.out.println(this.to_layer.name);

			this.to_layer.feedForward(result, activation);
		}else {
			this.output = input;
		}
	}
	
	
	public void backpropagate(Matrix target, Layer layer, Activation activation, double learning_rate) throws Exception {
		
		if(layer.from_layer == null) {
			return;
		}
		//calculate gradient
		Matrix gradient = activation.computeGradient(layer.output);
		
			//calculate output_errors
			Matrix error = Matrix.subtract(target, layer.output);
			gradient.mul(error);
			gradient.mul(learning_rate);

			// calculate delta
			Matrix transpose = layer.input.transpose();
			Matrix delta = Matrix.mul(gradient, transpose);

			// adjusting weights by delta
			layer.weight_matrix.add(delta);

			// adjusting bias
			layer.bias.add(gradient);
			
			
			Matrix hidden_gradient = activation.computeGradient(layer.output);
			//calculate hidden error
			Matrix transpose_h = layer.to_layer.weight_matrix.transpose();
			Matrix hidden_errors = Matrix.mul(transpose, target);
			hidden_gradient.mul(hidden_errors);
			hidden_gradient.mul(learning_rate);
			
		}
		
		
		
		
		
		
		
		
		
		
		//backpropagate
		layer.backpropagate(null, layer.from_layer,activation, learning_rate);
		
	}
  
}