package info6205.ga;

import java.util.Random;

public class GeneticAlgorithm {
	private int populationSize;
	private double mutationRate;

	public GeneticAlgorithm(int popSize, double mRate) {
		if (popSize <= 0)
			throw new IllegalArgumentException("Invalid Population Size For GA Parameter");
		this.populationSize = popSize;
		this.mutationRate = mRate;
	}

	// Evolve the population to next generation
	public Population evolve(Population pop) {

		Population newPopulation = new Population();

		// Make the survivor pool
		pop.sort();
		Population survive = cull(pop);

		// Make offsprings in the survivor pool
		newPopulation = crossover(survive);

		// Mutate the offsprings after breeding
		for (int i = 0; i < newPopulation.size(); i++) {

			// mutate(newPopulation.getIndividuals().get(i));

			mutateAlt(newPopulation.getIndividuals().get(i));

		}

		// Use the survivor pool to fill the rest of the population
		for (int i = 0; newPopulation.size() < populationSize; i++) {
			newPopulation.addIndividual(pop.getIndividuals().get(i));
		}

		return newPopulation;
	}

	// Select the best half of the population to survive
	private Population cull(Population pop) {
		Population survive = new Population();

		for (int i = 0; i < pop.size() / 2; i++) {
			survive.addIndividual(pop.getIndividuals().get(i));
		}
		// survive.shuffle();
		return survive;
	}

	// Mating Selection stage of crossover function for breeding
	public Population crossover(Population pop) {

		Population newPop = new Population();
		Random rand = new Random();

		while (newPop.size() < pop.size()) {
			// Select two random individuals as parents
			int i = rand.nextInt(pop.size());
			int j = rand.nextInt(pop.size());
			// The only constrain is the parents for breeding can't be the same individual
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

	// Actual crossover function for breeding
	public Individual doCrossover(Individual parent1, Individual parent2) {
		Individual child = new Individual();
		child.buildIndividual(parent1.size());

		int start = (int) (Math.random() * parent1.size());
		int end = (int) (Math.random() * parent1.size());

		while (start == end || Math.abs(start - end) > child.size() / 1.5) {
			end = (int) (Math.random() * parent1.size());
		}

		// Select one part of the gene from the first parent
		for (int i = 0; i < child.size(); i++) {
			if (start < end) {
				if (i >= start && i <= end) {
					child.getCities().set(i, parent1.getCities().get(i));
				}
			} else {
				if (i <= start && i >= end) {
					child.getCities().set(i, parent1.getCities().get(i));
				}
			}
		}

		// Fill the missing parts using selection from the second parent
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

	// Mutation by swapping two different cities while iterating
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

	// Another way of doing mutation, only swap one pair
	public void mutateAlt(Individual individual) {
		if (Math.random() <= mutationRate) {
			int i = (int) (individual.getCities().size() * Math.random());
			int j = (int) (individual.getCities().size() * Math.random());
			while (i == j) {
				j = (int) (individual.getCities().size() * Math.random());
			}
			City c1 = individual.getCities().get(i);
			City c2 = individual.getCities().get(j);

			individual.getCities().set(i, c2);
			individual.getCities().set(j, c1);
		}
	}

	public double getMutationRate() {
		return mutationRate;
	}

	public void setMutationRate(double mutationRate) {
		this.mutationRate = mutationRate;
	}

}
