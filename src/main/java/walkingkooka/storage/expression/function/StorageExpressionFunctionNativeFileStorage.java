package walkingkooka.storage.expression.function;

import javaemul.internal.annotations.GwtIncompatible;
import walkingkooka.Cast;
import walkingkooka.collect.list.Lists;
import walkingkooka.storage.Storage;
import walkingkooka.storage.StorageContext;
import walkingkooka.storage.Storages;
import walkingkooka.tree.expression.function.ExpressionFunctionParameter;
import walkingkooka.tree.expression.function.ExpressionFunctionParameterKind;
import walkingkooka.tree.expression.function.ExpressionFunctionParameterName;

import java.nio.file.Path;
import java.util.List;

/**
 * A function that maps a {@link Path} as a {@link Storages#nativeStorage(Path, StorageContext)}.
 */
@GwtIncompatible
final class StorageExpressionFunctionNativeFileStorage<C extends StorageExpressionEvaluationContext> extends StorageExpressionFunction<C, Storage<C>> {

    /**
     * Type-safe getter.
     */
    static <C extends StorageExpressionEvaluationContext> StorageExpressionFunctionNativeFileStorage<C> instance() {
        return Cast.to(INSTANCE);
    }

    /**
     * Singleton
     */
    private final static StorageExpressionFunctionNativeFileStorage<?> INSTANCE = new StorageExpressionFunctionNativeFileStorage<>();

    private StorageExpressionFunctionNativeFileStorage() {
        super("nativeFileStorage");
    }

    @Override
    public Class<Storage<C>> returnType() {
        return Cast.to(Storage.class);
    }

    final static ExpressionFunctionParameter<Path> NIO_PATH = ExpressionFunctionParameterName.with("path")
        .required(Path.class)
        .setKinds(ExpressionFunctionParameterKind.CONVERT_EVALUATE);

    private final static List<ExpressionFunctionParameter<?>> PARAMETERS = Lists.of(NIO_PATH);

    @Override
    public List<ExpressionFunctionParameter<?>> parameters(final int count) {
        return PARAMETERS;
    }

    @Override
    public Storage<C> apply(final List<Object> parameters,
                            final C context) {
        final Path path = NIO_PATH.getOrFail(
            parameters,
            0
        );

        return Storages.nativeStorage(
            path,
            context
        );
    }
}
