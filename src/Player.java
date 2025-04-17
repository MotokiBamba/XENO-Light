import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Player extends XENO { //スーパークラスXENOを拡張しサブクラスPlayer
	                               //⇒進行にあたってmainメソッドを見やすくするためのメソッドを用意
	
	//Player1用のメソッド
    void firstCard1(){ //ゲームを開始する前の１番最初の手札決め
		
		int default1 = (int)(Math.random() * cards.length);
		player1[0] = cards[default1];
		
		List<Integer> list = new ArrayList<>(Arrays.asList(cards));
		list.remove(cards[default1]);
		cards = list.toArray(new Integer[list.size()]);
		
		System.out.println("あなた(Player1)の最初の持ち札 → " + player1[0]);
		
    }
	
	void drawOne() { //ターンの始めに手札を１枚引く
		
		System.out.println("手札を追加します。");
		
		int index = (int)(Math.random() * cards.length);
		player1[1] = cards[index];
		
		List<Integer> list = new ArrayList<>(Arrays.asList(cards));
		list.remove(cards[index]);
		cards = list.toArray(new Integer[list.size()]);
		
		System.out.println(player1[1] + "を獲得しました。");
		System.out.println("あなた(Player1)の手札 → " + player1[0] + ", " + player1[1]);
		
	}
	
	void summonOne() { //手札２枚のうち、１枚を召喚する
		
		int index;
		System.out.print("どちらのカードを召喚しますか？");
		
		do {
			System.out.println("カード６は召喚できません。");
			System.out.println(player1[0] + " → 0 / " + player1[1] + " → 1");
			System.out.print("召喚するカード : ");
			index = stdIn.nextInt();
		}while(index != 0 && index != 1 || player1[index] == 6);
		
		summonnum1 = player1[index];
		
		List<Integer> list = new ArrayList<>(Arrays.asList(player1));
		list.remove(player1[index]);
		player1 = list.toArray(new Integer[list.size() + 1]);
		
	}
	
	void displayOne() { //ターンの終わりに残った手札を確認する
		
		System.out.println("あなた(Player1)の残った手札 → " + player1[0]);
		
	}
	
	//以下、上記と同様にPlayer2用のメソッドを用意してある。
    void firstCard2() {
    	
		int default2 = (int)(Math.random() * cards.length);
		player2[0] = cards[default2];
		
		List<Integer> list = new ArrayList<>(Arrays.asList(cards));
		list.remove(cards[default2]);
		cards = list.toArray(new Integer[list.size()]);
		
		System.out.println("あなた(Player2)の最初の持ち札 → " + player2[0]);
		
	}
	
    void drawTwo() {
		
    	if(cards.length == 0) {
    		//Player2はターン4で番が回ってくるとき、山札は絶対になくなっている。
    		//そのときに備えたif文である。
    		judge(player1, player2); //これ以上カードは引けないので、持っている数字が大きい方を勝ちとする。
    	}
    	
		System.out.println("手札を追加します。");
		
		int index = (int)(Math.random() * cards.length);
		player2[1] = cards[index];
		
		List<Integer> list = new ArrayList<>(Arrays.asList(cards));
		list.remove(cards[index]);
		cards = list.toArray(new Integer[list.size()]);
		
		System.out.println(player2[1] + "を獲得しました。");
		System.out.println("あなた(Player2)の手札 → " + player2[0] + ", " + player2[1]);
		
	}
    
    void summonTwo() {
		
    	int index;
		System.out.print("どちらのカードを召喚しますか？");
		
		do {
			System.out.println("カード６は召喚できません。");
			System.out.println(player2[0] + " → 0 / " + player2[1] + " → 1");
			System.out.print("召喚するカード : ");
			index = stdIn.nextInt();
		}while(index != 0 && index != 1 || player2[index] == 6);
		
		summonnum2 = player2[index];
		
		List<Integer> list = new ArrayList<>(Arrays.asList(player2));
		list.remove(player2[index]);
		player2 = list.toArray(new Integer[list.size() + 1]);
		
	}
	
	void displayTwo() {
		
		System.out.println("あなた(Player2)の残った手札 → " + player2[0]);
		
	}
}
