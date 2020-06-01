# AppiumGrid

1. Download Selenium Standalone Server
2. Go to the <<path of selenium standalone server>>
3. Enter Cmd
4. Enter java -jar selenium-server-standalone-3.141.59.jar -role hub (It will act as a root hub)
5. Go to Browser , enter localhost:4444
6. It will Open Selenium Grid server
7. Open Multiple Appium Server example: if you are executing parallel test in two devices then open two appium server instances.
8. Edit Appium server with
Enter appium Log path
In node config path need to input “Json path”
Add bootstrap and chrome driver port in both the instances
Chromedriver port and server port as well.
9. It will connect these as nodes to grid server(hub)
10. Go to localhost:4444 browser → Console. There we can see nodes connected to server.


Issues which i have faced during grid execution

How to Handle chromedriver exception?
org.openqa.selenium.SessionNotCreatedException: Unable to create a new remote session. Please check the server log for more details. Original error: An unknown server-side error occurred while processing the command. Original error: No Chromedriver found that can automate Chrome '69.0.3497'. See https://github.com/appium/appium/blob/master/docs/en/writing-running-appium/web/chromedriver.md for more details. You could also try to enable automated chromedrivers download server feature
Build info: version: '3.141.59', revision: 'e82be7d358', time: '2018-11-14T08:17:03'
System info: host: 'DESKTOP-B2426KB', ip: '192.168.43.67', os.name: 'Windows 10', os.arch: 'amd64', os.version: '10.0', java.version: '1.8.0_202'
Driver info: driver.version: AndroidDriver
remote stacktrace: UnknownError: An unknown server-side error occurred while processing the command. Original error: No Chromedriver found that can automate Chrome '69.0.3497'. See https://github.com/appium/appium/blob/master/docs/en/writing-running-appium/web/chromedriver.md for more details. You could also try to enable automated chromedrivers download server feature
   

Solution:

Just update your Chrome browser on real device to the latest version and download latest chromedriver.exe then update it to following directory
C:\Users\{username}\AppData\Roaming\npm\node_modules\appium\node_modules\appiu m-chromedriver\chromedriver\win
or You can try to set in manually with appium see here:
npm install appium --chromedriver_version="76.0.3809.68"

How to Install latest chrome browser in emulator?
Ans:  https://www.inflectra.com/support/knowledgebase/kb276.aspx

How to solve w3c error while launching Google.com?
Ans: cap.setCapability("appium:chromeOptions", ImmutableMap.of("w3c", false));


In hub.bat file added "java -jar selenium-server-standalone-3.141.59.jar -role hub"

In device1.bat and device2.bat file added paths.
all.json contains capabilities.

Why used .bat file?
Ans: WHen we double clicked on bat file it will create run based on the input in file


