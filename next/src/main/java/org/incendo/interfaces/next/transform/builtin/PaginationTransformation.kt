package org.incendo.interfaces.next.transform.builtin

import org.incendo.interfaces.next.element.Element
import org.incendo.interfaces.next.grid.GridPositionGenerator
import org.incendo.interfaces.next.pane.Pane
import org.incendo.interfaces.next.properties.Trigger
import org.incendo.interfaces.next.view.InterfaceView
import kotlin.properties.Delegates

public open class PaginationTransformation<P : Pane>(
    private val positionGenerator: GridPositionGenerator,
    default: Collection<Element>,
    back: PaginationButton,
    forward: PaginationButton,
    extraTriggers: Array<Trigger> = emptyArray()
) : PagedTransformation<P>(back, forward, extraTriggers) {

    private val values by Delegates.observable(default.toList()) { _, _, _ ->
        boundPage.max = maxPages()
    }

    init {
        boundPage.max = maxPages()
    }

    override suspend fun invoke(pane: P, view: InterfaceView) {
        val positions = positionGenerator.generate()
        val slots = positions.size

        val offset = page * slots

        positions.forEachIndexed { index, point ->
            val currentIndex = index + offset

            if (currentIndex >= values.size) {
                return@forEachIndexed
            }

            pane[point] = values[currentIndex]
        }

        super.invoke(pane, view)
    }

    private fun maxPages(): Int {
        return values.size.floorDiv(positionGenerator.generate().size)
    }
}
