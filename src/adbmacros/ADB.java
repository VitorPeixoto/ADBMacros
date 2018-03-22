package adbmacros;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vitor
 */
public class ADB {
    private final Runtime runtime = Runtime.getRuntime();
    private InputStream       inputStream       = null;
    private InputStreamReader inputStreamReader = null;
    private BufferedReader    bufferedReader    = null;
    
    private ADB() {}
    
    public String ls(String path, String complement) {
        Process p = run("cd "+path+" && ls "+complement);
        return processOutput(p) + processErrors(p);
    }
    
    public String ls(String complement) {
        Process p = run("ls"+" "+complement);
        return processOutput(p) + processErrors(p);
    }
    
    public String kill(String pid, String complement) {
        Process p = run("kill "+pid+" "+complement);
        return processOutput(p);
    }
    
    public String processOutput(Process p) {
        String output = "", line = "";
        try {            
            inputStream       = p.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream);
            bufferedReader    = new BufferedReader(inputStreamReader);
            while ((line = bufferedReader.readLine()) != null) {
                if(!line.isEmpty()) output += line + "\n";
            }    
        } catch (IOException ex) {
            Logger.getLogger(ADB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return output;
    }
    
    public String processErrors(Process p) {
        String errors = "";
        try {
            InputStream error = p.getErrorStream();
            for (int i = 0; i < error.available(); i++) {
                errors += error.read();
            }    } catch (IOException ex) {
            Logger.getLogger(ADB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return errors;
    }
    
    public String processOutput(Process p, int numberOfLines) {
        String output = "", line = "";
        try {            
            inputStream       = p.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream);
            bufferedReader    = new BufferedReader(inputStreamReader);
            while ((line = bufferedReader.readLine()) != null && numberOfLines > 0) {
                if(!line.isEmpty()) output += line + "\n";
                numberOfLines--;
            }    
        } catch (IOException ex) {
            Logger.getLogger(ADB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return output;
    }
    
    public Process run(String command) {
        Process p = null;
        try {
            p = runtime.exec("adb shell \""+command+"\"");
        } catch (IOException ex) {
            Logger.getLogger(ADB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }
    
    public static ADB getInstance() {
        return ADBHolder.INSTANCE;
    }
    
    private static class ADBHolder {
        private static final ADB INSTANCE = new ADB();
    }
}
