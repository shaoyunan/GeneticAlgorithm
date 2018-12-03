package info6205.ga;

import java.io.IOException;
import java.text.DecimalFormat;

import info6205.util.ReportWriter;
import info6205.util.CityReader;
import info6205.util.Report;
import info6205.util.*;

public class Main_Input {

	public static void main(String[] args) throws IOException {

		/*
		 * @param scale the range of coordinates of cities ie 500 means 500x500
		 * 
		 * @param input csv file name under input folder, default is cities.csv
		 * 
		 * @param cityNum number of cities, only used for creating new random input file
		 * 
		 * @param df the output precision
		 * 
		 * @param maxGen the max generation number, terminated value
		 * 
		 * @param iniPopSize the population size for genetic algorithm
		 * 
		 * @param mutationRate the mutation rate of offsprings after crossover
		 * 
		 * commenting for first time
		 */

		String input = "cities.csv";
		DecimalFormat df = new DecimalFormat("0.000");

		// Uncomment the next three lines for new random input file

//		int cityNum = 50;
//		int scale = 500;
//		ReportWriter.generateRandom(input, cityNum, scale);

		int maxGen = 1000;
		int initPopSize = 1000;
		double mutationRate = 0.05;

		City[] cities = CityReader.getInput(input);

		GeneticAlgorithm ga = new GeneticAlgorithm(initPopSize, mutationRate);
		Population population = new Population(initPopSize, cities);

		Report report = new Report();
		report.addRecord(new Record(String.valueOf(0), df.format(population.getAvgFitness()),
				df.format(population.getFittest().getFitness())));
		System.out.println("First Generation: " + "Average Fitness: " + df.format(population.getAvgFitness())
				+ " Best Fitness: " + df.format(population.getFittest().getFitness()) + " Shortest Distance: "
				+ population.getFittest().totalDistance());

		for (int i = 0; i < maxGen; i++) {

			population = ga.evolve(population);

			if (i < 100) {
				report.addRecord(new Record(String.valueOf(i + 1), df.format(population.getAvgFitness()),
						df.format(population.getFittest().getFitness())));
			}

			System.out.println("Generation: " + (i + 1) + " Average Fitness: " + df.format(population.getAvgFitness())
					+ " Best Fitness: " + df.format(population.getFittest().getFitness()) + " Shortest Distance: "
					+ df.format(population.getFittest().totalDistance()));
		}
		report.setResult("Candidate Path: " + population.getFittest().getCities().toString());
		ReportWriter.makeReport(report);
		System.out.println("Path: " + population.getFittest().getCities().toString());
	}

}
