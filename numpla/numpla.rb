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

require 'tempfile'


# [処理フロー]
# (1) 入力ファイルから問題を読み込む。
# (2) 問題定義ファイルを作成する。
# (3) glpsolを実行する。
# (4) 結果ファイルから解を読み込む。
# (5) 問題の解答を表示する。



# (1) 入力ファイルから問題を読み込む。
#     ----ここから----
#     X X X X X X X X X
#     X X X X X X X X X
#     X X X X X X X X X
#     X X X X X X X X X
#     X X X X X X X X X
#     X X X X X X X X X
#     X X X X X X X X X
#     X X X X X X X X X
#     X X X X X X X X X
#     ----ここまで----
#     X = 「-」、「1」から「9」

koma = Hash.new
row = 1
while line = gets

	if line !~ /^([-1-9]) ([-1-9]) ([-1-9]) ([-1-9]) ([-1-9]) ([-1-9]) ([-1-9]) ([-1-9]) ([-1-9])$/
		next
	end

	koma["#{row}1"] = (if $1 == "-" then "0" else $1 end)
	koma["#{row}2"] = (if $2 == "-" then "0" else $2 end)
	koma["#{row}3"] = (if $3 == "-" then "0" else $3 end)
	koma["#{row}4"] = (if $4 == "-" then "0" else $4 end)
	koma["#{row}5"] = (if $5 == "-" then "0" else $5 end)
	koma["#{row}6"] = (if $6 == "-" then "0" else $6 end)
	koma["#{row}7"] = (if $7 == "-" then "0" else $7 end)
	koma["#{row}8"] = (if $8 == "-" then "0" else $8 end)
	koma["#{row}9"] = (if $9 == "-" then "0" else $9 end)

	row += 1
end



# (2) 問題定義ファイルを作成する。
#     [モデル]
#     - 解変数
#     - パラメタ
#     - 目的関数
#     - 制約条件
#     [データ]
#     - パラメタ (初期配置)
#     ※モデルは固定、パラメタは入力ファイルから決定。

model_file = nil
Tempfile.open("numpla_model") {|fp|
	model_file = fp.path

	# [モデル]
	# - パラメタ: BOARD (初期配置)、INI (計算用の初期配置データ)
	# - 解変数: X (マスの配置)
	# - 目的関数: COST (初期配置との近さ) => 最大化
	# - 制約条件: NumConst (マスの数字は一つだけ)
	#             RowConst (行に同じ数字なし)
	#             ColConst (列に同じ数字なし)
	#             BlockConst (ブロックに同じ数字なし)
	fp.write <<__MODEL__
# パラメタ
param BOARD{1..9, 1..9} integer, >= 0, <= 9;
param INI{row in 1..9, col in 1..9, num in 1..9} binary, :=
	if num = BOARD[row, col] then 1 else 0;
# 解変数
var X{1..9, 1..9, 1..9} binary;
# 制約条件
s.t. NumConst{row in 1..9, col in 1..9}: sum{num in 1..9} X[row, col, num] = 1;
s.t. RowConst{row in 1..9, num in 1..9}: sum{col in 1..9} X[row, col, num] = 1;
s.t. ColConst{col in 1..9, num in 1..9}: sum{row in 1..9} X[row, col, num] = 1;
s.t. BlockConst{brow in 0..2, bcol in 0..2, num in 1..9}:
	sum{srow in 1..3, scol in 1..3} X[brow*3 + srow, bcol*3 + scol, num] = 1;
# 目的関数
maximize COST:
	sum{row in 1..9, col in 1..9, num in 1..9} INI[row, col, num] * X[row, col, num];
# 解く＆初期配置の検証
solve;
check{row in 1..9, col in 1..9, num in 1..9: INI[row, col, num] = 1}
	X[row, col, num] = 1;
# 表示
for{row in 1..9} {
	printf "BOARD:";
	for{col in 1..9} {
		for{num in 1..9: X[row, col, num] = 1} {
			printf " %d", num;
		}
	}
	printf "\\n";
}
__MODEL__

	# [データ]
	# - パラメタ (BOARD: 初期配置)
	fp.write("data;\n")
	fp.write("param BOARD:\n")
	fp.write("  1 2 3 4 5 6 7 8 9 :=\n")
	for row in 1..9
		fp.write("#{row}")
		for col in 1..9
            fp.write(" ")
            fp.write(koma["#{row}#{col}"])
		end
		fp.write("\n")
	end
	fp.write(";\n")
	fp.write("end;\n")
}



# (3) glpsolを実行する。
#print IO.read(model_file)
result_file = nil
Tempfile.open("numpla_result") {|fp| result_file = fp.path }
system("glpsol", "--math", model_file, "--output", result_file)
#print IO.read(result_file)



# (4) 結果ファイルから解を読み込む。
result_koma = Hash.new
IO.foreach(result_file) {|line|

	if line !~ /^\s+\d+\s+X\[(\d+),(\d+),(\d+)\]\s+\*\s+(1)\s+0\s+1\s*$/
		next
	end

	result_koma["#{$1}#{$2}"] = "#{$3}"
}



# (5) 問題の解答を表示する。
for row in 1..9
	printf("+-----+-----+-----+\n") if row % 3 == 1
	for col in 1..9
		if col %3 == 1
			printf("|")
		else
			printf(" ")
		end
		printf(result_koma["#{row}#{col}"])
	end
	printf("|\n")
end
printf("+-----+-----+-----+\n")
