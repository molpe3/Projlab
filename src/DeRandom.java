import java.io.IOException;
import java.util.Random;

public class DeRandom extends Random {

	private static final long serialVersionUID = -86146685753240378L;
	boolean isRandom = true;

	public int next(int bound, Animal caller) {
		if (isRandom) {
			return super.nextInt(bound);
		} else {
			try {
				return caller.AskForRandomNumber();
			} catch (IOException e) {
				System.err.println("Who crossed the streams?");
			}
		}
		return 0; // Only reachable due to error, but 0 is always in bound
	}

	public int next(int bound, Thing caller) {
		if (isRandom) {
			return super.nextInt(bound);
		} else {
			try {
				return caller.AskForRandomNumber();
			} catch (IOException e) {
				System.err.println("Who crossed the streams?");
			}
		}
		return 0; // Only reachable due to error, but 0 is always in bound
	}
}
