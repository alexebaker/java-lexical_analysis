# Lexical Analysis

CS 554 spike 1 - Lexical Analysis


## Contact Information

    Author: Alexander Baker
    Email: alexebaker@unm.edu
    Date: 02/09/2018


## Usage

Build the project:

```bash
make jar
```

Run the project:

```bash
java -jar spike1.jar tests/t1.lexi
```


## Specification Issues

s1.3.2.1.3 and s1.3.2.1.4

    These sections say that Identifier and Number tokens can't exceed 1024 bytes. However, it doesn't say how the program
    should behave when this happens. Is the token truncated and printed? Does the program throw an error and exit? In the
    case of my program, I decided to exit the program and error without printing anything. Truncating the token, or printing
    an incomplete token would have unexpected behavior that the user may not be aware of. By erroring out and exiting,
    the user is aware of the issue and can fix the problem, without the compiler making any assumptions
    

## Known Bugs

Currently, I am not aware of any bugs in the program. They may exist, but I have not found them yet.
