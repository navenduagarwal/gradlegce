package com.sparshik.jokesource;

import java.util.ArrayList;
import java.util.Random;

public class Joke {
    private ArrayList<String> jokes;

    public Joke() {
        jokes = new ArrayList<>();
        jokes.add("I went to the zoo the other day, there was only one dog in it, it was a shitzu");
        jokes.add("Dyslexic man walks into a bra");
        jokes.add("Police arrested two kids yesterday, one was drinking battery acid, the other was eating fireworks. They charged one - and let the other one off.");
        jokes.add("Two aerials meet on a roof - fall in love - get married.  The ceremony was rubbish - but the reception was brilliant. ");
        jokes.add("I went to buy some camouflage trousers the other day but I couldn't find any.");
    }

    public String getJoke() {
        int size = jokes.size();
        Random rand = new Random();
        if (size > 0) {
            int jokeNumber = rand.nextInt(size);
            return jokes.get(jokeNumber);
        } else {
            return "What a Joke !";
        }
    }
}
