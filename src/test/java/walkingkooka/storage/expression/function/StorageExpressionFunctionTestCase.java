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

import walkingkooka.environment.EnvironmentContext;
import walkingkooka.environment.EnvironmentContextDelegator;
import walkingkooka.environment.EnvironmentContexts;
import walkingkooka.environment.EnvironmentValueName;
import walkingkooka.net.email.EmailAddress;
import walkingkooka.storage.Storage;
import walkingkooka.storage.StorageContext;
import walkingkooka.storage.StoragePath;
import walkingkooka.storage.StorageValue;
import walkingkooka.storage.StorageValueInfo;
import walkingkooka.storage.expression.function.StorageExpressionFunctionTestCase.TestStorageExpressionEvaluationContext;
import walkingkooka.text.LineEnding;
import walkingkooka.tree.expression.function.ExpressionFunctionTesting;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

public abstract class StorageExpressionFunctionTestCase<F extends StorageExpressionFunction<TestStorageExpressionEvaluationContext, T>, T> implements ExpressionFunctionTesting<F, T, TestStorageExpressionEvaluationContext> {

    @Override
    public final void testTypeNaming() {
        throw new UnsupportedOperationException();
    }

    static class TestStorageExpressionEvaluationContext extends FakeStorageExpressionEvaluationContext implements StorageContext,
        EnvironmentContextDelegator {

        TestStorageExpressionEvaluationContext(final Storage<TestStorageExpressionEvaluationContext> storage) {
            super();
            this.storage = storage;
        }

        @Override
        public final Optional<StorageValue> loadStorage(final StoragePath path) {
            return this.storage.load(
                path,
                this
            );
        }

        @Override
        public final StorageValue saveStorage(final StorageValue value) {
            return this.storage.save(
                value,
                this
            );
        }

        @Override
        public final void deleteStorage(final StoragePath path) {
            this.storage.delete(
                path,
                this
            );
        }

        @Override
        public final List<StorageValueInfo> listStorage(final StoragePath parent,
                                                        final int offset,
                                                        final int count) {
            return this.storage.list(
                parent,
                offset,
                count,
                this
            );
        }

        private final Storage<TestStorageExpressionEvaluationContext> storage;

        // EnvironmentContextDelegator..................................................................................

        @Override
        public final TestStorageExpressionEvaluationContext cloneEnvironment() {
            throw new UnsupportedOperationException();
        }

        @Override
        public final TestStorageExpressionEvaluationContext setEnvironmentContext(final EnvironmentContext context) {
            throw new UnsupportedOperationException();
        }

        @Override
        public TestStorageExpressionEvaluationContext setLineEnding(final LineEnding lineEnding) {
            throw new UnsupportedOperationException();
        }

        @Override
        public TestStorageExpressionEvaluationContext setLocale(final Locale locale) {
            throw new UnsupportedOperationException();
        }

        @Override
        public final TestStorageExpressionEvaluationContext setUser(final Optional<EmailAddress> user) {
            throw new UnsupportedOperationException();
        }

        @Override
        public <T> TestStorageExpressionEvaluationContext setEnvironmentValue(final EnvironmentValueName<T> name,
                                                                              final T reference) {
            throw new UnsupportedOperationException();
        }

        @Override
        public final TestStorageExpressionEvaluationContext removeEnvironmentValue(final EnvironmentValueName<?> name) {
            throw new UnsupportedOperationException();
        }

        @Override
        public final EnvironmentContext environmentContext() {
            return EnvironmentContexts.fake();
        }
    }
}
