package jonathandilks.com.fingerpainter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class BrushColour extends AppCompatActivity {

    String[] colours = {"black",
            "darkgray",
            "gray",
            "lightgrey",
            "white",
            "red",
            "green",
            "blue",
            "yellow",
            "cyan",
            "magenta",
            "maroon",
            "navy",
            "olive",
            "purple",
            "teal"};

    GridView colourGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brush_colour);

        Bundle bundle = getIntent().getExtras();

        colourGrid = (GridView) findViewById(R.id.colourGrid);
        int currentColourPosition = 0;
        for (int i = 0; i < colours.length; i++) {
            if (Color.parseColor(colours[i]) == bundle.getInt("colour"))
            {
                currentColourPosition = i;
                break;
            }
        }
        colourGrid.setAdapter(new ColourGridAdapter(this, currentColourPosition));

        colourGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Intent result = new Intent();
                Bundle bundle = new Bundle();

                bundle.putInt("colour", Color.parseColor(colours[position]));

                result.putExtras(bundle);
                setResult(Activity.RESULT_OK, result);

                finish();
            }
        });
    }

    public class ColourGridAdapter extends BaseAdapter {
        private Context context;
        private int currentColourPosition;
        private int size;

        public ColourGridAdapter(Context context, int currentColourPosition) {
            this.context = context;
            this.currentColourPosition = currentColourPosition;
            this.size = 350;
        }

        public int getCount() {
            return colours.length;
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return 0;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView colourSquare;
            if (convertView == null) {
                colourSquare = new ImageView(context);
                colourSquare.setLayoutParams(new GridView.LayoutParams(size, size));
                colourSquare.setPadding(12, 12, 12, 12);
            } else {
                colourSquare = (ImageView) convertView;
            }

            colourSquare.setBackgroundColor(Color.parseColor(colours[position]));
            colourSquare.setContentDescription(colours[position]);

            if (position == currentColourPosition) {
                colourSquare.setImageResource(android.R.drawable.checkbox_on_background);
            }
            else {
                colourSquare.setImageResource(0);
            }

            return colourSquare;
        }

    }
}
