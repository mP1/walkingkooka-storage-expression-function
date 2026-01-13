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

import walkingkooka.storage.StoragePath;
import walkingkooka.storage.StorageValue;
import walkingkooka.storage.expression.function.FakeStorageExpressionEvaluationContext;
import walkingkooka.storage.expression.function.StorageExpressionEvaluationContext;

import java.time.LocalDateTime;
import java.util.Optional;

@J2clTestInput(J2clTest.class)
public class J2clTest {

    @Test
    @Test
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
        Assert.assertEquals(
                expected,
                actual
        );
    }
}
