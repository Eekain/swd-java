package website;

import org.junit.jupiter.api.extension.ExtendWith;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME) // ez kell ahhoz, hogy futás közben érvényesüljön, különben nem lesz használható
@ExtendWith(SeleniumExtension.class)
public @interface SeleniumTest {

}
