# Bootstrap Workshop #

The bootstrap workshop project can be used to get started with writing a workshop or presentation using Asciidoctor.

The project uses Gradle to transform the Asciidoctor source files to HTML output files. The Gradle wrapper is part of the project, which means we don't have to install Gradle ourselves, but we can simply invoke the `gradle` or `gradlew.bat` shell scripts.


## Building ##

To build both a workshop and presentation we simply invoke `gradlew asciidoctor` or `gradlew.bat asciidoctor` from the root of the project.

If we only want to build the workshop or the presentation we simply go to the `workshop` or `presentation` directory and invoke `gradlew` or `gradlew.bat`.

