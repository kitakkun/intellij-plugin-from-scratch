package greeting

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import kotlinx.coroutines.flow.distinctUntilChanged
import org.jetbrains.jewel.bridge.addComposeTab
import org.jetbrains.jewel.ui.component.DefaultButton
import org.jetbrains.jewel.ui.component.Text
import org.jetbrains.jewel.ui.component.TextField

class GreetingToolWindowFactory : ToolWindowFactory {
    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val counterStateService = ApplicationManager.getApplication().service<GreetingCounterStateService>()
        val configurationState = ApplicationManager.getApplication().service<GreetingConfigurationState>()
        toolWindow.addComposeTab {
            val incrementByTextFieldState = rememberTextFieldState(configurationState.incrementBy.toString())

            LaunchedEffect(Unit) {
                snapshotFlow { incrementByTextFieldState.text }
                    .distinctUntilChanged()
                    .collect { text ->
                        configurationState.incrementBy = text.toString().toIntOrNull() ?: return@collect
                    }
            }

            Column {
                DefaultButton(
                    onClick = {
                        counterStateService.counter += configurationState.incrementBy
                    }
                ) {
                    Text("Click me!! ${counterStateService.counter}")
                }
                TextField(
                    state = incrementByTextFieldState,
                    placeholder = { Text("Increment by...") },
                    inputTransformation = {
                        if (this.asCharSequence().any { !it.isDigit() }) {
                            this.revertAllChanges()
                        }
                    },
                )
            }
        }
    }
}
