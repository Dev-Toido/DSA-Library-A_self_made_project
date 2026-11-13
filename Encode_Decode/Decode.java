public class Decode extends Helper_functions {

	String cipherText;
	String plainText;

	String nCode;

	String[] nLetterEncoding;

	int skey;
	int con1, con2;

	Decode(String text, int sKey) {

		this.skey = sKey;
		this.cipherText = text;

		// EXACT same calculations as Encode
		con1 = Math.abs(digiPro(skey) + digiSum(skey));
		con2 = Math.abs(digiPro(skey) - digiSum(con1));

		nCode = String.valueOf(genNCode(skey));

		decodingStep1();
		decodingStep2();

		test();
	}

	// Recreate the same chunks produced by Encode
	void decodingStep1() {

		String temp = cipherText;

		String nLetter = "";

		int sum = 0;

		// EXACT same chunk calculation as Encode
		for (int i = 0; i < nCode.length(); i++) {

			int length = nPercentNum(
					Integer.parseInt(nCode),
					nCode.charAt(i) - '0',
					temp.length()
			);

			for (int j = 0; j < length; j++) {
				nLetter += temp.charAt(sum++);
			}

			nLetter += " ";
		}

		// Remaining characters
		for (int i = sum; i < temp.length(); i++) {
			nLetter += temp.charAt(i);
		}

		// EXACT same conversion as Encode
		nLetterEncoding = nLetter.trim().split(" ");
	}

	// Reverse the scrambling
	void decodingStep2() {

		plainText = "";

		for (int i = 0; i < nLetterEncoding.length; i++) {

			String currentChunk = nLetterEncoding[i];

			String newSTL = "";

			// IMPORTANT:
			// Encoder uses con2 = 51, NOT number of chunks.
			int token = tokenGen(con2, con1, i);

			for (int j = 0; j < currentChunk.length(); j++) {

				newSTL += (char)((int) currentChunk.charAt(j) - token);
			}

			nLetterEncoding[i] = newSTL;

			plainText += newSTL;
		}
	}

	void test() {

		System.out.println("Decoded text:");
		System.out.println(plainText);

		System.out.println("-----------------------------------");
		System.out.println("sKey = " + skey);
		System.out.println("con1 = " + con1);
		System.out.println("con2 = " + con2);
		System.out.println("nCode = " + nCode);
		System.out.println("-----------------------------------");
	}
}