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
import walkingkooka.environment.EnvironmentValueName;
import walkingkooka.net.email.EmailAddress;
import walkingkooka.storage.StoragePath;
import walkingkooka.storage.StorageValue;
import walkingkooka.storage.StorageValueInfo;
import walkingkooka.text.LineEnding;
import walkingkooka.tree.expression.FakeExpressionEvaluationContext;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class FakeStorageExpressionEvaluationContext extends FakeExpressionEvaluationContext implements StorageExpressionEvaluationContext {

    public FakeStorageExpressionEvaluationContext() {
        super();
    }

    @Override
    public Optional<StorageValue> loadStorage(final StoragePath path) {
        throw new UnsupportedOperationException();
    }

    @Override
    public StorageValue saveStorage(final StorageValue value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteStorage(final StoragePath path) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<StorageValueInfo> listStorage(final StoragePath parent,
                                              final int offset,
                                              final int count) {
        throw new UnsupportedOperationException();
    }

    // StorageContext...................................................................................................

    @Override
    public StorageExpressionEvaluationContext setLineEnding(final LineEnding lineEnding) {
        throw new UnsupportedOperationException();
    }

    @Override
    public StorageExpressionEvaluationContext setLocale(final Locale locale) {
        throw new UnsupportedOperationException();
    }

    @Override
    public StorageExpressionEvaluationContext setUser(final Optional<EmailAddress> user) {
        throw new UnsupportedOperationException();
    }

    @Override
    public FakeStorageExpressionEvaluationContext cloneEnvironment() {
        throw new UnsupportedOperationException();
    }

    @Override
    public StorageExpressionEvaluationContext setEnvironmentContext(final EnvironmentContext environmentContext) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> FakeStorageExpressionEvaluationContext setEnvironmentValue(final EnvironmentValueName<T> name,
                                                                          final T reference) {
        throw new UnsupportedOperationException();
    }

    @Override
    public FakeStorageExpressionEvaluationContext removeEnvironmentValue(final EnvironmentValueName<?> name) {
        throw new UnsupportedOperationException();
    }
}
