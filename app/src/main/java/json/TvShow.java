package json;

import java.io.Serializable;

/**
 * Created by Milan on 2/20/2017.
 */

public class TvShow implements Serializable {
    private int id;
    private String url;
    private String name;
    private int season;
    private int number;
    private ShowEmbedded _embedded;

    public TvShow() {

    }

    public int getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    public int getSeason() {
        return season;
    }

    public int getNumber() {
        return number;
    }

    public ShowEmbedded get_embedded() {
        return _embedded;
    }

    @Override
    public String toString() {
        return _embedded.getShowDetails().getName();
    }
}
