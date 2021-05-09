# 脳トレしりとりゲーム
### 概要
実験でソケット通信を利用したJavaのプログラムを個人で制作。  
一つのPC上にターミナルを2つ用意し、myShiritori.javaとoppShiritori.javaを実行すると、しりとりゲームをすることができる。
### 使用言語
Java
### ゲームのルール
* 4文字縛り
* 相手の単語の最後の二文字から始まる言葉を答える
* 相手の単語が日本語に存在しないと考えた場合、自分のターンに「審議」と入力することで相手の単語を変えさせることができる
* ひらがなのみを打ち込んで解答
### プログラムの流れ
1. ソケット通信の開始
2. ゲームオブジェクトを作成(同時に名前の登録が行われる)
3. 乱数を用いて先攻後攻を決め、相手にも同じメッセージを送信
4. 最初の単語を「しりとり」に設定
5. 相手が送った単語が正しいか判定
6. 勝敗が決まったらエラーメッセージを表示してゲーム終了(通信も終了する)
### 実行時の注意点
日本語が含まれるため、コンパイル時にUTF8でエンコードする必要がある
```
javac -encoding UTF-8 ○○.java
```
### 工夫した点
* 「審議」機能をつけることで、意味のない単語でずるすることができないようにした
* 見た目を楽しく読みやすくするため、ルール説明の文章はRPGゲームのように、一文字ずつ順に表示させるようにした
* ゲームオブジェクトにステータスを表す変数を持たせることで、次に何を実行するかを末端のクラスからサーバーに伝えるのが容易になった
### 課題
GUIを利用していないため、簡単に利用することができない。
### スライド
[脳トレしりとりゲーム](https://www.slideshare.net/TomomiKondo/ss-248137383) 
