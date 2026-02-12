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

import walkingkooka.reflect.PublicStaticHelper;
import walkingkooka.storage.StoragePath;
import walkingkooka.storage.StorageValueInfoList;
import walkingkooka.tree.expression.function.ExpressionFunction;

public final class StorageExpressionFunctions implements PublicStaticHelper {

    /**
     * {@see StorageExpressionFunctionGetCurrentWorkingDirectory}
     */
    public static <C extends StorageExpressionEvaluationContext> ExpressionFunction<StoragePath, C> getCurrentWorkingDirectory() {
        return StorageExpressionFunctionGetCurrentWorkingDirectory.instance();
    }

    /**
     * {@see StorageExpressionFunctionReadText}
     */
    public static <C extends StorageExpressionEvaluationContext> ExpressionFunction<String, C> storageReadText() {
        return StorageExpressionFunctionReadText.instance();
    }

    /**
     * {@see StorageExpressionFunctionDelete}
     */
    public static <C extends StorageExpressionEvaluationContext> ExpressionFunction<Void, C> storageDelete() {
        return StorageExpressionFunctionDelete.instance();
    }

    /**
     * {@see StorageExpressionFunctionList}
     */
    public static <C extends StorageExpressionEvaluationContext> ExpressionFunction<StorageValueInfoList, C> storageList() {
        return StorageExpressionFunctionList.instance();
    }

    /**
     * {@see StorageExpressionFunctionWriteText}
     */
    public static <C extends StorageExpressionEvaluationContext> ExpressionFunction<Void, C> storageWriteText() {
        return StorageExpressionFunctionWriteText.instance();
    }

    /**
     * Stop creation
     */
    private StorageExpressionFunctions() {
        throw new UnsupportedOperationException();
    }
}
