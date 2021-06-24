package dev.kscott.interfaces.paper.element;

import dev.kscott.interfaces.core.element.Element;
import dev.kscott.interfaces.paper.pane.ChestPane;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * Holds an {@link ItemStack} in an element.
 *
 * @see ChestPane
 */
public class ItemStackElement implements Element {

    /**
     * Returns an empty {@code ItemStackElement}.
     *
     * @return an empty {@code ItemStackElement}.
     */
    public static @NonNull ItemStackElement empty() {
        return new ItemStackElement(new ItemStack(Material.AIR));
    }

    /**
     * Returns an {@code ItemStackElement} with the provided ItemStack.
     *
     * @param itemStack the ItemStack
     * @return the element
     */
    public static @NonNull ItemStackElement of(final @NonNull ItemStack itemStack) {
        return new ItemStackElement(itemStack);
    }
    /**
     * The {@link ItemStack}.
     */
    private final @NonNull ItemStack itemStack;

    /**
     * Constructs {@code ItemStackElement}.
     *
     * @param itemStack the {@link ItemStack}
     */
    public ItemStackElement(final @NonNull ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    /**
     * Returns this element's {@link ItemStack}.
     *
     * @return the {@link ItemStack}
     */
    public @NonNull ItemStack itemStack() {
        return this.itemStack;
    }

}
