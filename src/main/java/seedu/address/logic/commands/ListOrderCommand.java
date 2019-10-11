package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ORDER;

import seedu.address.model.Model;

/**
 * List all customers in the Order book to the user
 */
public class ListOrderCommand extends Command {

    //to be discussed
    public static final String COMMAND_WORD = "to be confirmed";

    public static final String MESSAGE_SUCCESS = "Listed all current orders";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredOrderList(PREDICATE_SHOW_ALL_ORDER);
        return new CommandResult(MESSAGE_SUCCESS, UiChange.ORDER);
    }
}
