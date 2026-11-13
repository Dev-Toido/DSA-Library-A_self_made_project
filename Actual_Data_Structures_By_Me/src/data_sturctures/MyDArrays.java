package data_sturctures;
//This is staled as I will use the ArrayList instead and will be continued if needed
public class MyDArrays extends Input {
	int[] arr;
	int size;
	final int maxSize=100;
	//Construct Zone
	public MyDArrays(int size){
		if(size>maxSize){
			throw new IllegalArgumentException("The size is greater than the maximum size!!");
		}
		arr = new int[size];
		this.size = size;
	}
	public MyDArrays(){
		arr = new int[size];
	}
	public MyDArrays(int[] arr){
		this.arr = arr.clone();
	}
	//Operations
	//Input
	public boolean inputArr(){
		try{
			for (int i = 0; i < size; i++) {
				arr[i] = inputInt();
			}
			return true;
		}
		catch (Exception e){
			return false;
		}
	}
	//Output
	public void displayArr(){
		for(int i=0;i<size;i++){
			System.out.print(arr[i]+" ");
		}
	}
	//Insertion
	public boolean insertionAtIndex(int index,int value){
		//Enter 0(Zero) for insertion in beginning
		//Enter -1 for insertion at the end
		if(index<-1 || index>=size || size == maxSize){
			return false;
		}
		else{
			try{
				int[] newArr = new int[arr.length + 1];
				if(index==-1){
					newArr = arr.clone();
					newArr[newArr.length-1] = value;
					arr = newArr;size++;
					return true;
				}
				for (int i = 0, j = 0; i < size; i++) {
					if (arr[i] == index) {
						newArr[j++] = value;
					}
					newArr[j++] = arr[i];
				}
				arr = newArr;size++;
				return true;
			}
			catch (Exception e){
				return false;
			}
		}
	}
//	//Deletion
//	public boolean deletionAtIndex(int index){
//		//Enter 0 to delete the 1st element
//		//Enter -1 to the last Element
//		if(index<-1 || index>=size || size == 0){
//			return false;
//		}
//		else{
//			try{
//				int[] newArr = new int[arr.length - 1];
//				if(index==-1){
//
//				}
//			}
//			catch (Exception e){
//				return false;
//			}
//		}
//	}



}
