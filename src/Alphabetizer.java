import java.util.TreeSet;

public class Alphabetizer extends Filter {

	Pipe inPipe;
	Pipe outPipe;

	Alphabetizer(Pipe inPipe, Pipe outPipe) {
		super(inPipe, outPipe);
		this.inPipe = inPipe;
		this.outPipe = outPipe;
	}

	@Override
	public void start() {
		TreeSet<String> tree_set = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
		while (this.inPipe.isNotEmptyOrIsNotClosed()) {
			tree_set.add(this.inPipe.read());
		}
		for (String string : tree_set) {
			this.outPipe.write(string);
		}
		this.inPipe.close();
		this.outPipe.close();
		this.stop();
	}

	@Override
	void filter() {
	}
}
