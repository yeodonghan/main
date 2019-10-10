package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.phone.Brand;
import seedu.address.model.phone.Capacity;
import seedu.address.model.phone.Colour;
import seedu.address.model.phone.Cost;
import seedu.address.model.phone.Phone;
import seedu.address.model.phone.PhoneName;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Phone}.
 */
class JsonAdaptedPhone {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Phone's %s field is missing!";

    private final UUID id;
    private final PhoneName phoneName;
    private final Brand brand;
    private final Capacity capacity;
    private final Colour colour;
    private final Cost cost;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPhone} with the given phone details.
     */
    @JsonCreator
    public JsonAdaptedPhone(@JsonProperty("id") UUID id, @JsonProperty("phoneName") PhoneName phoneName,
                             @JsonProperty("brand") Brand brand,
                             @JsonProperty("capacity") Capacity capacity, @JsonProperty("colour") Colour colour,
                             @JsonProperty("cost") Cost cost, @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.id = id;
        this.phoneName = phoneName;
        this.brand = brand;
        this.capacity = capacity;
        this.colour = colour;
        this.cost = cost;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Phone} into this class for Jackson use.
     */
    public JsonAdaptedPhone(Phone source) {
        id = source.getId();
        phoneName = source.getPhoneName();
        brand = source.getBrand();
        capacity = source.getCapacity();
        colour = source.getColour();
        cost = source.getCost();
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted phone object into the model's {@code Phone} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted phone.
     */
    public Phone toModelType() throws IllegalValueException {
        final List<Tag> phoneTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            phoneTags.add(tag.toModelType());
        }

        final UUID modelId = id;

        if (phoneName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    PhoneName.class.getSimpleName()));
        }
        if (!PhoneName.isValidPhoneName(phoneName.toString())) {
            throw new IllegalValueException(PhoneName.MESSAGE_CONSTRAINTS);
        }
        final PhoneName modelPhoneName = new PhoneName(phoneName.toString());

        if (brand == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Brand.class.getSimpleName()));
        }
        if (!Brand.isValidBrand(brand.toString())) {
            throw new IllegalValueException(Brand.MESSAGE_CONSTRAINTS);
        }
        final Brand modelBrand = new Brand(brand.toString());

        final Capacity modelCapacity = capacity;

        if (colour == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Colour.class.getSimpleName()));
        }
        if (!Colour.isValidColour(colour.toString())) {
            throw new IllegalValueException(Colour.MESSAGE_CONSTRAINTS);
        }
        final Colour modelColour = new Colour(colour.toString());

        if (cost == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Cost.class.getSimpleName()));
        }
        if (!Cost.isValidCost(cost.toString())) {
            throw new IllegalValueException(Cost.MESSAGE_CONSTRAINTS);
        }
        final Cost modelCost = new Cost(cost.toString());

        final Set<Tag> modelTags = new HashSet<>(phoneTags);
        return new Phone(modelId, modelPhoneName, modelBrand, modelCapacity, modelColour, modelCost, modelTags);
    }

}
