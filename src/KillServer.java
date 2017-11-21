import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.application.ApplicationManager;

/**
 * Created by zhangshengli on 2017/9/29.
 */
public class KillServer extends AnAction {

    @Override
    public void actionPerformed(final AnActionEvent e) {
        ApplicationManager.getApplication().executeOnPooledThread(new Runnable() {
            public void run() {
                Command.kill(e.getProject());
            }
        });
    }
}
