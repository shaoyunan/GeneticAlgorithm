# INFO6205_204_GeneticAlgorithm
INFO6205 Final Project Topic
Solving travelling salesman problem by implementing genetic algorithm

## Group member: 
Yunan Shao(001818832)
Yuchen Qiao(001293335)
Vinod Thiagarajan(001237129)

## Quick Information
The travelling salesman problem (TSP) asks the following question: "Given a list of cities and the distances between each pair of cities, what is the shortest possible route that visits each city and returns to the origin city?" It is an NP-hard problem in combinatorial optimization, important in operations research and theoretical computer science. (wikipedia)

## Project Description
The path is represented by the order of city, the fitness is the total distance from start city to each other cities once then return back to start point.

## Implementation
- Seeding: When importing the city list into the original population as individual, the array list that for each individual will be shuffled for randomization.

- Evolve: Produce next generation by eliminating the second half of population after sorting by fitness. Then mating/breeding to have children by crossover and mutation. And fill the rest space using the selected/survivor pool to reach the maximum population.

- Culling: select the best half of the population
	-individual class implements comparable, compareTo function is used to reverse the order when sorting

- Crossover: Select part of the gene from parent1 and fill the rest using missing parts from parent2

- Mutate: randomly swap the order of gene (city) for each child, have two implementations
	-mutate: every city has a chance decided by mutation rate to swap position with one of the rest cities
	-mutateAlt: alternative method, only swap once. 

##Test Cases

## Utilities
- CityReader
 - A .csv file (default cities.csv, inside /input folder) for testing one fixed group of cities
 - Can use the getRandom() method for each run to test the application
 
- ReportWriter
 - Write the output file which keeps generation information, average fitness and best fitness
 - Change the parameter in Main_Input to decide how many generations are kept (all generation information still showed in console), depending on the population size and maximum generation value, after some point the results are the same. The output file is used for analyzing more efficiently.
 - If a new set of input needed, just uncomment three lines in Main_Input, run once, then comment them out again
 ```
 		// Uncomment the next three lines for new random input file

//		int cityNum = 50;
//		int scale = 500;
//		ReportWriter.generateRandom(input, cityNum, scale);
```
