
You should create your own Activity, inherited from FlutterActivity; override createFlutterView.
```Java
public class MainActivity extends FlutterActivity {
    @Override
    public FlutterView createFlutterView(Context context) {
        WindowManager.LayoutParams matchParent = new WindowManager.LayoutParams(-1, -1);
        FlutterNativeView nativeView = this.createFlutterNativeView();
        FlutterView flutterView = new FlutterView(this, (AttributeSet) null, nativeView);
        flutterView.setInitialRoute("/dashboard");
        flutterView.setLayoutParams(matchParent);
        this.setContentView(flutterView);
        return flutterView;
    }
}

```
then
```Java
startActivity(new Intent(this,MainActivity.class));
```
