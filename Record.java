
public class Record {
	private String key;
	private int score, level;

	//constructor that sets the values in the parameter when called to the variable in the calss
	public Record(String key, int score, int level) {
		this.key=key;
		this.score=score;
		this.level=level;
	}



	//three methods that return the values of (key, score, and level)

	public String getKey() {
		return key;

	}
	public int getScore() {
		return score;
	}
	public int getLevel() {
		return level;
	}
}
