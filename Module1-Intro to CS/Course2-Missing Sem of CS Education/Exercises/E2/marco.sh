##Defining the code for marco
marco() {
    tempPath=$(pwd)
    echo "tempPath variable created"
}

##Defining the code for polo
polo() {
    cd $tempPath
    echo "Moved to other directory"
}