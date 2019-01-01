package main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import input.InputStream3;
import input.InputStream4;
import input.InputStreamAbs;
import output.OutputStream3;
import output.OutputStream5;
import output.OutputStreamAbs;

public class StreamDriver<T> {
	int numberOfStreams;
	int bufferSize;
	int availableMemory;
	int methodType;
	String operationType;
	String[] path;
	String[] pathre;
	String pathwr = "C:\\Users\\buse\\Desktop\\output\\";
	int fileSize;
	int counter;
	InputStreamAbs input;
	OutputStreamAbs output;
	int numberOfInputStreams;
	int numberOfOutputStreams;
	public List<InputStreamAbs> inputStreamList;
	List<OutputStreamAbs> outputStreamList;

	public StreamDriver(int numberOfStreams, int methodType, int bufferSize, String operationType, int fileSize,
			String... path) throws IOException {
		this.path = path;
		this.numberOfStreams = numberOfStreams;
		this.methodType = methodType;
		this.bufferSize = bufferSize;
		this.operationType = operationType;
		this.fileSize = fileSize;
		inputStreamList = new ArrayList<>();
		outputStreamList = new ArrayList<>();
		counter = 0;
		createStreams();
		//mainDriver();
	}

	public StreamDriver(int numberOfInputStreams, int methodType, int bufferSize,
			String operationType, String... path) throws IOException {
		this.numberOfInputStreams = numberOfInputStreams;
		this.methodType = methodType;
		this.bufferSize = bufferSize;
		this.operationType = operationType;
		this.path = path;
		availableMemory = bufferSize / 4;
		inputStreamList = new ArrayList<>();
		outputStreamList = new ArrayList<>();
		counter = 0;
		createStreams();
	//	divideBigIntoFiles();
	}

	public List<String> divideBigIntoFiles() throws FileNotFoundException, IOException {
		List<String> outputfiles=new ArrayList<>();
		int value;
		int i = 0;
		for (InputStreamAbs streamInput : inputStreamList) {
			streamInput.open();
			while (!inputStreamList.get(0).endOfStream()) {
				OutputStreamAbs output = createOutputType(i);
				outputStreamList.add(output);
				output.create();
				outputfiles.add(output.returnPath());
				while (counter != (bufferSize / 4)) {
					value = streamInput.read();
					if(value==1) {
						continue;
					}
					output.write(value);
					counter++;
					
				}
				output.close();
				i++;//
				System.out.println("-----" + counter);
				counter = 0;

			}
			streamInput.close();
		}
		return outputfiles;

	}

	public void createStreams() throws IOException {
		if((operationType.equalsIgnoreCase("R"))||(operationType.equalsIgnoreCase("W"))) {
			for (int i = 0; i < numberOfStreams; i++) {
			

			if (operationType.equalsIgnoreCase("R")) {
				InputStreamAbs input = createInputType(i);
				inputStreamList.add(input);

			} else if (operationType.equalsIgnoreCase("W")) {
				System.out.println("writing operator");

				OutputStreamAbs output = createOutputType(i);
				outputStreamList.add(output);
				System.out.println();

			}
			
			
			
		}}
		else if((operationType.equalsIgnoreCase("RW"))){
			for (int i = 0; i < numberOfInputStreams; i++) {
				InputStreamAbs input = createInputType(i);
				inputStreamList.add(input);
			}
		}

	}

	/*
	 * public void mainDriver() throws IOException {// open and read till end of the
	 * file if (findRightStream() == 1) { System.out.println("read"); for (int i =
	 * 0; i < inputStreamList.size(); i++) { inputStreamList.get(i).open();
	 * System.out.println("ilk etap");
	 * 
	 * } while (!inputStreamList.get(0).endOfStream()) { for (int i = 0; i <
	 * inputStreamList.size(); i++) { inputStreamList.get(i).read(); } }
	 * System.out.println("done"); for (int i = 0; i < inputStreamList.size(); i++)
	 * { inputStreamList.get(i).close(); }
	 * 
	 * } else if (findRightStream() == 2) { for (int i = 0; i <
	 * outputStreamList.size(); i++) { System.out.println("Output Stream");
	 * outputStreamList.get(i).create(); } while
	 * (!outputStreamList.get(0).isFinished()) { for (int i = 0; i <
	 * outputStreamList.size(); i++) { outputStreamList.get(i).write(); } } for (int
	 * i = 0; i < outputStreamList.size(); i++) { outputStreamList.get(i).close(); }
	 * 
	 * } else System.out.println("Streams are empty");
	 * 
	 * }
	 */

	public int findRightStream() {
		if (inputStreamList.size() > 0 && outputStreamList.size() > 0)
			return 3;
		else if (inputStreamList.size() > 0)
			return 1;
		else if (outputStreamList.size() > 0)
			return 2;
		else
			return 0;
	}

	/*
	 * public void createOperationType() throws IOException { if
	 * (operationType.equalsIgnoreCase("R")) { createInputType(); } else if
	 * (operationType.equalsIgnoreCase("W")) { createOutputType(); } else
	 * System.out.println("Please enter a valid operation type: W or R"); }
	 */

	public InputStreamAbs createInputType(int i) {
		if (methodType == 3) {
			return new InputStream3(path[i], bufferSize);
		} else if (methodType == 4) {
			return new InputStream4(path[i], bufferSize);
		} else
			return null;

	}

	public OutputStreamAbs createOutputType(int i) throws IOException {
		if (methodType == 3) {
			return new OutputStream3(pathwr, bufferSize, i);
		} else if (methodType == 4) {
			return new OutputStream5(pathwr, bufferSize, i);
		} else {
			System.out.println("Please enter a valid type");
			return null;
		}

	}

}
