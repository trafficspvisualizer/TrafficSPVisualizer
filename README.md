# TrafficSPVisualizer
TrafficSPVisualizer is a tool used to create visualizations for stated-preference surveys.
The application is written in Java 21, so it should work on any supported operating system. 
Be aware that the application is only tested on Windows 10.

## Building the application
Requirements: JDK 21
1. Clone the project
   ```bash
   git clone https://gitlab.kit.edu/ujwny/trafficspvisualizer.git
   ```
2. Use gradle to build the application. You can use any installation of gradle 8.5 or the included wrapper:
    ```bash
    gradle clean build
    ```
3. To build the executable run the jpackageImage task:
    ```bash
   gradle jpackageImage
    ```
4. The executable can be found in `launcher/build/jpackage/TrafficSPVisualizer`. It is bundled with a java runtime, so
   it works even on systems without a java installation.
5. If you copy the executable to another location make sure to also move the `app` an `runtime` folder with the bundled
java runtime

## Usage
To use the application just start the executable. Once the application has started you can find instructions about the
usage in the menu bar under `Help`