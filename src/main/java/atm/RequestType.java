package atm;

/**
 * Represent ATM task request type.
 * Ordered from the most to the least important.
 *
 * @author Dominik_Janiga
 */
enum RequestType {
    FAILURE_RESTART,
    PRIORITY,
    SIGNAL_LOW,
    STANDARD
}
