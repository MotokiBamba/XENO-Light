import java.util.Scanner;

public class XENOTest {

	public static void main(String[] args) {
		
		Scanner stdIn = new Scanner(System.in);
		
		int select;
		Player p = new Player();
		Grave g = new Grave();
		SoundPlayer start = new SoundPlayer("start.wav");
		int turn = 1;
		int count = 0; //転生の数を数える変数
		
		System.out.println("XENO-Lightへようこそ");
		start.loop(start.loopNumber);
		System.out.println("まずはこちらで転生札を設定します。");
		p.revival(); //転生札の設定
		
		System.out.println("Playerのみなさんに最初の持ち札を与えます。\nまずはPlayer1のターンです。");
		
		do {
			System.out.print("Player1は準備ができたら１を入力してください。 : ");
			select = stdIn.nextInt();
		}while(select != 1);
		
		p.firstCard1();
		
		do {
			System.out.print("確認できたら１を入力してください。交代です。 : ");
			select = stdIn.nextInt();
		}while(select != 1);
		
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"
				+ "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		
		do {
			System.out.print("Player2は準備ができたら１を入力してください。 : ");
			select = stdIn.nextInt();
		}while(select != 1);
		
		p.firstCard2();
		
		do {
			System.out.print("確認出来たら１を入力してください。交代です。 : ");
			select = stdIn.nextInt();
		}while(select != 1);
		
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"
				+ "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		
		//ゲーム開始！！
		while(true) {
			
			System.out.println("---------ターン" + turn + "---------");
			
			do {
				System.out.print("Player1は準備ができたら１を入力してください。：");
				select = stdIn.nextInt();
			}while(select != 1);
			
			//以下のif文とelse if文はターン2以降に表示
			if(turn != 1 && XENO.summonnum2 == 1 && p.revivalcard == 0) { //前のターンで転生していたとき
				System.out.println("Player2は" + XENO.summonnum2 + "を召喚しました。");
				System.out.println("あなたは手札のカード６をあてられたため、転生札によって復活しました。");
			}else if(turn != 1) {
				System.out.println("Player2は" + XENO.summonnum2 + "を召喚しました。");
			}
			
			//Player1のターン
			p.drawOne(); //Player1が１枚カードを引く
			p.summonOne(); //Player1がカードを１枚召喚する
			if(XENO.summonnum1 == 1) { //カード１を召喚
				p.card1(p.player2);
				if(count == 0 && p.revivalcard == 0) { //countの更新がなく、revivalcardが0になっているため、転生１回目だと分かる。
						g.graveCarry2(); //転生札と引き換えに場に捨てられたカードを墓地に入れる。
						count++; //毎回墓地に入れられてしまわないようにcountを更新⇒もうこのif文には入ることは決してない。
				}
			}else if(XENO.summonnum1 == 2) { //カード２を召喚
				p.card2(p.player2);
			}else if(XENO.summonnum1 == 3) { //カード３を召喚
				p.card3(p.player1, p.player2);
			}else if(XENO.summonnum1 == 4) { //カード４を召喚
				p.card4(p.player1, p.player2);
			}else if(XENO.summonnum1 == 5) { //カード５を召喚
				p.card5of1();
				g.graveCarry2(); //Player1によって捨てられたカードをPlayer2の墓地に入れる
			}else if(XENO.summonnum1 ==6) {
				//カード６は場に出せないため、記述不要
			}
			
			p.displayOne(); //Player1の手札確認
			
			do {
				System.out.print("確認できたら１を入力してください。交代です。 : ");
				select = stdIn.nextInt();
			}while(select != 1);
			
			System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"
					+ "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
			
			do {
				System.out.print("Player2は準備ができたら１を入力してください。 : ");
				select = stdIn.nextInt();
			}while(select != 1);
			
			if(XENO.summonnum1 == 1 && p.revivalcard == 0) {
				System.out.println("Player1は" + XENO.summonnum1 + "を召喚しました。");
				System.out.println("あなたは手札のカード６をあてられたため、転生札によって復活しました。");
			}else {
				System.out.println("Player1は" + XENO.summonnum1 + "を召喚しました。");
			}
			
			//Player2のターン
			p.drawTwo(); //Player2が１枚カードを引く
			p.summonTwo(); //Player2がカードを１枚召喚する
			if(XENO.summonnum2 == 1) { //カード１を召喚
				p.card1(p.player1);
				//以下の６行のソースは、Player1の場合と同じはたらきをする。
				if(count == 0) {
					if(p.revivalcard == 0) {
						g.graveCarry1();
						count++;
					}
				}
			}else if(XENO.summonnum2 == 2) { //カード２を召喚
				p.card2(p.player1);
			}else if(XENO.summonnum2 == 3) { //カード３を召喚
				p.card3(p.player2, p.player1);
			}else if(XENO.summonnum2 == 4) { //カード４を召喚
				p.card4(p.player2, p.player1);
		    }else if(XENO.summonnum2 == 5) { //カード５を召喚
				p.card5of2();
				g.graveCarry1(); //Player2によって捨てられたカードをPlayer1の墓地に入れる
			}else if(XENO.summonnum2 == 6) {
				//カード６は場に出せないため、記述不要
			}
			
			p.displayTwo(); //Player2の手札確認
			
			do {
				System.out.print("墓地を表示します。Player2は１を入力してください。 : ");
				select = stdIn.nextInt();
			}while(select != 1);
			
			System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"
					+ "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
			
			g.graveOne(); //Player1の墓地
			System.out.println();
			g.graveTwo(); //Player2の墓地
			//２人ともの墓地を２人ともが見られるようになっている
			System.out.println();
			
			if(p.cards.length == 0 && p.revivalcard != 0) { //山札はなくなっているが、転生札は残っているとき
				p.cards = new Integer[1];
				p.cards[0] = p.revivalcard; //転生札を山札とする
				System.out.println("山札がなくなりましたが、転生札が使われていなかったため、"
						+ "\n転生札を山札とします。");
			}
			
			if(p.cards.length != 0) {
				System.out.println("残りの山札の枚数→" + p.cards.length);
			}else if(p.cards.length == 0){
				if(p.revivalcard == 0 && XENO.summonnum2 == 1) {
					//Player2がターン3でカード１を召喚して、あてたとき
					//⇒revivalcardが0のため転生している。山札もないため、数字の大小による勝敗判定に移る。
					//しかし、Player1はそのターンで何が起こったのかを知らない。そのため、以下の出力を用意した。
					System.out.println("Player1は手札のカード６をあてられたため、転生札によって復活しました。");
				}
				p.judge(p.player1, p.player2); //山札がなくなったときは、数字の大きい方を勝ちとする
			}
			
			turn++; //次のターンに変わる
			
		}
	}
}
