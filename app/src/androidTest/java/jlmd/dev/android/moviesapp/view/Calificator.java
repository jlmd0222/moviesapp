package jlmd.dev.android.moviesapp.view;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import jlmd.dev.android.moviesapp.MainActivity;
import jlmd.dev.android.moviesapp.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.endsWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class Calificator {
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    private MainActivity mainActivity;

    @Before
    public void setup(){
        mainActivity = mActivityTestRule.getActivity();
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }

    @Test
    public void emptyTest() {
        clickInputSearch();
        keyPressInputSearch("empty");

        onView(allOf(withText("La lista esta vacia"))).check(matches(withText("La lista esta vacia")));
    }

    @Test
    public void squadTest() {
        clickInputSearch();
        keyPressInputSearch("Squad");

        onView(allOf(withId(R.id.tvMovieName))).check(matches(withText("The Suicide Squad")));
    }

    @Test
    public void spaceTest() {
        clickInputSearch();
        keyPressInputSearch("Space");

        onView(allOf(withId(R.id.tvMovieName))).check(matches(withText("Space Jam: A New Legacy")));
    }

    @Test
    public void squadClickDetailTest() throws InterruptedException {
        clickInputSearch();
        keyPressInputSearch("squad");

        onView((withId(R.id.cvMovie))).perform(click());
        Thread.sleep(3000);

        onView(allOf(withId(R.id.tvTitleDetail))).check(matches(withText(endsWith("The Suicide Squad"))));
        onView(allOf(withId(R.id.tvReleaseDateDetail))).check(matches(withText("2021-07-28")));
    }

    private void keyPressInputSearch(String valueToWrite) {
        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.editTextSearch),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.textInputLayoutSearch),
                                        0),
                                0),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText(valueToWrite), closeSoftKeyboard());
    }

    private void clickInputSearch() {
        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.editTextSearch),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.textInputLayoutSearch),
                                        0),
                                0),
                        isDisplayed()));
        appCompatEditText.perform(click());
    }
}
