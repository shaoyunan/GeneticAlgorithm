package info6205.ga;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Population {
	private ArrayList<Individual> individuals;

	public Population() {
		individuals = new ArrayList<Individual>();
	}

	public Population(int popSize, City[] cities) {
		individuals = new ArrayList<Individual>();
		for (int i = 0; i < popSize; i++) {
			Individual individual = new Individual(cities);
			individuals.add(individual);
		}
	}

	public Population(List<Individual> list) {
		individuals = new ArrayList<Individual>(list);
	}
	
	public int size() {
		return individuals.size();
	}

	public Individual getFittest() {
		Individual fit = individuals.get(0);
		for (int i = 1; i < individuals.size(); i++) {
			if (fit.getFitness() < individuals.get(i).getFitness()) {
				fit = individuals.get(i);
			}
		}
		return fit;
	}

	public double getAvgFitness() {
		double total = 0.0;
		int count = 0;
		if (individuals.size() != 0) {
			for (int i = 0; i < individuals.size(); i++) {
				total += individuals.get(i).getFitness();
				count++;
			}
		}
		return total / count;
	}

	public ArrayList<Individual> getIndividuals() {
		return individuals;
	}

	public void setIndividuals(ArrayList<Individual> individuals) {
		this.individuals = individuals;
	}

	public void addIndividual(Individual indiv) {
		individuals.add(indiv);
	}

	public void shuffle() {
		Collections.shuffle(individuals);
	}

	public void sort() {
		Collections.sort(individuals);

	}
}
