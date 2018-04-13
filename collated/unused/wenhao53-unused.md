# wenhao53-unused
###### /WeightLog.java
``` java

/* removed implementation of weightlog due to time constraints and unable to resolve test issues */
package seedu.address.model.person;

import java.util.ArrayList;
import java.util.Date;

import javafx.util.Pair;


/**
 * Represents a Person's weight history (in Kg) in the Personal Trainer Pro app.
 * Guarantees: immutable; is valid as declared in {@link #isValidWeight(String)}
 */
public class WeightLog {

    private ArrayList<Pair<Date, Double>> list;

    /**
     * Constructs a new {@code WeightLog}.
     */
    public WeightLog(Weight weight) {
        this.list = new ArrayList<>();
        addNewEntry(new Date(), weight);
    }

    /**
     * Returns the ArrayList that contains the information in the WeightLog.
     */
    public ArrayList<Pair<Date, Double>> getList() {
        return this.list;
    }

    /**
     * Adds a new weight entry into the log.
     * @param date A date representing the moment where the Person was created.
     * @param weight A valid weight (in Kg).
     */
    public void addNewEntry(Date date, Weight weight) {
        list.add(new Pair(date, getDoubleValueFromWeight(weight)));
    }

    /*
     * Returns the double value of the input weight from the Weight string.
     */
    private Double getDoubleValueFromWeight(Weight weight) {
        return Double.parseDouble(weight.toString());
    }

    @Override
    public String toString() {
        String toDisplayAsString = " ";
        for (Pair entry:list) {
            toDisplayAsString += " " + entry.getValue().toString();
        }
        return toDisplayAsString;
    }



}
```
###### /weightLog.css
``` css
/* unused as weightlog was removed */

body {
    background-color: white;
    color: darkblue;
    font-family: Arial, Helvetica, sans-serif;
    text-align: center;
    font-size: 22px;
    padding: 30px;
}

h1 {
    margin-bottom: 20px;
}

h2 {
    font-size: 24px;
    text-align: center;
}

.name {
    text-align: center;
}

.col-sm-3 {
    width: 79%;
    display: inline-block;
}

```
###### /WeightLogHtmlFormatter.java
``` java

/* removed implementation of weightlog due to time constraints and unable to resolve test issues */
package seedu.address.model.util;

import static seedu.address.ui.UiPart.FXML_FILE_FOLDER;

import java.net.URL;

import seedu.address.MainApp;
import seedu.address.model.person.Person;
import seedu.address.model.person.WeightLog;

/**
 * HTML formatter for Person class.
 */
public class WeightLogHtmlFormatter {

    public static String getWeightLogHtmlFormat(Person person) {

        URL themeCss = MainApp.class.getResource(FXML_FILE_FOLDER + "LightTheme.css");
        URL weightLogCss = MainApp.class.getResource(FXML_FILE_FOLDER + "weightLog.css");

        String name = person.getName().toString();
        String weightLog = convertWeightLogToString(person.getWeightLog());

        return "<html>"
                + "<head>"
                + "<link rel='stylesheet' type='text/css' href='" + themeCss.toExternalForm() + "' />"
                + "<link rel='stylesheet' type='text/css' href='" + weightLogCss.toExternalForm() + "' />"
                + "</head>"
                + "<body>"
                + "<div class='row'>"
                + "<h1 class='name'>" + name + "'s Weight Log" + "</h1>"
                + "<div class='col-sm-3'>"
                + "<p>" + weightLog + "</p>"
                + "</div>"
                + "</div>"
                + "</div>"
                + "</body>"
                + "</html>";

    }


    /**
     * Converts a given a {@code weightLog} stored as an ArrayList into a String for HTML display.
     */
    private static String convertWeightLogToString(WeightLog weightLog) {
        final String twoTabspaces = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";

        String logString = "";
        for (int entry = 0; entry < weightLog.getList().size(); entry++) {
            logString = String.format("%s %s %s %s", logString,
                    "[" + weightLog.getList().get(entry).getKey().toString() + "]", twoTabspaces,
                    weightLog.getList().get(entry).getValue().toString() + "kg <br>");
        }
        return logString;

    }


}
```
###### /EditCommand.java
``` java
        /* removed implementation of weightlog due to time constraints and unable to resolve test issues */
        WeightLog weightLogToUpdate = personToEdit.getWeightLog();

        // Extracts the weight log from the old person to add the new weight before assigning it to the new person
        if (updatedWeight != null) {
            weightLogToUpdate.addNewEntry(new Date(), updatedWeight);

            return new Person(updatedName, updatedPhone, updatedEmail, updatedAddress,
                    updatedHeight, updatedWeight, updatedGender, updatedAge, updatedActivityLevel,
                    weightLogToUpdate, updatedTags);
        }

        return new Person(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedHeight, updatedWeight,
                updatedGender, updatedAge, updatedActivityLevel, weightLogToUpdate, updatedTags);
```
###### /WeightLogCommand.java
``` java

/* removed implementation of weightlog due to time constraints and unable to resolve test issues */
package seedu.address.logic.commands;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.PersonNotFoundException;

/**
 * Opens up the Weight Log of a specified person in the Personal Trainer Pro
 */
public class WeightLogCommand extends Command {

    public static final String COMMAND_WORD = "log";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Displays weight log of a person.\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_WEIGHT_LOG_SUCCESS = "Weight log for %1$s displayed!";

    private final Index index;

    public WeightLogCommand(Index index) {
        this.index = index;
    }

    @Override
    public CommandResult execute() throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToShowWeightLog = lastShownList.get(index.getZeroBased());

        try {
            model.showWeightLog(personToShowWeightLog);
        } catch (PersonNotFoundException pnfe) {
            assert false : "The target person cannot be missing";
        }

        return new CommandResult(String.format(MESSAGE_WEIGHT_LOG_SUCCESS,
                personToShowWeightLog.getName().fullName));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof WeightLogCommand // instanceof handles nulls
                && this.index.equals(((WeightLogCommand) other).index)); // state check
    }
}
```
###### /ShowWeightLogEvent.java
``` java

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
```
