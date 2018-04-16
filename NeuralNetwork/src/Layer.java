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
  public void initWeightMatrix(Layer from, Layer to){
    this.from_layer = from;
    this.to_layer = to;
    this.weight_matrix = new Matrix(to.neurons, from.neurons);
    this.weight_matrix.randomize();
    this.bias = new Matrix(to.neurons, 1);
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
  
	public void compute(Matrix input, Activation activation) throws Exception {
		Matrix result;
		this.input = input;

		if (this.weight_matrix != null) {
			Matrix out = Matrix.mul(this.weight_matrix, input);
			out.add(this.bias);
			result = activation.compute(out);
			this.output = result;
			System.out.println(this.to_layer.name);

			this.to_layer.compute(result, activation);
		}else {
			this.output = input;
		}
	}
  
}