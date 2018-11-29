package info6205.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import info6205.ga.City;

public class CityReader {

	public static City[] getInput(String path) throws IOException {
		ArrayList<City> cities = new ArrayList<City>();
		try {

			int id = 1;
			BufferedReader in = new BufferedReader(new FileReader("input/"+path));
			String line;
			while ((line = in.readLine()) != null) {
				String[] tokens = line.split(",");
				cities.add(new City(String.valueOf(id), Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1])));
				id++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		City[] input = cities.toArray(new City[cities.size()]);
		return input;
	}
	
	public static City[] getRnadom(int size, int scale) {
		City[] cities = new City[size];
		for (int i = 0; i < size; i++) {
			int x = (int) (scale * Math.random());
			int y = (int) (scale * Math.random());
			//System.out.println(x+","+y);
			cities[i] = new City(String.valueOf(i+1), x, y);
		}
		
		return cities;
	}
	
}
