package Week1;

public class ClasssPractice {
	
	public static int[] data = {1,7,1,3,10,100,99,33,15,71,10};
	public static int sumMultiples(int[] inputArray) {
		int res = 0;
		for(int i=0;i<data.length; i++) {
			if(data[i] % 3 == 0) {
				res += data[i];
			}
		}		
		
		return res;
	}
	public static void main(String[] args) {
		System.out.print("Sum = "+ sumMultiples(data));
		System.out.print("\n");
		//Practice p = new Practice();
		//System.out.print(p.data);
		Practice p = new Practice();
		System.out.print(p.data);
		System.out.print(Practice.time);
	}

}
