# wenhao53
###### /resources/view/LightTheme.css
``` css

#cardPane {
}

.ACCEPTABLE {
    -fx-background-color: #ddffdd;
    -fx-border-color: derive(#ddffdd, -30%);
    -fx-border-width: 2;
}

.OBESE {
    -fx-background-color: #ffdddd;
    -fx-border-color: derive(#ffdddd, -30%);
    -fx-border-width: 2;
}

.OVERWEIGHT {
    -fx-background-color: #ffffdd;
    -fx-border-color: derive(#ffffdd, -30%);
    -fx-border-width: 2;
}

.UNDERWEIGHT {
    -fx-background-color: #ffffdd;
    -fx-border-color: derive(#ffffdd, -30%);
    -fx-border-width: 2;
}

```
###### /resources/view/DarkTheme.css
``` css

#cardPane {
}

.ACCEPTABLE {
    -fx-background-color: #003000;
    -fx-border-color: derive(#003000, 25%);
    -fx-border-width: 2;
}

.OBESE {
    -fx-background-color: #500000;
    -fx-border-color: derive(#500000, 25%);
    -fx-border-width: 2;
}

.OVERWEIGHT {
    -fx-background-color: #553300;
    -fx-border-color: derive(#553300, 25%);
    -fx-border-width: 2;
}

.UNDERWEIGHT {
    -fx-background-color: #553300;
    -fx-border-color: derive(#553300, 25%);
    -fx-border-width: 2;
}

```
###### /java/seedu/address/ui/PersonCard.java
``` java
        bmiClass = person.getBodyMassIndexClassification();
        setBackgroundColourAccordingToClassification(cardPane, bmiClass);
    }

    private void setBackgroundColourAccordingToClassification(HBox cardPane, String bmiClass) {
        ObservableList<String> styleClass = cardPane.getStyleClass();
        styleClass.setAll("cardPane", bmiClass);
    }

```
###### /java/seedu/address/logic/parser/ClassificationCommandParser.java
``` java

package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import static seedu.address.model.person.BodyMassIndex.ACCEPTABLE_CLASSIFICATION;
import static seedu.address.model.person.BodyMassIndex.OBESE_CLASSIFICATION;
import static seedu.address.model.person.BodyMassIndex.OVERWEIGHT_CLASSIFICATION;
import static seedu.address.model.person.BodyMassIndex.UNDERWEIGHT_CLASSIFICATION;

import java.util.Arrays;

import seedu.address.logic.commands.ClassificationCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NameContainsClassificationPredicate;

/**
 * Parses input classifications and creates a new ClassificationCommand object
 */
public class ClassificationCommandParser implements Parser<ClassificationCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ClassificationCommand
     * and returns an ClassificationCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ClassificationCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClassificationCommand.MESSAGE_USAGE));
        }

        String[] classificationKeywords = trimmedArgs.split("\\s+");

        checkClassificationKeywordValidity(classificationKeywords);

        return new ClassificationCommand(
                new NameContainsClassificationPredicate(Arrays.asList(classificationKeywords)));
    }

    /**
     * Checks the validity of the keywords input in the context of a Classification Command
     * @throws ParseException if the keyword does not match any of the acceptable keywords
     */
    public void checkClassificationKeywordValidity(String[] classificationKeywords) throws ParseException {
        for (String keyword:classificationKeywords) {
            if (!isValidClassifcationKeyword(keyword)) {
                throw new ParseException(
                        String.format(ClassificationCommand.INVALID_KEYWORD, ClassificationCommand.MESSAGE_USAGE));
            }
        }
    }

    /**
     * Checks the given keyword in the parser to see it if matches any of the allowable classification
     */
    public boolean isValidClassifcationKeyword(String keyword) {
        return keyword.compareToIgnoreCase(ACCEPTABLE_CLASSIFICATION) == 0
                || keyword.compareToIgnoreCase(OBESE_CLASSIFICATION) == 0
                || keyword.compareToIgnoreCase(OVERWEIGHT_CLASSIFICATION) == 0
                || keyword.compareToIgnoreCase(UNDERWEIGHT_CLASSIFICATION) == 0;
    }

}
```
###### /java/seedu/address/logic/parser/ParserUtil.java
``` java
    /**
     * Parses a {@code String height} into a {@code Height}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws IllegalValueException if the given {@code height} is invalid.
     */
    public static Height parseHeight(String height) throws IllegalValueException {
        requireNonNull(height);
        String trimmedHeight = height.trim();
        if (!Height.isValidHeight(trimmedHeight)) {
            throw new IllegalValueException(Height.MESSAGE_HEIGHT_CONSTRAINTS);
        }
        return new Height(trimmedHeight);
    }

    /**
     * Parses a {@code Optional<String> height} into an {@code Optional<Height>} if {@code height} is present.
     * See header comment of this class regarding the use of {@code Optional} parameters.
     */
    public static Optional<Height> parseHeight(Optional<String> height) throws IllegalValueException {
        requireNonNull(height);
        return height.isPresent() ? Optional.of(parseHeight(height.get())) : Optional.empty();
    }

    /**
     * Parses a {@code String weight} into a {@code Weight}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws IllegalValueException if the given {@code weight} is invalid.
     */
    public static Weight parseWeight(String weight) throws IllegalValueException {
        requireNonNull(weight);
        String trimmedWeight = weight.trim();
        if (!Weight.isValidWeight(trimmedWeight)) {
            throw new IllegalValueException(Weight.MESSAGE_WEIGHT_CONSTRAINTS);
        }
        return new Weight(trimmedWeight);
    }

    /**
     * Parses a {@code Optional<String> weight} into an {@code Optional<Weight>} if {@code weight} is present.
     * See header comment of this class regarding the use of {@code Optional} parameters.
     */
    public static Optional<Weight> parseWeight(Optional<String> weight) throws IllegalValueException {
        requireNonNull(weight);
        return weight.isPresent() ? Optional.of(parseWeight(weight.get())) : Optional.empty();
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws IllegalValueException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws IllegalValueException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new IllegalValueException(Address.MESSAGE_ADDRESS_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code Optional<String> address} into an {@code Optional<Address>} if {@code address} is present.
     * See header comment of this class regarding the use of {@code Optional} parameters.
     */
    public static Optional<Address> parseAddress(Optional<String> address) throws IllegalValueException {
        requireNonNull(address);
        return address.isPresent() ? Optional.of(parseAddress(address.get())) : Optional.empty();
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws IllegalValueException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws IllegalValueException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new IllegalValueException(Email.MESSAGE_EMAIL_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code Optional<String> email} into an {@code Optional<Email>} if {@code email} is present.
     * See header comment of this class regarding the use of {@code Optional} parameters.
     */
    public static Optional<Email> parseEmail(Optional<String> email) throws IllegalValueException {
        requireNonNull(email);
        return email.isPresent() ? Optional.of(parseEmail(email.get())) : Optional.empty();
    }

    /**
     * Parses a {@code String gender} into a {@code Gender}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws IllegalValueException if the given {@code gender} is invalid.
     */
    public static Gender parseGender(String gender) throws IllegalValueException {
        requireNonNull(gender);
        String trimmedGender = gender.trim();
        if (!Gender.isValidGender(trimmedGender)) {
            throw new IllegalValueException(Gender.MESSAGE_GENDER_CONSTRAINTS);
        }
        return new Gender(trimmedGender);
    }

    /**
     * Parses a {@code Optional<String> gender} into an {@code Optional<Gender>} if {@code gender} is present.
     * See header comment of this class regarding the use of {@code Optional} parameters.
     */
    public static Optional<Gender> parseGender(Optional<String> gender) throws IllegalValueException {
        requireNonNull(gender);
        return gender.isPresent() ? Optional.of(parseGender(gender.get())) : Optional.empty();
    }

    /**
     * Parses a {@code String age} into a {@code Age}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws IllegalValueException if the given {@code age} is invalid.
     */
    public static Age parseAge(String age) throws IllegalValueException {
        requireNonNull(age);
        String trimmedAge = age.trim();
        if (!Age.isValidAge(trimmedAge)) {
            throw new IllegalValueException(Age.MESSAGE_AGE_CONSTRAINTS);
        }
        return new Age(trimmedAge);
    }

    /**
     * Parses a {@code Optional<String> age} into an {@code Optional<Age>} if {@code age} is present.
     * See header comment of this class regarding the use of {@code Optional} parameters.
     */
    public static Optional<Age> parseAge(Optional<String> age) throws IllegalValueException {
        requireNonNull(age);
        return age.isPresent() ? Optional.of(parseAge(age.get())) : Optional.empty();
    }

    /**
     * Parses a {@code String activityLevel} into a {@code ActivityLevel}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws IllegalValueException if the given {@code activityLevel} is invalid.
     */
    public static ActivityLevel parseActivityLevel(String activityLevel) throws IllegalValueException {
        requireNonNull(activityLevel);
        String trimmedActivityLevel = activityLevel.trim();
        if (!ActivityLevel.isValidActivityLevel(trimmedActivityLevel)) {
            throw new IllegalValueException(ActivityLevel.MESSAGE_ACTIVITYLEVEL_CONSTRAINTS);
        }
        return new ActivityLevel(trimmedActivityLevel);
    }

    /**
     * Parses a {@code Optional<String> activityLevel} into an {@code Optional<ActivityLevel>}
     * if {@code activityLevel} is present.
     * See header comment of this class regarding the use of {@code Optional} parameters.
     */
    public static Optional<ActivityLevel> parseActivityLevel(Optional<String> activityLevel) throws
            IllegalValueException {
        requireNonNull(activityLevel);
        return activityLevel.isPresent() ? Optional.of(parseActivityLevel(activityLevel.get())) : Optional.empty();
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws IllegalValueException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws IllegalValueException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new IllegalValueException(Tag.MESSAGE_TAG_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws IllegalValueException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    /**
     * Parses a {@code String eventName} into a {@code EventName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws IllegalValueException if the given {@code eventName} is invalid.
     */
    public static EventName parseEventName(String eventName) throws IllegalValueException {
        requireNonNull(eventName);
        String trimmedName = eventName.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new IllegalValueException(EventName.MESSAGE_EVENT_NAME_CONSTRAINTS);
        }
        return new EventName(trimmedName);
    }

    /**
     * Parses a {@code Optional<String> eventName} into an {@code Optional<EventName>} if {@code eventName} is present.
     * See header comment of this class regarding the use of {@code Optional} parameters.
     */
    public static Optional<EventName> parseEventName(Optional<String> eventName) throws IllegalValueException {
        requireNonNull(eventName);
        return eventName.isPresent() ? Optional.of(parseEventName(eventName.get())) : Optional.empty();
    }

    /**
     * Parses a {@code String eventStartDate} into a {@code EventStartDate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws IllegalValueException if the given {@code eventStartDate} is invalid.
     */
    public static EventStartDate parseEventStartDate(String eventStartDate) throws IllegalValueException {
        requireNonNull(eventStartDate);
        String trimmedStartDate = eventStartDate.trim();
        if (!EventStartDate.isValidDate(trimmedStartDate)) {
            throw new IllegalValueException(EventStartDate.MESSAGE_START_DATE_CONSTRAINTS);
        }
        return new EventStartDate(trimmedStartDate);
    }

    /**
     * Parses a {@code Optional<String> eventStartDate} into an {@code Optional<EventStartDate>}
     * if {@code eventStartDate} is present.
     * See header comment of this class regarding the use of {@code Optional} parameters.
     */
    public static Optional<EventStartDate> parseEventStartDate(Optional<String> eventStartDate)
            throws IllegalValueException {
        requireNonNull(eventStartDate);
        return eventStartDate.isPresent() ? Optional.of(parseEventStartDate(eventStartDate.get())) : Optional.empty();
    }

    /**
     * Parses a {@code String eventEndDate} into a {@code EventEndDate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws IllegalValueException if the given {@code eventEndDate} is invalid.
     */
    public static EventEndDate parseEventEndDate(String eventEndDate) throws IllegalValueException {
        requireNonNull(eventEndDate);
        String trimmedEndDate = eventEndDate.trim();
        if (!EventEndDate.isValidDate(trimmedEndDate)) {
            throw new IllegalValueException(EventEndDate.MESSAGE_END_DATE_CONSTRAINTS);
        }
        return new EventEndDate(trimmedEndDate);
    }

    /**
     * Parses a {@code Optional<String> eventEndDate} into an {@code Optional<EventEndDate>}
     * if {@code eventEndDate} is present.
     * See header comment of this class regarding the use of {@code Optional} parameters.
     */
    public static Optional<EventEndDate> parseEventEndDate(Optional<String> eventEndDate)
            throws IllegalValueException {
        requireNonNull(eventEndDate);
        return eventEndDate.isPresent() ? Optional.of(parseEventEndDate(eventEndDate.get())) : Optional.empty();
    }

    /**
     * Parses a {@code String eventStartTime} into a {@code EventStartTime}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws IllegalValueException if the given {@code eventStartTime} is invalid.
     */
    public static EventStartTime parseEventStartTime(String eventStartTime) throws IllegalValueException {
        requireNonNull(eventStartTime);
        String trimmedStartTime = eventStartTime.trim();
        if (!EventStartTime.isValidTime(trimmedStartTime)) {
            throw new IllegalValueException(EventStartTime.MESSAGE_START_TIME_CONSTRAINTS);
        }
        return new EventStartTime(trimmedStartTime);
    }

    /**
     * Parses a {@code Optional<String> eventStartTime} into an {@code Optional<EventStartTime>}
     * if {@code eventStartTime} is present.
     * See header comment of this class regarding the use of {@code Optional} parameters.
     */
    public static Optional<EventStartTime> parseEventStartTime(Optional<String> eventStartTime)
            throws IllegalValueException {
        requireNonNull(eventStartTime);
        return eventStartTime.isPresent() ? Optional.of(parseEventStartTime(eventStartTime.get())) : Optional.empty();
    }

    /**
     * Parses a {@code String eventEndTime} into a {@code EventEndTime}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws IllegalValueException if the given {@code eventEndTime} is invalid.
     */
    public static EventEndTime parseEventEndTime(String eventEndTime) throws IllegalValueException {
        requireNonNull(eventEndTime);
        String trimmedEndTime = eventEndTime.trim();
        if (!EventEndTime.isValidTime(trimmedEndTime)) {
            throw new IllegalValueException(EventEndTime.MESSAGE_END_TIME_CONSTRAINTS);
        }
        return new EventEndTime(trimmedEndTime);
    }

    /**
     * Parses a {@code Optional<String> eventEndTime} into an {@code Optional<EventEndTime>}
     * if {@code eventEndTime} is present.
     * See header comment of this class regarding the use of {@code Optional} parameters.
     */
    public static Optional<EventEndTime> parseEventEndTime(Optional<String> eventEndTime)
            throws IllegalValueException {
        requireNonNull(eventEndTime);
        return eventEndTime.isPresent() ? Optional.of(parseEventEndTime(eventEndTime.get())) : Optional.empty();
    }
}
```
###### /java/seedu/address/logic/commands/ClassificationCommand.java
``` java

package seedu.address.logic.commands;

import seedu.address.model.person.NameContainsClassificationPredicate;

/**
 * Finds and lists all persons in the Personal Trainer Pro application whose BodyMassIndexClassification matches
 * any of the given keywords.
 */
public class ClassificationCommand extends Command {

    public static final String COMMAND_WORD = "bmi";

    public static final String INVALID_KEYWORD = "One or more invalid keyword(s) found! \n%1$s";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose BMI classification matches "
            + "any of the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " overweight\n"
            + "KEYWORD: 'Underweight'(BMI < 18.5), 'Acceptable' (18.5 <= BMI <= 24.9), "
                    + "'Overweight' (25 <= BMI <= 29.9), 'Obese' (BMI > 30)\n";

    private final NameContainsClassificationPredicate predicate;

    public ClassificationCommand(NameContainsClassificationPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute() {
        model.updateFilteredPersonList(predicate);
        return new CommandResult(getMessageForPersonListShownSummary(model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ClassificationCommand // instanceof handles nulls
                && this.predicate.equals(((ClassificationCommand) other).predicate)); // state check
    }
}
```
###### /java/seedu/address/model/person/Height.java
``` java

package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's height (in cm) in the Personal Trainer Pro app.
 * Guarantees: immutable; is valid as declared in {@link #isValidHeight(String)}
 */
public class Height {


    public static final String MESSAGE_HEIGHT_CONSTRAINTS =
            "Height(in cm) can only contain numbers and decimals, and should be at between 10.0 to 400.0";
    public static final String HEIGHT_VALIDATION_REGEX = "\\d{2,}(\\.\\d+)?";
    public final String value;

    /**
     * Constructs a {@code Height}.
     *
     * @param height A valid height (in cm).
     */
    public Height(String height) {
        requireNonNull(height);
        checkArgument(isValidHeight(height), MESSAGE_HEIGHT_CONSTRAINTS);
        this.value = height;
    }

    /**
     * Returns true if a given string is a valid height.
     */
    public static boolean isValidHeight(String test) {
        return test.matches(HEIGHT_VALIDATION_REGEX) && heightWithinRange(test);
    }

    /**
     * Returns true if a given Height string is within the allowable range of input
     */
    private static boolean heightWithinRange(String test) {
        return Double.parseDouble(test) >= 10.0 && Double.parseDouble(test) <= 400.0;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Height // instanceof handles nulls
                && this.value.equals(((Height) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
```
###### /java/seedu/address/model/person/BodyMassIndex.java
``` java

package seedu.address.model.person;

/**
 * Represents a Person's Body Mass Index (BMI) (in kg/m^2) in the Personal Trainer Pro app.
 * Guarantees: mutable; is valid long as Height as declared in {@link #isValidHeight(String)} and Weight as
 * declared in {@Link #isValidWeight(String)} is valid.
 */
public class BodyMassIndex {

    public static final String ACCEPTABLE_CLASSIFICATION = "ACCEPTABLE";
    public static final String OBESE_CLASSIFICATION = "OBESE";
    public static final String OVERWEIGHT_CLASSIFICATION = "OVERWEIGHT";
    public static final String UNDERWEIGHT_CLASSIFICATION = "UNDERWEIGHT";

    public final String value;
    public final String classification;

    private double bodyMassIndexValue;
    private double heightValue;
    private double weightValue;

    private String bodyMassIndexString;

    /**
     * Constructs a {@code BodyMassIndex}.
     *
     * @param height A valid height (in cm).
     * @param weight A valid weight (in kg).
     */
    public BodyMassIndex(String height, String weight) {
        bodyMassIndexValue = getBodyMassIndexValue(height, weight);
        this.classification = getBodyMassIndexClassificationFromValue(bodyMassIndexValue);
        this.value = formatBodyMassIndexStringForDisplay(bodyMassIndexValue);
    }

    /*
     * Formats the BMI value of a Person into a String, displayed to two decimal places.
     */
    private String formatBodyMassIndexStringForDisplay(Double value) {
        return String.format("%.2f", value);

    }

    /*
     * Returns the BMI Classification of a Person when given the BMI value.
     */
    private String getBodyMassIndexClassificationFromValue(Double bodyMassIndexValue) {
        if (bodyMassIndexValue < 18.5) {
            return UNDERWEIGHT_CLASSIFICATION;
        } else if (bodyMassIndexValue < 25) {
            return ACCEPTABLE_CLASSIFICATION;
        } else if (bodyMassIndexValue < 30) {
            return OVERWEIGHT_CLASSIFICATION;
        } else {
            return OBESE_CLASSIFICATION;
        }
    }

    /*
     * Returns the BMI value of a Person when given valid Height and Weight
     */
    private double getBodyMassIndexValue(String height, String weight) {
        heightValue = Double.parseDouble(height);
        weightValue = Double.parseDouble(weight);
        bodyMassIndexValue = calculateBodyMassIndexValueFromHeightAndWeight(heightValue, weightValue);
        return bodyMassIndexValue;

    }

    /*
     * Returns the BMI value calculated from heightValue and weightValue
     */
    private double calculateBodyMassIndexValueFromHeightAndWeight(double heightValue, double weightValue) {
        return weightValue / ((heightValue / 100) * (heightValue / 100));
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
```
###### /java/seedu/address/model/person/Name.java
``` java
    /**
     * Returns true if two Names in comparison are alphabetically the same (regardless of case).
     */
    public boolean alphabeticallyEquals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Name // instanceof handles nulls
                && this.fullName.toLowerCase().equals(((Name) other).fullName.toLowerCase())); // state check
    }

```
###### /java/seedu/address/model/person/Weight.java
``` java
    /**
     * Returns true if a given Weight string is within the allowable range of input
     */
    private static boolean weightWithinRange(String test) {
        return Double.parseDouble(test) >= 10.0 && Double.parseDouble(test) <= 400.0;
    }

```
###### /java/seedu/address/model/person/NameContainsClassificationPredicate.java
``` java

package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code BodyMassIndexClassification} matches the given search query.
 */
public class NameContainsClassificationPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public NameContainsClassificationPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream().anyMatch(keyword ->
                StringUtil.containsWordIgnoreCase(person.getBodyMassIndexClassification(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsClassificationPredicate // instanceof handles nulls
                && this.keywords.equals(((NameContainsClassificationPredicate) other).keywords)); // state check
    }

}
```
