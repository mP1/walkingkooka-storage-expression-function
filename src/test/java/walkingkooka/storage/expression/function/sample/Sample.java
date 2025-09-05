/*
 * Copyright 2025 Miroslav Pokorny (github.com/mP1)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package walkingkooka.storage.expression.function.sample;

import org.junit.jupiter.api.Test;
import walkingkooka.net.email.EmailAddress;
import walkingkooka.storage.FakeStorageContext;
import walkingkooka.storage.StoragePath;
import walkingkooka.storage.StorageStore;
import walkingkooka.storage.StorageStores;
import walkingkooka.storage.StorageValue;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class Sample {

    public static void main(final String[] args) {
        final Sample sample = new Sample();
        sample.testStorageWriteRead();
    }

    @Test
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

    private static void checkEquals(final Object expected,
                                    final Object actual,
                                    final String message) {
        assertEquals(
            expected,
            actual,
            message
        );
    }
}

