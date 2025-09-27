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

import walkingkooka.environment.EnvironmentValueName;
import walkingkooka.net.email.EmailAddress;
import walkingkooka.storage.Storage;
import walkingkooka.tree.expression.FakeExpressionEvaluationContext;

import java.util.Locale;
import java.util.Optional;
import java.util.Set;

public class FakeStorageExpressionEvaluationContext extends FakeExpressionEvaluationContext implements StorageExpressionEvaluationContext {

    public FakeStorageExpressionEvaluationContext() {
        super();
    }

    @Override
    public Storage<StorageExpressionEvaluationContext> storage() {
        throw new UnsupportedOperationException();
    }

    // StorageContext...................................................................................................

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
    public <T> Optional<T> environmentValue(final EnvironmentValueName<T> environmentValueName) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<EnvironmentValueName<?>> environmentValueNames() {
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

    @Override
    public Optional<EmailAddress> user() {
        throw new UnsupportedOperationException();
    }
}
