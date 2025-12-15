#Problem 1
#Use journalctl on Linux or log show on macOS to get the super user accesses and commands in the last day. If there aren’t any you can execute some harmless commands such as sudo ls and check again.
##Solution
log show --last 1d | grep sudo

##Problem3
##Install shellcheck and try checking the following script. What is wrong with the code? Fix it. Install a linter plugin in your editor so you can get your warnings automatically.
#Solution
shellcheck p3.sh

##Problem 5
##Here are some sorting algorithm implementations. Use cProfile and line_profiler to compare the runtime of insertion sort and quicksort. What is the bottleneck of each algorithm? Use then memory_profiler to check the memory consumption, why is insertion sort better? Check now the inplace version of quicksort. Challenge: Use perf to look at the cycle counts and cache hits and misses of each algorithm.
##Solution for cProfile
python3 -m cProfile -s tottime sorts.py
##Solution for line_profiler
##add @profile decorator and run like below:
kernprof -l -v sorts.py

##Problem 6
##Here’s some (arguably convoluted) Python code for computing Fibonacci numbers using a function for each number.
##Put the code into a file and make it executable. Install prerequisites: pycallgraph and graphviz. (If you can run dot, you already have GraphViz.) Run the code as is with pycallgraph graphviz -- ./fib.py and check the pycallgraph.png file. How many times is fib0 called?. We can do better than that by memoizing the functions. Uncomment the commented lines and regenerate the images. How many times are we calling each fibN function now?
##Solution
##First install dependencies
python3 -m pip install pycallgraph2
brew install graphviz
#3Then run 
pycallgraph graphviz -- ./fib.py

##Problem7
##A common issue is that a port you want to listen on is already taken by another process. Let’s learn how to discover that process pid. First execute python -m http.server 4444 to start a minimal web server listening on port 4444. On a separate terminal run lsof | grep LISTEN to print all listening processes and ports. Find that process pid and terminate it by running kill <PID>.
##Solution
python -m http.server 4444
lsof | grep LISTEN
kill <PID>

##Problem8
##Limiting a process’s resources can be another handy tool in your toolbox. Try running stress -c 3 and visualize the CPU consumption with htop. Now, execute taskset --cpu-list 0,2 stress -c 3 and visualize it. Is stress taking three CPUs? Why not? Read man taskset. Challenge: achieve the same using cgroups. Try limiting the memory consumption of stress -m.
##Solution
##First install dependency
brew install stress
stress -c 3
##to terminate
ps aux | grep stress
kill <pid1> <pid2>...

