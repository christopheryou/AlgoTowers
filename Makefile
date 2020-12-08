JFLAGS = -g
JC = javac
JVM = java
FILE="Algo.csv"

.SUFFIXES: .java .class

.java.class:
			$(JC) $(JFLAGS) $*.java

CLASSES = \
		  AlgoTowers.java \
			Task1.java \
			Task2.java \
			Task3.java \
			Task4.java \
			Task5.java

MAIN = AlgoTowers

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class

run:
	java main
