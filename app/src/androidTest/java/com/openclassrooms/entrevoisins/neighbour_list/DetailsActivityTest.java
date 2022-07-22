package com.openclassrooms.entrevoisins.neighbour_list;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static com.openclassrooms.entrevoisins.Matchers.detailsItemViewMatcher.withTitle;
import android.content.Context;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(AndroidJUnit4.class)
public class DetailsActivityTest {

    // FOR DATA
    private Context context;

    @Rule
    public ActivityScenarioRule<ListNeighbourActivity> mActivityRule =
            new ActivityScenarioRule<>(ListNeighbourActivity.class);

    @Before
    public void setup() {
        this.context =  getApplicationContext().getApplicationContext();
    }

    /**
     * We ensure that our info item name is correct
     */
    @Test
    public void user_textview_equals_Caroline() {

        //Click first item main recyclerview
        onView(withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        //check field name equals caroline
        onView(withId(R.id.details_fragment_name))
                .check(matches(withTitle(context.getString((R.string.textview_name_for_test)))));

    }

    @Test
    public void check_onClickItem_detailActivityOpened() {
        //detailActivuty launched
        //Click item
        onView(withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
        //item name is displayed
        onView(withId(R.id.details_fragment_name)).check(matches(isDisplayed()));
    }


}
