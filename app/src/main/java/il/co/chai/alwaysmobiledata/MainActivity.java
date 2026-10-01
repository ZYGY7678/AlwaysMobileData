package il.co.chai.alwaysmobiledata;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
    TextView status;
    @Override public void onCreate(Bundle b) {
        super.onCreate(b);
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(32,48,32,32);
        layout.setGravity(Gravity.CENTER_HORIZONTAL);
        TextView title = new TextView(this);
        title.setText("נתונים תמיד פעילים"); title.setTextSize(26);
        status = new TextView(this);
        status.setText("\nהאפליקציה תבדוק את הנתונים ותנסה להפעיל אותם באמצעות Root.\n"); status.setTextSize(17);
        Button start = new Button(this); start.setText("הפעל ניטור");
        start.setOnClickListener(v -> { startService(new Intent(this, WatchService.class)); status.setText("הניטור הופעל. אשר הרשאת Root בחלון שיופיע."); });
        Button stop = new Button(this); stop.setText("עצור ניטור");
        stop.setOnClickListener(v -> { stopService(new Intent(this, WatchService.class)); status.setText("הניטור נעצר."); });
        layout.addView(title); layout.addView(status); layout.addView(start); layout.addView(stop); setContentView(layout);
    }
}
