package atm;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Collection;

/**
 * Represent collection of sorted ATMs.
 *
 * @author Dominik_Janiga
 */
record SortedATMs(@JsonValue Collection<ATM> atms) { }
