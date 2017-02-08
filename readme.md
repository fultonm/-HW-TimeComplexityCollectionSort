Chapter 13 assignment text:

> Demonstrate Big-O for the Oracle Java API Collections.sort().

> Use the System clock (milliseconds) and demonstrate the run-time complexity, O(N), O(N^2), O(1)???  for the Collections.sort() method, for the average case.

> Create an Excel workbook of your results, and clearly (in title) indicate your answer for Big-O. Your chart should have a title, label axes, a line that proposes your answer, and appropriate scales from zero to whatever is large enough to encompass your data.  Everyone will have different data, but we should all come to the same Big-O conclusion that Collections.sort is O???

> You will likely start with some preliminary results, often getting slightly different results, something like Preliminary.JPGView in a new window  In the end you will clean things up and get a more polished final chart that displays on the first worksheet along with your data FinalEst.JPGView in a new window

> Crucial consideration:  Your Collection must be large enough so sorting takes at least ideally one or two seconds.  But if you have a fast computer (bummer) you might run out of RAM for Eclipse, so you'll need to change the allocation for Eclipse (link) (Links to an external site.). Huge run times (like minutes for sorting) will likely be timing the problem of virtual memory (hard drive) instead of the actual Big-O algorithm processing.  Really short times (less than 10 ms) are just timing how long it takes to load the instructions to RAM.

> Submit 2 files here:

> your Testing.java program
> your Excel workbook
