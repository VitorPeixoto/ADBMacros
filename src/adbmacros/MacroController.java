package adbmacros;

import java.util.ArrayList;
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
    
    private final long margin = 4000;
    
    private EventChain mageMission, warriorMission, rogueMission, clericMission, berserkerMission,
                       mageLoot, warriorLoot, rogueLoot, clericLoot, berserkerLoot;
    
    private MacroController() {
        ArrayList<Event> events = new ArrayList<>();
        events.add(new Event("Mage.ev", 1000));
        events.add(new Event("CoralGiant.ev", 1000));
        events.add(new Event("StartMission.ev", 1000));
        mageMission = new EventChain(events, 55000);
        
        events = new ArrayList<>();
        events.add(new Event("Warrior.ev", 1000));
        events.add(new Event("WaterWillow.ev", 1000));
        events.add(new Event("StartMission.ev", 1000));
        warriorMission = new EventChain(events, 55000);
        
        events = new ArrayList<>();
        events.add(new Event("Rogue.ev", 1000));
        events.add(new Event("YarsolGathering.ev", 1000));
        events.add(new Event("StartMission.ev", 1000));
        rogueMission = new EventChain(events, 44000);
        
        events = new ArrayList<>();
        events.add(new Event("Cleric.ev", 1000));
        events.add(new Event("MurlokAssassin.ev", 1000));
        events.add(new Event("StartMission.ev", 1000));
        clericMission = new EventChain(events, 235000);
        
        events = new ArrayList<>();
        events.add(new Event("Beserker.ev", 1000));
        events.add(new Event("MurlockHighpriest.ev", 1000));
        events.add(new Event("StartMission.ev", 1000));
        berserkerMission = new EventChain(events, 325000);

        // LOOT
        events = new ArrayList<>();
        events.add(new Event("Mage.ev", 1000));
        events.add(new Event("BottomCenter.ev", 1000));
        events.add(new Event("BottomCenter.ev", 1000));
        mageLoot = new EventChain(events, 0);
        
        events = new ArrayList<>();
        events.add(new Event("Warrior.ev", 1000));
        events.add(new Event("BottomCenter.ev", 1000));
        events.add(new Event("BottomCenter.ev", 1000));
        warriorLoot = new EventChain(events, 0);
        
        events = new ArrayList<>();
        events.add(new Event("Rogue.ev", 1000));
        events.add(new Event("BottomCenter.ev", 1000));
        events.add(new Event("BottomCenter.ev", 1000));
        rogueLoot = new EventChain(events, 0);
        
        events = new ArrayList<>();
        events.add(new Event("Cleric.ev", 1000));
        events.add(new Event("BottomCenter.ev", 1000));
        events.add(new Event("BottomCenter.ev", 1000));
        clericLoot = new EventChain(events, 0);
        
        events = new ArrayList<>();
        events.add(new Event("Beserker.ev", 1000));
        events.add(new Event("BottomCenter.ev", 1000));
        events.add(new Event("BottomCenter.ev", 1000));
        berserkerLoot = new EventChain(events, 0);
    }
    
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
                runEventChains(mageMission, mageLoot);
                runEventChains(warriorMission, warriorLoot);
                runEventChains(rogueMission, rogueLoot);
                runEventChains(clericMission, clericLoot);
                runEventChains(berserkerMission, berserkerLoot);
                
                /*count += rodaMacro("Mage.ev", 1000);
                count += rodaMacro("CoralGiant.ev", 1000);
                count += rodaMacro("StartMission.ev", 1000);
                
                count += rodaMacro("Warrior.ev", 1000);
                count += rodaMacro("WaterWillow.ev", 1000);
                count += rodaMacro("StartMission.ev", 1000);
                
                count += rodaMacro("Rogue.ev", 1000);
                count += rodaMacro("YarsolGathering.ev", 1000);
                count += rodaMacro("StartMission.ev", 1000);
                
                if(count == 0) {
                    count += rodaMacro("Cleric.ev", 1000);
                    count += rodaMacro("MurlokAssassin.ev", 1000);
                    count += rodaMacro("StartMission.ev", 1000);
                }
                if(count == 0) {
                    count += rodaMacro("Beserker.ev", 1000);
                    count += rodaMacro("MurlockHighpriest.ev", 1000);
                    count += rodaMacro("StartMission.ev", 1000);
                }
                
                Thread.sleep(51000);
                count += 51000;
                
                count += rodaMacro("Mage.ev", 1000);
                count += rodaMacro("BottomCenter.ev", 1000);
                count += rodaMacro("BottomCenter.ev", 1000);
                
                count += rodaMacro("Warrior.ev", 1000);
                count += rodaMacro("BottomCenter.ev", 1000);
                count += rodaMacro("BottomCenter.ev", 1000);
                
                count += rodaMacro("Rogue.ev", 1000);
                count += rodaMacro("BottomCenter.ev", 1000);
                count += rodaMacro("BottomCenter.ev", 1000);

                if(count == 4) {
                    count += rodaMacro("Cleric.ev", 1000);
                    count += rodaMacro("BottomCenter.ev", 1000);
                    count += rodaMacro("BottomCenter.ev", 1000);
                }
                if(count == 5) {
                    count += rodaMacro("Beserker.ev", 1000);
                    count += rodaMacro("BottomCenter.ev", 1000);
                    count += rodaMacro("BottomCenter.ev", 1000);
                }
                
                if(count >= 6) count = 0;
                
                //Thread.sleep(2000);
//                return pid;*/
            } catch (InterruptedException ex) {
                Logger.getLogger(MacroController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void runEventChains(EventChain mission, EventChain loot) throws InterruptedException {
        if(System.currentTimeMillis() - mission.getStartTime() > (mission.getDurationTime() + margin)) {
            if(mission.isStarted()) {
                mission.stop();

                for(Event e : loot.getListOfEvents())
                    rodaMacro(e.getName(), e.getWaitingTime());
            } else {
                mission.start();
                for(Event e : mission.getListOfEvents())
                    rodaMacro(e.getName(), e.getWaitingTime());
            }
        }
    }
    
    public int rodaMacro(String macroName, int miliseconds) throws InterruptedException {
        System.out.println(macroName);
        Process p = adbShell.run("cd "+macroDirectory+" && $$ && i=0; cat "+macroName+" > /dev/input/event2;");
        this.pid  = adbShell.processOutput(p, 1).replace("/system/bin/sh: ", "").replace(": not found", "").replace("\n", "");
        Thread.sleep(miliseconds);
        adbShell.kill(pid, "");
        
        return miliseconds;
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
