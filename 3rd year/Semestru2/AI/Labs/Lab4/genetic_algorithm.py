import random
from random import randint, randrange
import matplotlib.pyplot as plt

def generate_chromosome(n: int):
    chromosome = []
    for _ in range(n):
        chromosome.append(randint(0, 1))
    return chromosome


def fitness(chromosome: [int], prices: [int], weights: [int], weight_max: int) -> float:
    result = 0
    total_weight = 0
    for i in range(len(chromosome)):
        result += chromosome[i] * prices[i]
        total_weight += chromosome[i] * weights[i]
    if total_weight > weight_max:
        result -= 1000
    return result


def mutation(chromosome: [int]) -> [int]:
    gene_index = randrange(0, len(chromosome))
    offspring = []
    for i in range(len(chromosome)):
        offspring.append(chromosome[i])
    if offspring[gene_index] == 0:
        offspring[gene_index] = 1
    else:
        offspring[gene_index] = 0
    return offspring


def crossover(chr1: [int], chr2: [int]) -> ([int], [int]):
    gene_index = randrange(1, len(chr1) - 1)
    off1 = []
    off2 = []
    for i in range(gene_index):
        off1.append(chr1[i])
        off2.append(chr2[i])
    for i in range(gene_index, len(chr1)):
        off1.append(chr2[i])
        off2.append(chr1[i])
    return off1, off2


def genetic_algorithm(prices: [int], weights: [int], weight_max: int) -> [int]:
    n = len(prices)
    probability_of_mutation = 0.1
    probability_of_crossover = 0.6
    pool_cap = 50
    pool = []
    best=[]
    worst=[]
    for i in range(pool_cap):
        pool.append(generate_chromosome(n))

    for epoch in range(100):
        for i in range(pool_cap):
            r = random.random()
            if r < probability_of_mutation:
                pool.append(mutation(pool[i]))
            if r < probability_of_crossover:
                idx_other = randint(0, i)
                off1, off2 = crossover(pool[i], pool[idx_other])
                pool.append(off1)
                pool.append(off2)
        pool.sort(key=lambda x: fitness(x, prices, weights, weight_max), reverse=True)
        pool = pool[:pool_cap]
        best.append(fitness(pool[0],prices,weights,weight_max))
        worst.append(fitness(pool[-1], prices, weights, weight_max))
    plt.plot(best)
    plt.plot(worst)
    plt.show()
    return pool[0]



total=1000
prices=[randint(0,1) for _ in range(total)]
weights=[randint(0,1) for _ in range(total)]
weight_max=100
print(genetic_algorithm(prices,weights,weight_max))

print(genetic_algorithm([2, 1, 3, 1, 4], [1, 1, 1, 1, 1], 3))
# print(crossover([1, 0, 0, 1], [0, 0, 1, 1]))
# print(fitness(mutation([1, 0, 1]), [10, 20, 30], [2, 1, 2], 100))
