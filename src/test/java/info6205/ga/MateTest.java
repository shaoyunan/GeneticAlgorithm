package info6205.ga;

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;

import org.junit.After;
import info6205.ga.GeneticAlgorithm;
import info6205.ga.Individual;
import info6205.ga.City;


public class MateTest {
	 
	//private static final Object Object = null;
	Individual in = new Individual();
	
	 @Before
	 public void before() throws Exception {
	 }

	 @After
	 public void after() throws Exception {
	 }
	    
	 //distance test
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
	      assertEquals(totalDistance, in.totalDistance(),0.1);
	       
	      
	 }   	 
	 
	 
	 //fitness test 
	 @Test
	    public void testFitness() throws Exception {
		 double fitness;
		 fitness = 1 / in.totalDistance();
		 assertEquals(fitness, in.getFitness(),0.1);
	 }
	 	/*try { 
     	Method td = in.getClass().getMethod("totalDistance");
     	td.setAccessible(true); 
	 	Object Parameters1 = null;
	 	td.invoke(td,Object, Parameters1); 
  	}  catch (SecurityException e) {
		e.printStackTrace();
	} catch (NoSuchMethodException e) {
		e.printStackTrace();
	} catch (IllegalArgumentException e) {
		e.printStackTrace();
	} catch (IllegalAccessException e) {
		e.printStackTrace();
	}  catch (InstantiationException e) {
		e.printStackTrace();
	}*/
	    
}
