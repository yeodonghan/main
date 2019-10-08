package seedu.address.model.order;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.text.NumberFormat;
import java.text.ParseException;


/**
 * Represents a Phone's selling price in an Order in the SML.
 * Guarantees: immutable; is valid as declared in {@link #isValidPrice(String)}
 */
public class Price {

    public static final String MESSAGE_CONSTRAINTS =
            "Prices must be non-negative, start with \'$\' and have at most 2 decimals.";

    public final String value;

    /**
     * Constructs a {@code Price}.
     *
     * @param price A valid price.
     */
    public Price(String price) {
        requireNonNull(price);
        checkArgument(isValidPrice(price), MESSAGE_CONSTRAINTS);
        value = price;
    }

    /**
     * Returns true if a given string is a valid price.
     */
    public static boolean isValidPrice(String price) {
        try {
            Number number = NumberFormat.getCurrencyInstance().parse(price);
            return number != null;
        } catch (ParseException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Price // instanceof handles nulls
                && value.equals(((Price) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    public String getValue() {
        return this.value;
    }

}
