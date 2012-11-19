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
 * 素数列を生成/表示する。
 */
object Prime {

	def main(args: Array[String]) =
		primes take args(0).toInt foreach println

	val primes: Stream[Long] = 2L #:: 3L #:: 5L #:: generate(5L)

	private def generate(n: Long): Stream[Long] = {
		val p = next(n + 2, primes)
		p #:: generate(p)
	}

	private def next(n: Long, p: Stream[Long]): Long =
		if (p.head * p.head > n)
			n
		else if (n % p.head == 0)
			next(n + 2, primes)
		else
			next(n, p.tail)

}
