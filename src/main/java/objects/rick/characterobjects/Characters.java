package objects.rick.characterobjects;

import java.util.ArrayList;

public class Characters {
    public Info info;
    public ArrayList<Result> results;

    @Override
    public String toString() {
        return results.get(1).name;
    }
}
