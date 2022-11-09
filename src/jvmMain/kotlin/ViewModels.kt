import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue

class ApplicationVM {
    val page = mutableStateOf(Pages.HomePage)
}

class CreatureVM {
    val creatureName = mutableStateOf(TextFieldValue(""))
    val creatureRarity = mutableStateOf(Rarities.Common)
    val creatureAlignment = mutableStateOf(Alignment.TN)
    val creatureSize = mutableStateOf(Size.Medium)
    val creatureTraits = mutableStateListOf("")
}

interface ColorDropdownItem {
    val color: Color
    val name: String
}

enum class Alignment(override val color: Color): ColorDropdownItem {
    LG(Color.Green),
    NG(Color.Green),
    CG(Color.Green),
    LN(Color.Green),
    NN(Color.Green),
    TN(Color.Green),
    CN(Color.Green),
    LE(Color.Green),
    NE(Color.Green),
    CE(Color.Green)
}

enum class Size(override val color: Color): ColorDropdownItem {
    Tiny(Color.LightGray),
    Small(Color.LightGray),
    Medium(Color.LightGray),
    Large(Color.LightGray),
    Huge(Color.LightGray),
    Gargantuan(Color.LightGray)
}

enum class Rarities(override val color: Color) : ColorDropdownItem {
    Common(Color.White),
    Uncommon(Color.Yellow),
    Rare(Color.Cyan),
    Unique(Color.Magenta)
}

enum class Pages {
    HomePage, CreatureCreatorPage
}

@Composable
fun navigate(applicationVM: ApplicationVM) {
    when (applicationVM.page.value) {
        Pages.HomePage -> homePage(applicationVM)
        Pages.CreatureCreatorPage -> creatureCreatorPage(applicationVM)
    }
}