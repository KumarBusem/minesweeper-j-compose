/*
 * Copyright 2021 Jaya Surya Thotapalli
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
 */
package com.jayasuryat.minesweeperengine.model.grid

import androidx.compose.runtime.Immutable
import com.jayasuryat.minesweeperengine.model.block.GridSize
import com.jayasuryat.minesweeperengine.model.block.Position
import com.jayasuryat.minesweeperengine.model.cell.RawCell

@Immutable
internal data class MineGrid(
    override val gridSize: GridSize,
    override val totalMines: Int,
    override val cells: List<List<RawCell>>,
) : Grid {

    override operator fun get(position: Position): RawCell {
        return cells[position.row][position.column]
    }

    override fun getOrNull(position: Position): RawCell? {
        return kotlin.runCatching { get(position) }.getOrNull()
    }
}
