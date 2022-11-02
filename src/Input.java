import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Input extends Filter {
	Pipe outPipe;
	String fileName;

	public Input(Object o, Pipe outPipe, String fileName) {
		super((Pipe) o, outPipe);
		this.outPipe = outPipe;
		this.fileName = fileName;
	}

	@Override
	public void start() {
		try {
			File myObj = new File(this.fileName);
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNext()) {
				String data = myReader.nextLine();
				this.outPipe.write(data);

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();

		}
		this.outPipe.close();
		this.stop();
	}

	@Override
	void filter() {
	}
}
