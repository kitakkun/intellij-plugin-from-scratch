package greeting

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import com.intellij.openapi.components.Service

@Service(Service.Level.APP)
class GreetingCounterStateService {
    var counter by mutableIntStateOf(0)
}
