all: jar

jar: src/*.java
	@cp README.md src/README.txt && cd src && javac *.java && jar cvfe ../spike1.jar Main *.java *.class README.txt && rm *.class README.txt && cd ..

clean: FORCE
	rm src/*.class src/README.txt

TEST_FILES:=$(wildcard tests/*.lexi)
TEST_RESULTS:=$(patsubst tests/%.lexi, tests/%.lexo,$(TEST_FILES))

test: jar $(TEST_RESULTS)

tests/%.lexo: tests/%.lexi FORCE
	@echo -n "[Test $< -> $@ file: "
	@java -jar spike1.jar $< | diff $@ -
	@echo -n "OK, stdin: "
	@cat $< | java -jar spike1.jar | diff $@ -
	@echo "OK]"

.PHONY: FORCE
