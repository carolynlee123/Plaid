Encoding algorithm:  The code reads in the input file with the ASCII art line by line, and then encodes the file and appends it to the output string in the following format:

    ascii[number of repeats]
    aaa => 97[3]

Decoding algorithm:  The code reads in the encoded string line by line and decodes using the opposite approach by converting the ascii value to its character and repeating it by the number in the brackets.  Then, the code appends the decoded value to the string.

To use the code, compile the file Plaid.java, then run the code including the data.txt file or other ascii bitmap file in the command line. The code will output the encoded string in encodedData.txt and the decoded string in decodedData.txt.

    Example: javac Plaid.java, java Plaid data.txt

Tests are in the file, PlaidTest.java
