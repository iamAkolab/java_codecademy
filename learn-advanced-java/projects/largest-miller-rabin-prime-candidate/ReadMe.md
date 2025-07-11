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
