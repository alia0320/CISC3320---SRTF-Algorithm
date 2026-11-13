public class PCB {
    
    // more instance variables added since HW2

    private int PID;
    private Status status;
    private int priority;
    private int burst; // needed for print statement once task is completed
    private int currentBurst; // will be decremented over time
    private int arrival;
    private int response = 0; // easy to take note of the response once task hits CPU
    private int start = 0; // same applies here
    private int completion = 0; // easy to take note of when the while loop is still referencing the object
    private int wait = 0; // same applies here
    private int TAT = 0; // and here
    private boolean hasResponse; // helps avoid modifying the response times by the time the process finally hits the CPU

    // only one constructor for the PCB object

    public PCB(int id, Status status, int priority, int burst, int arrival) {
        this.PID = id;
        this.status = status;
        this.priority = priority;
        this.burst = burst;
        this.currentBurst = burst;
        this.arrival = arrival;
        this.hasResponse = false;
    }

    // getters and setters for data fields here

    public int getPID() {
        return this.PID;
    }

    public void setPID(int PID) {
        this.PID = PID;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getPriority() {
        return this.priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getBurst() {
        return this.burst;
    }

    public void setBurst(int burst) {
        this.burst = burst;
    }

    public int getArrival() {
        return this.arrival;
    }

    public void setArrival(int arrival) {
        this.arrival = arrival;
    }

    public int getResponse() {
        return this.response;
    }

    public void setResponse(int response) {
        this.response = response;
    }

    public int getWait() {
        return this.wait;
    }

    public void setWait(int wait) {
        this.wait = wait;
    }

    public int getTAT() {
        return this.TAT;
    }

    public void setTAT(int TAT) {
        this.TAT = TAT;
    }

    public boolean getResponded() {
        return this.hasResponse;
    }

    public void responded() {
        this.hasResponse = true;
    }

    public int getStart() {
        return this.start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getCompletion() {
        return this.completion;
    }

    public void setCompletion(int completion) {
        this.completion = completion;
    }

    public int getCurrentBurst() {
        return this.currentBurst;
    }

    public void setCurrentBurst(int burst) {
        this.currentBurst = burst;
    }

    @Override
    public String toString() {
        return this.PID + " " + this.currentBurst;
    }
}
