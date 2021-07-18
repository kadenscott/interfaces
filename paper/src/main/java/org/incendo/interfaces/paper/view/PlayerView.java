package org.incendo.interfaces.paper.view;

import org.bukkit.Bukkit;
import org.bukkit.inventory.InventoryHolder;
import org.incendo.interfaces.core.pane.Pane;
import org.incendo.interfaces.core.view.InterfaceView;
import org.incendo.interfaces.paper.PlayerViewer;

/**
 * An InterfaceView containing a Bukkit inventory.
 *
 * @param <T> the pane type
 */
public interface PlayerView<T extends Pane> extends InterfaceView<T, PlayerViewer>,
        InventoryHolder {

    /**
     * Emits a {@link ViewOpenEvent} for this view
     */
    default void emitEvent() {
        Bukkit.getPluginManager().callEvent(new ViewOpenEvent(this));
    }

}
