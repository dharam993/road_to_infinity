
public class Test {
	public static void main(String args[]) throws Exception {
		
	
			  System.out.println("executing setup");
			  //define input_layer
			  Layer input_layer = new Layer(5, "input");
			  //define hidden_layers
			  Layer hidden_layer1 = new Layer(2, "hidden");
			  Layer hidden_layer2 = new Layer(2, "hidden");
			  Layer hidden_layer3 = new Layer(2, "hidden");
			  Layer[] hidden_layers = new Layer[3];
			  hidden_layers[0] = hidden_layer1;
			  hidden_layers[1] = hidden_layer2;
			  hidden_layers[2] = hidden_layer3;
			  
			  //define output_layer
			  Layer output_layer = new Layer(3, "output");
			 
			 System.out.println("initializing network");
			  // initialize network
			  NeuralNetwork nn = new NeuralNetwork(input_layer, hidden_layers, output_layer, Sigmoid.class, 0.1);
			  double[] input_features = {0, 1,1,2,3};
			  
			  nn.feedForward(input_features);
			  double[] result = Matrix.toArray(output_layer.getOutput());
			  for(int i = 0; i< result.length; i ++)
				  System.out.println(result[i]);
			}

	}
