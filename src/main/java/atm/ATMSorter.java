package atm;

import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * ATM sorter
 *
 * @author Dominik_Janiga
 */
@Service
final class ATMSorter {

    /**
     * Sort list of ATM tasks according to natural order (ATM region and {@link RequestType}).
     * Maps task to instance of {@link ATM} and return LinkedHashSet. Uses set to remove duplicated
     * requests for the same ATM.
     *
     * @param tasks list of ATM tasks
     * @return set of tasks.
     * @see Task
     */
    SortedATMs sort(Tasks tasks) {
        Set<ATM> atms = tasks.stream().sorted()
                .map(Task::toATM)
                .collect(Collectors.toCollection(LinkedHashSet::new));
        return new SortedATMs(atms);
    }
}
