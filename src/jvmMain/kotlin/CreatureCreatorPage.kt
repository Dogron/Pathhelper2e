import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun creatureCreatorPage(applicationVM: ApplicationVM) {
    val creatureVM = remember { CreatureVM() }

    MaterialTheme {
        Column(
            Modifier.padding(15.dp)
        ) {
            Row(Modifier.fillMaxWidth()) {
                TextField(
                    value = creatureVM.creatureName.value,
                    onValueChange = { creatureVM.creatureName.value = it },
                    label = { Text("Name") })
                Spacer(Modifier.weight(1f))
                Button(
                    onClick = { applicationVM.page.value = Pages.HomePage },
                    content = { Text("x") },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)
                )
            }

            Row { Text("TRAITS") }

            Row(Modifier.padding(vertical = 3.dp)) {
                dropdownWithColor(creatureVM.creatureRarity, enumValues())
                dropdownWithColor(creatureVM.creatureAlignment, enumValues())
                dropdownWithColor(creatureVM.creatureSize, enumValues())
                secondaryTraits(creatureVM.creatureTraits)
            }
        }
    }
}

@Composable
fun <T : ColorDropdownItem> dropdownWithColor(
    dropdownList: MutableState<T>, values: Array<T>
) {
    var showDropdown by remember { mutableStateOf(false) }

    Box(Modifier.wrapContentSize()) {
        Text(
            dropdownList.value.name,
            Modifier.size(180.dp, 55.dp).background(dropdownList.value.color).border(1.dp, Color.Gray),
        )
        Button(onClick = { showDropdown = !showDropdown }, content = { Text("^") },
            modifier = Modifier.align(Alignment.CenterEnd).padding(3.dp)
        )
        DropdownMenu(
            expanded = showDropdown,
            onDismissRequest = { showDropdown = false }) { //TODO resolve dismiss request problem
            values.forEach {
                DropdownMenuItem(onClick = {
                    dropdownList.value = it
                    showDropdown = false
                }) {
                    Text(it.name)
                }
            }
        }
    }
}

@Composable
private fun secondaryTraits(creatureTraits: SnapshotStateList<String>) {
    creatureTraits.forEachIndexed { index, _ ->
        if (index != creatureTraits.size - 1) {
            OutlinedTextField(
                creatureTraits[index], { creatureTraits[index] = it }, modifier = Modifier
                    .width(180.dp)
                    .padding(horizontal = 5.dp)
            )
        } else {
            OutlinedTextField(
                creatureTraits[index], { creatureTraits[index] = it }, modifier = Modifier
                    .width(180.dp)
                    .padding(start = 5.dp)
            )
        }

    }
    Column(verticalArrangement = Arrangement.Top) {
        Button(
            onClick = { creatureTraits.add("") },
            Modifier.size(27.5f.dp),
            contentPadding = PaddingValues(2.dp),
        ) {
            Text(text = "+", textAlign = TextAlign.Center, fontSize = 7.sp)
        }
        Button(
            onClick = { creatureTraits.removeAt(creatureTraits.size - 1) },
            Modifier.size(27.5f.dp),
            contentPadding = PaddingValues(2.dp),
        ) {
            Text("-", textAlign = TextAlign.Center, fontSize = 7.sp)
        }
    }
}