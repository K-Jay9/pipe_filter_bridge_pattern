public class MasterControl {

	Input input;
	CircularShifter circularShifter;
	Alphabetizer alphabetizer;
	Output output;
	Object o;

	MasterControl(){

	}

	public void start(PipeQueue pipeQueue, PipeQueue pipeQueue1, PipeQueue pipeQueue2, String inputFileName,
			String outputFileName) {
		input = new Input(this.o, pipeQueue, inputFileName);
		input.start();

		circularShifter = new CircularShifter(pipeQueue, pipeQueue1);
		circularShifter.start();

		alphabetizer = new Alphabetizer(pipeQueue1, pipeQueue2);
		alphabetizer.start();

		output = new Output(pipeQueue2, this.o, outputFileName);
		output.start();
	}

	public void start(PipeList pipeList, PipeList pipeList1, PipeList pipeList2, String inputFileName,
			String outputFileName) {
		input = new Input(this.o, pipeList, inputFileName);
		input.start();

		circularShifter = new CircularShifter(pipeList, pipeList1);
		circularShifter.start();

		alphabetizer = new Alphabetizer(pipeList1, pipeList2);
		alphabetizer.start();

		output = new Output(pipeList2, this.o, outputFileName);
		output.start();
	}

	public void start(PipeQueue pipeQueue, PipeList pipeList1, PipeList pipeList2, String inputFileName,
			String outputFileName) {
		input = new Input(this.o, pipeQueue, inputFileName);
		input.start();

		circularShifter = new CircularShifter(pipeQueue, pipeList1);
		circularShifter.start();

		alphabetizer = new Alphabetizer(pipeList1, pipeList2);
		alphabetizer.start();

		output = new Output(pipeList2, this.o, outputFileName);
		output.start();
	}

	public void start(PipeList pipeList, PipeQueue pipeQueue, PipeList pipeList1, String inputFileName,
			String outputFileName) {
		input = new Input(this.o, pipeList, inputFileName);
		input.start();

		circularShifter = new CircularShifter(pipeList, pipeQueue);
		circularShifter.start();

		alphabetizer = new Alphabetizer(pipeQueue, pipeList1);
		alphabetizer.start();

		output = new Output(pipeList1, this.o, outputFileName);
		output.start();
	}
}
