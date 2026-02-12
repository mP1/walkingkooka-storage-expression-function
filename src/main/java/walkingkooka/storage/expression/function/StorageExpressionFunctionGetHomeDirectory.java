package walkingkooka.storage.expression.function;

import walkingkooka.Cast;
import walkingkooka.collect.list.Lists;
import walkingkooka.storage.StoragePath;
import walkingkooka.tree.expression.function.ExpressionFunctionParameter;
import walkingkooka.tree.expression.function.ExpressionFunctionParameterKind;
import walkingkooka.tree.expression.function.ExpressionFunctionParameterName;

import java.util.List;

/**
 * A function that returns the home directory {@link StoragePath}.
 */
final class StorageExpressionFunctionGetHomeDirectory<C extends StorageExpressionEvaluationContext> extends StorageExpressionFunction<C, StoragePath> {

    /**
     * Type-safe getter.
     */
    static <C extends StorageExpressionEvaluationContext> StorageExpressionFunctionGetHomeDirectory<C> instance() {
        return Cast.to(INSTANCE);
    }

    /**
     * Singleton
     */
    private final static StorageExpressionFunctionGetHomeDirectory<?> INSTANCE = new StorageExpressionFunctionGetHomeDirectory<>();

    private StorageExpressionFunctionGetHomeDirectory() {
        super("getHomeDirectory");
    }

    @Override
    public Class<StoragePath> returnType() {
        return StoragePath.class;
    }

    @Override
    public StoragePath apply(final List<Object> values,
                             final C context) {
        return HOME_DIRECTORY.get(
            values,
            0
        ).orElseGet(
            () -> context.homeDirectory().orElse(null)
        );
    }

    /**
     * Given the count assembles the parameters with the correct parameter names and types.
     */
    @Override
    public List<ExpressionFunctionParameter<?>> parameters(final int count) {
        List<ExpressionFunctionParameter<?>> parameters;

        switch (count) {
            case 0:
            case 1:
                parameters = PARAMETERS;
                break;
            default:
                throw new IllegalArgumentException("Too many parameters");
        }

        return parameters;
    }

    private final ExpressionFunctionParameter<StoragePath> HOME_DIRECTORY = ExpressionFunctionParameterName.with("homeDirectory")
        .optional(StoragePath.class)
        .setKinds(ExpressionFunctionParameterKind.CONVERT_EVALUATE);

    private final List<ExpressionFunctionParameter<?>> PARAMETERS = Lists.of(HOME_DIRECTORY);
}
