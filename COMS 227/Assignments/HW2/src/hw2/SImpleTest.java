package hw2;
import hw2.FuzzballGame;

public class SImpleTest {
	public static void main(String[] args)
	  {
	    FuzzballGame game = new FuzzballGame(3);
	    game.hit(150);
	    game.hit(15);
	    game.ball();
	    game.ball();
	    game.ball();
	    game.ball();
	    game.ball();
//	    game.hit(180);
//		game.hit(100);
//		game.ball();
//		game.ball();
//		game.strike(false);
//		game.ball();
//		game.ball();
//		game.ball();
//		game.strike(false);
//		game.ball();
//		game.ball();
//		game.strike(false);
//		game.strike(false);
//		game.ball();
//		game.ball();
//		game.ball();
//		game.hit(249);
//		game.ball();
//		game.caughtFly();
//		game.strike(false);
//		game.ball();
//		game.strike(false);
//		System.out.println(game.isTopOfInning()); //true
		System.out.println(game.getBases()); //xxx
//		System.out.println(game); //0

}
}
