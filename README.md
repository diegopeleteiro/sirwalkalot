sirwalkalot
===========

Sir WalkALot is a project to build a Step Counter using Android native language with Team collaboration features.
At first we developed only a simple Step Counter based on the Step Counter project from http://code.google.com/p/pedometer/

The user is able to select some sensibility for the counting algorithm and then start the counting.

For every step taken there is a beep and an increase in the total steps' number. Later the user can stop the counting.

Known bugs at the moment are:
- If the phone changes its orientation (e.g. to landscape), the counting stops.
- The system seems to stop counting steps around 350-370 steps, the cause might be the phone entering the "lock screen" status

Project structure:
The front page is defined on MainActivity and it responds to the design created at res/layout/activity_main.xml, the step counting 
algorithm is defined on StepDetector.java

That is all. 

Junit 4, Mockito and Roboletrict are already imported to pom.xml but not in use, also the test folder is created, but empty.

Getting to work:
You need Maven 3.0.3 for dependency management and build. I am using 3.0.5, also you need the Android SDK and the Android API.
To install the Android SDK, you need the Android SDK Manager, and then install the "Tools" and at least one Android API (I have all).

After installing the Android SDK and cloning the repository you should be able to run "mvn clean install". 

For development:
Using IntelliJ is quite straightforward - open IntelliJ and open the project (no need to import, but you can). Then from the Maven
tab, you can select "clean" and "install". You should get the same results than using the command line.

After building with "clean" + "install" you will get an APK at the target directory. You can use it to install the app in your Android phone.

You can also run: "mvn clean install android:deploy" from your command line and this will install the APK automatically in your phone 
ps. Do not forget to connect your phone to the pc/mac :)