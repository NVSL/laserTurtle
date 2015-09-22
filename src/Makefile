
JAR = turtleClasses.jar
SRCS = $(shell echo turtleClasses/*.java)
CLASSES = $(subst .java,.class,$(SRCS))

all: $(JAR)

example: $(JAR)
	javac -cp turtleClasses.jar:. ExampleLaserTurtle.java 

$(JAR): $(CLASSES)
	jar -cf $@ $(CLASSES)

%.class: %.java
	javac $^

clean:
	rm turtleClasses/*.class
	rm *.jar
