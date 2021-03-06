package com.example.android.unscramble.ui.game

import android.text.Spannable
import android.text.SpannableString
import android.text.style.TtsSpan
import android.util.Log
import androidx.core.text.set
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class GameViewModel:ViewModel() {

    private var _score = MutableLiveData(0)
    val score :LiveData<Int>
        get() = _score

    private var _currentWordCount = MutableLiveData(0)
    val currentWordCount:LiveData<Int>
        get() = _currentWordCount

    private val _currentScrambledWord = MutableLiveData<String>()
    val currentScrambleWord: LiveData<String>
        get() = _currentScrambledWord

    //talkBackを有効にして使用する時、currentScrambleWordが単語のように読み上げられるのではなく
    //個々の文字を読み上げられるように設定する
//    val currentScrambleWord: LiveData<Spannable> = Transformations.map(_currentScrambledWord){
//        if (it == null){
//            SpannableString("")
//        }else{
//            val scrambleWord = it.toString()
//            val spannable:Spannable = SpannableString(scrambleWord)
//            spannable.setSpan(
//                TtsSpan.VerbatimBuilder(scrambleWord).build(),
//                0,
//                scrambleWord.length,
//                Spannable.SPAN_INCLUSIVE_INCLUSIVE
//            )
//            spannable
//        }
//    }


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
            _currentScrambledWord.value = String(tempWord)
            _currentWordCount.value = (_currentWordCount.value)?.inc()
            wordList.add(currentWord)
        }
    }

    //単語カウントをチェックする（継続するか終了するか）
    fun nextWord():Boolean{
        return if (currentWordCount.value!! < MAX_NO_OF_WORDS) {
            getNextWord()
            true
        } else false
    }

    //スコアの更新
    private fun increaseScore(){
        _score.value = (_score.value)?.plus(SCORE_INCREASE)
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
        _score.value = 0
        _currentWordCount.value = 0
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