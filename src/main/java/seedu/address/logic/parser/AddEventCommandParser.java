package seedu.address.logic.parser;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddEventCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.CalendarEvent.CalendarEvent;
import seedu.address.model.CalendarEvent.EventEndDate;
import seedu.address.model.CalendarEvent.EventEndTime;
import seedu.address.model.CalendarEvent.EventName;
import seedu.address.model.CalendarEvent.EventStartDate;
import seedu.address.model.CalendarEvent.EventStartTime;
import seedu.address.model.person.Address;
import seedu.address.model.person.Age;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Height;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Weight;
import seedu.address.model.tag.Tag;

import java.util.Set;
import java.util.stream.Stream;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_END_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_START_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_START_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HEIGHT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEIGHT;

/**
 * Parses input arguments and creates a new AddEventCommand object
 */
public class AddEventCommandParser implements Parser<AddEventCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddEventCommand
     * and returns an AddEventCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddEventCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_EVENT_NAME, PREFIX_EVENT_START_DATE, PREFIX_EVENT_START_TIME,
                        PREFIX_EVENT_END_DATE, PREFIX_EVENT_END_TIME);

        if (!arePrefixesPresent(argMultimap, PREFIX_EVENT_NAME, PREFIX_EVENT_START_DATE, PREFIX_EVENT_START_TIME,
                PREFIX_EVENT_END_DATE, PREFIX_EVENT_END_TIME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddEventCommand.MESSAGE_USAGE));
        }

        try {
            EventName eventName =
                    ParserUtil.parseEventName(argMultimap.getValue(PREFIX_EVENT_NAME)).get();
            EventStartDate eventStartDate =
                    ParserUtil.parseEventStartDate(argMultimap.getValue(PREFIX_EVENT_START_DATE)).get();
            EventStartTime eventStartTime =
                    ParserUtil.parseEventStartTime(argMultimap.getValue(PREFIX_EVENT_START_TIME)).get();
            EventEndDate eventEndDate =
                    ParserUtil.parseEventEndDate(argMultimap.getValue(PREFIX_EVENT_END_DATE)).get();
            EventEndTime eventEndTime =
                    ParserUtil.parseEventEndTime(argMultimap.getValue(PREFIX_EVENT_END_TIME)).get();

            CalendarEvent calendarEvent = new CalendarEvent(eventName, eventStartDate, eventStartTime,
                    eventEndDate, eventEndTime);

            return new AddEventCommand(calendarEvent);
        } catch (IllegalValueException ive) {
            throw new ParseException(ive.getMessage(), ive);
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
