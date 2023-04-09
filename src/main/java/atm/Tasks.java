package atm;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.List;
import java.util.stream.Stream;

/**
 * Represent collection of ATM tasks to sort.
 *
 * @author Dominik_Janiga
 */
final class Tasks {

    private final List<Task> tasks;

    @JsonCreator
    Tasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    Stream<Task> stream() {
       return this.tasks.stream();
    }
}
