package output;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;



public interface OutputStreamAbs {
	//create, write, close()
	public void create() throws FileNotFoundException, IOException;
	public void write(int value) throws IOException;
	public void close() throws IOException;
	public String returnPath();
	//boolean isFinished();
	
	
}
