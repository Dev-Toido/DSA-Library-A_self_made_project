public class Main {

	public static void main(String[] args) {

		String original =
				"The cat is the lazy one to see.";

		int key = 345;

		// -------------------------
		// ENCODE
		// -------------------------

		Encode encoder = new Encode(original, key);

		String cipherText = "";

		for (String part : encoder.scramblednLetterEncoding) {
			cipherText += part;
		}

		System.out.println("Ciphertext:");
		System.out.println(cipherText);


		// -------------------------
		// DECODE
		// -------------------------

		Decode decoder = new Decode(cipherText, key);

		System.out.println();
		System.out.println("Original without spaces:");
		System.out.println(decoder.plainText);
	}
}