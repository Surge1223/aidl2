package projekt.interfacer.services;

import android.content.om.IOverlayManager;
import android.content.pm.Signature;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by surge on 4/13/17.
 */

public class JobService implements Parcelable {
    public static final String INTENT_STATUS_CHANGED = "projekt.interfacer.STATUS_CHANGED";
    public static final String PRIMARY_COMMAND_KEY = "primary_command_key";
    public static final String JOB_TIME_KEY = "job_time_key";
    public static final String INSTALL_LIST_KEY = "install_list";
    public static final String UNINSTALL_LIST_KEY = "uninstall_list";
    public static final String WITH_RESTART_UI_KEY = "with_restart_ui";
    public static final String BOOTANIMATION_FILE_NAME = "bootanimation_file_name";
    public static final String FONTS_PID = "fonts_pid";
    public static final String FONTS_FILENAME = "fonts_filename";
    public static final String AUDIO_PID = "audio_pid";
    public static final String AUDIO_FILENAME = "audio_filename";
    public static final String ENABLE_LIST_KEY = "enable_list";
    public static final String DISABLE_LIST_KEY = "disable_list";
    public static final String PRIORITY_LIST_KEY = "priority_list";
    public static final String SOURCE_FILE_KEY = "source_file";
    public static final String DESTINATION_FILE_KEY = "destination_file";
    public static final String WITH_DELETE_PARENT_KEY = "delete_parent";
    public static final String PROFILE_NAME_KEY = "profile_name";
    public static final String COMMAND_VALUE_JOB_COMPLETE = "job_complete";
    public static final String COMMAND_VALUE_INSTALL = "install";
    public static final String COMMAND_VALUE_UNINSTALL = "uninstall";
    public static final String COMMAND_VALUE_RESTART_UI = "restart_ui";
    public static final String COMMAND_VALUE_RESTART_SERVICE = "restart_service";
    public static final String COMMAND_VALUE_CONFIGURATION_SHIM = "configuration_shim";
    public static final String COMMAND_VALUE_BOOTANIMATION = "bootanimation";
    public static final String COMMAND_VALUE_FONTS = "fonts";
    public static final String COMMAND_VALUE_AUDIO = "audio";
    public static final String COMMAND_VALUE_ENABLE = "enable";
    public static final String COMMAND_VALUE_DISABLE = "disable";
    public static final String COMMAND_VALUE_PRIORITY = "priority";
    public static final String COMMAND_VALUE_COPY = "copy";
    public static final String COMMAND_VALUE_MOVE = "move";
    public static final String COMMAND_VALUE_DELETE = "delete";
    public static final String COMMAND_VALUE_PROFILE = "profile";
    public static final String COMMAND_VALUE_MKDIR = "mkdir";
    private static final String TAG = projekt.interfacer.services.JobService.class.getSimpleName();
    private static final boolean DEBUG = true;
    private static final String INTERFACER_TOKEN = "interfacer_token";
    private static final String INTERFACER_PACKAGE = "projekt.interfacer";
    private static final String SUBSTRATUM_PACKAGE = "projekt.substratum";
    private static final String[] AUTHORIZED_CALLERS = new String[]{
            INTERFACER_PACKAGE,
            SUBSTRATUM_PACKAGE,
    };
    private static final Signature SUBSTRATUM_SIGNATURE = new Signature(""
            + "308202eb308201d3a003020102020411c02f2f300d06092a864886f70d01010b050030263124302206"
            + "03550403131b5375627374726174756d20446576656c6f706d656e74205465616d301e170d31363037"
            + "30333032333335385a170d3431303632373032333335385a3026312430220603550403131b53756273"
            + "74726174756d20446576656c6f706d656e74205465616d30820122300d06092a864886f70d01010105"
            + "000382010f003082010a02820101008855626336f645a335aa5d40938f15db911556385f72f72b5f8b"
            + "ad01339aaf82ae2d30302d3f2bba26126e8da8e76a834e9da200cdf66d1d5977c90a4e4172ce455704"
            + "a22bbe4a01b08478673b37d23c34c8ade3ec040a704da8570d0a17fce3c7397ea63ebcde3a2a3c7c5f"
            + "983a163e4cd5a1fc80c735808d014df54120e2e5708874739e22e5a22d50e1c454b2ae310b480825ab"
            + "3d877f675d6ac1293222602a53080f94e4a7f0692b627905f69d4f0bb1dfd647e281cc0695e0733fa3"
            + "efc57d88706d4426c4969aff7a177ac2d9634401913bb20a93b6efe60e790e06dad3493776c2c0878c"
            + "e82caababa183b494120edde3d823333efd464c8aea1f51f330203010001a321301f301d0603551d0e"
            + "04160414203ec8b075d1c9eb9d600100281c3924a831a46c300d06092a864886f70d01010b05000382"
            + "01010042d4bd26d535ce2bf0375446615ef5bf25973f61ecf955bdb543e4b6e6b5d026fdcab09fec09"
            + "c747fb26633c221df8e3d3d0fe39ce30ca0a31547e9ec693a0f2d83e26d231386ff45f8e4fd5c06095"
            + "8681f9d3bd6db5e940b1e4a0b424f5c463c79c5748a14a3a38da4dd7a5499dcc14a70ba82a50be5fe0"
            + "82890c89a27e56067d2eae952e0bcba4d6beb5359520845f1fdb7df99868786055555187ba46c69ee6"
            + "7fa2d2c79e74a364a8b3544997dc29cc625395e2f45bf8bdb2c9d8df0d5af1a59a58ad08b32cdbec38"
            + "19fa49201bb5b5aadeee8f2f096ac029055713b77054e8af07cd61fe97f7365d0aa92d570be98acb89"
            + "41b8a2b0053b54f18bfde092eb");
    private static final Signature[] AUTHORIZED_SIGNATURES = new Signature[]{
            SUBSTRATUM_SIGNATURE,
    };

    private static final String INTENT_CALLER_AUTHORIZED = "projekt.interfacer.CALLER_AUTHORIZED";
    private static JobHandler mJobHandler;
    private MainHandler mMainHandler;

    public static void log(String msg) {
        if (!DEBUG) {
            return;
        }
        // More -Wall than -Werror like
        Log.d(TAG, msg);
    }
    private static IOverlayManager mOMS;

    public static String getIntentStatusChanged() {
        return INTENT_STATUS_CHANGED;
    }

    public static String getPrimaryCommandKey() {
        return PRIMARY_COMMAND_KEY;
    }

    public static String getJobTimeKey() {
        return JOB_TIME_KEY;
    }

    public static String getInstallListKey() {
        return INSTALL_LIST_KEY;
    }

    public static String getUninstallListKey() {
        return UNINSTALL_LIST_KEY;
    }

    public static String getWithRestartUiKey() {
        return WITH_RESTART_UI_KEY;
    }

    public static String getBootanimationFileName() {
        return BOOTANIMATION_FILE_NAME;
    }

    public static String getFontsPid() {
        return FONTS_PID;
    }

    public static String getFontsFilename() {
        return FONTS_FILENAME;
    }

    public static String getAudioPid() {
        return AUDIO_PID;
    }

    public static String getAudioFilename() {
        return AUDIO_FILENAME;
    }

    public static String getEnableListKey() {
        return ENABLE_LIST_KEY;
    }

    public static String getDisableListKey() {
        return DISABLE_LIST_KEY;
    }

    public static String getPriorityListKey() {
        return PRIORITY_LIST_KEY;
    }

    public static String getSourceFileKey() {
        return SOURCE_FILE_KEY;
    }

    public static String getDestinationFileKey() {
        return DESTINATION_FILE_KEY;
    }

    public static String getWithDeleteParentKey() {
        return WITH_DELETE_PARENT_KEY;
    }

    public static String getProfileNameKey() {
        return PROFILE_NAME_KEY;
    }

    public static String getCommandValueJobComplete() {
        return COMMAND_VALUE_JOB_COMPLETE;
    }

    public static String getCommandValueInstall() {
        return COMMAND_VALUE_INSTALL;
    }

    public static String getCommandValueUninstall() {
        return COMMAND_VALUE_UNINSTALL;
    }

    public static String getCommandValueRestartUi() {
        return COMMAND_VALUE_RESTART_UI;
    }

    public static String getCommandValueRestartService() {
        return COMMAND_VALUE_RESTART_SERVICE;
    }

    public static String getCommandValueConfigurationShim() {
        return COMMAND_VALUE_CONFIGURATION_SHIM;
    }

    public static String getCommandValueBootanimation() {
        return COMMAND_VALUE_BOOTANIMATION;
    }

    public static String getCommandValueFonts() {
        return COMMAND_VALUE_FONTS;
    }

    public static String getCommandValueAudio() {
        return COMMAND_VALUE_AUDIO;
    }

    public static String getCommandValueEnable() {
        return COMMAND_VALUE_ENABLE;
    }

    public static String getCommandValueDisable() {
        return COMMAND_VALUE_DISABLE;
    }

    public static String getCommandValuePriority() {
        return COMMAND_VALUE_PRIORITY;
    }

    public static String getCommandValueCopy() {
        return COMMAND_VALUE_COPY;
    }

    public static String getCommandValueMove() {
        return COMMAND_VALUE_MOVE;
    }

    public static String getCommandValueDelete() {
        return COMMAND_VALUE_DELETE;
    }

    public static String getCommandValueProfile() {
        return COMMAND_VALUE_PROFILE;
    }

    public static String getCommandValueMkdir() {
        return COMMAND_VALUE_MKDIR;
    }

    public static String getTAG() {
        return TAG;
    }

    public static boolean isDEBUG() {
        return DEBUG;
    }

    public static String getInterfacerToken() {
        return INTERFACER_TOKEN;
    }

    public static String getInterfacerPackage() {
        return INTERFACER_PACKAGE;
    }

    public static String getSubstratumPackage() {
        return SUBSTRATUM_PACKAGE;
    }

    public static String[] getAuthorizedCallers() {
        return AUTHORIZED_CALLERS;
    }

    public static String getIntentCallerAuthorized() {
        return INTENT_CALLER_AUTHORIZED;
    }

    public static Signature getSubstratumSignature() {
        return SUBSTRATUM_SIGNATURE;
    }

    public static Signature[] getAuthorizedSignatures() {
        return AUTHORIZED_SIGNATURES;
    }

    public static IOverlayManager getmOMS() {
        return mOMS;
    }

    public List<Runnable> getmJobQueue() {
        return mJobQueue;
    }

    public boolean shouldRestartUi() {
        return mshouldRestartUi;
    }


    public long getmLastJobTime() {
        return mLastJobTime;
    }

    public boolean ismIsRunning() {
        return mIsRunning;
    }

    public boolean ismShouldRestartService() {
        return mShouldRestartService;
    }

    public void restartUi() { }

    public void installPackage(List<String> paths) throws RemoteException {

    }

    public void uninstallPackage(List<String> packages, boolean restartUi) throws RemoteException {

    }

    public void restartSystemUI() throws RemoteException {

    }

    public void configurationShim() throws RemoteException {

    }

    public void applyBootanimation(String name) throws RemoteException {

    }

    public void applyFonts(String pid, String fileName) throws RemoteException {

    }

    public void applyAudio(String pid, String fileName) throws RemoteException {

    }

    public void enableOverlay(List<String> packages, boolean restartUi) throws RemoteException {

    }

    public void disableOverlay(List<String> packages, boolean restartUi) throws RemoteException {

    }

    public void changePriority(List<String> packages, boolean restartUi) throws RemoteException {

    }

    
    public void copy(String source, String destination) throws RemoteException {

    }

    
    public void move(String source, String destination) throws RemoteException {

    }

    
    public void mkdir(String destination) throws RemoteException {

    }

    
    public void deleteDirectory(String directory, boolean withParent) throws RemoteException {

    }

    
    public void applyProfile(List<String> enable, List<String> disable, String name, boolean restartUi) throws RemoteException {

    }

    private List<Runnable> mJobQueue = new ArrayList<>(0);

    private long mLastJobTime;
    private boolean mIsRunning;
    private boolean mShouldRestartService;
    private boolean mshouldRestartUi;

    private class MainHandler extends Handler {
        static final int MSG_JOB_QUEUE_EMPTY = 1;

        public MainHandler() {
            super();
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            return super.equals(obj);
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }

        public MainHandler(Callback callback) {
            super(callback);
        }

        MainHandler(Looper looper) {
            super(looper);
        }

        public MainHandler(Looper looper, Callback callback) {
            super(looper, callback);
        }

        @Override
        public void dispatchMessage(Message msg) {
            super.dispatchMessage(msg);
        }

        @Override
        public String getMessageName(Message message) {
            return super.getMessageName(message);
        }

        @Override
        public boolean sendMessageAtTime(Message msg, long uptimeMillis) {
            return super.sendMessageAtTime(msg, uptimeMillis);
        }

        @Override
        public String toString() {
            return super.toString();
        }

        @Override
        protected void finalize() throws Throwable {
            super.finalize();
        }
    }

    private class JobHandler extends Handler {
        private static final int MESSAGE_CHECK_QUEUE = 1;
        private static final int MESSAGE_DEQUEUE = 2;

        JobHandler(Looper looper) {
            super(looper);
        }

        public JobHandler(Looper looper, Callback callback) {
            super(looper, callback);
        }

        public JobHandler() {
        }

        public JobHandler(Callback callback) {
            super(callback);
        }

    }

    private static class Sound {
        String themePath;
        String cachePath;
        String soundName;
        String soundPath;
        int type;

        Sound(String themePath, String cachePath, String soundName, String soundPath) {
            this.themePath = themePath;
            this.cachePath = cachePath;
            this.soundName = soundName;
            this.soundPath = soundPath;
        }

        Sound(String themePath, String cachePath, String soundName, String soundPath, int type) {
            this.themePath = themePath;
            this.cachePath = cachePath;
            this.soundName = soundName;
            this.soundPath = soundPath;
            this.type = type;
        }
    }




    public static IBinder asBinder() {
        return mOMS.asBinder();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.mJobQueue);
        dest.writeLong(this.mLastJobTime);
        dest.writeByte(this.mIsRunning ? (byte) 1 : (byte) 0);
        dest.writeByte(this.mShouldRestartService ? (byte) 1 : (byte) 0);
    }

    public JobService() {
    }

    protected JobService(Parcel in) {
        this.mJobHandler = in.readParcelable(JobHandler.class.getClassLoader());
        this.mMainHandler = in.readParcelable(MainHandler.class.getClassLoader());
        this.mJobQueue = new ArrayList<Runnable>();
        in.readList(this.mJobQueue, Runnable.class.getClassLoader());
        this.mLastJobTime = in.readLong();
        this.mIsRunning = in.readByte() != 0;
        this.mShouldRestartService = in.readByte() != 0;
    }

    public static final Creator<JobService> CREATOR = new Creator<JobService>() {
        @Override
        public JobService createFromParcel(Parcel source) {
            return new JobService(source);
        }

        @Override
        public JobService[] newArray(int size) {
            return new JobService[size];
        }
    };
}

