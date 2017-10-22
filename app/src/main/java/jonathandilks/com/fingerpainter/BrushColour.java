package jonathandilks.com.fingerpainter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.graphics.Color.*;

public class BrushColour extends AppCompatActivity {

    int[] colours = {BLACK,
            BLUE,
            CYAN,
            DKGRAY,
            GRAY,
            GREEN,
            LTGRAY,
            MAGENTA,
            RED,
            TRANSPARENT,
            WHITE,
            YELLOW};

    GridView colourGrid = (GridView) findViewById(R.id.colourGrid);;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brush_colour);

        // Populate a List from Array elements
//        final List<Integer> coloursList = new ArrayList<>(Arrays.asList(colours));

        // Create a new ArrayAdapter
        final ArrayAdapter<Integer> gridViewArrayAdapter = new ArrayAdapter<>
                (this, android.R.layout.simple_list_item_1, coloursList);

        // Data bind GridView with ArrayAdapter (String Array elements)
        colourGrid.setAdapter(gridViewArrayAdapter);


    }
}
