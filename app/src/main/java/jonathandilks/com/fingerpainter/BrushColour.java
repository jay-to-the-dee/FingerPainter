package jonathandilks.com.fingerpainter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
        colourGrid.setAdapter(new ImageAdapter(this, currentColourPosition));

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

    public class ImageAdapter extends BaseAdapter {
        private Context mContext;
        private int currentColourPosition;
        private int size;

        public ImageAdapter(Context c, int currentColourPosition) {
            mContext = c;
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

        // create a new ImageView for each item referenced by the Adapter
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            if (convertView == null) {
                // if it's not recycled, initialize some attributes
                imageView = new ImageView(mContext);
                imageView.setLayoutParams(new GridView.LayoutParams(size, size));
                imageView.setPadding(8, 8, 8, 8);
            } else {
                imageView = (ImageView) convertView;
            }

            imageView.setBackgroundColor(Color.parseColor(colours[position]));
            imageView.setContentDescription(colours[position]);

            if (position == currentColourPosition) {
                imageView.setImageResource(android.R.drawable.checkbox_on_background);
            }
            else {
                imageView.setImageResource(0);
            }

            return imageView;
        }

    }
}
