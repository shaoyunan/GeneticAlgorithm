package info6205.ga;

import java.util.ArrayList;
import java.util.Collections;

//Each individual is a sequence of cities which represents the route
//The overrided compareTo function keeps the descending order when sorting the population
public class Individual implements Comparable<Individual>{
	private ArrayList<City> cities;
	private double fitness;

	public Individual() {
		this.cities = new ArrayList<City>();
		this.fitness = 0.0;
	}

	public Individual(City[] cities) {
		this.fitness=0.0;
		this.cities = new ArrayList<City>();
		for (int i = 0; i < cities.length; i++) {
			this.cities.add(cities[i]);
		}
		
		//Shuffle to make the random first generation
		Collections.shuffle(this.cities);
	}

	//Add null values for crossover
	public void buildIndividual(int size) {
		for(int i=0; i<size; i++) {
			cities.add(null);
		}
	}
	
	public ArrayList<City> getCities() {
		return cities;
	}

	public void setCities(ArrayList<City> cities) {
		this.cities = cities;
	}

	//Fitness calculation, simply just 1/totalDistance of the route
	public double getFitness() {
		if (fitness == 0.0) {
			fitness = 1 / totalDistance();
		}
		return fitness*1000;
	}

	public double totalDistance() {
		double totalDistance = 0.0;
		for (int i = 0; i < cities.size(); i++) {
			City start = cities.get(i);
			City end = cities.get(0);
			if (i + 1 < cities.size()) {
				end = cities.get(i + 1);
			}
			totalDistance += start.distanceTo(end);
		}
		
		return totalDistance;
	}

	public void setFitness(double fitness) {
		this.fitness = fitness;
	}

	public int size() {
		return cities.size();
	}

	//Helps rearrange the candidates with best fitness to the beginning when sorting
	public int compareTo(Individual indiv) {
		
		if(getFitness()<indiv.getFitness()) {
			return 1;
		}else if(getFitness()>indiv.getFitness()) {
			return -1;
		}
		return 0;
	}

}
