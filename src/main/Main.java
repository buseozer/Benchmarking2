package main;

import java.io.IOException;
import java.util.List;

public class Main {

	public static void main(String[] args) throws IOException {

		int bufferSize = 1024*8;
		int methodType = 4;
		int numberOfd=64;
		String operationType = "RW";
		String path = "C:\\Users\\buse\\Desktop\\BigFile0.txt";

		Sort ex = new Sort(methodType, bufferSize, operationType, path);
		List<String> myList = ex.ExternalMainFunc();
		Merge newMerge = new Merge(bufferSize, methodType, "RW",numberOfd);
		newMerge.mergeFirstPhase(myList);

		
	}

}
