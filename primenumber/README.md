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

#### Scala
    time scala -cp target/scala-2.9.2/classes Eratosthenes 1000000 > /dev/null

    real    0m14.123s
    user    0m22.417s
    sys     0m0.772s

#### 環境
* OS: Ubuntu Server 12.04.1 LTS
* CPU: Core i5 2.3GHz * 2 (VMware Player)
* MEM: 2GB

#### 条件
* 上限数 = 1,000,000
   * 素数の数 = 78,498
* 出力は /dev/null にリダイレクト

素数判定
--------
* コマンドライン引数に指定した件数の素数を標準出力に出力する。

### 実行結果
#### Scala
##### 出力件数 = 78,498 (エラトステネスのふるいと同じ数を出力)
    time scala -cp target/scala-2.9.2/classes Prime 78498 > /dev/null

    real    0m1.035s
    user    0m1.164s
    sys     0m0.244s

##### 出力件数 = 1,000,000
    time scala -cp target/scala-2.9.2/classes Prime 1000000 > /dev/null

    real    0m11.333s
    user    0m11.769s
    sys     0m0.724s

#### 環境
* OS: Ubuntu Server 12.04.1 LTS
* CPU: Core i5 2.3GHz * 2 (VMware Player)
* MEM: 2GB
