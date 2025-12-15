#!/Library/Frameworks/Python.framework/Versions/3.12/bin/python3
import matplotlib
import matplotlib.pyplot as plt
import numpy as np
import argparse

parser = argparse.ArgumentParser()
parser.add_argument('-i', required=True)
parser.add_argument('-o', required=True)
args = parser.parse_args()

with open(args.i, 'r') as f:
    data = np.loadtxt(f)

plt.plot(data[:, 0], data[:, 1])
plt.savefig(args.o)