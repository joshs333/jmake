# JMake
With the large amount of C++ development I had grown quite adjusted to command-line building, development using atom, and a CMakeLists.txt. I could use netbeans or JGrasp or some other such Java utility but my class projects didn't want there to be packages which NetBeans is a fan of... and I didn't feel like moving files around to make everything work.
So I made this in order to have a similar workflow to what I like. I looked at MakeFiles and using a java extension of CMake but ended up just hacking this together overnight. I later decided to polish it up a bit more (add setup.bash generation, package checking, colors) so it could be something neat to have on github.

# Basic Usage
Define a `JMakeLists.txt` which you need to define the source files to build.
```
add_src <JMAKE Package Name> <source files> ...
```
The JMAKE package name does not coorelate to any Java class. You can use the same name, but JMAKE makes no relation. I will typically say either JMAKE Package Name or Java Package Name to have a clear distincion.

```
add_post_build_command <JMAKE Package Name> <command to run>
```
or
```
add_post_build_command <JMAKE Package Name> "<command to run> [<args to command> ... ]"
```
Will execute the passed in command after the given package <JMAKE Package Name> has been built successfully.
This command or bash function will be executed in bash and will have the following bash variables available to it:
```
JMAKE_SOURCE_DIRECTORY=<source directory where the JMakeLists.txt lives>
JMAKE_BUILD_DIRECTORY=<Directory where jmake is being executed from (where all build files live)>
JMAKE_CURRENT_BUILD_DIR=<Directory where src code in package is being built into class files>
JMAKE_CURRENT_BUILD_PKG=<Name of JMAKE Package currently being built>
JMAKE_CURRENT_SRC_FILES=<a space delimited list of source files relative to $JMAKE_SOURCE_DIRECTORY>
```
There are other bash variables that exist and can technically be used (see the source code, it's bash, it's not that hard).
But these should be the ones that matter.

I could add post-jar/post-executable creation commands and the same thing but pre-build/creation... but for now it's YAGNI.

```
add_jar <JMAKE Package Name> <jar name> [<class to execute>]
```
This will generate an executable jar file if <class to execute> is provided, otherwise the class file will be packaged in a jar and can be run with `java -cp <jar file> <class to execute>`. Jars are put in the build/ directory (where jmake is run).

```
add_executable <JMAKE Package Name> <executable name> <class to execute>
```
This will make a linux/macos executable file that executes <class to execute>. These also go into the build/ directory. This relies on executable jars of that class, if there are none made in the JMakeLists.txt then it will make one.

# How to use
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
