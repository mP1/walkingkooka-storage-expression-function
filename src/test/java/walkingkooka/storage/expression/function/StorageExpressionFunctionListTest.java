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
import walkingkooka.Cast;
import walkingkooka.collect.list.Lists;
import walkingkooka.environment.AuditInfo;
import walkingkooka.net.email.EmailAddress;
import walkingkooka.storage.FakeStorage;
import walkingkooka.storage.StoragePath;
import walkingkooka.storage.StorageValueInfo;
import walkingkooka.storage.StorageValueInfoList;
import walkingkooka.storage.expression.function.StorageExpressionFunctionTestCase.TestStorageExpressionEvaluationContext;

import java.time.LocalDateTime;
import java.util.List;

public final class StorageExpressionFunctionListTest extends StorageExpressionFunctionTestCase<StorageExpressionFunctionList<TestStorageExpressionEvaluationContext>, StorageValueInfoList> {

    private final static StoragePath PATH = StoragePath.parse("/dir1/");

    private final static StorageValueInfoList LIST = StorageValueInfoList.with(
        Lists.of(
            StorageValueInfo.with(
                StoragePath.parse("/dir1/file1.txt"),
                AuditInfo.create(
                    EmailAddress.parse("user1@example.com"),
                    LocalDateTime.MIN
                )
            ),
            StorageValueInfo.with(
                StoragePath.parse("/dir1/file2.txt"),
                AuditInfo.create(
                    EmailAddress.parse("user2@example.com"),
                    LocalDateTime.MIN
                )
            )
        )
    );

    @Test
    public void testApply() {
        this.applyAndCheck(
            Lists.of(PATH),
            LIST
        );
    }

    @Test
    public void testApplyMissingPath() {
        this.applyAndCheck(
            Lists.empty(),
            LIST
        );
    }

    @Override
    public void testSetParametersSame() {
        throw new UnsupportedOperationException();
    }

    @Override
    public StorageExpressionFunctionList<TestStorageExpressionEvaluationContext> createBiFunction() {
        return StorageExpressionFunctionList.instance();
    }

    @Override
    public TestStorageExpressionEvaluationContext createContext() {
        return new TestStorageExpressionEvaluationContext(
            new FakeStorage<TestStorageExpressionEvaluationContext>() {

                @Override
                public List<StorageValueInfo> list(final StoragePath path,
                                                   final int offset,
                                                   final int count,
                                                   final TestStorageExpressionEvaluationContext context) {
                    return path.equals(PATH) && 0 == offset && Integer.MAX_VALUE == count ?
                        LIST
                        : Lists.of();
                }
            }
        );
    }

    @Override
    public int minimumParameterCount() {
        return 1;
    }

    // toString.........................................................................................................

    @Test
    public void testToString() {
        this.toStringAndCheck(
            StorageExpressionFunctionList.instance(),
            "storageList"
        );
    }

    // class............................................................................................................

    @Override
    public Class<StorageExpressionFunctionList<TestStorageExpressionEvaluationContext>> type() {
        return Cast.to(StorageExpressionFunctionList.class);
    }
}
