package seedu.address.model.phone;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPhones.IPHONEONE;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PhoneBuilder;

class PhoneTest {

    private static final String VALID_NAME = "iPhone 2";
    private static final String VALID_BRAND = "Huawei";
    private static final String VALID_COLOUR = "Vanta Black";
    private static final String VALID_COST = "$0";
    private static final String VALID_TAG = "1337code";

    @Test
    void isSamePhone() {
        // same object -> returns true
        assertTrue(IPHONEONE.isSamePhone(IPHONEONE));

        // clone -> returns true
        assertTrue(IPHONEONE.isSamePhone(IPHONEONE.clone()));

        // null -> returns false
        assertFalse(IPHONEONE.isSamePhone(null));

        // different id -> returns false
        assertFalse(IPHONEONE.isSamePhone(new PhoneBuilder(IPHONEONE, false).build()));
    }

    @Test
    void testEquals() {
        // same object -> returns true
        assertEquals(IPHONEONE, IPHONEONE);

        // clone -> returns true
        assertEquals(IPHONEONE, IPHONEONE.clone());

        // null -> returns false
        assertNotEquals(null, IPHONEONE);

        // same data fields -> returns true
        assertEquals(IPHONEONE, new PhoneBuilder(IPHONEONE).build());

        // different name -> returns false
        assertNotEquals(IPHONEONE, new PhoneBuilder(IPHONEONE).withName(VALID_NAME).build());

        // different brand -> returns false
        assertNotEquals(IPHONEONE, new PhoneBuilder(IPHONEONE).withBrand(VALID_BRAND).build());

        // different capacity -> returns false
        assertNotEquals(IPHONEONE, new PhoneBuilder(IPHONEONE).withCapacity(Capacity.SIZE_8GB).build());

        // different colour -> returns false
        assertNotEquals(IPHONEONE, new PhoneBuilder(IPHONEONE).withColour(VALID_COLOUR).build());

        // different cost -> returns false
        assertNotEquals(IPHONEONE, new PhoneBuilder(IPHONEONE).withCost(VALID_COST).build());

        // different tags -> returns false
        assertNotEquals(IPHONEONE, new PhoneBuilder(IPHONEONE).withTags(VALID_TAG).build());
    }
}
