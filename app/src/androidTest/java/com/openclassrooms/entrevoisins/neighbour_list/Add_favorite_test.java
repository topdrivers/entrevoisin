package com.openclassrooms.entrevoisins.neighbour_list;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static com.openclassrooms.entrevoisins.Matchers.detailsItemNameViewMatcher.childAtPosition;
import static com.openclassrooms.entrevoisins.Matchers.detailsItemViewMatcher.withTitle;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;

import static org.hamcrest.Matchers.allOf;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;



@RunWith(AndroidJUnit4.class)
public class Add_favorite_test {

    @Rule
    public ActivityScenarioRule<ListNeighbourActivity> mActivityRule =
            new ActivityScenarioRule<>(ListNeighbourActivity.class);

    @Test
    public  void check_add_favorite_list(){
        //click first item main recyclerview
        onView(ViewMatchers.withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        //click floating button favorite
        onView(ViewMatchers.withId(R.id.details_fragment_floating_button))
                .perform(click());

        //click back button
        onView(ViewMatchers.withId(R.id.details_fragment_back))
                .perform(click());

        //click forth item main recyclerview
        onView(ViewMatchers.withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(4, click()));

        //click floating button favorite
        onView(ViewMatchers.withId(R.id.details_fragment_floating_button))
                .perform(click());

        //click back button
        onView(ViewMatchers.withId(R.id.details_fragment_back))
                .perform(click());


        //click favorites tab
        onView(ViewMatchers.withText("FAVORITES"))
                .perform(ViewActions.click());

        //Check item count in favorites equals one
        onView(ViewMatchers.withId(R.id.list_favorite_neighbours))
                .check(withItemCount(2));

        //Verification textview "Caroline" position 0 de la recyclerview et du textview de la recyclerview favorite
        ViewInteraction textViewCaroline = onView(
                allOf(withId(R.id.item_list_name),
                        childAtPosition(
                                allOf(withId(R.id.constraint_layout),
                                        childAtPosition(
                                                withId(R.id.list_favorite_neighbours),
                                                0)),
                                1),
                        isDisplayed()));


        //Verification textview "Elodie" position 4 de la recyclerview et du textview de la recyclerview favorite
        ViewInteraction textViewElodie = onView(
                allOf(withId(R.id.item_list_name),
                        childAtPosition(
                                allOf(withId(R.id.constraint_layout),
                                        childAtPosition(
                                                withId(R.id.list_favorite_neighbours),
                                                1)),
                                1),
                        isDisplayed()));



        // resultat des comparaison caroline et elodie
        textViewElodie.check(matches(withTitle("Elodie")));
        textViewCaroline.check(matches(withTitle("Caroline")));


    }

}
