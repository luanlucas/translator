# Text Translator

Simple API that connects to Microsoft Azure Text Translator and perform text translation.

## Getting Started

This sample prompts on console for a input file, a output directory and the languages you would like the text to be translated to. After these three information are given, you'll be provided with one file for each selected language with the content translated.

### Prerequisites

Just Java 1.8 and maven are enough to run this sample application. You will also need to type your Microsoft Azure access-key in file application.yml.

```
java -jar translator-0.0.1-SNAPSHOT.jar
```

### Installing

Clone this repository, go the root folder and run mvn install to deploy the application.

## Authors

* **Luan Lucas Lourenço** 

# Future

I would like to perform some improvements on this sample in the future, which at first includes:
1. Turn it into a Container supported application (most likely on Docker);
2. Create a web interface to interact with the application (main purpose would be to act like a microservice).
