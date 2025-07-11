# Largest Miller-Rabin Prime Candidate
The Miller-Rabin primality test is a probabilistic algorithm that determines whether a given number is likely to be prime. This is one of the simplest and fastest known tests for a polynomial-time deterministic primality test.

Given the pseudocode and a working serial implementation for this test, we’re going to see if we can convert this into a parallelized program and compare how it runs against the serial version.

Write a LargestPrimeForkJoin.java program that outputs the largest computable prime number in an allotted amount of time.

## A Brief Introduction to the Miller-Rabin Primality Test
The algorithm driving the Miller-Rabin primality test uses the following facts to build its logic (Note: You don’t need to really understand what you’re looking at here, just recognize that this is how it works and why this algorithm can verify primality):

Fermat’s theorem states the following: If n is a prime number, then for every a:

1 <= a < n
a^ -1 mod n = 1

Base cases exist to ensure that n must be odd, implying that n-1 must be even. Even numbers can then be written as
d * 2^8
Where d is an odd number and s > 0. 3. Using points 1. and 2., for every randomly picked number in the range [2, n-2], the value of:
a ^ d * 2r mod n

Must be 1. 4. As per Euclid’s Lemma, if any of the following is true:

x ^ 2 mod n = 1
(x ^ 2 - 1) mod n = 0
(x - 1)(x + 1) mod n = 0

Then n is prime if it either divides (x-1) or divides (x+1). Which means either of the following must be true:

x mod n = 1
x mod n = -1

Using points 2. and 3., we can conclude the following ruleset. For n to be prime, either:
a ^ d mod n = 1
or
a ^ d * 2i mod n = -1


For some i, where 0 <= i <= r-1.

If you understood any of that, that’s great! To reiterate, you don’t need to understand the math behind this algorithm, just that it exists and that it’s sound.

Move to the next task when you’re ready!

```
Input #1: n > 3, an odd integer to be tested for primality
Input #2: k, the number of rounds of testing to perform
Output: "composite" if n is found to be composite, "probably prime" otherwise
 
write n as 2^r * d + 1 with d odd (by factoring out powers of 2 from n − 1)
WitnessLoop: repeat k times:
pick a random integer a in the range [2, n − 2]
    x ← a^d mod n
    if x = 1 or x = n − 1 then
        continue WitnessLoop
    repeat r − 1 times:
        x ← x^2 mod n
        if x = n − 1 then
            continue WitnessLoop
    return "composite"
return "probably prime"
```

Let’s break this down with that old-fashioned English I promised.

Input:
The input n represents some number we want to check the primality of, and since we’re sticking to some base cases we can assume this n is greater than 3 and odd.

The input k is how we control the accuracy of this test, and becomes involved with the WitnessLoop. We’ll talk about what it does in a second.


Before we simplify this algorithm with some good old-fashioned English, let’s take a look at the pseudocode:

Input #1: n > 3, an odd integer to be tested for primality
Input #2: k, the number of rounds of testing to perform
Output: "composite" if n is found to be composite, "probably prime" otherwise
 
write n as 2^r * d + 1 with d odd (by factoring out powers of 2 from n − 1)
WitnessLoop: repeat k times:
pick a random integer a in the range [2, n − 2]
    x ← a^d mod n
    if x = 1 or x = n − 1 then
        continue WitnessLoop
    repeat r − 1 times:
        x ← x^2 mod n
        if x = n − 1 then
            continue WitnessLoop
    return "composite"
return "probably prime"


Let’s break this down with that old-fashioned English I promised.

### Input:
The input n represents some number we want to check the primality of, and since we’re sticking to some base cases we can assume this n is greater than 3 and odd.

The input k is how we control the accuracy of this test, and becomes involved with the WitnessLoop. We’ll talk about what it does in a second.

### Output:
Our output is very binary. It tells us whether or not n is a composite or “probably prime” number. The reason why there’s “probably” in there is because this algorithm doesn’t enforce absolute certainty, but enough certainty with 4^-k odds of being wrong (2^-k odds in the worst case scenario).

To put this into perspective, we’ll be using a k value of 160 for this project.


### WitnessLoop:
The WitnessLoop is where we use all that math we talked about earlier. You can cross reference this pseudocode with LargestPrimeSerial.java if you want to see how each step gets implemented serially. However, in English, all this WitnessLoop does is simulate asking k witnesses whether or not n is composite or probably prime.

Because this algorithm can determine if a number is composite with 100% certainty, we can stop checking a given n if even 1 of our group of witnesses says it’s composite. However, if every k witness agrees that our n is “probably prime,” it’s a very safe bet that n is prime.

This WitnessLoop is where all the heavy lifting happens for this primality test and will be where we’ll be leveraging some parallelism to see if we can speed things up.

That’s enough math. Let’s get into the actual project!

Move to the next task when you’re ready to get started!

## Separating the driver from our RecursiveAction object
