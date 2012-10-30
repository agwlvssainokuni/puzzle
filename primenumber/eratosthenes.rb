#!/usr/bin/ruby
# coding: utf-8
#
# Copyright 2012 agwlvssainokuni
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#

max_num = ARGV[0].to_i

count = 0
not_prime = {}
(2..max_num).each {|num|
	next if not_prime.delete(num)
	puts num
	count += 1
	(num..(max_num/num)).each {|i| not_prime[i * num] = true }
}
