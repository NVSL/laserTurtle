
JAR = laserTurtle.jar
SRCS = $(shell echo laserTurtle/*.java)
CLASSES = $(subst .java,.class,$(SRCS))

all: $(JAR)

example: $(JAR)
	javac -cp $(JAR):. ExampleLaserTurtle.java 

snowflake: $(JAR)
	javac -cp $(JAR):. SnowflakeTurtle.java 	

$(JAR): $(CLASSES)
	jar -cf $@ $(CLASSES)

%.class: %.java
	javac $^

clean:
	rm -f laserTurtle/*.class
	rm -f *.jar
