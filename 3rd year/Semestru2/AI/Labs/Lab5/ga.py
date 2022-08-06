import random
from random import randint, randrange


def generate(n):
    chr = []
    for i in range(n):
        chr.append(randint(0, 1))
    return chr


def fitness(chr, prices, weights, w_max):
    pt = 0
    wt = 0
    for i in range(len(chr)):
        pt += chr[i] * prices[i]
        wt += chr[i] * weights[i]
    if wt > w_max:
        pt -= 1000
    return pt


def mutation(chr):
    gene_index = randrange(0, len(chr))
    off = []
    for i in range(len(chr)):
        off.append(chr[i])
    if off[gene_index] == 0:
        off[gene_index] = 1
    else:
        off[gene_index] = 0
    return off


def crossover(c1, c2):
    cut_index = randrange(1, len(c1) - 1)
    off1 = []
    off2 = []
    for i in range(cut_index):
        off1.append(c1[i])
        off2.append(c2[i])
    for i in range(cut_index, len(c1)):
        off1.append(c2[i])
        off2.append(c1[i])
    return off1, off2


def ga(n, prices, weights, w_max):
    pop_size = 25
    pop = []
    for i in range(pop_size):
        pop.append(generate(n))
    for epoch in range(100):
        for i in range(pop_size):
            r = random.random()
            if r < 0.1:
                off = mutation(pop[i])
                pop.append(off)
            if r < 0.7 and i > 0:
                off1, off2 = crossover(pop[i], pop[randrange(0, i)])
                pop.append(off1)
                pop.append(off2)
        pop.sort(key=lambda chr: fitness(chr, prices, weights, w_max),
                 reverse=True)
        pop = pop[:pop_size]
    return pop[0]


n = 100
prices = [randint(1, 10) for _ in range(n)]
weights = [randint(1, 10) for _ in range(n)]
print(prices)
print(weights)
w_max = 100
print(ga(n, prices, weights, w_max))
