package greeting

import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import org.jetbrains.jewel.bridge.addComposeTab
import org.jetbrains.jewel.ui.component.DefaultButton
import org.jetbrains.jewel.ui.component.Text

class GreetingToolWindowFactory : ToolWindowFactory {
    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val counterStateService = project.service<GreetingCounterStateService>()
        toolWindow.addComposeTab {
            DefaultButton(onClick = { counterStateService.counter++ }) {
                Text("Click me!! ${counterStateService.counter}")
            }
        }
    }
}
