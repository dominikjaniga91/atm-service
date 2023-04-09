package atm;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ATM controller
 *
 * @author Dominik_Janiga
 */
@RestController
@RequestMapping("/atms")
class ATMController {

    private final ATMSorter atmSorter;

    ATMController(ATMSorter atmSorter) {
        this.atmSorter = atmSorter;
    }

    @PostMapping(value = "/calculate-order")
    public SortedATMs sortATMs(@RequestBody Tasks tasks) {
        return this.atmSorter.sort(tasks);
    }
}