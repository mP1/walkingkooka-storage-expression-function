/*
 * Copyright © 2025 Miroslav Pokorny
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
 */
package test;


import com.google.j2cl.junit.apt.J2clTestInput;
import org.junit.Assert;
import org.junit.Test;

import walkingkooka.net.email.EmailAddress;
import walkingkooka.storage.FakeStorageStoreContext;
import walkingkooka.storage.StoragePath;
import walkingkooka.storage.StorageStore;
import walkingkooka.storage.StorageStores;
import walkingkooka.storage.StorageValue;

import java.time.LocalDateTime;
import java.util.Optional;

@J2clTestInput(J2clTest.class)
public class J2clTest {

    @Test
    public void testStorageWriteRead() {
        final StorageStore store = StorageStores.tree(
            new FakeStorageStoreContext() {
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
        Assert.assertEquals(
                expected,
                actual
        );
    }
}
