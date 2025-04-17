import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class XENO{ //大まかなルールを定義
	
	Integer[] cards = {1, 1, 2, 2, 3, 3, 4, 4, 5, 6}; //山札
	Integer[] player1 = new Integer[2]; //Player1の手札
	Integer[] player2 = new Integer[2]; //Player2の手札
	int revivalcard; //転生札
	static int grave = 0; //墓地に入れる数字を格納する変数
	static int summonnum1 = 1; //Player1が召喚した数字を格納する変数
	static int summonnum2 = 1; //Player2が召喚した数値を格納する変数
	SoundPlayer lose = new SoundPlayer("lose.wav");
	SoundPlayer win = new SoundPlayer("win.wav");
	SoundPlayer draw = new SoundPlayer("draw.wav");
	
	Scanner stdIn = new Scanner(System.in);
	
	void revival() { //転生札を設定するメソッド
		
		int revivalnum = (int)(Math.random() * cards.length);
		revivalcard = cards[revivalnum];
		List<Integer> list = new ArrayList<>(Arrays.asList(cards)); //配列(山札)をリスト化
		list.remove(cards[revivalnum]); //転生札をリストから除く
		cards = list.toArray(new Integer[list.size()]); //転生札を除いたリストの中身を配列(山札)に戻す
		
	}
	
	void card1(Integer[] a) { //カード１(相手のカードを当てる)のルール定義
		//a配列は相手の手札
		
		System.out.println("カード１が召喚されました。\n相手のカードを予想し、あてることができればあなたの勝ちです。");
		System.out.println("ただし、あてたカードが６のときは相手は転生札を手札として復活します。");
		
		System.out.print("予想する数字を入力してください。 : ");
		int guess;
		do {
			guess = stdIn.nextInt();
		}while(guess < 1 || 6 < guess);
		
		if(a[0] == guess && a[0] != 6) { //オーソドックスにあてることができたとき
			System.out.println("あたりです。あなたの勝ちです。");
			win.loop(win.loopNumber);
			System.exit(0);
		}
		else if(a[0] == guess && a[0] == 6 && cards.length != 0) { //オーソドックスにあてて転生したとき
			System.out.println("あたりです。\nあてたカードは６だったので、相手が転生しました。");
			grave = guess;
			a[0] = revivalcard;
			
			revivalcard = 0; //転生札によって復活したPlayerに転生したことを伝えるための数値設定
		}
		else if(a[0] == guess && a[0] == 6 && cards.length == 0 && a == player1) {
			//山札はなくなっているが、転生札は残っているとき = Player2がターン3でカード１を召喚して、あてたとき
			System.out.println("あたりです。\nあてたカードは６だったので、相手が転生しました。");
			grave = guess;
			a[0] = revivalcard;
			
			revivalcard = 0; //転生札によって復活したPlayerに転生したことを伝えるための数値設定
		}
		else if(a[0] == guess && a[0] == 6 && cards.length == 0 && a == player2) {
			//転生札を山札として、それを使い切り、山札がなくなっているとき = Player1がターン4でカード１を召喚して、相手の6をあてたとき
			System.out.println("あたりです。\n当てたカードは６ですが、転生札は既に山札とされているため、");
			System.out.println("転生できません。よって、あなたの勝ちです。");
			win.loop(win.loopNumber);
			System.exit(0);
		}
		else {
			System.out.println("はずれです。ゲームを続行します。");
		}
		
	}
	
	void card2(Integer[] a) { //カード２(相手の数字を知ることができる)のルール定義
		//a配列は相手の手札
		
		System.out.println("カード２が召喚されました。\n相手の手札を知ることができます。");
		
		System.out.println("相手の手札 → " + a[0]);
		
	}
	
	void card3(Integer[] a, Integer[] b) { //カード３(対決)のルール定義
		//a配列をそのターンの人の手札、b配列を相手の手札とする
		
		System.out.println("カード３が召喚されました。\n対決を実施します。カードの数字の大きい方が勝ちです。");
		
		System.out.println("あなたの手札 → " + a[0] + " / 相手の手札 → " + b[0]);
		
		if(a[0] > b[0]) {
			System.out.println("あなたの勝ちです。");
			win.loop(win.loopNumber);
			System.exit(0);
		}
		else if(a[0] == b[0]) {
			System.out.println("引分けです。");
			draw.loop(draw.loopNumber);
			System.exit(0);
		}
		else {
			System.out.println("あなたの負けです。");
			lose.loop(lose.loopNumber);
			System.exit(0);
		}
	}
	
	void card4(Integer[] a, Integer[] b) { //カード４(交換)のルール定義
		//手札に関して、card3メソッドと同じ設定とする
		
		System.out.println("カード４が召喚されました。\nあなたと相手のカードを交換します。");
		
		Integer oldA = a[0];
		a[0] = b[0];
		b[0] = oldA;
		
		System.out.println("あなたの新たな手札 → " + a[0]);
		
	}
	
	void card5of1() { //カード５(相手に１牧引かせて、元の手札と合わせた２枚を見て、１枚選んで捨てさせる)のルール定義
		//Player1が使う用
		//a配列は相手の手札
		
		System.out.println("カード５が召喚されました。");
		System.out.println("相手に１枚引かせて、元の手札と合わせた２枚を見て、\n１枚選んで捨てさせることができます。");
		System.out.println("ここで、カード６を捨てさせることができればあなたの勝ちです。");
		
		if(cards.length == 0) {
			//山札がなく、１枚引かせることができないとき = Player1がターン4でカード５を召喚したとき
			//最後に持っていた両者の数字で大きい方を勝ちとする。
			judge(player1, player2);
		}
		
		//山札から１枚引く
		int index1 = (int)(Math.random() * cards.length);
		player2[1] = cards[index1];
		
		List<Integer> list1 = new ArrayList<>(Arrays.asList(cards)); //配列(山札)をリスト化
		list1.remove(cards[index1]); //引かれたカードをリストから除く
		cards = list1.toArray(new Integer[list1.size()]); //引かれたカードを除いたリストの中身を配列(山札)に戻す
		
		System.out.println("相手の手札→" + player2[0] + ", " + player2[1]);
		System.out.println("どちらを場に出させますか？");
		
		int index2;
		do {
			System.out.println(player2[0] + " → 0 / " + player2[1] + " → 1");
			System.out.print("場に出させるカード : ");
			index2 = stdIn.nextInt();
		}while(index2 != 0 && index2 != 1);
		
		grave = player2[index2];
		System.out.println("場に" + grave + "を出させました。");
		
		//配列(相手の手札)をリスト化
		List<Integer> list2 = new ArrayList<>(Arrays.asList(player2));
		//選ばれたカードをリストから除く
		list2.remove(player2[index2]);
		//選ばれたカードを除いたリストの中身(=１枚)を配列(相手の手札)に戻す
		player2 = list2.toArray(new Integer[list2.size() + 1]);
		
		if(grave == 6) {
			System.out.println("あなたの勝ちです。");
			win.loop(win.loopNumber);
			System.exit(0);
		}
		
	}
	
	void card5of2() { //カード５(相手に１牧引かせて、元の手札と合わせた２枚を見て、１枚選んで捨てさせる)のルール定義
		//Player2が使う用
		//a配列は相手の手札
		
		//if(cards.length == 0){
		//    judge(Player1, Player2);
		//}
		//山札がなくなっている状態でPlayer2がカード５を召喚することは決してないため、上記のif文は不要。
		
		System.out.println("カード５が召喚されました。");
		System.out.println("相手に１枚引かせて、元の手札と合わせた２枚を見て、\n１枚選んで捨てさせることができます。");
		System.out.println("ここで、カード６を捨てさせることができればあなたの勝ちです。");
		
		//山札から１枚引く
		int index1 = (int)(Math.random() * cards.length);
		player1[1] = cards[index1];
		
		List<Integer> list1 = new ArrayList<>(Arrays.asList(cards)); //配列(山札)をリスト化
		list1.remove(cards[index1]); //引かれたカードをリストから除く
		cards = list1.toArray(new Integer[list1.size()]); //引かれたカードを除いたリストの中身を配列(山札)に戻す
		
		System.out.println("相手の手札 → " + player1[0] + ", " + player1[1]);
		System.out.println("どちらを場に出させますか？");
		
		int index2;
		do {
			System.out.println(player1[0] + " → 0 / " + player1[1] + " → 1");
			System.out.print("場に出させるカード : ");
			index2 = stdIn.nextInt();
		}while(index2 != 0 && index2 != 1);
		
		grave = player1[index2];
		System.out.println("場に" + grave + "を出させました。");
		
		//配列(相手の手札)をリスト化
		List<Integer> list2 = new ArrayList<>(Arrays.asList(player1));
		//選ばれたカードをリストから除く
		list2.remove(player1[index2]);
		//選ばれたカードを除いたリストの中身(=１枚)を配列(相手の手札)に戻す
		player1 = list2.toArray(new Integer[list2.size() + 1]);
		
		if(grave == 6) {
			System.out.println("あなたの勝ちです。");
			win.loop(win.loopNumber);
			System.exit(0);
		}
		
	}
	
	void card6() {
		//カード６は手札から場に出せないカードであり、性能自体はないため、未定義でよい
	}
	
	void judge(Integer[] a, Integer[] b) { //山札がなくなったときの勝敗設定
		//手札に関して、card3メソッドと同じ設定とする
		
		System.out.println("山札がなくなりました。");
		System.out.println("Player1の手札 → " + a[0] + " / Player2の手札 → " + b[0]);
		
		if(a[0] > b[0]) {
			System.out.println("Player1の勝ちです。");
			win.loop(win.loopNumber);
			System.exit(0);
		}
		else if(a[0] == b[0]) {
			System.out.println("引分けです。");
			draw.loop(draw.loopNumber);
			System.exit(0);
		}
		else {
			System.out.println("Player2の勝ちです。");
			win.loop(win.loopNumber);
			System.exit(0);
		}
	}

}
