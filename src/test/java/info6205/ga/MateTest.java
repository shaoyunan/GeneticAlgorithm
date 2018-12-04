package info6205.ga;

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.junit.After;
import info6205.ga.GeneticAlgorithm;
import info6205.ga.Individual;


public class MateTest {
	 @Before
	 public void before() throws Exception {
	 }

	 @After
	 public void after() throws Exception {
	 }
	    
	 //distance test
	 @Test
	    public void testDistance() throws Exception {
		 Individual in = new Individual();
		 ArrayList<City> cities; 
		 cities.add(1, null);
		 cities.add(2, null);
	        
	        int totalDistance = 0;
	        for (int i = 0; i < cities.size(); i++) {
				City start = cities.get(i);
				City end = cities.get(0);
				if (i + 1 < cities.size()) {
					end = cities.get(i + 1);
				}
				totalDistance += start.distanceTo(end);
	        assertEquals(totalDistance, in.totalDistance());
	 }
	 
	 
	 //fitness test 
	 @Test
	    public void testFitness() throws Exception {
	        
	 }
	    
}
