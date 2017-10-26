package jonathandilks.com.fingerpainter;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.RadioButton;

public class BrushSizeShape extends AppCompatActivity {

    NumberPicker sizeEntry;
    RadioButton roundRadioButton;
    RadioButton squareRadioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brush_size_shape);

        sizeEntry = (NumberPicker) findViewById(R.id.sizeEntry);
        roundRadioButton = (RadioButton) findViewById(R.id.roundRadioButton);
        squareRadioButton = (RadioButton) findViewById(R.id.squareRadioButton);

        Bundle bundle = getIntent().getExtras();

        sizeEntry.setMinValue(1);
        sizeEntry.setMaxValue(50);
        sizeEntry.setValue(bundle.getInt("size"));

        switch ((Paint.Cap) bundle.get("cap"))
        {
            case ROUND:
                roundRadioButton.setChecked(true);
                break;

            case SQUARE:
                squareRadioButton.setChecked(true);
                break;
        }
    }

    public void confirmButton(View view) {
        Intent result = new Intent();
        Bundle bundle = new Bundle();

        Integer integer = sizeEntry.getValue();
        bundle.putInt("size", integer);

        Paint.Cap selectedCap = null;
        if (roundRadioButton.isChecked())
        {
            selectedCap = Paint.Cap.ROUND;
        }
        else if(squareRadioButton.isChecked())
        {
            selectedCap = Paint.Cap.SQUARE;
        }
        bundle.putSerializable("cap", selectedCap);

        result.putExtras(bundle);
        setResult(Activity.RESULT_OK, result);

        finish();
    }
}
