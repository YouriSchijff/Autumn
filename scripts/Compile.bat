javac -cp ../out;../libs/* -sourcepath ../Project/src -d ../out ../Project/src/Project/Main.java
cd ../out
java -cp .;../libs/* -Dorg.lwjgl.librarypath=../libs/* Project.Main
jar cvfm jar.jar manifest.mf Project/Main.class
pause