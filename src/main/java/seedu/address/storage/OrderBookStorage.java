package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyOrderBook;

/**
 * Represents a storage for {@link seedu.address.model.OrderBook}.
 */
public interface OrderBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getOrderBookFilePath();

    /**
     * Returns OrderBook data as a {@link seedu.address.model.ReadOnlyOrderBook}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyOrderBook> readOrderBook() throws DataConversionException, IOException;

    /**
     * @see #getOrderBookFilePath()
     */
    Optional<ReadOnlyOrderBook> readOrderBook(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyOrderBook} to the storage.
     * @param orderBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveOrderBook(ReadOnlyOrderBook orderBook) throws IOException;

    /**
     * @see #saveOrderBook(ReadOnlyOrderBook)
     */
    void saveOrderBook(ReadOnlyOrderBook addressBook, Path filePath) throws IOException;

}