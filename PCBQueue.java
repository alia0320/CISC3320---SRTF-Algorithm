import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.Iterator;

/*
Slight modifications from HW2:
    - switched back to PriorityQueue
    - camparator is different from HW1, as it is referencing current bursts
    - Iterator for the queue is needed as we will need to modify the READY processes over time.
    - toString was added for debugging
*/

public class PCBQueue {

    PriorityQueue<PCB> readyQueue;

    public PCBQueue() {
        Comparator<PCB> priorityComparator = Comparator.comparingInt(t -> t.getCurrentBurst());
        this.readyQueue = new PriorityQueue<>(priorityComparator);
    }

    public void put(PCB pcbObject) {
        readyQueue.offer(pcbObject);
    }

    // removes PCB object from queue
    public PCB runNextProcess() {
        return readyQueue.poll();
    }
    
    public boolean isEmpty() {
        return readyQueue.isEmpty();
    }

    public Iterator<PCB> getIterator() {
        return readyQueue.iterator();
    }

    @Override
    public String toString() {
        return readyQueue.toString();
    }


}
