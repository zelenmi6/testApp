package json;

import java.io.Serializable;

/**
 * Created by Milan on 2/22/2017.
 */

public class ShowDetails implements Serializable {
    private String name;
    private String url;
    private String type;
    private String language;

    public ShowDetails() {

    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getType() {
        return type;
    }

    public String getLanguage() {
        return language;
    }

    @Override
    public String toString() {
        return name;
    }
}
