package main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FileClass {

	public File myFile;
	int fileSize; // assign required file size
	String path;
	public List<Integer> randomList;

	public FileClass(int fileSize,String mainPath) {
		this.fileSize = fileSize;
		this.path = mainPath;
		this.randomList=generateRandomList();
		myFile=createFileFun();
	}

	public File createFileFun() {
		return new File(path);
	}

	public List<Integer> generateRandomList() {

		List<Integer> randomList = new ArrayList<>();
		Random ran=new Random();
		int randomNumber;
		for (int i = 0; i < fileSize*262144; i++) {
			randomNumber=(int)(Math.random()*Integer.MAX_VALUE);
			randomList.add(randomNumber);
		
		}
		return randomList;

	}
}
