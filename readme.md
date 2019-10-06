# JMake
With the large amount of C++ development I had grown quite adjusted to command-line building, development using atom, and a CMakeLists.txt. When I started taking a Java-intensive algorithms class I realized I could use netbeans or JGrasp or some other such Java utility to do this... but there wasn't supposed to be any packages (which netbeans at least requires) and I didn't want to fiddle with changing the file for submission and having it one way or another in git. Not to mention that the grading would take place in terminal... not through some IDE.
So I made this in order to have a similar workflow to what I like, and also be the environment where my projects would be graded. I looked at MakeFiles and using a java extension of CMake but ended up just hacking this together overnight. I later decided to polish it up a bit more (add setup.bash generation, package checking, colors) so it could be something neat to have on github.

# Basic Usage
Define a `JMakeLists.txt` which you need to define the source files to build.
```
add_src <JMAKE Package Name> <source files> ...
```
The JMAKE package name does not coorelate to any Java class. You can use the same name, but JMAKE makes no relation. I will typically say either JMAKE Package Name or Java Package Name to have a clear distincion.

```
add_jar <JMAKE Package Name> <jar name> [<class to execute>]
```
This will generate an executable jar file if <class to execute> is provided, otherwise the class file will be packaged in a jar and can be run with `java -cp <jar file> <class to execute>`. Jars are put in the build/ directory (where jmake is run).

```
add_executable <JMAKE Package Name> <executable name> <class to execute>
```
This will make a linux/macos executable file that executes <class to execute>. These also go into the build/ directory. This relies on executable jars of that class, if there are none made in the JMakeLists.txt then it will make one.

# See it in action
I put an example in test/ that I used to debug and test. To see it in action go to test/ and run the following.
```
mkdir build/
cd build/
../../jmake
cd ..
source setup.bash
```
Setup.bash is a beautiful thing I like to add to my projects that let me run build/run commands no matter where I am in the file structure. It will give you two main commands `build` and `run`. `build` just goes to the build/ directory and execute jmake.
```
run <Class to run> [<args> ...]
```
Setup.bash also adds the build directory to path so any executables that are generated can be run anywhere in terminal. 

# "Installation"
To use this you need to have Java Development Kit & Runtime installed as well as python (does regex on the source files to find classes and packages). Then you need to put jmake bash executable in a directory in path. Personally I have make the folder `~/.scripts` where I put a ton of convenience scripts.

# Why use this?
## Compared to IDE's
Simpler/Light Weight. I'm personally not a big fan of big IDE's that have requirements on how you structure things and that you need to do a ton of menu and preference stuff to get jar files and whatnot.

## Compared to your own bash scripting
I used to just do my own bash scripts for these things, but I found myself rewriting the same code over and over again. This makes it so you don't need to do that and also adds nifty tools (setup.bash, jar files).

## General Notes
Something I don't think I've ever seen in Java build tools I've used is packaging into an executable file which this does. I'd also like to note there are a ton of reasons TO use a big fancy IDE. It's all about use case, and personal preference. Keep coding friends.

# TODO:
- Add more documentation. On the bright side it's just <1000 lines of bash scripting. It's not that difficult to see what's going on and I tend to comment a fair amount.
- Add support for pre-build/post-build commands in the form of functions
- add more convenience functions to setup.bash (I'm thinking of file structure navigation to different directories but IDK)
We'll see when I get time to do more for this. It's fitting my needs right now so the rest is YAGNI as far as I'm concerned for the moment.

Questions?: joshs333@live.com
Want to use this?: I doubt people will want to... haha but if you do go for it.
