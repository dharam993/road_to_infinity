class NeuralNetwork{
  
  Layer input_layer; 
  Layer[] hidden_layers;
  Layer output_layer;
  Class activation;
  double learning_rate;
  
	NeuralNetwork(Layer input_layer, Layer[] hidden_layers, Layer output_layer, Class activation,
			double learning_rate) {
		this.input_layer = input_layer;
		this.hidden_layers = hidden_layers;
		this.output_layer = output_layer;
		this.activation = activation;
		this.learning_rate = learning_rate;

		// initialize weight matrices
		if (hidden_layers.length > 0) {
			Layer layer_ih1 = hidden_layers[0];
			this.input_layer.initWeightMatrix(this.input_layer, layer_ih1);
			/*Layer layer_hlo = hidden_layers[hidden_layers.length - 1];
			this.output_layer.initWeightMatrix(this.output_layer, layer_hlo);*/
		}

		if (hidden_layers.length > 0) {
			for (int i = 0; i < hidden_layers.length; i++) {
				Layer layer_hh = hidden_layers[i];
				if (i == hidden_layers.length - 1) {
					layer_hh.initWeightMatrix(hidden_layers[i], this.output_layer);
				} else {
					layer_hh.initWeightMatrix(hidden_layers[i], hidden_layers[i+1]);
				}
			}
		}
	}
  
  
	public void feedForward(double[] input_features) throws Exception {

		// check inputs
		if (input_features.length != this.input_layer.neurons) {
			throw new RuntimeException("input count should be: " + input_layer.neurons);
		}

		Matrix input_matrix = Matrix.fromArray(input_features);
		Layer layer = this.input_layer;
		Activation act = (Activation) this.activation.newInstance();
		System.out.println(layer.name);
		layer.compute(input_matrix, act);

	}
}