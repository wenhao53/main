//@@author wenhao53-unused

/* removed implementation of weightlog due to time constraints and unable to resolve test issues */
package seedu.address.commons.events.ui;

import seedu.address.commons.events.BaseEvent;
import seedu.address.model.person.Person;


/**
 * An event requesting to show the weight log of a given person
 */
public class ShowWeightLogEvent extends BaseEvent {

    public final Person person;

    public ShowWeightLogEvent(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
