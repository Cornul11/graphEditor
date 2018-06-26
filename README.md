# graphEditor
This is a simple OOP Java project for the first year of the Computing Science bachelor at *University of Groningen*.

This small application can open graphs that are stored in a specific format, make changes on it and then save them back in a file. We used the MVC architecture and the Observer/Observable pattern.

The format of the input files should be as follows:
```n m
x y h w name
v1 v2
```
Where *n* is the amount of vertices and *m* is the amount of edges.
*x*, *y* are the x and y coordinates for where the figure of the vertex is to be positioned. *H* and *W* are the height and the width of the figure. *name* is the text that is used as a label for the vertex. 

Opening files can be done from the interface of the program or from the command line by providing the name of the file as an argument to the executable.
