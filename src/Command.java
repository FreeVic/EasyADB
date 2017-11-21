import com.intellij.openapi.project.Project;
import com.intellij.openapi.projectRoots.Sdk;
import org.jetbrains.android.sdk.AndroidSdkUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.List;

/**
 * Created by zhangshengli on 2017/9/29.
 */
public class Command {
    private  static void exeCmd(String commandStr) {
        BufferedReader br = null;
        try {
            Process p = Runtime.getRuntime().exec(commandStr);
            br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = null;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            System.out.println(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally
        {
            if (br != null)
            {
                try {
                    br.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void reconnectADB(Project project) {
        exeCmd(getADBPath(project)+"reconnect");
    }

    public static void restartADB(Project project){
        StringBuilder sb = new StringBuilder();
        sb.append(getADBPath(project)+"kill-server").append("\n");
        sb.append(getADBPath(project)+"devices");
        exeCmd(sb.toString());
    }

    public static void kill(Project project){
        exeCmd(getADBPath(project)+"shell am start com.eclite.activity/.MainActivity");
    }

    private static String getADBPath(Project project){
        File adb = AndroidSdkUtils.getAdb(project);
        Collection<String> sdkPaths = AndroidSdkUtils.getAndroidSdkPathsFromExistingPlatforms();
        List<Sdk> allAndroidSdks = AndroidSdkUtils.getAllAndroidSdks();
        System.out.println(sdkPaths);
        System.out.println(allAndroidSdks);
        if(adb!=null){
            return adb.getAbsolutePath()+" ";
        }
        return "adb ";
    }
}
