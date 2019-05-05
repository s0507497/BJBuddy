package com.student.bjbuddy

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var dealerCards = mutableListOf<Int>()
        var playerCards = mutableListOf<Int>()
        var hitIndex = 0

        var cardImages = mutableListOf(R.drawable.ac,R.drawable.ah,R.drawable.ad,R.drawable.`as`,
            R.drawable.c2,R.drawable.h2,R.drawable.d2,R.drawable.s2,R.drawable.c3,R.drawable.h3,
            R.drawable.d3,R.drawable.s3,R.drawable.c4,R.drawable.h4,R.drawable.d4,R.drawable.s4,
            R.drawable.c5,R.drawable.h5,R.drawable.d5,R.drawable.s5,R.drawable.c6,R.drawable.h6,
            R.drawable.d6,R.drawable.s6,R.drawable.c7,R.drawable.h7,R.drawable.d7,R.drawable.s7,
            R.drawable.c8,R.drawable.h8,R.drawable.d8,R.drawable.s8,R.drawable.c9,R.drawable.h9,
            R.drawable.d9,R.drawable.s9,R.drawable.c10,R.drawable.h10,R.drawable.d10,R.drawable.s10,
            R.drawable.jc,R.drawable.jh,R.drawable.jd,R.drawable.js,R.drawable.qc,R.drawable.qh,
            R.drawable.qd,R.drawable.qs,R.drawable.kc,R.drawable.kh,R.drawable.kd,R.drawable.ks)
        var deck = mutableListOf(1,1,1,1,2,2,2,2,3,3,3,3,4,4,4,4,5,5,5,5,6,6,6,6,7,7,7,7,8,8,8,8,
            9,9,9,9,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10)

        val dealerCard1 = findViewById<ImageView>(R.id.dealerCard1)
        val dealerCard2 = findViewById<ImageView>(R.id.dealerCard2)
        val playerCard1 = findViewById<ImageView>(R.id.playerCard1)
        val playerCard2 = findViewById<ImageView>(R.id.playerCard2)
        val dealerHit1 = findViewById<ImageView>(R.id.dealerHit1)
        val dealerHit2 = findViewById<ImageView>(R.id.dealerHit2)
        val dealerHit3 = findViewById<ImageView>(R.id.dealerHit3)
        val dealerHit4 = findViewById<ImageView>(R.id.dealerHit4)
        val playerHit1 = findViewById<ImageView>(R.id.playerHit1)
        val playerHit2 = findViewById<ImageView>(R.id.playerHit2)
        val playerHit3 = findViewById<ImageView>(R.id.playerHit3)
        val playerHit4 = findViewById<ImageView>(R.id.playerHit4)
        val playButton = findViewById<Button>(R.id.playButton)
        val hitButton = findViewById<Button>(R.id.hitButton)
        val stayButton = findViewById<Button>(R.id.stayButton)
        val resultView = findViewById<TextView>(R.id.resultsTextView)

        fun init_deck(){
            deck = mutableListOf(1,1,1,1,2,2,2,2,3,3,3,3,4,4,4,4,5,5,5,5,6,6,6,6,7,7,7,7,8,8,8,8,
                9,9,9,9,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10)
        }

        fun init_card_images(){
            cardImages = mutableListOf(R.drawable.ac,R.drawable.ah,R.drawable.ad,R.drawable.`as`,
                R.drawable.c2,R.drawable.h2,R.drawable.d2,R.drawable.s2,R.drawable.c3,R.drawable.h3,
                R.drawable.d3,R.drawable.s3,R.drawable.c4,R.drawable.h4,R.drawable.d4,R.drawable.s4,
                R.drawable.c5,R.drawable.h5,R.drawable.d5,R.drawable.s5,R.drawable.c6,R.drawable.h6,
                R.drawable.d6,R.drawable.s6,R.drawable.c7,R.drawable.h7,R.drawable.d7,R.drawable.s7,
                R.drawable.c8,R.drawable.h8,R.drawable.d8,R.drawable.s8,R.drawable.c9,R.drawable.h9,
                R.drawable.d9,R.drawable.s9,R.drawable.c10,R.drawable.h10,R.drawable.d10,R.drawable.s10,
                R.drawable.jc,R.drawable.jh,R.drawable.jd,R.drawable.js,R.drawable.qc,R.drawable.qh,
                R.drawable.qd,R.drawable.qs,R.drawable.kc,R.drawable.kh,R.drawable.kd,R.drawable.ks)
        }

        fun resetImages(){
            dealerHit1.setImageResource(R.color.colorPrimaryDark)
            dealerHit2.setImageResource(R.color.colorPrimaryDark)
            dealerHit3.setImageResource(R.color.colorPrimaryDark)
            dealerHit4.setImageResource(R.color.colorPrimaryDark)
            playerHit1.setImageResource(R.color.colorPrimaryDark)
            playerHit2.setImageResource(R.color.colorPrimaryDark)
            playerHit3.setImageResource(R.color.colorPrimaryDark)
            playerHit4.setImageResource(R.color.colorPrimaryDark)
        }

        fun drawCard() : Int = Random.nextInt(0,deck.size)

        fun removeCard(index:Int){
            deck.removeAt(index)
            cardImages.removeAt(index)
        }

        fun checkForBust(hand:MutableList<Int>) : Boolean = hand.sum() > 21

        fun dealerShouldHit(hand:MutableList<Int>) : Boolean {
            if(hand.sum() >= 17) return false

            if(hand.contains(1) && hand.sum() + 10 > 17) return false

            return true
        }

        fun getFinalCardValue(hand:MutableList<Int>) : Int{
            if(!hand.contains(1)) return hand.sum()

            if(hand.sum() + 10 <= 21) return hand.sum() + 10

            return hand.sum()
        }

        fun endHand(){
            //draw dealer second card
            var newCardIndex = drawCard()
            var dealerHitIndex = 0
            dealerCard2.setImageResource(cardImages[newCardIndex])
            dealerCards.add(deck[newCardIndex])
            removeCard(newCardIndex)

            //dealer continue to hit to hard 17 / soft 18
            while(dealerShouldHit(dealerCards)){
                newCardIndex = drawCard()
                dealerCards.add(deck[newCardIndex])
                when(dealerHitIndex){
                    0 -> dealerHit1.setImageResource(cardImages[newCardIndex])
                    1 -> dealerHit2.setImageResource(cardImages[newCardIndex])
                    2 -> dealerHit3.setImageResource(cardImages[newCardIndex])
                    3 -> dealerHit4.setImageResource(cardImages[newCardIndex])
                }
                removeCard(newCardIndex)
                dealerHitIndex += 1
                if(dealerHitIndex > 3) break
            }

            //get final values
            val dealerFinal = getFinalCardValue(dealerCards)
            val playerFinal = getFinalCardValue(playerCards)

            val winner : String

            if(checkForBust(playerCards)) {winner = "Player Bust\nDealer Wins!"}
            else if (checkForBust(dealerCards)) {winner = "Dealer Bust\nPlayer Wins!"}
            else if (dealerFinal > playerFinal) {winner = "Dealer: ${dealerFinal}\nPlayer: ${playerFinal}\nDealer Wins!"}
            else if (playerFinal > dealerFinal) {winner = "Dealer: ${dealerFinal}\n" +
                    "Player: ${playerFinal}\nPlayer Wins!"}
            else {winner = "Draw!"}

            resultView.text = winner
            playButton.visibility = Button.VISIBLE
            hitButton.visibility = Button.INVISIBLE
            stayButton.visibility = Button.INVISIBLE

        }




        playButton.setOnClickListener {
            //init deck and images
            init_card_images()
            init_deck()
            resetImages()
            playerCards.clear()
            dealerCards.clear()
            resultView.text = ""
            dealerCard2.setImageResource(R.drawable.red_back)
            hitIndex = 0

            //get card for dealer
            val dealer1 = drawCard()
            dealerCard1.setImageResource(cardImages[dealer1])
            dealerCards.add(deck[dealer1])
            //remove card from deck
            removeCard(dealer1)

            val player1 = drawCard()
            playerCard1.setImageResource(cardImages[player1])
            playerCards.add(deck[player1])
            removeCard(player1)

            val player2 = drawCard()
            playerCard2.setImageResource(cardImages[player2])
            playerCards.add(deck[player2])
            removeCard(player2)

            //hide button
            playButton.visibility = Button.INVISIBLE

            //show hit and stay buttons
            hitButton.visibility = Button.VISIBLE
            stayButton.visibility = Button.VISIBLE
        }

        hitButton.setOnClickListener {
            val newCardIndex = drawCard()


            when(hitIndex){
                0 -> playerHit1.setImageResource(cardImages[newCardIndex])
                1 -> playerHit2.setImageResource(cardImages[newCardIndex])
                2 -> playerHit3.setImageResource(cardImages[newCardIndex])
                3 -> playerHit4.setImageResource(cardImages[newCardIndex])
            }

            playerCards.add(deck[newCardIndex])
            removeCard(newCardIndex)

            hitIndex += 1

            //check for player bust
            if(checkForBust(playerCards)){
                endHand()
            }
        }

        stayButton.setOnClickListener {
            endHand()
        }


    }
}
