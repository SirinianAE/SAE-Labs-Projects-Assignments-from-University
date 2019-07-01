#name:	Sirinian Aram Emmanouil
#AM:	2537

import sys
import random

sample = []
number_of_lines = 0
K = 0

def parse_args():
	global K
	usage = """usage: %prog [options] 
	python3 sample.py K"""
	
	if (len(sys.argv) != 2):
		print(usage)
		exit()
	
	K = int(sys.argv[1])

def jana():
	global K
	global number_of_lines
	global sample
	
	for line in sys.stdin:
		number_of_lines += 1
		for i in range(0, K):
			random_value = random.random()
			if ((1/number_of_lines) >= random_value):
				sample[i] = line

def initSample():
	global sample
	global K
	
	for i in range(0, K):
		sample.append("")

def print_sample():
	global sample
	global K
	
	for i in range(0, K):
		print(sample[i])

def main():
	parse_args()
	initSample()
	jana()
	print_sample()

if __name__ == "__main__":
	main()
