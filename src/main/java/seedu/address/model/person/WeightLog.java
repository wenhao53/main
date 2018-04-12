//@@author wenhao53

package seedu.address.model.person;

import java.util.ArrayList;
import java.util.Date;

import javafx.util.Pair;


/**
 * Represents a Person's weight history (in Kg) in the Personal Trainer Pro app.
 * Guarantees: immutable; is valid as declared in {@link #isValidWeight(String)}
 */
public class WeightLog {

    public ArrayList<Pair<Date, Double>> list;

    /**
     * Constructs a new {@code WeightLog}.
     */
    public WeightLog(Weight weight) {
        this.list = new ArrayList<>();
        addNewEntry(new Date(), weight);
    }

    /**
     * Adds a new weight entry into the log.
     * @param date A date representing the moment where the Person was created.
     * @param weight A valid weight (in Kg).
     */
    public void addNewEntry(Date date,Weight weight) {
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
