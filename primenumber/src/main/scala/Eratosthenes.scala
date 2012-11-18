/*
 * Copyright 2012 agwlvssainokuni
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * エラトステネスの篩。
 */
object Eratosthenes {

	type T = Long

	def main(args: Array[String]) =
		eratosthenes(2, args(0).toInt, Map[T, Boolean](), 0)

	def eratosthenes(n: T, max: T, notprime: Map[T, _], count: T): T =
		if (n > max) count
		else if (notprime contains n)
			eratosthenes(n + 1, max, notprime - n, count)
		else {
			println(n)
			eratosthenes(n + 1, max, sieve(n, n, max, notprime), count + 1)
		}

	def sieve(i: T, n: T, max: T, notprime: Map[T, _]): Map[T, _] =
		if (i > max) notprime
		else sieve(i + n, n, max, notprime + (i -> true))

}
