package test;

import com.google.gwt.junit.client.GWTTestCase;

import walkingkooka.net.email.EmailAddress;
import walkingkooka.storage.FakeStorageContext;
import walkingkooka.storage.StoragePath;
import walkingkooka.storage.StorageStore;
import walkingkooka.storage.StorageStores;
import walkingkooka.storage.StorageValue;

import java.time.LocalDateTime;
import java.util.Optional;

@walkingkooka.j2cl.locale.LocaleAware
public class TestGwtTest extends GWTTestCase {

    @Override
    public String getModuleName() {
        return "test.Test";
    }

    public void testAssertEquals() {
        assertEquals(
                1,
                1
        );
    }

    public void testStorageWriteRead() {
        final StorageStore store = StorageStores.tree(
            new FakeStorageContext() {
                @Override
                public LocalDateTime now() {
                    return LocalDateTime.of(1999, 12, 31, 12, 58, 59);
                }

                @Override
                public Optional<EmailAddress> user() {
                    return Optional.of(
                        EmailAddress.parse("user@example.com")
                    );
                }
            }
        );

        final StoragePath path = StoragePath.parse("/dir1/file2.txt");
        final String text = "The quick brown fox jumps over the lazy dog";

        final StorageValue storageValue = StorageValue.with(
            path,
            Optional.of(text)
        );

        store.save(
            storageValue
        );

        checkEquals(
            storageValue,
            store.loadOrFail(path),
            "load " + path
        );
    }

    private void checkEquals(final Object expected,
                             final Object actual,
                             final String message) {
        assertEquals(
                expected,
                actual
        );
    }
}
