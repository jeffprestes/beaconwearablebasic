# beaconwearablebasic
This projects cover basic interaction between iBeacons and Android Wearable.
It monitors if there is a iBeacon nearby and if it detects it sends a notification to watch.
The watch receives, show notification to user and user could select the option to turn on the light.
An specific intent on handheld is called and via MQTT sends a message to a broker where a Java client app is listening a topic 
activates the relay and turn the light on.

For Java Client see https://github.com/jeffprestes/lightbulbswitch
