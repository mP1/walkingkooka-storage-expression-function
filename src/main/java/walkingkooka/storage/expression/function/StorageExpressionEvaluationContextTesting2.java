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

package walkingkooka.storage.expression.function;

import org.junit.jupiter.api.Test;
import walkingkooka.storage.StorageEnvironmentContextTesting;
import walkingkooka.storage.StoragePath;
import walkingkooka.storage.convert.StorageConverterContextTesting;
import walkingkooka.tree.expression.ExpressionEvaluationContextTesting;

import static org.junit.jupiter.api.Assertions.assertThrows;

public interface StorageExpressionEvaluationContextTesting2<C extends StorageExpressionEvaluationContext> extends ExpressionEvaluationContextTesting<C>,
    StorageExpressionEvaluationContextTesting,
    StorageEnvironmentContextTesting<C>,
    StorageConverterContextTesting<C> {

    @Test
    @Override
    default void testSetLocaleWithNullFails() {
        ExpressionEvaluationContextTesting.super.testSetLocaleWithNullFails();
    }

    // loadStorage......................................................................................................

    @Test
    default void testLoadStorageWithNullIdFails() {
        assertThrows(
            NullPointerException.class,
            () -> this.createContext()
                .loadStorage(null)
        );
    }

    // saveStorage......................................................................................................

    @Test
    default void testSaveStorageWithNullValueFails() {
        assertThrows(
            NullPointerException.class,
            () -> this.createContext()
                .saveStorage(null)
        );
    }

    // deleteStorage....................................................................................................

    @Test
    default void testDeleteStorageWithNullValueFails() {
        assertThrows(
            NullPointerException.class,
            () -> this.createContext()
                .deleteStorage(null)
        );
    }

    // listStorage......................................................................................................

    @Test
    default void testListStorageWithNullParentFails() {
        assertThrows(
            NullPointerException.class,
            () -> this.createContext()
                .listStorage(
                    null,
                    0,
                    0
                )
        );
    }

    @Test
    default void testListStorageWithNegativeOffsetFails() {
        final IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> this.createContext()
                .listStorage(
                    StoragePath.ROOT,
                    -1,
                    1
                )
        );

        this.checkEquals(
            "Invalid offset -1 < 0",
            thrown.getMessage()
        );
    }

    @Test
    default void testListStorageWithNegativeCountFails() {
        final IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> this.createContext()
                .listStorage(
                    StoragePath.ROOT,
                    0,
                    -1
                )
        );

        this.checkEquals(
            "Invalid count -1 < 0",
            thrown.getMessage()
        );
    }

    @Override
    default C createConverterLike() {
        return this.createContext();
    }

    // class............................................................................................................

    @Override
    default String typeNameSuffix() {
        return StorageExpressionEvaluationContext.class.getSimpleName();
    }
}
