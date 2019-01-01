package output;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class OutputStream3 implements OutputStreamAbs {
	DataOutputStream dataOut;
	BufferedOutputStream bufferedOut;
	FileOutputStream fileOut;

	public String path;
	int bufferSize;
	int counter;

	public OutputStream3(String path, int bufferSize, int fileNum) throws FileNotFoundException {
		this.path = path + Integer.toString(fileNum) + ".txt";
		this.bufferSize = bufferSize;
		int counter = 0;
	}

	@Override
	public void create() throws FileNotFoundException {

		fileOut = new FileOutputStream(path);
		bufferedOut = new BufferedOutputStream(fileOut, bufferSize);
		dataOut = new DataOutputStream(bufferedOut);

	}

	@Override
	public void write(int value) throws IOException {
		dataOut.writeInt(value);
		counter++;
	}

	@Override
	public void close() throws IOException {
		bufferedOut.flush();
		fileOut.close();
		dataOut.close();
		bufferedOut.close();
	}

	@Override
	public String returnPath() {
		return path;
	}

}
