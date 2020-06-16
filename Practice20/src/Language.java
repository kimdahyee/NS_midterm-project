//	24번: 클래스와 멤버들에 대한 접근성을 최소화하라
interface Language {
	Game encode();

	String decode(Game com);

	String print(Score s);
}
