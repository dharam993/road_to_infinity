import java.util.ArrayList;

public class Test {
	public static void main(String args[]) throws Exception {
		
	
			  System.out.println("executing setup");
			  //define input_layer
			  Layer input_layer = new Layer(2, "input");
			  //define hidden_layers
			  Layer hidden_layer1 = new Layer(2, "hidden");
			  Layer[] hidden_layers = new Layer[1];
			  hidden_layers[0] = hidden_layer1;
			  
			  //define output_layer
			  Layer output_layer = new Layer(1, "output");
			 
			 System.out.println("initializing network");
			  // initialize network
			  NeuralNetwork nn = new NeuralNetwork(input_layer, hidden_layers, output_layer, Sigmoid.class, 0.1);
			  
			  //prepating training data
			  InputSet inputSet = prepareTrainingData();
			  ArrayList<Input> inputs = inputSet.getInputs();
			  
			  //training model
			  for(int i = 0; i < 1000 ; i ++) {
				  Input data = inputs.get((int)Math.floor(Math.random() * 4));
				  nn.train(data.inputs, data.output);
			  }
			  
			  //predict output
			  double[] input_features = {0, 0};
			  nn.feedForward(input_features);
			  double[] result = Matrix.toArray(nn.output_layer.getOutput());
			  for(int i = 0; i< result.length; i ++)
				  System.out.println("output is :" + result[i]);
			}
	
	
	public static InputSet prepareTrainingData(){		
		double[] x1 = {1,0};
		double[] x2 = {0,1};
		double[] x3 = {1, 1};
		double[] x4 = {0,0};
		
		
		double[] inactive = {0};
		double[] active = {1};
		InputSet inputSet = new InputSet();
		inputSet.add(new Input(x1, active));
		inputSet.add(new Input(x2, active));
		inputSet.add(new Input(x3, inactive));
		inputSet.add(new Input(x4, inactive));
		
		return inputSet;
	}

	}
