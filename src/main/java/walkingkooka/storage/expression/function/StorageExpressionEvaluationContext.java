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
import walkingkooka.storage.Storage;
import walkingkooka.storage.StoragePath;
import walkingkooka.storage.StorageValue;
import walkingkooka.storage.StorageValueInfo;
import walkingkooka.text.LineEnding;
import walkingkooka.tree.expression.ExpressionEvaluationContext;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

/**
 * A {@link ExpressionEvaluationContext} that adds methods that should delegate to an internal {@link Storage}.
 */
public interface StorageExpressionEvaluationContext extends ExpressionEvaluationContext {

    Optional<StorageValue> loadStorage(final StoragePath path);

    StorageValue saveStorage(final StorageValue value);

    void deleteStorage(final StoragePath path);

    /**
     * Gets the {@link StorageValueInfo} for the given range.<br>
     * Conceptually equivalent to getting a directory listing.
     */
    List<StorageValueInfo> listStorage(final StoragePath parent,
                                       final int offset,
                                       final int count);
    // EnvironmentContext...............................................................................................

    @Override
    StorageExpressionEvaluationContext setLineEnding(final LineEnding lineEnding);

    @Override
    StorageExpressionEvaluationContext setLocale(final Locale locale);

    @Override
    StorageExpressionEvaluationContext setUser(final Optional<EmailAddress> user);

    @Override
    StorageExpressionEvaluationContext cloneEnvironment();

    @Override
    StorageExpressionEvaluationContext setEnvironmentContext(final EnvironmentContext environmentContext);

    @Override
    <T> StorageExpressionEvaluationContext setEnvironmentValue(final EnvironmentValueName<T> name,
                                                               final T value);

    @Override
    StorageExpressionEvaluationContext removeEnvironmentValue(final EnvironmentValueName<?> name);
}
