//test deep links command

adb shell am start \
-a android.intent.action.VIEW \
-d "https://mvvmpostlast.example.com/post_details/123" \
com.example.mvvmpostlast.dev
