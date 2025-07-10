# Largest Miller-Rabin Prime Candidate
The Miller-Rabin primality test is a probabilistic algorithm that determines whether a given number is likely to be prime. This is one of the simplest and fastest known tests for a polynomial-time deterministic primality test.

Given the pseudocode and a working serial implementation for this test, we’re going to see if we can convert this into a parallelized program and compare how it runs against the serial version.

Write a LargestPrimeForkJoin.java program that outputs the largest computable prime number in an allotted amount of time.
