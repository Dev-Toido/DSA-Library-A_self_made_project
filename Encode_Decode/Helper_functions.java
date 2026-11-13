public class Helper_functions {
	//to generate the ncode
	int genNCode(int n){
		if(n>=111 && n<=999){
			int[] skey=new int[3];
			skey[0]=n;skey[1]=digiSum(skey[0]);skey[2]=digiSum(skey[1]);
			int temp = (skey[0]*100+skey[1])*10+skey[2];
			return temp;
		}
		else{
			return 0;
		}
	}
	//return the n_percent_number
	int nPercentNum(int ncode,int n,int strlen){
		return (int)(((double)n/digiSum(ncode))*strlen);
	}
	//return the products of the digits
	int digiPro(int a){
		String s = Integer.toString(a);
		int pro = 1;
		for(int i = 0; i < s.length(); i++){
			pro *= s.charAt(i)-'0';
		}
		return pro;
	}
	//return the sum of digits
	int digiSum(int a){
		String s = Integer.toString(a);
		int sum = 0;
		for(int i = 0; i < s.length(); i++){
			sum += s.charAt(i)-'0';
		}
		return sum;
	}
	//To check whether the number is even or not
	boolean isEven(int n){
		if(n%2==0){return true;}
		return false;
	}
	//A function that makes token number to scramble the n lettered string
	int tokenGen(int con1,int con2,int var){
		if(isEven(con1)&&isEven(con2)){
			return -((isEven(var))?con1:con2);
		}
		else if(!isEven(con1)&&!isEven(con2)){
			return ((isEven(var))?con1:con2);
		}
		else if(!isEven(con1)&&isEven(con2)){
			return ((isEven(var))?con2:-con1);
		}
		else if(isEven(con1)&&!isEven(con2)){
			return ((isEven(var))?-con1:con2);
		}
		return 0;
	}
}
