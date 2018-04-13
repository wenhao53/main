//@@author wenhao53-unused

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
