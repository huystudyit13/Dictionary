# Dictionary

This is a big project in OOP subject in UET-VNU.

Project name is E-VDictionary that allows everyone use to translate English-VietNamese or VietNamese-English words.

This project is written in Java by using [JavaFX Scene Builder](#JavaFX-Scene-Builder) in IntelliJ IDEA.

## Prerequisites

Before you begin, ensure you have met the following requirements:
<!--- These are just example requirements. Add, duplicate or remove as required --->
* You have installed the latest version of [IntelliJ IDEA](#IntelliJ-IDEA) at https://www.jetbrains.com/idea/.
* You have download [Scene Builder](#Scene-Builder) works with the JavaFX for Java 8 at https://gluonhq.com/products/scene-builder/.

## Installing Dictionary

First of all, you need to download all files in this repository.
1. Open [IntelliJ IDEA](#IntelliJ-IDEA) and create a new project with Project SDK `java version "1.8.0_261"`.
2. Copy all files in `Dictionary_WithJavaFx_SceneBuilder` folder to `src` folder of your new IntelliJ project.
3. Link Scene Builder to your IntelliJ IDEA by opening file sample.fxml in project that require you to link Scene Builder to project.
4. Add `FreeTTS` library to your project. Go to File -> Project Structure, click on (+) icon (New Project Library), and link this to `Java lib` in `Dictionary_WithJavaFx_SceneBuilder` folder.
5. Change File Input in `Main.java` and String fileEV_name, fileVE_name in `Controller.java`. 

    Example : ``"D:\\Code big project\\DicitonaryWithFX\\src\\Source Dictionary\\E_V.txt".``
    
## Using Dictionary

After following steps in `Installing Dictionary`, you can Run this project by click Run (Shift + F10) in [IntelliJ IDEA](#IntelliJ-IDEA).

## Contributors

Thanks to the following people who have contributed to this project:

* [@doquan21](https://github.com/doquan21)
* [@huystudyit13](https://github.com/huystudyit13)
