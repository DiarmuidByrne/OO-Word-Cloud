#Advanced Object Oriented Programming Project: 
##A Java Word Cloud

**Diarmuid Byrne**

To run this project:
- Ensure that both, the desired text file (if required) 
and Stopwords.txt are in the same directory as wordie.jar
- from command line, run the command:
java –cp ./wordie.jar ie.gmit.sw.runner.Runner

This project is used to generate word cloud images using a given text file or URL.
The files are parsed and the most commonly appearing words are saved to an image file, with 
varying colours and font sizes, depending on the frequency of the word.

The text file name, the url link and the output image file 
name can all be customised by the user at runtime, or, if the user leaves the field blank
a sample will be used. Collision detection is used in the project to prevent overlap with words.

**References**

In some parts of this project have been taken, altered and adapted from other projects and sources:

- Kumo word cloud 
https://github.com/kennycason/kumo/
Parts of the project structure has been used from Kumo, such as Word, WordCloud and the collision package. However, aside from the Vector2D class, the implementation of these classes and interfaces have been totally changed. 
- RMI Encryption:
https://github.com/DiarmuidByrne/RMI-Encryption
Code from the QuadgramMap class has been used, namely the parse() method. However, it has been changed to save full words rather than quadgrams, and html pages in the urlparser class.
- StackOverflow
http://stackoverflow.com/questions/8119366/sorting-hashmap-by-values
A code exerpt was taken from stackoverflow to sort a hashmap by values and add it to a linkedhashmap
