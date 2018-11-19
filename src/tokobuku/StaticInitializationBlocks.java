package tokobuku;

public class StaticInitializationBlocks {
	static int[] val = new int[10];

	static {
		System.out.println("Running initialization block.");
		for (int i = 0; i < val.length; i++) {
			val[i] = (int) (100.0 * Math.random());
		}
	}

	void values() {
		for (int i = 0; i < val.length; i++) {
			System.out.print(" " + val[i]);
		}
		System.out.println();
	}

	public static void main(String[] args) {
		StaticInitializationBlocks staticBlock = new StaticInitializationBlocks();
		staticBlock.values();

		staticBlock = new StaticInitializationBlocks();
		staticBlock.values();
	}
}
