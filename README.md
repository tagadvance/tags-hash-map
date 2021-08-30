# Tag's Hash Map

An example implementation of a hash map in Java.

In a recent interview I was unable to explain how a HashMap handles collisions. I simply didn't know. Now as an engineer
that has been writing software over half my life this really hurt me. My knowledge stopped at "Objects are converted to
a hash and stored in a bucket." I can tell you the performance properties of a `HashMap`: namely that it'll take at most
1 loop over the internal hashes to find a given object, and that on average it'll take .5 loops to find a given object.
Here is another mistake I made: my big O notation was rusty, and I said that the performance of a `HashMap` is `O(n)`
instead of `O(1)`. Always brush up on your computer science before a big interview.

This leads us to now. I decided to roll my own `HashMap` class, something I'd never ever waste time on in a real project
so that if this question ever comes up again I'll nail it.
