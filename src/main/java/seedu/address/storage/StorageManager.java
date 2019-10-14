package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyDataBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.customer.Customer;
import seedu.address.model.phone.Phone;
import seedu.address.model.schedule.Schedule;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private AddressBookStorage addressBookStorage;
    private CustomerBookStorage customerBookStorage;
    private PhoneBookStorage phoneBookStorage;
    private ScheduleBookStorage scheduleBookStorage;
    private UserPrefsStorage userPrefsStorage;


    public StorageManager(AddressBookStorage addressBookStorage, CustomerBookStorage customerBookStorage,
                          PhoneBookStorage phoneBookStorage, ScheduleBookStorage scheduleBookStorage,
                          UserPrefsStorage userPrefsStorage) {
        super();
        this.addressBookStorage = addressBookStorage;
        this.customerBookStorage = customerBookStorage;
        this.phoneBookStorage = phoneBookStorage;
        this.scheduleBookStorage = scheduleBookStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ AddressBook methods ==============================

    @Override
    public Path getAddressBookFilePath() {
        return addressBookStorage.getAddressBookFilePath();
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook() throws DataConversionException, IOException {
        return readAddressBook(addressBookStorage.getAddressBookFilePath());
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return addressBookStorage.readAddressBook(filePath);
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException {
        saveAddressBook(addressBook, addressBookStorage.getAddressBookFilePath());
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        addressBookStorage.saveAddressBook(addressBook, filePath);
    }

    // ================ CustomerBook methods ==============================

    @Override
    public Path getCustomerBookFilePath() {
        return customerBookStorage.getCustomerBookFilePath();
    }

    @Override
    public Optional<ReadOnlyDataBook<Customer>> readCustomerBook() throws DataConversionException, IOException {
        return readCustomerBook(customerBookStorage.getCustomerBookFilePath());
    }

    @Override
    public Optional<ReadOnlyDataBook<Customer>> readCustomerBook(Path filePath) throws DataConversionException,
            IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return customerBookStorage.readCustomerBook(filePath);
    }

    @Override
    public void saveCustomerBook(ReadOnlyDataBook<Customer> customerBook) throws IOException {
        saveCustomerBook(customerBook, customerBookStorage.getCustomerBookFilePath());
    }

    @Override
    public void saveCustomerBook(ReadOnlyDataBook<Customer> customerBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        customerBookStorage.saveCustomerBook(customerBook, filePath);
    }

    // ================ PhoneBook methods ==============================

    @Override
    public Path getPhoneBookFilePath() {
        return phoneBookStorage.getPhoneBookFilePath();
    }

    @Override
    public Optional<ReadOnlyDataBook<Phone>> readPhoneBook() throws DataConversionException, IOException {
        return readPhoneBook(phoneBookStorage.getPhoneBookFilePath());
    }

    @Override
    public Optional<ReadOnlyDataBook<Phone>> readPhoneBook(Path filePath) throws DataConversionException,
            IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return phoneBookStorage.readPhoneBook(filePath);
    }

    @Override
    public void savePhoneBook(ReadOnlyDataBook<Phone> phoneBook) throws IOException {
        savePhoneBook(phoneBook, phoneBookStorage.getPhoneBookFilePath());
    }

    @Override
    public void savePhoneBook(ReadOnlyDataBook<Phone> phoneBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        phoneBookStorage.savePhoneBook(phoneBook, filePath);
    }

    // ================ PhoneBook methods ==============================

    @Override
    public Path getScheduleBookFilePath() {
        return scheduleBookStorage.getScheduleBookFilePath();
    }

    @Override
    public Optional<ReadOnlyDataBook<Schedule>> readScheduleBook() throws DataConversionException, IOException {
        return readScheduleBook(scheduleBookStorage.getScheduleBookFilePath());
    }

    @Override
    public Optional<ReadOnlyDataBook<Schedule>> readScheduleBook(Path filePath) throws DataConversionException,
            IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return scheduleBookStorage.readScheduleBook(filePath);
    }

    @Override
    public void saveScheduleBook(ReadOnlyDataBook<Schedule> scheduleBook) throws IOException {
        saveScheduleBook(scheduleBook, scheduleBookStorage.getScheduleBookFilePath());
    }

    @Override
    public void saveScheduleBook(ReadOnlyDataBook<Schedule> scheduleBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        scheduleBookStorage.saveScheduleBook(scheduleBook, filePath);
    }
}
