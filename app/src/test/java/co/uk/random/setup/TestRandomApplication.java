package co.uk.random.setup;

import android.app.Application;

import co.uk.random.RandomApplication;

/**
 * Test Application which prevents Robolectric from using {@link RandomApplication}.
 */
@SuppressWarnings("unused")
public class TestRandomApplication extends Application {
}
