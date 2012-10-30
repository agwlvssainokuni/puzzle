パズル「ナンバープレース」
==========================

GLPK (GNU Linear Programming Kit)
---------------------------------
* ナンプレを整数計画法で解く。
* 整数計画法のソルバとしてGLPKを使用する。
* GLPKの入力ファイルを作成するのにRubyを使用する。

### 実行方法
#### 入力ファイル
    X X X X X X X X X
    X X X X X X X X X
    X X X X X X X X X
    X X X X X X X X X
    X X X X X X X X X
    X X X X X X X X X
    X X X X X X X X X
    X X X X X X X X X
    X X X X X X X X X
* `X` は「-」、または、「1」から「9」
* ファイル名「puzzleXXXX.txt」

#### 実行
    ./numpla.sh

#### 結果ファイル
* ファイル名「puzzleXXXX_answer.txt」
