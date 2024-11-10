MAVEN_CLEAN = mvn clean
MAVEN_CLEAN_COMPILE = mvn clean compile
MAVEN_ASSEMBLY = mvn assembly:assembly
EXECUTE_JAR = java -jar
JAR_FILENAME = navigator-1.0-SNAPSHOT-jar-with-dependencies.jar

all: s21_graph
	cd target && $(EXECUTE_JAR) $(JAR_FILENAME)

s21_graph:
	$(MAVEN_CLEAN_COMPILE)
	$(MAVEN_ASSEMBLY)

test:
	$(MAVEN_CLEAN_COMPILE) test

clean:
	$(MAVEN_CLEAN)
