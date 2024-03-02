#!/bin/bash

test_source_file=$1

CLASSPATH=".:utils/junit-platform-console-standalone-1.8.2.jar:utils/apiguardian-api-1.1.0.jar:utils/junit-jupiter-api-5.8.2.jar:utils/junit-jupiter-engine-5.8.2.jar:utils/junit-platform-launcher-1.8.2.jar"

# Compile the test class
javac -cp $CLASSPATH $test_source_file

# Execute the compiled test class if compilation was successful
if [ $? -eq 0 ]; then
    # Extract the class name without the ".java" extension
    class_name=$(basename -s .java $test_source_file)
    
    # Execute the test class
    java -cp $CLASSPATH:$class_name org.junit.platform.console.ConsoleLauncher --select-class $class_name
else
    echo "Compilation failed. Exiting..."
fi


rm *class

# Unset the CLASSPATH variable
unset CLASSPATH