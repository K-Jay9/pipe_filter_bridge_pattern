import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class PipeQueue implements Pipe {

	private Queue<String> queue;
	private boolean closed;

	public PipeQueue() {
		queue = new ConcurrentLinkedQueue<>();
		closed = false;
	}

	@Override
	public boolean isNotEmptyOrIsNotClosed() {
		return !queue.isEmpty() || !closed;
	}

	@Override
	public boolean hasNext() {
		return !queue.isEmpty();
	}

	@Override
	public String read() {
		return queue.poll();
	}

	@Override
	public void write(String hi_bye_foo) {
		queue.offer(hi_bye_foo);
	}

	@Override
	public void close() {
		closed = true;
	}
}
