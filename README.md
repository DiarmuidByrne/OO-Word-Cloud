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
