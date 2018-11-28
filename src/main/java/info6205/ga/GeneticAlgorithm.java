package info6205.ga;

import java.util.Random;

public class GeneticAlgorithm {
	private int populationSize;
	private double mutationRate;

	public GeneticAlgorithm(int popSize, double mRate) {
		this.populationSize = popSize;
		this.mutationRate = mRate;

	}

	public Population evolve(Population pop) {
		Population newPopulation = new Population();

		Population survive = cull(pop);

		newPopulation = crossover(survive);
		// mutate after breeding
		for (int i = 0; i < newPopulation.size(); i++) {
			mutate(newPopulation.getIndividuals().get(i));
		}
		for (int i = 0; newPopulation.size() < populationSize; i++) {
			newPopulation.addIndividual(survive.getIndividuals().get(i));
		}

		return newPopulation;
	}

	private Population cull(Population pop) {
		Population survive = new Population();
		pop.sort();
		for (int i = 0; i < pop.size() / 2; i++) {
			survive.addIndividual(pop.getIndividuals().get(i));
		}
		survive.shuffle();
		return survive;
	}

	public Population crossover(Population pop) {

		Population newPop = new Population();
		Random rand = new Random();
		// int[] record = new int[pop.size()];
		while (newPop.size() < pop.size()) {
			int i = rand.nextInt(pop.size());
			int j = rand.nextInt(pop.size());
			if (i != j) {
				Individual parent1 = pop.getIndividuals().get(i);
				Individual parent2 = pop.getIndividuals().get(j);

				for (int k = 0; k < 2; k++) {
					newPop.addIndividual(doCrossover(parent1, parent2));
				}

			}
		}

		return newPop;
	}

	private Individual doCrossover(Individual parent1, Individual parent2) {
		Individual child = new Individual();
		child.buildIndividual(parent1.size());

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
