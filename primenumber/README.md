素数判定
========

エラトステネスのふるい
----------------------
* コマンドライン引数に指定した上限数以下の素数を標準出力に出力する。

### 実行結果
#### Ruby
    time ruby eratosthenes.rb 1000000 > /dev/null
    
    real    0m1.911s
    user    0m1.852s
    sys     0m0.048s

#### Scheme (Gauche)
    time gosh eratosthenes.scm 1000000 > /dev/null
    
    real    0m0.998s
    user    0m0.948s
    sys     0m0.044s

#### 環境
* OS: Ubuntu Server 12.04.1 LTS
* CPU: Core i5 2.3GHz * 2 (VMware Player)
* MEM: 2GB

#### 条件
* 上限数 = 1,000,000
   * 素数の数 = 78,498
* 出力は /dev/null にリダイレクト
