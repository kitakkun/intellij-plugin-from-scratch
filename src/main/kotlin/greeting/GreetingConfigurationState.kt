package greeting

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.util.xmlb.XmlSerializerUtil

@Service(Service.Level.APP)
@State(name = "GreetingConfigurationState", storages = [Storage("Greeting.xml")])
class GreetingConfigurationState : PersistentStateComponent<GreetingConfigurationState> {
    var incrementBy: Int by mutableIntStateOf(1)

    override fun getState(): GreetingConfigurationState {
        return this
    }

    override fun loadState(state: GreetingConfigurationState) {
        XmlSerializerUtil.copyBean(state, this)
    }
}
