class NeuralNetwork {

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
			input_layer.initWeightMatrix(layer_ih1, null);

			for (int i = 0; i < hidden_layers.length; i++) {
				Layer layer_hh = hidden_layers[i];
				if (i == hidden_layers.length - 1) {
					layer_hh.initWeightMatrix(output_layer, input_layer);
				} else {
					layer_hh.initWeightMatrix(hidden_layers[i + 1], hidden_layers[i - 1]);
				}
			}
			output_layer.from_layer = hidden_layers[hidden_layers.length - 1];
		} else {
			this.input_layer.initWeightMatrix(output_layer, null);
			this.output_layer.from_layer = input_layer;

		}
	}

	public void feedForward(double[] input_features) throws Exception {

		// check inputs
		if (input_features.length != this.input_layer.neurons) {
			throw new RuntimeException("input count should be: " + input_layer.neurons);
		}

		// change array into matrix
		Matrix input_matrix = Matrix.fromArray(input_features);
		Layer layer = this.input_layer;

		Activation act = (Activation) this.activation.newInstance();
		
		// compute
		layer.feedForward(input_matrix, act);
	}

	public void train(double[] input_features, double[] targets) throws Exception {
		
		this.feedForward(input_features);
		Activation activation = (Activation)this.activation.newInstance();
		Matrix output_error = Matrix.subtract(Matrix.fromArray(targets), this.output_layer.output);
		//Matrix delta_output = Matrix.mul(activation.computeGradient(this.output_layer.output),output_error);
		
		this.output_layer.backpropagate(output_error, output_layer, activation, this.learning_rate);
		
	}
}