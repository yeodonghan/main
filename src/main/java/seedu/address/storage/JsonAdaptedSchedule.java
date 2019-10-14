package seedu.address.storage;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;

import seedu.address.model.schedule.Schedule;
import seedu.address.model.schedule.Venue;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Schedule}.
 */
class JsonAdaptedSchedule {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Schedule's %s field is missing!";

    // Identity fields
    private final UUID id;

    // Data fields
    private final Calendar calendar;
    private final Venue venue;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedSchedule} with the given schedule details.
     */
    @JsonCreator
    public JsonAdaptedSchedule(@JsonProperty("id") UUID id,
                            @JsonProperty("calendar") Calendar calendar,
                            @JsonProperty("venue") Venue venue,
                            @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.id = id;
        this.calendar = calendar;
        this.venue = venue;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Schedule} into this class for Jackson use.
     */
    public JsonAdaptedSchedule(Schedule source) {
        id = source.getId();
        calendar = source.getCalendar();
        venue = source.getVenue();
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted schedule object into the model's {@code Schedule} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted schedule.
     */
    public Schedule toModelType() throws IllegalValueException {
        final List<Tag> scheduleTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            scheduleTags.add(tag.toModelType());
        }

        if (id == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    UUID.class.getSimpleName()));
        }

        final UUID modelId = id;

        if (calendar == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Calendar.class.getSimpleName()));
        }

        final Calendar modelCalendar = calendar;

        if (venue == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Venue.class.getSimpleName()));
        }
        if (!Venue.isValidVenue(venue.toString())) {
            throw new IllegalValueException(Venue.MESSAGE_CONSTRAINTS);
        }
        final Venue modelVenue = new Venue(venue.toString());

        final Set<Tag> modelTags = new HashSet<>(scheduleTags);
        return new Schedule(modelId, modelCalendar, modelVenue, modelTags);
    }

}
