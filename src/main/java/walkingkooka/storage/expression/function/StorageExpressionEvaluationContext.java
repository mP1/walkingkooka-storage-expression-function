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
import walkingkooka.storage.StorageContext;
import walkingkooka.text.LineEnding;
import walkingkooka.tree.expression.ExpressionEvaluationContext;

import java.util.Optional;

/**
 * A {@link ExpressionEvaluationContext} that adds a storage getter.
 */
public interface StorageExpressionEvaluationContext extends ExpressionEvaluationContext,
    StorageContext {

    @Override
    StorageExpressionEvaluationContext setLineEnding(final LineEnding lineEnding);

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

    /**
     * Getter that returns the current {@link Storage}
     */
    Storage<StorageExpressionEvaluationContext> storage();
}
