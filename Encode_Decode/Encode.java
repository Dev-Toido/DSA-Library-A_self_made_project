public class Encode extends Helper_functions{
	String ogtext,nCode;
	String[] nLetterEncoding;
	String[] scramblednLetterEncoding;
	int skey;
	int con1,con2;
	Encode(String text,int sKey){
		this.skey=sKey;
		this.ogtext = text.trim();
		con1=Math.abs(digiPro(skey)+digiSum(skey));
		con2=Math.abs(digiPro(skey)-digiSum(con1));
		nCode=String.valueOf(genNCode(skey));
		encodingStep1();
		encodingStep2();
		test();
	}

	//To produce the n lettered string
	void encodingStep1(){
		String temp = "";
		String nLetter="";
		//To have a normal String without the space in temp
		for(char letter : ogtext.toCharArray()){
			if(letter==' '){continue;}
			temp += letter;
		}
		//To have the n-lettered string in n-Letter
		int sum=0;
		for(int i=0;i<nCode.length();i++){
			for(int j = 0; j<(nPercentNum(Integer.parseInt(nCode),nCode.charAt(i)-'0',temp.length())); j++){
				nLetter+=temp.charAt(sum++);
			}
			nLetter+=" ";
		}
		for(int i=sum;i<temp.length();i++){
			nLetter+=temp.charAt(i);
		}
		//conversation form the string to array of string
		nLetterEncoding=nLetter.trim().split(" ");
	}

	//To do the scramblization of the n lettered string
	void encodingStep2(){
		scramblednLetterEncoding = nLetterEncoding.clone();
		String scrambledNLetter;
		int token;
		for(int i=0;i<scramblednLetterEncoding.length;i++){
			scrambledNLetter = scramblednLetterEncoding[i];
			String newSTL="";
			for(int j = 0; j< scrambledNLetter.length(); j++){
				newSTL+=(char)((int) scrambledNLetter.charAt(j)+tokenGen(con2,con1,i));
			}
			scramblednLetterEncoding[i]=newSTL;
		}
	}
	//To display the n-letter string arrays
	void diplaynletteredString(String[] s){
//		for (String string : s) {
//			System.out.print(string+" ");
//		}
		System.out.println();
		for (String string : s) {
			System.out.print(string);
		}
		System.out.println();
	}
	void test(){
		System.out.println(ogtext);
		System.out.println("con1="+con1+" con2="+con2+" ncode="+nCode);
		diplaynletteredString(nLetterEncoding);
		diplaynletteredString(scramblednLetterEncoding);
		System.out.println();
	}

}
