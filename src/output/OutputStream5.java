package output;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class OutputStream5 implements OutputStreamAbs {
    File myFile;
	FileChannel fileChanel;
	MappedByteBuffer buffer;
	public String path;
	int bufferSize;
	int fileNum;
	int curPtr;

	public OutputStream5(String path, int bufferSize, int fileNum) {
		this.path = path + Integer.toString(fileNum) + ".txt";
		this.bufferSize = bufferSize;
		this.fileNum = fileNum;
		curPtr = 0;
	}

	@Override
	public void create() throws FileNotFoundException, IOException {
		myFile = new File(path);
		myFile.delete();
		fileChanel = new RandomAccessFile(myFile, "rw").getChannel();
		buffer = fileChanel.map(FileChannel.MapMode.READ_WRITE, 0, bufferSize);
		buffer.load();
	}

	public void write(int value) throws IOException {

		if (buffer.hasRemaining()) {
			buffer.putInt(value);
			curPtr++;

		} else {
			buffer.clear();
			buffer = fileChanel.map(FileChannel.MapMode.READ_WRITE, curPtr * 4, bufferSize);
		}
	}


	public boolean isFinished() {
		if((curPtr)==myFile.length()) {
			return true;

		}
		else
			return false;
	}

	@Override
	public void close() throws IOException {
		buffer.clear();
		fileChanel.close();
	}

	@Override
	public String returnPath() {
		return path;
	}

}
