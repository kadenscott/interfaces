package org.incendo.interfaces.next

import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.plugin.Plugin
import org.incendo.interfaces.next.click.ClickContext
import org.incendo.interfaces.next.click.ClickHandler
import org.incendo.interfaces.next.click.CompletableClickHandler
import org.incendo.interfaces.next.grid.GridPoint
import org.incendo.interfaces.next.view.InterfaceView

public class InterfacesListeners : Listener {

    public companion object {
        public fun install(plugin: Plugin) {
            Bukkit.getPluginManager().registerEvents(InterfacesListeners(), plugin)
        }
    }

    @EventHandler
    public fun onClose(event: InventoryCloseEvent) {
        val holder = event.inventory.holder

        if (holder !is InterfaceView<*>) {
            return
        }

        holder.close()
    }

    @EventHandler
    public fun onClick(event: InventoryClickEvent) {
        val holder = event.inventory.holder

        if (holder !is InterfaceView<*>) {
            return
        }

        if (holder.isProcessingClick) {
            event.isCancelled = true
            return
        }

        holder.isProcessingClick = true

        val player = event.whoClicked as Player
        val bukkitIndex = event.slot
        val clickedPoint = GridPoint.at(bukkitIndex / 9, bukkitIndex % 9)

        val clickContext = ClickContext(player, holder, event.click)

        holder.backing.clickPreprocessors
            .forEach { handler -> ClickHandler.process(handler, clickContext) }

        val clickHandler = holder.pane[clickedPoint]
            ?.clickHandler() ?: ClickHandler.EMPTY

        val completedClickHandler = clickHandler
            .run { CompletableClickHandler().apply { handle(clickContext) } }
            .onComplete { holder.isProcessingClick = false }

        if (!completedClickHandler.completingLater) {
            completedClickHandler.complete()
        }

        event.isCancelled = completedClickHandler.cancelled
    }
}
