package seedu.address.ui.nodes.order;

import seedu.address.model.order.Order;
import seedu.address.ui.Node;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class OrderCustomerIndexNode extends Node<Order> {

    public OrderCustomerIndexNode(List<Order> backingList) {
        super(backingList);
    }

    @Override
    public SortedSet<String> getValues() {
        SortedSet<String> values = new TreeSet<>();
        int minIndex = 1;
        int maxIndex = backingList.size();
        values.add(String.valueOf(minIndex));
        values.add(String.valueOf(maxIndex));
        return values;
    }

}
