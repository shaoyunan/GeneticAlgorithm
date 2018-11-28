package info6205.ga;

import java.text.DecimalFormat;

public class Main_Random {

	public static void main(String[] args) {

		int cityNum = 50;
		int maxGen = 1000;
		int initPopSize = 1000;
		double mutationRate = 0.05;
		//double crossoverRate = 0.8;

		DecimalFormat df = new DecimalFormat("0.000");

		City cities[] = new City[cityNum];
		for (int i = 0; i < cityNum; i++) {
			int x = (int) (100 * Math.random());
			int y = (int) (100 * Math.random());

			cities[i] = new City(String.valueOf(i), x, y);
		}
		
		GeneticAlgorithm ga = new GeneticAlgorithm(initPopSize, mutationRate);
		Population population = new Population(initPopSize, cities);
		System.out.println(population.getIndividuals().size());
		System.out.println("First Generation: " + "Average Fitness: " + df.format(population.getAvgFitness())
				+ " Best Fitness: " + df.format(population.getFittest().getFitness()) + " Shortest Distance: "
				+ population.getFittest().totalDistance());
		for (int i = 0; i < maxGen; i++) {
			population = ga.evolve(population);

			System.out.println("Generation: " + (i + 1) + " Average Fitness: " + df.format(population.getAvgFitness())
					+ " Best Fitness: " + df.format(population.getFittest().getFitness()) + " Shortest Distance: "
					+ df.format(population.getFittest().totalDistance()));
		}
		System.out.println("Path: " + population.getFittest().getCities().toString());
	}

}
