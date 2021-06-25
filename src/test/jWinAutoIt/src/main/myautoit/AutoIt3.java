package test.jWinAutoIt.src.main.myautoit;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import test.jWinAutoIt.src.main.myautoit.AutoItX.LPPOINT;
import test.jWinAutoIt.src.main.myutils.ResourceUtil;

//import test.login.jWinAutoIt.src.org.slf4j.Logger;
//import test.login.jWinAutoIt.src.org.slf4j.LoggerFactory;

import com.sun.jna.WString;


public class AutoIt3 implements IAutoIt3 {

   // private static final Logger log = LoggerFactory.getLogger(AutoIt3.class);
    
    private static final String AUTO_IT_DLL_x86 = "AutoItX3.dll";
    private static final String AUTO_IT_DLL_x64 = "AutoItX3_x64.dll";
    
    final private AutoItX autoItLib;
    

    public AutoIt3() throws IOException, IllegalStateException {
        if (ResourceUtil.isWindows()) {
            System.setProperty("jna.encoding", "UTF16");
            
            String filename;
            if (ResourceUtil.is64()) {
     //           log.debug("Loading AutoIt x64");
                filename = ResourceUtil.copyToTemp(AUTO_IT_DLL_x64, AutoIt3.class);
            } else {
       //         log.debug("Loading AutoIt x86");
                filename = ResourceUtil.copyToTemp(AUTO_IT_DLL_x86, AutoIt3.class);
            }
            
            loadDll(filename);
            
            autoItLib = AutoItX.INSTANCE;
        } else {
            throw new IllegalStateException("OS is not Windows. Unable to load AutoIt library.");
        }
    }
    
    protected static String getAutoItLib() {
        if (ResourceUtil.is64()) {
            return AUTO_IT_DLL_x64.replace(".dll", "");
        } else {
            return AUTO_IT_DLL_x86.replace(".dll", "");
        }
    }
    
    
    private void loadDll(String dll) throws IOException {
        try {
          //  log.debug("Loading DLL: {}", dll);
            System.load(dll);
          //  log.info("Loaded DLL: {}", dll);
        } catch (Exception ex) {
            throw new IOException("Failed to load DLL: " + dll, ex);
        }
    }
    
    private WString _w(String s) {
        return new WString(s);
    }
    
    private String byteToString(byte[] byteArray){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int byteCounter = 0;
        while(byteCounter < byteArray.length && byteArray[byteCounter] != 0){
            baos.write(byteArray[byteCounter++]);
        }
        return baos.toString();
    }

    /*
     * =============================================================================================
     *   Implemented Methods
     * =============================================================================================
     */
    
    @Override
    public void init() {
        autoItLib.AU3_Init();
    }

    @Override
    public int error() {
        return autoItLib.AU3_error();
    }

    @Override
    public int autoItSetOption(String option, int value) {
        return autoItLib.AU3_AutoItSetOption(_w(option), value);
    }

    @Override
    public void blockInput(int flag) {
        autoItLib.AU3_BlockInput(flag);
    }

    @Override
    public void cdTray(String drive, String action) {
        autoItLib.AU3_CDTray(_w(drive), _w(action));
    }

    @Override
    public void clipGet(byte[] clip, int bufSize) {
        autoItLib.AU3_ClipGet(clip, bufSize);
    }

    @Override
    public void clipPut(String clip) {
        autoItLib.AU3_ClipPut(_w(clip));
    }

    @Override
    public void controlClick(String title, String text, String control, String button, int numClicks, int x, int y) {
        if (autoItLib.AU3_ControlClick(_w(title), _w(text), _w(control), _w(button), numClicks, x, y) == 0) {
            throw new AutoItException("Failed to click control '"+ control +"' on window '"+ title +"'");
        }
    }

    @Override
    public void controlClick(String title, String text, String control) {
        controlClick(title, text, control, "left", 1, 1, 1);
    }

    @Override
    public void controlCommand(String title, String text, String control, String command, String extra, byte[] result, int bufSize) {
        autoItLib.AU3_ControlCommand(_w(title), _w(text), _w(control), _w(command), _w(extra), result, bufSize);
        
    }

    @Override
    public void controlListView(String title, String text, String control, String command, String extra1, String extra2, byte[] result, int bufSize) {
        autoItLib.AU3_ControlListView(_w(title), _w(text), _w(control), _w(command), _w(extra1), _w(extra2), result, bufSize);
    }

    @Override
    public int controlDisable(String title, String text, String control) {
        return autoItLib.AU3_ControlDisable(_w(title), _w(text), _w(control));
    }

    @Override
    public int controlEnable(String title, String text, String control) {
        return autoItLib.AU3_ControlEnable(_w(title), _w(text), _w(control));
    }

    @Override
    public int controlFocus(String title, String text, String control) {
        return autoItLib.AU3_ControlFocus(_w(title), _w(text), _w(control));
    }

    @Override
    public void controlGetFocus(String title, String text, byte[] controlWithFocus, int bufSize) {
        autoItLib.AU3_ControlGetFocus(_w(title), _w(text), controlWithFocus, bufSize);
    }

    @Override
    public void controlGetHandle(String title, String text, String control, byte[] retText, int bufSize) {
        autoItLib.AU3_ControlGetHandle(_w(title), _w(text), _w(control), retText, bufSize);
    }

    @Override
    public int controlGetPosX(String title, String text, String control) {
        return autoItLib.AU3_ControlGetPosX(_w(title), _w(text), _w(control));
    }

    @Override
    public int controlGetPosY(String title, String text, String control) {
        return autoItLib.AU3_ControlGetPosY(_w(title), _w(text), _w(control));
    }

    @Override
    public int controlGetPosHeight(String title, String text, String control) {
        return autoItLib.AU3_ControlGetPosHeight(_w(title), _w(text), _w(control));
    }

    @Override
    public int controlGetPosWidth(String title, String text, String control) {
        return autoItLib.AU3_ControlGetPosWidth(_w(title), _w(text), _w(control));
    }

    @Override
    public void controlGetText(String title, String text, String control, byte[] controlText, int bufSize) {
        autoItLib.AU3_ControlGetText(_w(title), _w(text), _w(control), controlText, bufSize);
    }
    
    @Override
    public String controlGetText(String title, String text, String control) {
        int bufSize = 2048;
        byte[] bytes = new byte[bufSize];
        controlGetText(title, text, control, bytes, bufSize);
        return byteToString(bytes);
    }

    @Override
    public int controlHide(String title, String text, String control) {
        return autoItLib.AU3_ControlHide(_w(title), _w(text), _w(control));
    }

    @Override
    public int controlMove(String title, String text, String control, int x, int y, int width, int height) {
        return autoItLib.AU3_ControlMove(_w(title), _w(text), _w(control), x, y, width, height);
    }

    @Override
    public int controlSend(String title, String text, String control, String sendText, int mode) {
        return autoItLib.AU3_ControlSend(_w(title), _w(text), _w(control), _w(sendText), mode);
    }

    @Override
    public int controlSend(String title, String text, String control, String sendText) {
        return controlSend(title, text, control, sendText, 0);
    }

    @Override
    public int controlSetText(String title, String text, String control, String controlText) {
        return autoItLib.AU3_ControlSetText(_w(title), _w(text), _w(control), _w(controlText));
    }

    @Override
    public int controlShow(String title, String text, String control) {
        return autoItLib.AU3_ControlShow(_w(title), _w(text), _w(control));
    }

    @Override
    public void driveMapAdd(String device, String share, int flags, String user, String pwd, byte[] result, int bufSize) {
        autoItLib.AU3_DriveMapAdd(_w(device), _w(share), flags, _w(user), _w(pwd), result, bufSize);
    }

    @Override
    public int driveMapDel(String device) {
        return autoItLib.AU3_DriveMapDel(_w(device));
    }

    @Override
    public void driveMapGet(String device, byte[] mapping, int bufSize) {
        autoItLib.AU3_DriveMapGet(_w(device), mapping, bufSize);
    }

    @Override
    public int iniDelete(String filename, String section, String key) {
        return autoItLib.AU3_IniDelete(_w(filename), _w(section), _w(key));
    }

    @Override
    public void iniRead(String filename, String section, String key, String defaultValue, byte[] value, int bufSize) {
        autoItLib.AU3_IniRead(_w(filename), _w(section), _w(key), _w(defaultValue), value, bufSize);
    }

    @Override
    public int iniWrite(String filename, String section, String key, String value) {
        return autoItLib.AU3_IniWrite(_w(filename), _w(section), _w(key), _w(value));
    }

    @Override
    public int isAdmin() {
        return autoItLib.AU3_IsAdmin();
    }

    @Override
    public int mouseClick(String button, int x, int y, int clicks, int speed) {
        return autoItLib.AU3_MouseClick(_w(button), x, y, clicks, speed);
    }

    @Override
    public int mouseClickDrag(String button, int x1, int y1, int x2, int y2, int speed) {
        return autoItLib.AU3_MouseClickDrag(_w(button), x1, y1, x2, y2, speed);
    }

    @Override
    public void mouseDown(String button) {
        autoItLib.AU3_MouseDown(_w(button));
    }

    @Override
    public int mouseGetCursor() {
        return autoItLib.AU3_MouseGetCursor();
    }

    @Override
    public int mouseGetPosX() {
        return autoItLib.AU3_MouseGetPosX();
    }

    @Override
    public int mouseGetPosY() {
        return autoItLib.AU3_MouseGetPosY();
    }

    @Override
    public int mouseMove(int x, int y, int speed) {
        return autoItLib.AU3_MouseMove(x, y, speed);
    }

    @Override
    public void mouseUp(String button) {
        autoItLib.AU3_MouseUp(_w(button));
    }

    @Override
    public void mouseWheel(String direction, int clicks) {
        autoItLib.AU3_MouseWheel(_w(direction), clicks);
    }

    @Override
    public int opt(String option, int value) {
        return autoItLib.AU3_Opt(_w(option), value);
    }

    @Override
    public int pixelChecksum(int left, int top, int right, int bottom, int step) {
        return autoItLib.AU3_PixelChecksum(left, top, right, bottom, step);
    }

    @Override
    public int pixelGetColor(int x, int y) {
        return autoItLib.AU3_PixelGetColor(x, y);
    }

    @Override
    public void pixelSearch(int left, int top, int right, int bottom, int col, int var, int step, LPPOINT pointResult) {
        autoItLib.AU3_PixelSearch(left, top, right, bottom, col, var, step, pointResult);
    }

    @Override
    public int processClose(String process) {
        return autoItLib.AU3_ProcessClose(_w(process));
    }

    @Override
    public int processExists(String process) {
        return autoItLib.AU3_ProcessExists(_w(process));
    }

    @Override
    public int processSetPriority(String process, int priority) {
        return autoItLib.AU3_ProcessSetPriority(_w(process), priority);
    }

    @Override
    public int processWait(String process, int timeout) {
        return autoItLib.AU3_ProcessWait(_w(process), timeout);
    }

    @Override
    public int processWaitClose(String process, int timeout) {
        return autoItLib.AU3_ProcessWaitClose(_w(process), timeout);
    }

    @Override
    public int regDeleteKey(String keyname) {
        return autoItLib.AU3_RegDeleteKey(_w(keyname));
    }

    @Override
    public int regDeleteVal(String keyname, String valueName) {
        return autoItLib.AU3_RegDeleteVal(_w(keyname), _w(valueName));
    }

    @Override
    public void regEnumKey(String keyname, int instance, byte[] result, int bufSize) {
        autoItLib.AU3_RegEnumKey(_w(keyname), instance, result, bufSize);
    }

    @Override
    public void regEnumVal(String keyname, int instance, byte[] result, int bufSize) {
        autoItLib.AU3_RegEnumVal(_w(keyname), instance, result, bufSize);
    }

    @Override
    public void regRead(String keyname, String valueName, byte[] retText, int bufSize) {
        autoItLib.AU3_RegRead(_w(keyname), _w(valueName), retText, bufSize);
    }

    @Override
    public int regWrite(String keyname, String valueName, String type, String value) {
        return autoItLib.AU3_RegWrite(_w(keyname), _w(valueName), _w(type), _w(value));
    }

    @Override
    public int run(String run, String dir, int showFlags) {
        return autoItLib.AU3_Run(_w(run), _w(dir), showFlags);
    }
    
    @Override
    public int run(String run) {
        return run(run, "", 1);
    }

    @Override
    public int runAsSet(String user, String domain, String password, int options) {
        return autoItLib.AU3_RunAsSet(_w(user), _w(domain), _w(password), options);
    }

    @Override
    public int runWait(String run, String dir, int showFlags) {
        return autoItLib.AU3_RunWait(_w(run), _w(dir), showFlags);
    }

    @Override
    public void send(String sendText, int mode) {
        autoItLib.AU3_Send(_w(sendText), mode);
    }

    @Override
    public int shutdown(int flags) {
        return autoItLib.AU3_Shutdown(flags);
    }

    @Override
    public void sleep(int milliseconds) {
        autoItLib.AU3_Sleep(milliseconds);
    }

    @Override
    public void statusbarGetText(String title, String text, int part, byte[] statusText, int bufSize) {
        autoItLib.AU3_StatusbarGetText(_w(title), _w(text), part, statusText, bufSize);
    }

    @Override
    public void toolTip(String tip, int x, int y) {
        autoItLib.AU3_ToolTip(_w(tip), x, y);
    }

    @Override
    public int winActive(String title, String text) {
        return autoItLib.AU3_WinActive(_w(title), _w(text));
    }

    @Override
    public void winActivate(String title, String text) {
        autoItLib.AU3_WinActivate(_w(title), _w(text));
    }

    @Override
    public int winClose(String title, String text) {
        return autoItLib.AU3_WinClose(_w(title), _w(text));
    }

    @Override
    public int winExists(String title, String text) {
        return autoItLib.AU3_WinExists(_w(title), _w(text));
    }

    @Override
    public int winGetCaretPosX() {
        return autoItLib.AU3_WinGetCaretPosX();
    }

    @Override
    public int winGetCaretPosY() {
        return autoItLib.AU3_WinGetCaretPosY();
    }

    @Override
    public void winGetClassList(String title, String text, byte[] retText, int bufSize) {
        autoItLib.AU3_WinGetClassList(_w(title), _w(text), retText, bufSize);
    }

    @Override
    public int winGetClientSizeHeight(String title, String text) {
        return autoItLib.AU3_WinGetClientSizeHeight(_w(title), _w(text));
    }

    @Override
    public int winGetClientSizeWidth(String title, String text) {
        return autoItLib.AU3_WinGetClientSizeWidth(_w(title), _w(text));
    }

    @Override
    public void winGetHandle(String title, String text, byte[] retText, int bufSize) {
        autoItLib.AU3_WinGetHandle(_w(title), _w(text), retText, bufSize);
    }

    @Override
    public int winGetPosX(String title, String text) {
        return autoItLib.AU3_WinGetPosX(_w(title), _w(text));
    }

    @Override
    public int winGetPosY(String title, String text) {
        return autoItLib.AU3_WinGetPosY(_w(title), _w(text));
    }

    @Override
    public int winGetPosHeight(String title, String text) {
        return autoItLib.AU3_WinGetPosHeight(_w(title), _w(text));
    }

    @Override
    public int winGetPosWidth(String title, String text) {
        return autoItLib.AU3_WinGetPosWidth(_w(title), _w(text));
    }

    @Override
    public void winGetProcess(String title, String text, byte[] retText, int bufSize) {
        autoItLib.AU3_WinGetProcess(_w(title), _w(text), retText, bufSize);
    }

    @Override
    public int winGetState(String title, String text) {
        return autoItLib.AU3_WinGetState(_w(title), _w(text));
    }

    @Override
    public void winGetText(String title, String text, byte[] retText, int bufSize) {
        autoItLib.AU3_WinGetText(_w(title), _w(text), retText, bufSize);
    }
    
    @Override
    public String winGetText(String title, String text) {
        int bufSize = 2048;
        byte[] bytes = new byte[bufSize];
        winGetText(title, text, bytes, bufSize);
        return byteToString(bytes);
    }

    @Override
    public void winGetTitle(String title, String text, byte[] retText, int bufSize) {
        autoItLib.AU3_WinGetTitle(_w(title), _w(text), retText, bufSize);
    }
    
    @Override
    public String winGetTitle(String title, String text) {
        int bufSize = 2048;
        byte[] bytes = new byte[bufSize];
        winGetTitle(title, text, bytes, bufSize);
        return byteToString(bytes);
    }

    @Override
    public int winKill(String title, String text) {
        return autoItLib.AU3_WinKill(_w(title), _w(text));
    }

    @Override
    public int winMenuSelectItem(String title, String text, String item1, String item2, String item3, String item4, String item5, String item6, String item7, String item8) {
        return autoItLib.AU3_WinMenuSelectItem(_w(title), _w(text), _w(item1), _w(item2), _w(item3), _w(item4), _w(item5), _w(item6), _w(item7), _w(item8));
    }

    @Override
    public void winMinimizeAll() {
        autoItLib.AU3_WinMinimizeAll();
    }

    @Override
    public void winMinimizeAllUndo() {
        autoItLib.AU3_WinMinimizeAllUndo();
    }

    @Override
    public int winMove(String title, String text, int x, int y, int width, int height) {
        return autoItLib.AU3_WinMove(_w(title), _w(text), x, y, width, height);
    }

    @Override
    public int winSetOnTop(String title, String text, int flag) {
        return autoItLib.AU3_WinSetOnTop(_w(title), _w(text), flag);
    }

    @Override
    public int winSetState(String title, String text, int flags) {
        return autoItLib.AU3_WinSetState(_w(title), _w(text), flags);
    }

    @Override
    public int winSetTitle(String title, String text, String newTitle) {
        return autoItLib.AU3_WinSetTitle(_w(title), _w(text), _w(newTitle));
    }

    @Override
    public int winSetTrans(String title, String text, int trans) {
        return autoItLib.AU3_WinSetTrans(_w(title), _w(text), trans);
    }

    @Override
    public int winWait(String title, String text, int timeout) {
        return autoItLib.AU3_WinWait(_w(title), _w(text), timeout);
    }

    @Override
    public int winWaitActive(String title, String text, int timeout) {
        return autoItLib.AU3_WinWaitActive(_w(title), _w(text), timeout);
    }

    @Override
    public int winWaitClose(String title, String text, int timeout) {
        return autoItLib.AU3_WinWaitClose(_w(title), _w(text), timeout);
    }

    @Override
    public int winWaitNotActive(String title, String text, int timeout) {
        return autoItLib.AU3_WinWaitNotActive(_w(title), _w(text), timeout);
    }
    
}
