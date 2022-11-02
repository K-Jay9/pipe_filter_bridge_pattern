import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InputTest {

	Input input;
	Pipe outPipe;
	List<String> actual;

	@BeforeEach
	void setUp() {
		actual = new ArrayList<>();
	}

	@Test
	void input_from_scanner_with_queue() throws IOException {
		String fileName = "input1.txt";
		writeFileForReading(fileName);
		outPipe = new PipeQueue();
		input = new Input(null, outPipe, fileName);

		input.start();

		getActualList();
		assertSizeAndContents();
	}

	@Test
	void input_from_scanner_with_list() throws IOException {
		String fileName = "input2.txt";
		writeFileForReading(fileName);
		outPipe = new PipeList();
		input = new Input(null, outPipe, fileName);

		input.start();

		getActualList();
		assertSizeAndContents();
	}

	private void writeFileForReading(String fileName) throws IOException {
		List<String> lines = new ArrayList<>();
		lines.add("Descent of Man");
		lines.add("The Ascent of Man");
		lines.add("The Old Man and The Sea");
		lines.add("A Portrait of The Artist As a Young Man");
		FileWriter fileWriter = new FileWriter(fileName);
		for (String line : lines) {
			fileWriter.write(line);
			fileWriter.write("\n");
		}
		fileWriter.close();
	}

	private void assertSizeAndContents() {
		assertSame(4, actual.size());
		assertEquals("Descent of Man", actual.get(0));
		assertEquals("The Ascent of Man", actual.get(1));
		assertEquals("The Old Man and The Sea", actual.get(2));
		assertEquals("A Portrait of The Artist As a Young Man", actual.get(3));
	}

	private void getActualList() {
		while (outPipe.isNotEmptyOrIsNotClosed()) {
			if (outPipe.hasNext()) {
				actual.add(outPipe.read());
			}
		}
	}

}
