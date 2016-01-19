#Advanced Object Oriented Programming Project:
##A Java Word Cloud

**Diarmuid Byrne**

To run this project:
- Ensure that both, the desired text file (if required)
and Stopwords.txt are in the same directory as wordie.jar
- Ensure that the libs folder is in the current directory
- from command line, run the command:
java -cp libs/*;wordie.jar ie.gmit.sw.runner.Runner

Full javadocs can be found at dist/docs
![Alt text](http://i.imgur.com/V9SfUWj.png "Wordcloud from URL (www.gmit.ie)")

This project is used to generate word cloud images using a given text file or URL.
The files are parsed and the most commonly appearing words are saved to an image file, with
varying colours and font sizes, depending on the frequency of the word.

The text file name, the url link and the output image file
name can all be customised by the user at runtime, or, if the user leaves the field blank
a sample will be used. Collision detection is used in the project to prevent overlap with words.

**References**<br>
In order to create this project, some tutorials, projects and code extracts were taken and adapted:

- JSoup URL parser:<br>
http://jsoup.org/<br>
- Apache-commons-validator URLValidator:<br>
https://commons.apache.org/proper/commons-validator/apidocs/org/apache/commons/validator/UrlValidator.html

- Kumo word cloud<br>
https://github.com/kennycason/kumo/<br>
This project uses some elements from the Kumo wordcloud project:
 - WordCloud.java place() method
This projects borrows from the Kumo naming convention.
The class WordCloud.java uses the place() method from Kumo, which is used to
continually attempt to place the word until requirements are met for a suitable position.
 - The getters in the Collidable interface is used in CollisionDetector in order for the Word object to
 implement a useful interface that promotes abstraction and encapsulation.

- RMI Encryption:<br>
https://github.com/DiarmuidByrne/RMI-Encryption<br>
Code from the QuadgramMap class has been used, namely the parse() method to parse "stopWords.txt". However, it has been adapted to save full words rather than quadgrams.

- Tutorials:<br>
http://stackoverflow.com/questions/8119366/sorting-hashmap-by-values<br>
A code exerpt to sort a hashmap by values and add it to a LinkedHashMap.
http://stackoverflow.com/questions/1600291/validating-url-in-java<br>
Uses Apache commons-validator URLValidator class to validate a URL string.
http://stackoverflow.com/questions/12943734/jsoup-strip-all-formatting-and-link-tags-keep-text-only<br>
Jsoup code extract for ignoring HTML for a URL.<br>
http://stackoverflow.com/questions/941272/how-do-i-trim-a-file-extension-from-a-string-in-java<br>
Code exerpt to trim a file extension from a String.<br>
http://www.javaworld.com/article/2077386/learn-java/factory-methods.html<br>
Example use of factory class in Java.
