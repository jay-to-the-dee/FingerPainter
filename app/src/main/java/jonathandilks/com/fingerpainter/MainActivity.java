package jonathandilks.com.fingerpainter;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    static final int ACTIVITY_SIZESHAPE_REQUEST_CODE = 1;
    static final int ACTIVITY_COLOUR_REQUEST_CODE = 2;

    FingerPainterView fingerPainter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fingerPainter = (FingerPainterView) findViewById(R.id.myFingerPainterViewId);
    }

    public void openBrushSizeShapeActivity(View view) {

        Bundle bundle = new Bundle();
        bundle.putSerializable("cap", fingerPainter.getBrush());
        bundle.putInt("size", fingerPainter.getBrushWidth());

        Intent intent = new Intent(MainActivity.this, BrushSizeShape.class);
        intent.putExtras(bundle);
        startActivityForResult(intent, ACTIVITY_SIZESHAPE_REQUEST_CODE);
    }


    public void openBrushColourActivity(View view) {
//        Intent intent = new Intent(MainActivity.this, BrushColour.class);
//        startActivityForResult(intent, ACTIVITY_COLOUR_REQUEST_CODE);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case ACTIVITY_SIZESHAPE_REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    Bundle bundle = data.getExtras();

                    try {
                        int brushWidth = bundle.getInt("size");
                        fingerPainter.setBrushWidth(brushWidth);
                    } catch (Exception e) {
                    }
                    fingerPainter.setBrush((Paint.Cap) bundle.get("cap"));
                }
                break;

            case ACTIVITY_COLOUR_REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    Bundle bundle = data.getExtras();

                    try {
                        int colour = bundle.getInt("colour");
                        fingerPainter.setColour(colour);
                    } catch (Exception e) {
                    }
                }
                break;
        }
    }
}
