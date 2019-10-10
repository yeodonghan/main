package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyDataBook;

/**
 * Represents a storage for {@link seedu.address.model.OrderBook}.
 */
public interface OrderBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getOrderBookFilePath();

    /**
     * Returns OrderBook data as a {@link seedu.address.model.ReadOnlyDataBook}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyDataBook> readOrderBook() throws DataConversionException, IOException;

    /**
     * @see #getOrderBookFilePath()
     */
    Optional<ReadOnlyDataBook> readOrderBook(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyDataBook} to the storage.
     * @param orderBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveOrderBook(ReadOnlyDataBook orderBook) throws IOException;

    /**
     * @see #saveOrderBook(ReadOnlyDataBook)
     */
    void saveOrderBook(ReadOnlyDataBook addressBook, Path filePath) throws IOException;

}