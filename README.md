# SRTF Algorithm

## Overview
The code written will execute the Shortest Remaining Time first algorithm. Tasks that enter the `READY` queue at a certain time interval will preempt the running process if its Burst period is shorter than the running process' burst.

### The following changes have been made since HW2:
 + Regular Queue LinkedList has been changed back to a Priority Queue.
 + `PCB` Object has been modified to include more data, which includes: TAT, Response Time, Wait time, and etc.
 + `PCBQueue` has an iterator method
 + `main()` will now handle processes instead of a `doProess()` method

### Code functionality

 + Since `main()` now handles processes, the code block behind it will be much longer.
 + There is a program timer that will be used to mark the start time and completion time. It is also used to check whether the next process in the file can be enqueued. This is to avoid a process arriving in the `READY` queue when it was not supposed to. While this is an SRTF algorithm, the running process is kicked off the processor after one unit and re-enters the `READY` queue. This queue is a PriorityQueue that sorts items by their burst, where the first elements are PCBs with the shortest bursts. The algorithm also deals with scheduling conflicts, where a `READY` also has the same burst as the `RUNNING` process.

## Compilation

To compile the program, you must cd into the working directory with all related files. Enter the following to compile the program:
```text
javac Main.java
```
To run it, enter
```text
java Main
```