import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CircularShifterTest {

	CircularShifter circularShifter;
	Pipe inPipe;
	Pipe outPipe;
	List<String> actual;

	@BeforeEach
	void setUp() {
		actual = new ArrayList<>();
	}

	@Test
	void shift_2_lines_completely_with_queues() {
		inPipe = new PipeQueue();
		outPipe = new PipeQueue();
		circularShifter = new CircularShifter(inPipe, outPipe);
		setUpInputPipe();

		circularShifter.start();

		getActualList();
		assertSizeAndContents(actual);
	}

	@Test
	void shift_2_lines_completely_with_lists() {
		inPipe = new PipeList();
		outPipe = new PipeList();
		circularShifter = new CircularShifter(inPipe, outPipe);
		setUpInputPipe();

		circularShifter.start();

		getActualList();
		assertSizeAndContents(actual);
	}

	private void assertSizeAndContents(List<String> actual) {
		assertSame(5, actual.size());
		assertTrue(actual.contains("bye hi"));
		assertTrue(actual.contains("hi bye"));
		assertTrue(actual.contains("foo hi bye"));
		assertTrue(actual.contains("bye foo hi"));
		assertTrue(actual.contains("hi bye foo"));
	}

	private void getActualList() {
		while (outPipe.isNotEmptyOrIsNotClosed()) {
			if (outPipe.hasNext()) {
				actual.add(outPipe.read());
			}
		}
	}

	private void setUpInputPipe() {
		inPipe.write("hi bye foo");
		inPipe.write("hi bye");
		inPipe.close();
	}

}
