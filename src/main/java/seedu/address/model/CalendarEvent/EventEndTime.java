package seedu.address.model.CalendarEvent;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the end time of an event in the Personal Trainer Pro app.
 * Guarantees: immutable; is valid as declared in {@link #isValidTime(String)}
 */

public class EventEndTime {

    public static final String MESSAGE_END_TIME_CONSTRAINTS =
            "Event end time should only contain numbers in the HH:MM format. Eg. 11:30";

    public static final String TIME_VALIDATION_REGEX = "^([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$";

    public final String endTime;

    /**
     * Constructs an {@code EventEndTime}.
     *
     * @param endTime A valid endTime.
     */

    public EventEndTime(String endTime) {
        requireNonNull(endTime);
        checkArgument(isValidTime(endTime), MESSAGE_END_TIME_CONSTRAINTS);
        this.endTime = endTime;
    }

    /**
     * Returns true if a given string is a valid event end time.
     */
    public static boolean isValidTime(String test) {
        return test.matches(TIME_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return endTime;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EventEndTime // instanceof handles nulls
                && this.endTime.equals(((EventEndTime) other).endTime)); // state check
    }

    @Override
    public int hashCode() {
        return endTime.hashCode();
    }
}
