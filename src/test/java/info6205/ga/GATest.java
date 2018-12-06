package info6205.ga;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

public class GATest {

	Individual in = new Individual();
	Population pop;
	City cities[];
	ArrayList<City> cityCollection;

	public GATest() {
		cities = new City[10];
		cityCollection = new ArrayList<City>();

		for (int i = 0; i < cities.length; i++) {
			City c = new City(String.valueOf(i), (int) (Math.random() * 100), (int) (Math.random() * 100));
			cities[i] = c;
			cityCollection.add(c);
		}

	}

	GeneticAlgorithm ga = new GeneticAlgorithm(10, 1);

	@Test
	public void populateCompleteTest() {
		// Test if all individuals have all cities after initializing the population
		pop = new Population(5, cities);
		for (int i = 0; i < 5; i++) {
			assertEquals(pop.getIndividuals().get(i).getCities().containsAll(cityCollection), true);
		}

	}

	@Test
	public void evolveCompleteTest() {
		// if the individuals in next generation have complete city list
		pop = new Population(5, cities);
		ga = new GeneticAlgorithm(5, 0.05);
		Population newPop = ga.evolve(pop);
		for (int i = 0; i < 5; i++) {
			assertEquals(newPop.getIndividuals().get(i).getCities().containsAll(cityCollection), true);
		}
	}

	@Test
	public void evolveTest() {
		// Test if the new population has better (or at least the same) fitness as
		// previous one
		pop = new Population(10, cities);
		ga = new GeneticAlgorithm(10, 0.05);
		Population newPop = ga.evolve(pop);
		assertTrue(pop.getFittest().getFitness() <= newPop.getFittest().getFitness());
	}

	@Test
	public void seedingTest() {
		// Test if the seeding process for first generation randomize every individuals
		pop = new Population(2, cities);
		assertNotEquals(pop.getIndividuals().get(0).getCities().toString(),
				pop.getIndividuals().get(1).getCities().toString());
	}

	@Test
	public void mutateTest() {

		// Test mutate function
		Individual indiv = new Individual(cities);
		String original = indiv.getCities().toString();

		ga.setMutationRate(0);
		ga.mutate(indiv);
		assertEquals(indiv.getCities().toString(), original);

		ga.setMutationRate(1);
		ga.mutate(indiv);
		assertNotEquals(indiv.getCities().toString(), original);
	}

	@Test
	public void mutateAltTest() {

		// Test mutateAlt function
		Individual indiv = new Individual(cities);
		String original = indiv.getCities().toString();
		ga.setMutationRate(0);
		ga.mutateAlt(indiv);
		assertEquals(indiv.getCities().toString(), original);

		ga.setMutationRate(1);
		ga.mutateAlt(indiv);
		assertNotEquals(indiv.getCities().toString(), original);
	}

	@Test
	public void crossoverTest() {
		// Test crossover by comparing child with parents
		Individual mother = new Individual(cities);
		Individual father = new Individual(cities);

		Individual child = ga.doCrossover(mother, father);

		assertNotEquals(child.getCities().toString(), mother.getCities().toString());
		assertNotEquals(child.getCities().toString(), father.getCities().toString());

	}

	// distance test
	@Test
	public void testDistance() throws Exception {

		ArrayList<City> cities = new ArrayList<City>();
		City City1 = new City();
		City City2 = new City();
		City City3 = new City();
		City1.setX(1);
		City1.setY(1);
		City2.setX(2);
		City2.setY(2);
		City3.setX(3);
		City3.setY(3);
		cities.add(City1);
		cities.add(City2);
		cities.add(City3);
		in.setCities(cities);

		double totalDistance = 0;
		for (int i = 0; i < cities.size(); i++) {
			City start = cities.get(i);
			City end = cities.get(0);
			if (i + 1 < cities.size()) {
				end = cities.get(i + 1);
			}
			totalDistance += start.distanceTo(end);
		}
		assertEquals(totalDistance, in.totalDistance(), 0.05);

	}

	// fitness test
	@Test
	public void testFitness() throws Exception {
		double fitness;
		fitness = 1 / in.totalDistance();
		assertEquals(fitness, in.getFitness(), 0.05);
	}
}
