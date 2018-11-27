package info6205.ga;

public class Population {
	private Individual[] individuals;

	public Population(int popSize) {
		individuals = new Individual[popSize];
	}

	public Population(int popSize, City[] cities) {
		individuals = new Individual[popSize];
		for (int i = 0; i < popSize; i++) {
			Individual individual = new Individual(cities);
			individuals[i] = individual;
		}	
	}

	public int size() {
		return individuals.length;
	}

	public Individual[] getIndividuals() {
		return individuals;
	}

	public void setIndividuals(Individual[] individuals) {
		this.individuals = individuals;
	}

	public Individual getFittest() {
		Individual fit = individuals[0];
		for (int i = 1; i < individuals.length; i++) {
            if (fit.getFitness() < individuals[i].getFitness()) {
                fit = individuals[i];
            }
        }
        return fit;
	}
	
	public double getAvgFitness() {
		return 0.0;
	}
}
