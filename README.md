# Mua Interpreter

This is a script interactive interpreter running according to the <em>MUA</em> language grammer regulation. It is basiclly a CLI program, like, a python interpreter. 

## Introduction

<em>MUA</em> : MakeUp Programming Language

For the regualtion detail, please refer to [MadeUpProgrammingLanguage.pdf](./MadeUpProgrammingLanguage.pdf)

## Using Method

This interpreter is implemented based on <em>JAVA</em>. So, it is necessary to install JVM. Use the following command to compile and start the interpreter.

```
javac Mua.java
java Mua
```

This package provides a [testing file](./test.mua). And <em>MUA</em> supports an instruction called <b>load</b>, which can load a file and execute commands in it on the interpreter.

So, try ```load "test.mua```.

UI is about as follows.

```
Hello, welcome to mua interpreter!
>>> load "test.mua
...
>>> exit
Byebye, mua...
```

Some Tips :

* Command can be either entered in one line or be splited to several lines
* Any spaces, empty lines are allowed in a command
* You can enter anything when ```>>>``` occurs on the screen, the interpreter will judge what you enter is legal or not and throw exception prompt if something illegal appear

## Design Idea

Now, let's talk about the design idea of some key parts.

* First of all, learn about the interpreter framework or architecture.

Let's have a look at the catalog.

```
- data
    - InstSet.java
    - ListSet.java
    - NameSpace.java
    - WordSet.java
- function
    ...
    - *.java    // various functions
    ...
- mua
    - Handler.java
    - Interpreter.java
    - MuaException.java
- Mua.java
```

We can easily find that [Mua.java](Mua.java) is the entrance of the interpreter.

The data package stores all data of the interpreter. [InstSet.java](data/InstSet.java) manages standard instructions supported by the interpreter. [WordSet.java](data/WordSet.java) uses a stack to store name and value of words. [ListSet.java](data/ListSet.java) uses a stack to store name and content of lists. [NameSpace.java](data/NameSpace.java) shares four stacks, manages the local space words, local space lists, global space words and global space lists. Here, the variety <em>state</em> representes localspace mode or globalspace mode.

The function package contains the implementation detail of all document defined instructions. Files in this package have same format. So, if any instruction extended, modify the corresponding model or insert a new model. This design pattern makes adding extension easier. And don't forget register new instructions in instset stack in data package, otherwise it won't be recognised by the interpreter.

The mua package provides some other parts. [MuaException.java](mua/MuaException.java) is the subclass of <em>Exception</em>, it can print the exception info. [Interpreter.java](mua/Interpreter.java) initialize the interpreter including allocating dataset and inputbuffer, etc. [Handler.java](mua/Handler.java) provides some general purpose methods.

This design mode is easier for making extension.

* Then, how interpreter runs like a pipeline.

The interpreter runs in a recursive way.

For each integrated instruction sentence, it exits a cycle ```interpreter->handler->function->handler->interpreter```, function can visit dataset and throw muaexception.

In interactive mode, interpreter read instructions from system in, so, here uses scanner. But in a convenient interactive way, we need to print ```>>> ``` at the head of each input line. So, we need to judge where comes the end of a line. And here we create a buffer to store the line, when the line finish reading, execute it.

```
public String getNextStr (Scanner in, Vector<String> instbuffer) {
    if (instbuffer.size() > 0)
        return instbuffer.remove(0);
    System.out.print(">>> ");
    String line = getNextLine(in);
    String[] element = line.split(" ");
    for (String str : element)
        instbuffer.add(str);
    return instbuffer.remove(0);
}
```

Here we use a stack as the line instruction buffer.

Use this algorithm we can get the next input element and judge the line ending at the same time. It can make sure printing prompt at the head of each input line.

* What's more, how the interpreter throw exception.

We can find that all methods running on the recursive pipeline are written in ```try-catch``` mode.

Each method on the recursive path.

```
try {
    ...
} catch (... e) {
    throw new MuaException("...!");
}
```

while for the ```interpreter.run()``` method, which is the top entrance of the recursive path.

```
try {
    ...
} catch (MuaException e) {
    System.out.println(e.toString);
} finally {
    ...
}
```

This design pattern makes all exceptions thrown directly to the top of stack no matter how deep level it lies in. And the exception info print out at the top level with stop executing of the current instruction.

* At last, talk about the namespace.

The data structure of the namespace is defined as follows.

```
private boolean state;

private WordSet localword;
private WordSet globalword;

private ListSet locallist;
private ListSet globallist;
```

```state``` represents the interpreter is inside a function or stays in global space. Gloabl space only do io visit on globallist and globalword. While in a function or say, a local space, interpreter can read both local and global words and lists but it can only write data to local words and lists.

Attention! Consider we define a subfunction in a function. The subfunction stores in the locallist of function, the function stores in the globallist, the subfunction can only visit the local space of itself and the global space but not the local space of the function. A little complicated?

For more details, refer to the PDF document.

## Some Implementation Details

* Use stack as list buffer

When deal with ```if, repeat, etc...```, it will make the interpreter execute a list. Here create a list buffer to take place system in line buffer temporarily so as to avoid the collision.

* Express contains negative numbers

Negative numbers exist in a expression only in such situations.

```
-5 + 3
3 * (-5 + 3)
3 + -5
3 - -5
```

Other situations negative sign appears as unary operator are defined to be illegal. So, the resolution is as follows.

```
-5 + 3        ==>    0 - 5 + 3
3 * (-5 + 3)  ==>    3 * (0 - 5 + 3)
3 + -5        ==>    3 + 0 - 5
3 - -5        ==>    3 - 0 - 5
```

Then use the traditional double stack algorithm can solve it.

* Customized function execution

Because in and out of a function will cause the change of namespace, so, just new an interpreter to execute recursively.

## Sample

You can visit [test.mua](test.mua) to check some sample mua programs, also can enter ```load "test.mua``` in the interpreter to check results.

UI is as follows.

```
Hello, welcome to mua interpreter!
>>> make "a 1
>>> repeat 5 [
>>>   make "a add :a 1
>>>   print :a
>>> ]
2
3
4
5
6
>>>
>>>
>>> // gcd algorithm
>>> make "gcd [
>>>   [a b]
>>>   [
>>>     if eq :b 0
>>>       [output :a]
>>>       [output gcd :b (:a % :b)]
>>>   ]
>>> ]
>>> print gcd 18 -12
-6
>>> exit
Byebye, mua...
```

You can try some more complicated programs, just legal is ok.

## Further Extension

Maybe you can define <em>class</em> for mua language. For example, ```[]``` defines functions or lists, ```()``` defines mathematical expressions, you can use ```{}``` to define class, and use ```.``` to visit members in the class.

The framework has built, adding other function model just as follows.

* Add claim in the stack of data.InstSet

* Add implementation of new function in function package (refer to other functions)

* Add recognition inst op string in handler
