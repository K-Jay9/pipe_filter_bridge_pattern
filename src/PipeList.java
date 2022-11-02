import java.util.ArrayList;
import java.util.List;

public class PipeList implements Pipe {

	List<String> pipeList;
	private boolean closed;

	public PipeList() {
		pipeList = new ArrayList<>();
		closed = false;
	}

	@Override
	public boolean isNotEmptyOrIsNotClosed() {
		return !pipeList.isEmpty() || !closed;
	}

	@Override
	public boolean hasNext() {
		return !pipeList.isEmpty();
	}

	@Override
	public String read() {
		return pipeList.remove(0);
	}

	@Override
	public void write(String hi_bye_foo) {
		pipeList.add(hi_bye_foo);
	}

	@Override
	public void close() {
		closed = true;
	}
}
