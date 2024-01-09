package org.javaFundamentals2.Question3;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Book {
    private static final Logger logger = LoggerFactory.getLogger(APIResponseParser.class);

    private String title;
    private int publicationYear;
    private double averageRating;
    private int ratingsCount;
    private String imageUrl;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public int getRatingsCount() {
        return ratingsCount;
    }

    public void setRatingsCount(int ratingsCount) {
        this.ratingsCount = ratingsCount;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public void printAllAttributes() {
        logger.info("Title: {}", getTitle());
        logger.info("Publication Year: {}", getPublicationYear());
        logger.info("Average Rating: {}", getAverageRating());
        logger.info("Ratings Count: {}", getRatingsCount());
        logger.info("Image URL: {}", getImageUrl());
        logger.info("Name: {}", getName());
    }
}
