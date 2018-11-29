package info6205.ga;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

public class GATest {

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
	public void seedingTest() {
		// Test if the seeding process for first generation randomize every individuals
		pop = new Population(2, cities);
		assertNotEquals(pop.getIndividuals().get(0).getCities().toString(), pop.getIndividuals().get(1).getCities().toString());
	}

	@Test
	public void mutateTest() {
		
		//Test mutateAlt function
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
		
		//Test mutateAlt function
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
		//Test crossover by comparing child with parents
		Individual mother = new Individual(cities);
		Individual father = new Individual(cities);
		
		Individual child = ga.doCrossover(mother, father);
		
		assertNotEquals(child.getCities().toString(), mother.getCities().toString());
		assertNotEquals(child.getCities().toString(), father.getCities().toString());

	}

}
