package com.twitter;

public class TwitterApplication {

    public static void main(String args[]) {

        TwitterHandling tweets = new TwitterHandling("#knoldus");
        tweets.findingNoOfTweets()
                .thenAccept(nooftweets -> System.out.println("number of tweets" + nooftweets));
        tweets.averageTweetsPerDay()
                .thenAccept(averagetweets -> System.out.println("average tweets per day" + averagetweets));
        tweets.findingAverageLikes()
                .thenAccept(averagelikes -> System.out.println("average likes" + averagelikes));
        tweets.findingAverageReTweets()
                .thenAccept(averageretweet -> System.out.println("average retweet" + averageretweet));
        try {
            Thread.sleep(50000);
        } catch (InterruptedException e) {
            e.getMessage();
        }

    }
}



