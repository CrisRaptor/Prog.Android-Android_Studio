cd .\app\src\main\assets\adb
.\adb.exe -d reverse tcp:8080 tcp:8080
.\adb.exe reverse --list