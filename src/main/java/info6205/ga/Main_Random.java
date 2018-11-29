package info6205.ga;

import java.text.DecimalFormat;

import info6205.util.CityReader;

public class Main_Random {

	public static void main(String[] args) {

		int scale = 500;
		int cityNum = 50;
		int maxGen = 500;
		int initPopSize = 1000;
		double mutationRate = 0.05;
//		double fecundity = 0.5;
//		int maturity = 2;

		DecimalFormat df = new DecimalFormat("0.000");

		City[] cities = CityReader.getRnadom(cityNum, scale);
		
		GeneticAlgorithm ga = new GeneticAlgorithm(initPopSize, mutationRate);
		Population population = new Population(initPopSize, cities);
		
		//System.out.println(population.getIndividuals().size());
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
