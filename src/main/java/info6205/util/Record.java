package info6205.util;

public class Record {
	private String generation;
	private String avgFit;
	private String bestFit;

	public Record(String generation, String avgFit, String bestFit) {
		this.generation = generation;
		this.avgFit = avgFit;
		this.bestFit = bestFit;
	}

	public String getGeneration() {
		return generation;
	}

	public void setGeneration(String generation) {
		this.generation = generation;
	}

	public String getAvgFit() {
		return avgFit;
	}

	public void setAvgFit(String avgFit) {
		this.avgFit = avgFit;
	}

	public String getBestFit() {
		return bestFit;
	}

	public void setBestFit(String bestFit) {
		this.bestFit = bestFit;
	}

}