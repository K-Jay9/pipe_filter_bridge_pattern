import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AlphabetizerTest {

	Alphabetizer alphabetizer;
	Pipe inPipe;
	Pipe outPipe;
	List<String> actual;

	@BeforeEach
	void setUp() {
		actual = new ArrayList<>();
	}

	@Test
	void alpha_2_lines_with_queues() {
		inPipe = new PipeQueue();
		outPipe = new PipeQueue();
		alphabetizer = new Alphabetizer(inPipe, outPipe);
		setUpInputPipe();

		alphabetizer.start();

		getActualList();
		assertSizeAndContents(actual);
	}

	@Test
	void alpha_2_lines_with_lists() {
		inPipe = new PipeList();
		outPipe = new PipeList();
		alphabetizer = new Alphabetizer(inPipe, outPipe);
		setUpInputPipe();

		alphabetizer.start();

		getActualList();
		assertSizeAndContents(actual);
	}

	private void assertSizeAndContents(List<String> actual) {
		assertSame(2, actual.size());
		assertEquals("bye hi", actual.get(0));
		assertEquals("hi bye foo", actual.get(1));
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
		inPipe.write("bye hi");
		inPipe.close();
	}

}
