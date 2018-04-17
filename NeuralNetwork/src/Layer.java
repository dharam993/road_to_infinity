class Layer {

	int neurons;
	Matrix weight_matrix;
	Matrix bias;
	Layer from_layer;
	Layer to_layer;
	Matrix input;
	Matrix output;
	Matrix layer_error;
	String name;

	// initialize Layer with number of neurons
	public Layer(int neurons, String name) {
		this.neurons = neurons;
		this.name = name;
	}

	// initialize weight matrix
	public void initWeightMatrix(Layer to, Layer from) {
		this.from_layer = from;
		this.to_layer = to;
		this.weight_matrix = new Matrix(to.neurons, this.neurons);
		this.weight_matrix.randomize();
		this.bias = new Matrix(to.neurons, 1);
		this.bias.randomize();
	}

	public Matrix getWeightMatrix() {
		return weight_matrix;
	}

	public Matrix getOutput() {
		return output;
	}

	public Matrix getBias() {
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
		} else {
			this.output = input;
		}
	}

	public void backpropagate(Matrix output_error, Layer layer, Activation activation, double learning_rate)
			throws Exception {

		if (layer.from_layer == null) {
			return;
		}

		// check if output layer
		if (layer.to_layer == null) {

			//output_error
			Matrix output_gradient = activation.computeGradient(layer.output);
			Matrix layer_error = output_error.mul(output_gradient);
			layer.from_layer.layer_error = layer_error;
		//	gradient.mul(learning_rate);

			Matrix inputTranspose = layer.from_layer.input.transpose();
			Matrix delta = Matrix.mul(layer_error, inputTranspose);
			delta.mul(learning_rate);

			// adjusting weights by delta
			layer.from_layer.weight_matrix.subtract(delta);

			// adjusting bias by gradient
			layer.from_layer.bias.subtract(layer_error);
			
		} else {
			// calculate error
			Matrix transpose = layer.weight_matrix.transpose();
			Matrix hidden_errors = Matrix.mul(transpose, layer.layer_error);
			Matrix hidden_gradient = activation.computeGradient(layer.input);
			
			Matrix layer_error = hidden_errors.mul(hidden_gradient);
			layer.from_layer.layer_error = layer_error;
			
			//hidden_gradient.mul(hidden_errors);
			//hidden_gradient.mul(learning_rate);

			// calculate deltas
			Matrix inputTranspose = layer.from_layer.input.transpose();
				
			Matrix delta = Matrix.mul(layer_error, inputTranspose);
			delta.mul(learning_rate);
			
			layer.from_layer.weight_matrix.subtract(delta);
			layer.from_layer.bias.subtract(layer_error);
		}
		
		layer.backpropagate(output_error, layer.from_layer, activation, learning_rate);
	}
}