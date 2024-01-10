package org.JavaFundamentals2.question3;



public class APIResponseParser {
    public static String parser(String response, String startRule, String endRule) {
        int startIndex = response.indexOf(startRule) + startRule.length();
        int endIndex = response.indexOf(endRule, startIndex);
        return response.substring(startIndex, endIndex);
    }

    private static String parser(String response, String[] startRule, String endRule) {
        int startIndex = response.indexOf(startRule[0]);
        for (int i = 1; i < startRule.length; i++) {
            startIndex = response.indexOf(startRule[i], startIndex);
        }
        startIndex += startRule[startRule.length - 1].length();
        int endIndex = response.indexOf(endRule, startIndex);
        return response.substring(startIndex, endIndex);
    }

    public static Book parse(String response) {
        Book book = new Book();
        String endRule = "<";

        String startRule = "<title>";
        String title = parser(response, startRule, endRule);
        book.setTitle(title);

        startRule = "<original_publication_year type=\"integer\">";
        String publicationYear = parser(response, startRule, endRule);
        int pubYear = Integer.parseInt(publicationYear);
        book.setPublicationYear(pubYear);

        startRule = "<average_rating>";
        String averageRating = parser(response, startRule, endRule);
        double avgRating = Double.parseDouble(averageRating);
        book.setAverageRating(avgRating);

        String[] ratingsStartRule = {"<ratings_count type=\"integer\">"};
        String ratings = parser(response, ratingsStartRule, endRule);
        ratings = ratings.replace(",", "");
        int ratingCount = Integer.parseInt(ratings);
        book.setRatingsCount(ratingCount);

        startRule = "<image_url>";
        String imageUrl = parser(response, startRule, endRule);
        book.setImageUrl(imageUrl);

        String[] authorStartRule = {"<author>", "<name>"};
        String authorName = parser(response, authorStartRule, endRule);
        book.setName(authorName);

        return book;
    }

    public static void main(String[] args) {
        String response = """
        <work>
            <id type="integer">2361393</id>
            <books_count type="integer">813</books_count>
            <ratings_count type="integer">1,16,315</ratings_count>
            <text_reviews_count type="integer">3439</text_reviews_count>
            <original_publication_year type="integer">1854</original_publication_year>
            <original_publication_month type="integer" nil="true"/>
            <original_publication_day type="integer" nil="true"/>
            <average_rating>3.79</average_rating>
            <best_book type="Book">
                <id type="integer">16902</id>
                <title>Walden</title>
                <author>
                    <id type="integer">10264</id>
                    <name>Henry David Thoreau</name>
                </author>
                <image_url>http://images.gr-assets.com/books/1465675526m/16902.jpg</image_url>
                <small_image_url>http://images.gr-assets.com/books/1465675526s/16902.jpg</small_image_url>
            </best_book>
        </work>
        """;
        Book obj1 = parse(response);

            obj1.printAllAttributes();

    }
}
