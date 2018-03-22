package adbmacros;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vitor
 */
public class MacroController {
    private String macroDirectory = "/";
    private final ADB adbShell = ADB.getInstance();
    private String pid = "0";
    
    private MacroController() {}    
    
    public String startMacro(String name) {
        
        return this.merchantMacro(name);
        /*if(!pid.equals("0")) stopMacro();
        Process p = adbShell.run("cd "+macroDirectory+" && $$ && i=0; while [ $(($i)) == 0 ]; do cat "+name+" > /dev/input/event2; $i++; done;");
        this.pid  = adbShell.processOutput(p, 1).replace("/system/bin/sh: ", "").replace(": not found", "").replace("\n", "");
        return pid;*/
    }
    
    public String merchantMacro(String name) {
        if(!pid.equals("0")) stopMacro();
        int count = 0;
        while(true) {
            
            try {
                rodaMacro("Mage.ev", 1000);
                rodaMacro("CoralGiant.ev", 1000);
                rodaMacro("StartMission.ev", 1000);
                
                rodaMacro("Warrior.ev", 1000);
                rodaMacro("WaterWillow.ev", 1000);
                rodaMacro("StartMission.ev", 1000);
                
                rodaMacro("Rogue.ev", 1000);
                rodaMacro("YarsolGathering.ev", 1000);
                rodaMacro("StartMission.ev", 1000);
                
                if(count == 0) {
                    rodaMacro("Cleric.ev", 1000);
                    rodaMacro("MurlokAssassin.ev", 1000);
                    rodaMacro("StartMission.ev", 1000);
                }
                if(count == 0) {
                    rodaMacro("Beserker.ev", 1000);
                    rodaMacro("MurlockHighpriest.ev", 1000);
                    rodaMacro("StartMission.ev", 1000);
                }
                
                Thread.sleep(51000);
                
                rodaMacro("Mage.ev", 1000);
                rodaMacro("BottomCenter.ev", 1000);
                rodaMacro("BottomCenter.ev", 1000);
                
                rodaMacro("Warrior.ev", 1000);
                rodaMacro("BottomCenter.ev", 1000);
                rodaMacro("BottomCenter.ev", 1000);
                
                rodaMacro("Rogue.ev", 1000);
                rodaMacro("BottomCenter.ev", 1000);
                rodaMacro("BottomCenter.ev", 1000);

                if(count == 4) {
                    rodaMacro("Cleric.ev", 1000);
                    rodaMacro("BottomCenter.ev", 1000);
                    rodaMacro("BottomCenter.ev", 1000);
                }
                if(count == 5) {
                    rodaMacro("Beserker.ev", 1000);
                    rodaMacro("BottomCenter.ev", 1000);
                    rodaMacro("BottomCenter.ev", 1000);
                }
                
                count++;
                if(count >= 6) count = 0;
                
                //Thread.sleep(2000);
//                return pid;
            } catch (InterruptedException ex) {
                Logger.getLogger(MacroController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void rodaMacro(String macroName, int miliseconds) throws InterruptedException {
        System.out.println(macroName);
        Process p = adbShell.run("cd "+macroDirectory+" && $$ && i=0; cat "+macroName+" > /dev/input/event2;");
        this.pid  = adbShell.processOutput(p, 1).replace("/system/bin/sh: ", "").replace(": not found", "").replace("\n", "");
        Thread.sleep(miliseconds);
        adbShell.kill(pid, "");
    }
    
    public void stopMacro() {
        if(!pid.equals("0")) {
            adbShell.kill(pid, "");
            pid = "0";
        }
    }
    //cd /mnt/sdcard/civCrafter && $$; cat /dev/input/event2 > temp.tev
    public String startRecording() {
        if(!pid.equals("0")) stopMacro();
        Process p = adbShell.run("cd "+macroDirectory+" && $$; cat /dev/input/event2 > temp.tev");
        this.pid  = adbShell.processOutput(p, 1).replace("/system/bin/sh: ", "").replace(": not found", "").replace("\n", "");
        return pid;
    }
    
    public void stopRecording() {
        if(!pid.equals("0")) {
            adbShell.kill(pid, "");
            pid = "0";
        }
    }
    
    public void saveTemporaryMacro(String macroName) {
        adbShell.run("cd "+macroDirectory+"; cp temp.tev "+macroName+".ev");
    }
    
    public String getDirectories() {
        String output = adbShell.ls(macroDirectory, "-d */");
        return (isValid(output) ? output : "");
    }
    
    public String getMacros() {
        String output = adbShell.ls(macroDirectory, "*.ev");
        return (isValid(output) ? output : "");
    }
    
    public String getTemporaryMacro() {
        String output = adbShell.ls(macroDirectory, "*.tev");
        return (isValid(output) ? output : "");
    }
    
    private boolean isValid(String output) {
        return !(output.contains("/system/bin/sh") || output.contains("No such file or directory") || output.contains("Not a directory"));
    }
    
    public String getMacroDirectory() {
        return macroDirectory;
    }

    public void setMacroDirectory(String macroDirectory) {
        this.macroDirectory = macroDirectory;
    }
            
    public static MacroController getInstance() {
        return MacroControllerHolder.INSTANCE;
    }
    
    private static class MacroControllerHolder {
        private static final MacroController INSTANCE = new MacroController();
    }
}
