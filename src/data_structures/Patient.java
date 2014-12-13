package data_structures;

public class Patient implements Comparable<Patient> {
    @Override
    public String toString() {
        return "Patient [name=" + name + ", priority=" + priority + "]";
    }

    private String name;
    private int priority;

    public Patient(String name, int priority) {
        this.setName(name);
        this.setPriority(priority);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Patient next) {
        return this.priority - next.priority;
    }
    

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
