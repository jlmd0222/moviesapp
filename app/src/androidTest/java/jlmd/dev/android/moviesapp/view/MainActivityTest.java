package jlmd.dev.android.moviesapp.view;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import jlmd.dev.android.moviesapp.MainActivity;
import jlmd.dev.android.moviesapp.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static jlmd.dev.android.moviesapp.view.TestUtils.withRecyclerView;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void deployRecyclerViewTest() throws InterruptedException {
        Thread.sleep(2000);
        onView(withId(R.id.recyclerViewSearchResults)).check(matches(isDisplayed()));
    }

    @Test
    public void selectItemRecyclerViewTest(){
        onView(withRecyclerView(R.id.recyclerViewSearchResults)
                .atPositionOnView(0, R.id.tvMovieName))
                .check(matches(withText("The Suicide Squad")));
    }

    @Test
    public void selectButtonInHolderTest() throws InterruptedException {
        onView(withId(R.id.recyclerViewSearchResults)).perform(TestUtils.actionOnItemViewAtPosition(1,
                R.id.cvMovie,
                click()));

        Thread.sleep(3000);
        onView(withId(R.id.tvReleaseDateDetail)).check(matches(isDisplayed()));
    }

    @Test
    public void recyclerScrollTest() {
        RecyclerView recyclerView = mainActivityActivityTestRule.getActivity().findViewById(R.id.recyclerViewSearchResults);
        int countItems = recyclerView.getAdapter().getItemCount();
        onView(withId(R.id.recyclerViewSearchResults)).perform(RecyclerViewActions.scrollToPosition(countItems -1));
    }
}
