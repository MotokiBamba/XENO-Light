
public class Grave extends XENO { //スーパークラスXENOを拡張しサブクラスGrave = 墓地クラス
	
	int[] grave1 = new int[10]; //Player1の墓地
	int[] grave2 = new int[10]; //Player2の墓地
	static int index1 = 0;
	static int index2 = 0;
	
	void graveOne() {
		
		grave1[index1] = summonnum1;
		index1++;
		
		System.out.print("Player1墓地 → ");
		int i = 0;
		while(true){
			if(grave1[i] == 0) {
				break;
			}
			System.out.print(grave1[i] + " ");
			i++;
		}
		
	}
	
	void graveCarry1() { //カードの性能によって場に捨てられたカード(数字)を墓地に入れる(格納する)メソッド
		                //Player1の墓地に入れる用
		
		grave1[index1] = grave;
		index1++;
		
	}
	
	void graveTwo() {
		
		grave2[index2] = summonnum2;
		index2++;
		
		System.out.print("Player2墓地 → ");
		int i = 0;
		while(true) {
			if(grave2[i] == 0) {
				break;
			}
			System.out.print(grave2[i] + " ");
			i++;
		}
		
	}
	
	void graveCarry2() { //カードの性能によって場に捨てられたカード(数字)を墓地に入れる(格納する)メソッド
		                 //Player2の墓地に入れる用
		
		grave2[index2] = grave;
		index2++;
		
	}
}
