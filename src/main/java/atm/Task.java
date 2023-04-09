package atm;

/**
 * Represent ATM task.
 *
 * @author Dominik_Janiga
 */
record Task(int region, RequestType requestType, int atmId) implements Comparable<Task> {

    ATM toATM() {
        return new ATM(this.region, this.atmId);
    }

    @Override
    public int compareTo(Task task) {
        int regionResult = this.region - task.region;
        return regionResult != 0 ? regionResult
                                : requestType.compareTo(task.requestType);
    }
}
