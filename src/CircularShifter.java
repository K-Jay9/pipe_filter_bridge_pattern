public class CircularShifter extends Filter {

	Pipe inPipe;
	Pipe outPipe;

	public CircularShifter(Pipe inPipe, Pipe outPipe) {
		super(inPipe, outPipe);
		this.inPipe = inPipe;
		this.outPipe = outPipe;
	}

	@Override
	public void start() {

		while (this.inPipe.isNotEmptyOrIsNotClosed()) {
			String res = "";
			String[] arr = this.inPipe.read().split(" ");
			int k = arr.length;
			while (k-- > 0) {
				String tmp = arr[0];
				for (int i = 1; i < arr.length; i++) {
					arr[i - 1] = arr[i];
				}
				arr[arr.length - 1] = tmp;
				for (String st : arr) {
					res += st + " ";
				}
				this.outPipe.write(res.trim());
				res = "";
			}
		}
		this.inPipe.close();
		this.outPipe.close();
		this.stop();
	}

	@Override
	void filter() {
	}
}
