package info6205.ga;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int cityNum = 50;
		int maxGen = 10000;
		int popSize = 100;
		double mutationRate = 0.05;
		double crossoverRate = 0.8;
		int tournamentSize = 5;
		
		//GeneticAlgorithm ga = new GeneticAlgorithm(popSize, mutationRate, crossoverRate);
		
		City cities[] = new City[cityNum];
		for (int i = 0; i < cityNum; i++) {
			int x = (int) (100 * Math.random());
			int y = (int) (100 * Math.random());
			
			cities[i] = new City(String.valueOf(i), x, y);
		}
		
		GeneticAlgorithm ga = new GeneticAlgorithm(popSize, mutationRate, crossoverRate, tournamentSize, cityNum);
		Population population = new Population(popSize, cities);
		System.out.println("First Generation: "+"Fitness: "+population.getFittest().getFitness()+" Distance: "+population.getFittest().totalDistance());
		for (int i = 0; i < maxGen; i++) {
            population = ga.evolve(population);
            System.out.println(population.getIndividuals().length);
            System.out.println("Generation: "+(i+1)+" Fitness: "+population.getFittest().getFitness()+" Distance: "+population.getFittest().totalDistance());
        }
		System.out.println("Path: "+population.getFittest().getCities().toString());
	}

}
