package seedu.address.model.schedule;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Calendar;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.order.Order;
import seedu.address.model.tag.Tag;

/**
 * Represents a Schedule in the SML.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Schedule implements Cloneable {

    // Identity fields
    private final Order order;

    // Data fields
    private final Calendar calendar;
    private final Venue venue;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     * @throws CloneNotSupportedException If Order class does not implement Cloneable interface.
     */
    public Schedule(Order order, Calendar calendar, Venue venue, Set<Tag> tags) {
        requireAllNonNull(order, calendar, venue, tags);
        this.order = (Order) order.clone();
        this.calendar = calendar;
        this.venue = venue;
        this.tags.addAll(tags);
    }

    public Order getOrder() {
        return order;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public Venue getVenue() {
        return venue;
    }

    /**
     * Return a formatted String of the Calendar Object.
     */
    private String getCalendarString() {
        StringBuilder sb = new StringBuilder();
        sb.append(calendar.get(Calendar.YEAR) + ".")
                .append(calendar.get(Calendar.MONTH) + ".")
                .append(calendar.get(Calendar.DAY_OF_MONTH) + " ")
                .append(calendar.get(Calendar.HOUR_OF_DAY) + ":")
                .append(calendar.get(Calendar.MINUTE));
        return sb.toString();
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both schedules have the identity field.
     * This defines a weaker notion of equality between two schedules.
     */
    public boolean isSameSchedule(Schedule otherSchedule) {
        if (otherSchedule == this) {
            return true;
        }

        return otherSchedule != null
                && otherSchedule.getOrder().isSameOrder(getOrder());
    }

    /**
     * Returns true if both schedules have the same identity and data fields.
     * This defines a stronger notion of equality between two schedules.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Schedule)) {
            return false;
        }

        Schedule otherSchedule = (Schedule) other;
        return otherSchedule.getOrder().equals(getOrder())
                && otherSchedule.getCalendar().equals(getCalendar())
                && otherSchedule.getVenue().equals(getVenue())
                && otherSchedule.getTags().equals((getTags()));
    }

    @Override
    public Object clone() {
        Schedule clone = new Schedule((Order) this.order.clone(), (Calendar) this.calendar.clone(),
                (Venue) this.venue.clone(), this.getTags());
        return clone;
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(order, calendar, venue, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Order: ")
                .append(getOrder())
                .append(" Date and Time: ")
                .append(getCalendarString())
                .append(" Venue: ")
                .append(getVenue())
                .append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }
}
