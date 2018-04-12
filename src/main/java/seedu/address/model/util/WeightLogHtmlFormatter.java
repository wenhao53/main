//@@author wenhao53

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


    private static String convertWeightLogToString(WeightLog weightLog) {
        final String TABSPACE = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";

        String logString = "";
        for (int entry = 0; entry < weightLog.list.size(); entry++) {
            logString = String.format("%s %s %s %s", logString,
                    "[" + weightLog.list.get(entry).getKey().toString() + "]", TABSPACE,
                    weightLog.list.get(entry).getValue().toString() + "kg <br>");
        }
        return logString;

    }


}
