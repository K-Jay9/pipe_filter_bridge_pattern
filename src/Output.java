import java.io.FileWriter;
import java.io.IOException;

public class Output extends Filter {

	Pipe inPipe;
	String fileName;

	public Output(Pipe inPipe, Object o, String fileName) {
		super(inPipe, (Pipe) o);

		this.inPipe = inPipe;
		this.fileName = fileName;
	}

	@Override
	public void start() {
		try {
			FileWriter myFile = new FileWriter(this.fileName);
			while (this.inPipe.isNotEmptyOrIsNotClosed()) {
				myFile.write(this.inPipe.read());
				myFile.write("\n");
			}
			myFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.inPipe.close();
		this.stop();
	}

	@Override
	void filter() {

	}
}
