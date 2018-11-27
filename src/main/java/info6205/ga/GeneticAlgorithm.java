package info6205.ga;

public class GeneticAlgorithm {
	private int populationSize;
	private double mutationRate;
	private double crossoverRate;
	private int tournamentSize;
	private boolean elitism = true;
	private int cityCount;
	
	public GeneticAlgorithm(int popSize, double mRate, double cRate, int tSize, int cityCount) {
		this.populationSize = popSize;
		this.mutationRate = mRate;
		this.crossoverRate = cRate;
		this.tournamentSize = tSize;
		this.cityCount = cityCount;
	}

	public Population evolve(Population pop) {
		Population newPopulation = new Population(pop.size());

		int elitismOffset = 0;
		if (elitism) {
			newPopulation.getIndividuals()[0] = pop.getFittest();
			elitismOffset = 1;
		}

		for (int i = elitismOffset; i < newPopulation.size(); i++) {

			Individual parent1 = tournamentSelection(pop);
			Individual parent2 = tournamentSelection(pop);

			Individual child = crossover(parent1, parent2);
			newPopulation.getIndividuals()[i] = child;
		}

		for (int i = elitismOffset; i < newPopulation.size(); i++) {
			mutate(newPopulation.getIndividuals()[i]);
		}

		return newPopulation;
	}

	private Individual tournamentSelection(Population pop) {
		Population tournament = new Population(tournamentSize);
		for (int i = 0; i < tournamentSize; i++) {
			int rand = (int) (Math.random() * pop.size());
			tournament.getIndividuals()[i] = pop.getIndividuals()[rand];
		}
		//System.out.println(tournament.getFittest().getFitness());
		return tournament.getFittest();
	}

	public Individual crossover(Individual parent1, Individual parent2) {
		Individual child = new Individual(cityCount);
		
		int start = (int) (Math.random() * parent1.size());
		int end = (int) (Math.random() * parent1.size());

		for (int i = 0; i < child.size(); i++) {
			if (start < end && i > start && i < end) {
				child.getCities().set(i, parent1.getCities().get(i));
			} else if (start > end) {
				if (!(i < start && i > end)) {
					child.getCities().set(i, parent1.getCities().get(i));
				}
			}
		}

		for (int i = 0; i < parent2.size(); i++) {
			if (!child.getCities().contains(parent2.getCities().get(i))) {
				for (int j = 0; j < child.size(); j++) {
					if (child.getCities().get(j) == null) {
						child.getCities().set(j, parent2.getCities().get(i));
						break;
					}
				}
			}
		}
		return child;
	}

	public void mutate(Individual individual) {

		for (int i = 0; i < individual.getCities().size(); i++) {
			if (Math.random() <= mutationRate) {
				int j = (int) (individual.getCities().size() * Math.random());
				City c1 = individual.getCities().get(i);
				City c2 = individual.getCities().get(j);

				individual.getCities().set(i, c2);
				individual.getCities().set(j, c1);
			}
		}
	}
}
