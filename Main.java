import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        // reads from the data file
        Scanner filein = new Scanner(
            new File("data_assn2.txt"));
        
        PCBQueue readyQueue = new PCBQueue();

        int timer = 0;

        double totalWaitTime = 0;
        double totalResponseTime = 0;
        double totalTAT = 0;
        
        PCB upcomingProcess = new PCB(0, Status.READY, 0, 0, 0);

        if (filein.hasNext()) {
            upcomingProcess = getProcess(filein, readyQueue); // getting the first process as it will be checked for enque eligibility
        }

        double processes = 1.0; // to avoid hard integer division

        System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s %-15s%n", "PID", "Burst", "Arrival", "Completion", "Turnaround", "Waiting", "Response");


        while (filein.hasNext() || !readyQueue.isEmpty()) {
            // does not allow the upcoming process to enqueue if its arrival is below the current program's timer
            if (upcomingProcess.getArrival() == timer && filein.hasNext()) {
                readyQueue.put(upcomingProcess);
                upcomingProcess = getProcess(filein, readyQueue); // will cause runtime error if there is nothing left in the file
                processes++;
            } else if (upcomingProcess.getArrival() == timer) { // will execute when there is nothing left in the file
                readyQueue.put(upcomingProcess);
                processes++;
            }

            PCB processingTask = readyQueue.runNextProcess();

            // to avoid touching the response time when there was already a response
            // for the process

            if (processingTask.getResponded() == false) {
                processingTask.responded();
                processingTask.setStart(timer);
                processingTask.setResponse(processingTask.getStart() - processingTask.getArrival());
            }



            processingTask.setCurrentBurst(processingTask.getCurrentBurst() - 1);

            Iterator<PCB> queueIterator = readyQueue.getIterator();

            // this iterates through the READY queue and increments the wait time
            // of all READY status processes

            while (queueIterator.hasNext()) {
                PCB sittingProcess = queueIterator.next();
                sittingProcess.setWait(sittingProcess.getWait() + 1);
            }



            // checks if the task is completed:
            //  if so, mark its completion
            //  -   set its TAT
            //  -   add its general stats to the totals variables
            //  -   print out the completed process
            //  if not,
            //  -   requeue the process (and check if it can run again via the priorityQueue)
            if (processingTask.getCurrentBurst() == 0) {
                timer++; // timer here is incremented early as putting it after will mess up its completion time

                processingTask.setCompletion(timer);
                processingTask.setTAT(processingTask.getCompletion() - processingTask.getArrival());

                totalWaitTime+=processingTask.getWait();
                totalTAT+=processingTask.getTAT();
                totalResponseTime+=processingTask.getResponse();

                System.out.printf("%-15d %-15d %-15d %-15d %-15d %-15d %-15d%n",processingTask.getPID(), processingTask.getBurst(), processingTask.getArrival(), processingTask.getCompletion(), processingTask.getTAT(), processingTask.getWait(), processingTask.getResponse());
                continue;

            } else {
                readyQueue.put(processingTask);
            }
            timer++;
        }

        double averageWaitTime = totalWaitTime/processes;
        double averageResponseTime = totalResponseTime/processes;
        double averageTAT = totalTAT/processes;

        System.out.printf("Average Turnaround Time: %.2f%nAverage Waiting Time: %.2f%nAverage Response Time: %.2f%n", 
            averageTAT, averageWaitTime, averageResponseTime
        );
    }

    public static PCB getProcess(Scanner in, PCBQueue queue) {
        int id = in.nextInt();
        String strStatus = in.next();
        Status status = Status.READY; // default value for enum
        switch (strStatus) {
                case "READY": status = Status.READY;
                break;
                case "WAITING": status = Status.WAITING;
                break;
                case "RUNNING": status = Status.RUNNING;
                break;
                case "TERMINATE": status = Status.TERMINATE;
                break;
        }
        int priority = in.nextInt();
        int burst = in.nextInt();
        int arrival = in.nextInt();
        return new PCB(id, status, priority, burst, arrival);
    }
}
