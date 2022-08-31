package org.incendo.interfaces.next.view

import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder
import org.incendo.interfaces.next.interfaces.ChestInterface
import org.incendo.interfaces.next.inventory.ChestInterfacesInventory
import org.incendo.interfaces.next.pane.ChestPane
import org.incendo.interfaces.next.utilities.TitleState
import org.incendo.interfaces.next.utilities.runSync

public class ChestInterfaceView(
    player: Player,
    backing: ChestInterface
) : InterfaceView<ChestInterfacesInventory, ChestPane>(
    player,
    backing
),
    InventoryHolder {
    private val titleState = TitleState(backing.initialTitle)

    override fun createInventory(): ChestInterfacesInventory = ChestInterfacesInventory(
        this,
        titleState.current,
        backing.rows
    )

    override fun openInventory() {
        titleState.hasChanged = false

        runSync {
            player.openInventory(this.inventory)
        }
    }

    override fun requiresPlayerUpdate(): Boolean = !firstPaint && titleState.hasChanged

    override fun requiresNewInventory(): Boolean = firstPaint || titleState.hasChanged

    override fun getInventory(): Inventory = currentInventory.chestInventory
}
