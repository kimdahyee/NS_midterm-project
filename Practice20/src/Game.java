// 28번: enum에서 부여한 순서번호에 의미를 두지 말라
public enum Game {
	ROCK(0), PAPER(1), SCISSORS(2), ERROR(-1);

	int GameNum;

	Game(int num) {
		GameNum = num;
	}

	public int getGameNum() {
		return GameNum;
	}
}
