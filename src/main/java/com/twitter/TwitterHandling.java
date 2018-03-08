package com.twitter;

import twitter4j.Query;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

import java.io.FileInputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;

class TwitterHandling {
    private Twitter twitter = new TwitterFactory().getInstance();
    private Query query;

    public TwitterHandling(String inputTag) {
        Properties properties = new Properties();
        try {
            InputStream input = new FileInputStream("/home/knoldus/java08Assignment02/src/main/resources/application.conf");
            properties.load(input);
            twitter.setOAuthConsumer(properties.getProperty("ConsumerKey"), properties.getProperty("ConsumerSecret"));
            twitter.setOAuthAccessToken
                    (new AccessToken(properties.getProperty("accessToken"), properties.getProperty("accessTokenSecret")));
            query = new Query(inputTag);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * number of tweets
     *
     * @return tweetsCount
     */
    public CompletableFuture<Integer> findingNoOfTweets() {
        return CompletableFuture.supplyAsync(() -> {
            Integer tweetsCount = 0;
            try {
                tweetsCount = twitter.search(query).getTweets().size();
            } catch (TwitterException e) {
                e.getMessage();
            }
            return tweetsCount;
        });
    }


    /**
     * AverageTweetsPerDay
     *
     * @return averageTweets
     */
    public CompletableFuture<Long> averageTweetsPerDay() {

        return CompletableFuture.supplyAsync(() -> {

            Long averageTweets = 0L;
            try {
                String startingDate = "2018-01-30";
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate oldDate = LocalDate.parse(startingDate, formatter);
                String currentDate = "2018-02-03";
                LocalDate newDate = LocalDate.parse(currentDate, formatter);
                Long diff = newDate.toEpochDay() - oldDate.toEpochDay();
                Integer averageTweetssize = twitter.search(query).getTweets().size();
                averageTweets = averageTweetssize / diff;
            } catch (TwitterException e) {
                e.getMessage();
            }
            return averageTweets;
        });
    }

    /**
     * Average Likes
     *
     * @return averageLike
     */
    //Average Like Count
    public CompletableFuture<Double> findingAverageLikes() {
        return CompletableFuture.supplyAsync(() -> {
            Double averageLike = 0.0;
            try {

                Integer tweetsize = twitter.search(query).getTweets().size();
                averageLike = twitter.search(query).getTweets().stream().map(tweets -> tweets.getFavoriteCount()).reduce((a, b) -> a + b).get() / tweetsize + 0.0;
            } catch (TwitterException ex) {
                ex.getMessage();
            }
            return averageLike;
        });
    }

    /**
     * Average ReTweets
     *
     * @return ReTweet
     */

    public CompletableFuture<Double> findingAverageReTweets() {
        return CompletableFuture.supplyAsync(() -> {
            Double Retweet = 0.0;
            try {
                Integer tweetsize = twitter.search(query).getTweets().size();
                Retweet = twitter.search(query).getTweets().stream().map(tweets -> tweets.getRetweetCount()).reduce((a, b) -> a + b).get() / tweetsize + 0.0;

            } catch (TwitterException te) {
                te.getMessage();
            }
            return Retweet;
        });
    }
}



