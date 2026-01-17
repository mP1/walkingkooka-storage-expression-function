package test;

import com.google.gwt.junit.client.GWTTestCase;

import walkingkooka.storage.StoragePath;
import walkingkooka.storage.StorageValue;
import walkingkooka.storage.expression.FakeStorageExpressionEvaluationContext;
import walkingkooka.storage.expression.StorageExpressionEvaluationContext;

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

    public void testLoadStorage() {
        final StoragePath path = StoragePath.parse("/hello");

        final Optional<StorageValue> storageValue = Optional.of(
            StorageValue.with(
                path,
                Optional.of(1)
            )
        );

        final StorageExpressionEvaluationContext context = new FakeStorageExpressionEvaluationContext() {

            @Override
            public Optional<StorageValue> loadStorage(final StoragePath path) {
                return storageValue;
            }
        };

        checkEquals(
            storageValue,
            context.loadStorage(
                path
            ),
            "loadStorage " + path
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
