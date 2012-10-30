パズル「箱入り娘」
==================

Erlang
------
### 実行方法
#### コンパイル
    erlc hakoiri.erl

#### 実行
    erl -noshell -s hakoiri solve -s init stop | tee hakoiri-result.txt

### 結果
#### 実行環境
* OS: Ubuntu Server 12.04.1 LTS
* CPU: Core i5 2.3GHz * 2 (VMware Player)
* MEM: 2GB

#### 所要時間 (timeコマンドで計測)
* real    0m18.080s
* user    0m16.497s
* sys     0m0.416s
