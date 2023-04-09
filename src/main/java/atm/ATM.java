package atm;

/**
 * Represent single ATM
 *
 * @author Dominik_Janiga
 */
record ATM(int region, int atmId) {

    @Override
    public int hashCode() {
        return 31 * Integer.hashCode(this.region) + Integer.hashCode(this.atmId);
    }
}
