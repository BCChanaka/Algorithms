package data_structures;

public class PrioritizedItem implements Comparable<PrioritizedItem> {
    private int priority;
    private int itemNumber;

    public PrioritizedItem(int p, int n) {
        priority = p;
        itemNumber = n;
    }

    public int compareTo(PrioritizedItem item) {
        return priority - item.priority;
    }

    public String toString() {
        return "Priority: " + priority + " Item Number: " + itemNumber;
    }

    public int getPriority() {
        return priority;
    }

    public int getSequenceNumber() {
        return itemNumber;
    }
}