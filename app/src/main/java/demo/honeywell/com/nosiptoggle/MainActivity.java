package demo.honeywell.com.nosiptoggle;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Context context;
    // package="com.honeywell.tools.nosip"
    // <activity android:label="@string/app_name" android:name="com.honeywell.tools.nosip.NoSIPActivity"
    final String packagename="com.honeywell.tools.nosip";
    final String className="com.honeywell.tools.nosip.NoSIPActivity";

    final String TAG = "nosiptoggle";
    Button button_toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getApplicationContext();
        toggleNoSIP();
        finish();

        setContentView(R.layout.activity_main);
        button_toggle=(Button)findViewById(R.id.button_toggle);
        if(button_toggle!=null) {
            button_toggle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    toggleNoSIP();
                }
            });
        }
    }

    //adb shell am start -n "demo.honeywell.com.nosiptoggle/demo.honeywell.com.nosiptoggle.MainActivity" -a android.intent.action.MAIN -c android.intent.category.LAUNCHER -D
    //am start com.honeywell.tools.nosip/.NoSIPActivity
    void toggleNoSIP(){
        try {

            Intent intent = context.getPackageManager().getLaunchIntentForPackage(packagename);
            if(intent==null)
            {
                intent = new Intent();
                intent.setClassName(packagename,className);
                context.startActivity(intent);
            }
            /*
            ResolveInfo resolveInfo = getPackageManager().resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);
            // Only start the activity if the package manager can resolve the Intent
            if (resolveInfo != null) {
                startActivity(intent);
            }
            else{
                Toast.makeText (context, "no resolveinfo for "+packagename, Toast.LENGTH_LONG).show();
            }

            if (intent != null) {
                // See how the package manager will resolve this Intent
            }
            else{
                Toast.makeText (context, "no intent for "+packagename, Toast.LENGTH_LONG).show();
            }
            */
        } catch (Exception e) {
            Toast.makeText (context, "starting "+packagename+" failed", Toast.LENGTH_LONG).show();
            Log.d(TAG, e.getMessage());
        }
    }
}
