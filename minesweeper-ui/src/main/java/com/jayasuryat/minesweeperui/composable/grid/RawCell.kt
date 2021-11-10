package com.jayasuryat.minesweeperui.composable.grid

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import com.jayasuryat.minesweeperengine.model.cell.MineCell
import com.jayasuryat.minesweeperengine.model.cell.RawCell.RevealedCell
import com.jayasuryat.minesweeperengine.model.cell.RawCell.UnrevealedCell
import com.jayasuryat.minesweeperui.composable.cell.concealed.ConcealedCell
import com.jayasuryat.minesweeperui.composable.cell.revealed.EmptyCell
import com.jayasuryat.minesweeperui.composable.cell.revealed.MineCell
import com.jayasuryat.minesweeperui.composable.cell.revealed.ValueCell
import com.jayasuryat.minesweeperui.composable.component.LogCompositions
import com.jayasuryat.minesweeperui.composable.util.exhaustive
import com.jayasuryat.minesweeperengine.model.cell.RawCell as RawCellData

@Composable
internal fun RawCell(
    modifier: Modifier,
    cell: RawCellData,
) {

    LogCompositions(name = "RawCell")

    Box(
        modifier = modifier
            .clipToBounds(),
    ) {

        when (cell) {

            is UnrevealedCell -> ConcealedCell(modifier = Modifier.fillMaxSize())

            is RevealedCell -> {

                when (val revealedCell = cell.cell) {

                    is MineCell.EmptyCell -> EmptyCell(modifier = Modifier.fillMaxSize())

                    is MineCell.Mine -> MineCell(modifier = Modifier.fillMaxSize())

                    is MineCell.Cell -> ValueCell(
                        modifier = Modifier.fillMaxSize(),
                        cell = revealedCell,
                    )

                }.exhaustive
            }

        }.exhaustive
    }
}