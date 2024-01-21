package ottplatform.show.com;

import java.util.ArrayList;

public class CategoryItems {

    String category;
    ArrayList<MovieDataItem> movieDataItems;

    public CategoryItems(String category, ArrayList<MovieDataItem> movieDataItems) {
        this.category = category;
        this.movieDataItems = movieDataItems;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public ArrayList<MovieDataItem> getMovieDataItems() {
        return movieDataItems;
    }

    public void setMovieDataItems(ArrayList<MovieDataItem> movieDataItems) {
        this.movieDataItems = movieDataItems;
    }
}
