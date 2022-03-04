package com.example.android.unscramble.ui.game

import android.util.Log
import androidx.lifecycle.ViewModel

class GameViewModel:ViewModel() {

    private var _score = 0
    val score :Int
        get() = _score

    private var _currentWordCount = 0
    val currentWordCount:Int
        get() = _currentWordCount

    private lateinit var _currentScrambledWord:String
    val currentScrambleWord:String
        get() = _currentScrambledWord

    private val wordList : MutableList<String> = mutableListOf()
    private lateinit var currentWord:String

    //次に表示する単語をシャッフル、今までに出ていない単語か確かめる
    private fun getNextWord(){
        currentWord = allWordsList.random()

        val tempWord= currentWord.toCharArray()
        tempWord.shuffle()
        while (String(tempWord).equals(currentWord, false))
            tempWord.shuffle()

        if (wordList.contains(currentWord)){
            getNextWord()
        }else{
            _currentScrambledWord = String(tempWord)
            ++_currentWordCount
            wordList.add(currentWord)
        }
    }

    //単語カウントをチェックする（継続するか終了するか）
    fun nextWord():Boolean{
        return if (currentWordCount < MAX_NO_OF_WORDS) {
            getNextWord()
            true
        } else false
    }

    //スコアの更新
    private fun increaseScore(){
        _score += SCORE_INCREASE
    }

    //単語が正しいかチェック
    fun isUserWordCurrent(playerWord:String):Boolean{
        return if (playerWord.equals(currentWord, true)){
            increaseScore()
            true
        }else false
    }

    //play_againの処理、カウントリセット
    fun reinitializeData(){
        _score = 0
        _currentWordCount = 0
        wordList.clear()
        getNextWord()
    }

    //ライフサイクルを確認するためのログの表示
    init{
        Log.d("GameFragment", "GameViewModel created!")
        getNextWord()
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("GameFragment", "GameViewModel destroyed!")
    }

}