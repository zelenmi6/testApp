package json;

import java.io.Serializable;

/**
 * Created by Milan on 2/22/2017.
 */

public class ShowEmbedded implements Serializable{

    ShowDetails show;

    public ShowDetails getShowDetails() {
        return show;
    }

    @Override
    public String toString() {
        return "Embedded " + show.getName();
    }

}
