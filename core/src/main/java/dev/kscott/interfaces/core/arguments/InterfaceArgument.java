package dev.kscott.interfaces.core.arguments;

import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * Holds arguments passed into an interface.
 */
public interface InterfaceArgument {

    /**
     * Returns the value at the given key.
     *
     * @param key the key
     * @param <T> the value's type
     * @return the value
     */
    <T> T get(final @NonNull String key);

    /**
     * Returns the value at the given key.
     *
     * @param key the key
     * @param <T> the value's type
     * @param def the default object
     * @return the value
     */
    <T> T getOrDefault(final @NonNull String key, final @NonNull T def);

    /**
     * Sets a value of the argument.
     *
     * @param key   the key
     * @param value the value
     */
    void set(final @NonNull String key, final @NonNull Object value);

}
