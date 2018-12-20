default:
	javac Main.java
clean:
	$(RM) *.class
run:
	javac Main.java
	java Main
