
package statistics.matcher;

import statistics.Player;

import java.lang.reflect.Method;

public class HasFewerThan implements Matcher {

    private int value;
    private String fieldName;

    public HasFewerThan(int value, String category) {
        this.value = value;
        fieldName = category;
    }

    @Override
    public boolean matches(Player p) {
        return new Not(new HasAtLeast(this.value, this.fieldName)).matches(p);
    }    
    
}
