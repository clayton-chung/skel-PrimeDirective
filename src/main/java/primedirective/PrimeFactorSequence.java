package primedirective;

import java.util.ArrayList;
import java.util.List;

public class PrimeFactorSequence {
	private List<Integer> primes;
	private int upperBound;

	/**
	 * Create a PrimeFactorSequence object with a defined upperbound.
	 *
	 * @param _upperBound the upper bound for sequences and primes in this instance,
	 *                    {@code upperBound > 0}.
	 */
	public PrimeFactorSequence(int _upperBound) {
		upperBound = _upperBound;
		primes = Primes.getPrimes(upperBound);
	}

	/**
	 * Obtain a sequence L[0 .. upper bound] where L[i] represents the number of prime factors i
	 * has, including repeated factors
	 *
	 * @return sequence L[0 .. upper bound] where L[i] represents the number of prime factors i
	 * has, including repeated factors
	 */
	public List<Integer> primeFactorSequence() {
		List<Integer> seq = new ArrayList<>();

		for (int i = 0; i <= upperBound; i++) {
			int currentNum = i;
			int count = 0;

			for (int prime : primes) {
				while (currentNum % prime == 0 && currentNum > 1) {
					currentNum /= prime;
					count++;
				}
			}
			seq.add(count);
		}

		return seq;
	}

	/**
	 * Obtain a sequence L that is sorted in ascending order and where L[i] has exactly m
	 * prime factors (including repeated factors) and L[i] <= upper bound
	 *
	 * @param m the number of prime factors that each element of the output sequence has
	 * @return a sequence L that is sorted in ascending order and where L[i] has exactly
	 * m prime factors (including repeated factors) and L[i] <= upper bound
	 */
	public List<Integer> numbersWithMPrimeFactors(int m) {
		List<Integer> seq = new ArrayList<>();
		for (int i = 0; i <= upperBound; i++) {
			int currentNum = i;
			int count = 0;

			for (int prime : primes) {
				while (currentNum % prime == 0 && currentNum > 1) {
					currentNum /= prime;
					count++;
				}
			}
			if (count == m) {
				seq.add(i);
			}

		}

		return seq;
	}

	/**
	 * Obtain a sequence of integer pairs [(Sa, Sb)] where Sa and Sb have exactly m prime factors
	 * (including repeated factors), and where |Sa - Sb| <= gap and where Sa and Sb are
	 * adjacent each other in the output of {@code numbersWithMPrimeFactors(m)}
	 *
	 * @param m   the number of prime factors that each element in the output sequence has
	 * @param gap the maximum gap between two adjacent entries of interest in the output
	 *            of {@code numbersWithMPrimeFactors(m)}
	 * @return a sequence of integer pairs [(Sa, Sb)] where Sa and Sb have exactly
	 * m prime factors (including repeated factors), and where |Sa - Sb| <= gap and where
	 * Sa and Sb are adjacent each other in the output of {@code numbersWithMPrimeFactors(m)}
	 */
	public List<IntPair> numbersWithMPrimeFactorsAndSmallGap(int m, int gap) {
		List<IntPair> listOfPairs = new ArrayList<>();
		List<Integer> seq = numbersWithMPrimeFactors(m);

		for (int i = 0; i < seq.size() - 1; i++) {
			if (seq.get(i + 1) - seq.get(i) <= gap) {
				IntPair pair = new IntPair(seq.get(i + 1), seq.get(i));
				listOfPairs.add(pair);
			}
		}

		return listOfPairs;
	}

	/**
	 * Transform n to a larger prime (unless n is already prime) using the following steps:
	 * <p>
	 *     <ul>
	 *         <li>A 0-step where we obtain 2 * n + 1</li>,
	 *         <li>or a 1-step where we obtain n + 1</li>.
	 *     </ul>
	 *      We can apply these steps repeatedly, with more details in the problem statement.
	 * </p>
	 *
	 * @param n the number to transform
	 * @return a string representation of the smallest transformation sequence
	 */
	public String changeToPrime(int n) {
		/*
		Recursion
		Base cases: n is prime, or n > upper bound
		Recursive step: use the 0-step or the 1-step
						Return the string that is shortest
		 */

		if (primes.contains(n)) {
			return "";
		} else if (n > upperBound) {
			return "-";
		}

		int n_new0 = 2 * n + 1;
		String step0 = "0" + changeToPrime(n_new0);

		int n_new1 = n + 1;
		String step1 = "1" + changeToPrime(n_new1);


		if (step0.endsWith("-")) {
			return step1;
		} else if (step1.endsWith("-")) {
			return step0;
		} else {
			return step0.length() <= step1.length() ? step0 : step1;
		}
	}

}
