package co.uk.random;

import android.app.Application;

/**
 * Test Application which prevents Robolectric from using {@link RandomApplication}.
 */
@SuppressWarnings("unused")
public class TestRandomApplication extends Application {
}
