package io.github.leeonardoo.jetmarket.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.insets.ui.TopAppBar
import io.github.leeonardoo.jetmarket.R

/**
 * A custom implementation of a toolbar "menu" similar to how it is done using XML and Views, while
 * we still don't have that functionality officially.
 *
 * The code can be found at a gist from MachFour
 * @see <a href="https://gist.github.com/MachFour/369ebb56a66e2f583ebfb988dda2decf">Original Gist</a>
 */

// Kind of equivalent to a menu XML entry, except for the onClick lambda
data class ActionItemSpec(
    val modifier: Modifier = Modifier,
    val name: String,
    val painter: Painter,
    val visibility: ActionItemMode = ActionItemMode.IF_ROOM,
    val onClick: () -> Unit
) {
    companion object {
        @Composable
        fun fromImageVector(
            modifier: Modifier = Modifier,
            name: String,
            imageVector: ImageVector,
            visibility: ActionItemMode = ActionItemMode.IF_ROOM,
            onClick: () -> Unit
        ): ActionItemSpec = ActionItemSpec(
            modifier,
            name,
            rememberVectorPainter(image = imageVector),
            visibility,
            onClick
        )
    }
}

// Whether to show the action item as an icon or not (or if room)
enum class ActionItemMode {
    ALWAYS_SHOW, IF_ROOM, NEVER_SHOW
}

@Preview
@Composable
fun PreviewActionMenu() {
    val items = listOf(
        ActionItemSpec.fromImageVector(
            name = "Call",
            imageVector = Icons.Default.Call,
            visibility = ActionItemMode.ALWAYS_SHOW
        ) {},
        ActionItemSpec.fromImageVector(
            name = "Send",
            imageVector = Icons.Default.Send,
            visibility = ActionItemMode.IF_ROOM
        ) {},
        ActionItemSpec.fromImageVector(
            name = "Email",
            imageVector = Icons.Default.Email,
            visibility = ActionItemMode.IF_ROOM
        ) {},
        ActionItemSpec.fromImageVector(
            name = "Delete",
            imageVector = Icons.Default.Delete,
            visibility = ActionItemMode.IF_ROOM
        ) {},
    )
    TopAppBar(
        title = { Text("App bar") },
        navigationIcon = {
            IconButton(onClick = {}) {
                Icon(Icons.Default.Menu, "Menu")
            }
        },
        actions = {
            ActionMenu(items, defaultIconSpace = 3)
        }
    )
}


@Composable
fun ActionMenu(
    items: List<ActionItemSpec>,
    defaultIconSpace: Int = 3, // includes overflow menu
    menuExpanded: MutableState<Boolean> = remember { mutableStateOf(false) }
) {
    // decide how many ifRoom icons to show as actions
    val (actionItems, overflowItems) = remember(items, defaultIconSpace) {
        separateIntoActionAndOverflow(items, defaultIconSpace)
    }

    Row {
        for (item in actionItems) {
            IconButton(onClick = item.onClick) {
                Icon(
                    modifier = item.modifier,
                    painter = item.painter,
                    contentDescription = item.name
                )
            }
        }
        if (overflowItems.isNotEmpty()) {
            IconButton(onClick = { menuExpanded.value = true }) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = stringResource(id = R.string.more),
                )
            }
            DropdownMenu(
                expanded = menuExpanded.value,
                onDismissRequest = { menuExpanded.value = false }
            ) {
                for (item in overflowItems) {
                    DropdownMenuItem(onClick = item.onClick) {
                        //Icon(item.icon, item.name) just have text in the overflow menu
                        Text(item.name)
                    }
                }
            }
        }
    }
}

private fun separateIntoActionAndOverflow(
    items: List<ActionItemSpec>,
    defaultIconSpace: Int
): Pair<List<ActionItemSpec>, List<ActionItemSpec>> {
    var (alwaysCount, neverCount, ifRoomCount) = Triple(0, 0, 0)
    for (item in items) {
        when (item.visibility) {
            ActionItemMode.ALWAYS_SHOW -> alwaysCount++
            ActionItemMode.NEVER_SHOW -> neverCount++
            ActionItemMode.IF_ROOM -> ifRoomCount++
        }
    }

    val needsOverflow = alwaysCount + ifRoomCount > defaultIconSpace || neverCount > 0
    val actionIconSpace = defaultIconSpace - (if (needsOverflow) 1 else 0)

    val actionItems = ArrayList<ActionItemSpec>()
    val overflowItems = ArrayList<ActionItemSpec>()

    var ifRoomsToDisplay = actionIconSpace - alwaysCount
    for (item in items) {
        when (item.visibility) {
            ActionItemMode.ALWAYS_SHOW -> {
                actionItems.add(item)
            }
            ActionItemMode.NEVER_SHOW -> {
                overflowItems.add(item)
            }
            ActionItemMode.IF_ROOM -> {
                if (ifRoomsToDisplay > 0) {
                    actionItems.add(item)
                    ifRoomsToDisplay--
                } else {
                    overflowItems.add(item)
                }

            }
        }
    }
    return Pair(actionItems, overflowItems)
}