package seedu.address.storage;

import java.util.*;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.customer.Customer;
import seedu.address.model.order.Order;
import seedu.address.model.order.Price;
import seedu.address.model.order.Status;
import seedu.address.model.phone.Phone;
import seedu.address.model.schedule.Schedule;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Order}.
 */
class JsonAdaptedOrder {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Order's %s field is missing!";

    private final UUID id;
    private final Customer customer;
    private final Phone phone;
    private final Price price;
    private final Status status;
    private final Schedule schedule;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedOrder} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedOrder(@JsonProperty("id") UUID id, @JsonProperty("customer") Customer customer,
                             @JsonProperty("phone") Phone phone,
                             @JsonProperty("price") Price price, @JsonProperty("status") Status status,
                             @JsonProperty("schedule") Schedule schedule,
                             @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.id = id;
        this.customer = customer;
        this.phone = phone;
        this.price = price;
        this.status = status;
        this.schedule = schedule;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedOrder(Order source) {
        id = source.getId();
        customer = source.getCustomer();
        phone = source.getPhone();
        price = source.getPrice();
        status = source.getStatus();
        schedule = source.getSchedule();
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Order toModelType() throws IllegalValueException, CloneNotSupportedException {
        final List<Tag> orderTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            orderTags.add(tag.toModelType());
        }

        final UUID modelId = id;
        final Customer modelCustomer = (Customer) customer.clone();
        final Phone modelPhone = (Phone) phone.clone();
        final Price modelPrice = new Price(price.getValue());
        final Status modelStatus = status;
        final Schedule modelSchedule = new Schedule(schedule.getOrder(), schedule.getCalendar(), schedule.getVenue(),
                schedule.getTags());
        final Set<Tag> modelTags = new HashSet<>(orderTags);
        return new Order(modelId, modelCustomer, modelPhone, modelPrice, modelStatus, modelSchedule, modelTags);
    }

}
