package il.co.chai.alwaysmobiledata;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Handler;
import android.os.Looper;
import java.io.DataOutputStream;

public class WatchService extends Service {
    private final Handler handler = new Handler(Looper.getMainLooper());
    private final Runnable checker = new Runnable() { public void run() {
        new Thread(() -> enableDataAsRoot()).start(); handler.postDelayed(this, 30000L);
    }};
    @Override public int onStartCommand(Intent intent, int flags, int startId) {
        handler.removeCallbacks(checker); checker.run(); return START_STICKY;
    }
    private void enableDataAsRoot() {
        Process p = null;
        try { p = Runtime.getRuntime().exec("su"); DataOutputStream out = new DataOutputStream(p.getOutputStream());
            out.writeBytes("svc data enable\\n"); out.writeBytes("exit\\n"); out.flush(); p.waitFor();
        } catch (Exception ignored) { } finally { if (p != null) p.destroy(); }
    }
    @Override public void onDestroy() { handler.removeCallbacks(checker); super.onDestroy(); }
    @Override public IBinder onBind(Intent intent) { return null; }
}
