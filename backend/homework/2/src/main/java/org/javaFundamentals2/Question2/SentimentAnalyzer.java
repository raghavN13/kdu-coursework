package org.javaFundamentals2.Question2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.*;

public class SentimentAnalyzer {
    private static final Logger logger = LoggerFactory.getLogger(SentimentAnalyzer.class);


    public static int[] detectProsAndCons(String review, String[][] featureSet, String[] posOpinionWords, String[] negOpinionWords) {
        if (review == null || featureSet == null || posOpinionWords == null || negOpinionWords == null) {
            throw new IllegalArgumentException("Input arguments cannot be null");
        }

        int size = featureSet.length;
        int[] arr = new int[size];

        for (int j = 0; j < size; j++) {
            String[] temp = featureSet[j];

            for (int i = 0; i < temp.length; i++) {
                String feature1 = temp[i];
                int opinion = getOpinionOnFeature(review, feature1, posOpinionWords, negOpinionWords);
                if (opinion == 1 || opinion == -1) {
                    arr[j] = opinion;
                    break;
                }
            }

            if (arr[j] != 1 && arr[j] != -1) {
                arr[j] = 0;
            }
        }
        return arr;
    }

    private static int getOpinionOnFeature(String review, String feature, String[] posOpinionWords, String[] negOpinionWords) {
        if (review == null || feature == null || posOpinionWords == null || negOpinionWords == null) {
            throw new IllegalArgumentException("Input arguments cannot be null");
        }

        int result = checkForWasPhrasePattern(review, feature, posOpinionWords, negOpinionWords);
        if (result != 0) {
            return result;
        }

        return checkForOpinionFirstPattern(review, feature, posOpinionWords, negOpinionWords);
    }

    private static int checkForWasPhrasePattern(String review, String feature, String[] posOpinionWords, String[] negOpinionWords) {
        String pattern1 = feature + " was ";
        String pattern = pattern1.toLowerCase();

        for (String posOpinionWord : posOpinionWords) {
            String updatedPattern = pattern + posOpinionWord;
            if (review.contains(updatedPattern)) {
                return 1;
            }
        }

        for (String negOpinionWord : negOpinionWords) {
            String updatedPattern = pattern + negOpinionWord;
            if (review.contains(updatedPattern)) {
                return -1;
            }
        }

        return 0;
    }

    private static int checkForOpinionFirstPattern(String review, String feature, String[] posOpinionWords, String[] negOpinionWords) {
        for (String posOpinionWord : posOpinionWords) {
            String updatedPattern1 = posOpinionWord + " " + feature;
            String updatedPattern = updatedPattern1.toLowerCase();
            if (review.contains(updatedPattern)) {
                return 1;
            }
        }

        for (String negOpinionWord : negOpinionWords) {
            String updatedPattern = negOpinionWord + " " + feature;
            String updatedPattern1 = updatedPattern.toLowerCase();
            if (review.contains(updatedPattern)) {
                return -1;
            }
        }

        return 0;
    }

    public static void main(String[] args) {
        try {
            String review = "Haven't been here in years! Fantastic service and the food was delicious! Definetly will be a frequent flyer! Francisco was very attentive";
            String[][] featureSet = {
                    {"ambiance", "ambience", "atmosphere", "decor"},
                    {"dessert", "ice cream", "desert"},
                    {"food"},
                    {"soup"},
                    {"service", "management", "waiter", "waitress", "bartender", "staff", "server"}};

            String[] posOpinionWords = {"good", "fantastic", "friendly", "great", "excellent", "amazing",
                    "awesome", "delicious"};
            String[] negOpinionWords = {"slow", "bad", "horrible", "awful", "unprofessional", "poor"};

            String reviewTemp = review;
            review = reviewTemp.toLowerCase();

            int[] ans = detectProsAndCons(review, featureSet, posOpinionWords, negOpinionWords);

            logger.info("Result: {}", Arrays.toString(ans));

        } catch (IllegalArgumentException e) {
            logger.error("Error: " + e.getMessage());
        }
    }
}
