import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OutputTest {

	Output output;
	Pipe inPipe;
	List<String> actual;

	@BeforeEach
	void setUp() {
		actual = new ArrayList<>();
	}

	@Test
	void output_lines_to_file_with_queues() throws IOException, InterruptedException {
		String fileName = "output1.txt";
		inPipe = new PipeQueue();
		output = new Output(inPipe, null, fileName);
		setUpInputPipe();

		output.start();
		Thread.sleep(1000);

		getActualList(fileName);
		assertSizeAndContents(actual);
	}

	@Test
	void output_lines_to_file_with_list() throws IOException, InterruptedException {
		String fileName = "output2.txt";
		inPipe = new PipeList();
		output = new Output(inPipe, null, fileName);
		setUpInputPipe();

		output.start();
		Thread.sleep(1000);

		getActualList(fileName);
		assertSizeAndContents(actual);
	}

	private void assertSizeAndContents(List<String> actual) {
		assertSame(2, actual.size());
		assertEquals("hi bye", actual.get(0));
		assertEquals("hi bye foo", actual.get(1));
	}

	private void getActualList(String fileName) throws FileNotFoundException {
		File file = new File(fileName);
		Scanner reader = new Scanner(file);
		while (reader.hasNextLine()) {
			actual.add(reader.nextLine());
		}
		reader.close();
	}

	private void setUpInputPipe() {
		inPipe.write("hi bye");
		inPipe.write("hi bye foo");
		inPipe.close();
	}

}
