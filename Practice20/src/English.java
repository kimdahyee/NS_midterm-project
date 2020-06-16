import java.util.Scanner;

//64번: 논리적 완벽을 추구하라
public class English implements Language { //영어 출력

	public English(Game com) {
	}

	@Override
	public Game encode() {
		Scanner s = new Scanner(System.in);

		System.out.print("You : ");
		String input = s.next();
		s.close();

		if (input.equals("SCISSORS")) {
			return Game.SCISSORS;
		} else if (input.equals("ROCK")) {
			return Game.ROCK;
		} else if (input.equals("PAPER")) {
			return Game.PAPER;
		} else {
			return Game.ERROR;
		}
	}

	@Override
	public String decode(Game com) {
		String intro = "Computer : ";

		if (com.getGameNum() == 2) {
			return intro + "SCISSORS";
		} else if (com.getGameNum() == 0) {
			return intro + "ROCK";
		} else {
			return intro + "PAPER";
		}
	}

	@Override
	public String print(Score s) {
		if (s == Score.WIN) {
			return "You Win.";
		} else if (s == Score.LOSE) {
			return "Computer Win.";
		} else {
			return "Draw.";
		}
	}

}
