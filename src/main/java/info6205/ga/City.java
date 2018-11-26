package info6205.ga;

public class City {
	private String name;
	private int x;
	private int y;

	public City() {

	}

	public City(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public City(String name, int x, int y) {
		this.name = name;
		this.x = x;
		this.y = y;
	}

	public double distanceTo(City c) {
		int xDist = Math.abs(this.x - c.getX());
		int yDist = Math.abs(this.y - c.getY());
		return Math.sqrt(xDist * xDist + yDist * yDist);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return this.name;
	}
}
