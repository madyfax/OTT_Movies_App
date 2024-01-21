package ottplatform.show.com;

public class MovieDataItem {

    String title;
    String video_path;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVideo_path() {
        return video_path;
    }

    public void setVideo_path(String video_path) {
        this.video_path = video_path;
    }

    public MovieDataItem(String title, String video_path) {
        this.title = title;
        this.video_path = video_path;
    }
}
