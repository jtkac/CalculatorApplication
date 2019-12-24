package ca.useful.calculatorapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private TextView calcResultTextView;
    private TextView calcClearTextView;
    private TextView calcDivideTextView;
    private TextView calcMultiplyTextView;
    private TextView calcBackTextView;
    private TextView calc7TextView;
    private TextView calc8TextView;
    private TextView calc9TextView;
    private TextView calcSubtractTextView;
    private TextView calc4TextView;
    private TextView calc5TextView;
    private TextView calc6TextView;
    private TextView calcAddTextView;
    private TextView calc1TextView;
    private TextView calc2TextView;
    private TextView calc3TextView;
    private TextView calcEqualsTextView;
    private TextView calc0TextView;
    private TextView calcDecimalTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindControls();
    }

    private void bindControls() {
        calcResultTextView = findViewById(R.id.calc_result);
        calcClearTextView = findViewById(R.id.calc_clear);
        calcDivideTextView = findViewById(R.id.calc_divide);
        calcMultiplyTextView = findViewById(R.id.calc_multiply);
        calcBackTextView = findViewById(R.id.calc_back);
        calc7TextView = findViewById(R.id.calc_7);
        calc8TextView = findViewById(R.id.calc_8);
        calc9TextView = findViewById(R.id.calc_9);
        calcSubtractTextView = findViewById(R.id.calc_subtract);
        calc4TextView = findViewById(R.id.calc_4);
        calc5TextView = findViewById(R.id.calc_5);
        calc6TextView = findViewById(R.id.calc_6);
        calcAddTextView = findViewById(R.id.calc_add);
        calc1TextView = findViewById(R.id.calc_1);
        calc2TextView = findViewById(R.id.calc_2);
        calc3TextView = findViewById(R.id.calc_3);
        calcEqualsTextView = findViewById(R.id.calc_equals);
        calc0TextView = findViewById(R.id.calc_0);
        calcDecimalTextView = findViewById(R.id.calc_decimal);

        calc1TextView.setOnClickListener(v -> {
            calcResultTextView.setText(calcResultTextView.getText().toString() + "1");
        });
        calc2TextView.setOnClickListener(v -> {
            calcResultTextView.setText(calcResultTextView.getText().toString() + "2");
        });
        calc3TextView.setOnClickListener(v -> {
            calcResultTextView.setText(calcResultTextView.getText().toString() + "3");
        });
        calc4TextView.setOnClickListener(v -> {
            calcResultTextView.setText(calcResultTextView.getText().toString() + "4");
        });
        calc5TextView.setOnClickListener(v -> {
            calcResultTextView.setText(calcResultTextView.getText().toString() + "5");
        });
        calc6TextView.setOnClickListener(v -> {
            calcResultTextView.setText(calcResultTextView.getText().toString() + "6");
        });
        calc7TextView.setOnClickListener(v -> {
            calcResultTextView.setText(calcResultTextView.getText().toString() + "7");
        });
        calc8TextView.setOnClickListener(v -> {
            calcResultTextView.setText(calcResultTextView.getText().toString() + "8");
        });
        calc9TextView.setOnClickListener(v -> {
            calcResultTextView.setText(calcResultTextView.getText().toString() + "9");
        });
        calc0TextView.setOnClickListener(v -> {
            calcResultTextView.setText(calcResultTextView.getText().toString() + "0");
        });
        calcDecimalTextView.setOnClickListener(v -> {
            calcResultTextView.setText(calcResultTextView.getText().toString() + ".");
        });
        calcDivideTextView.setOnClickListener(v -> {
            calcResultTextView.setText(calcResultTextView.getText().toString() + "/");
        });
        calcMultiplyTextView.setOnClickListener(v -> {
            calcResultTextView.setText(calcResultTextView.getText().toString() + "x");
        });
        calcAddTextView.setOnClickListener(v -> {
            calcResultTextView.setText(calcResultTextView.getText().toString() + "+");
        });
        calcSubtractTextView.setOnClickListener(v -> {
            calcResultTextView.setText(calcResultTextView.getText().toString() + "-");
        });
        calcBackTextView.setOnClickListener(v -> {
            if (calcResultTextView.getText().length() > 0) {
                //remove the last character of the result text
                calcResultTextView.setText(calcResultTextView.getText().toString().substring(0, calcResultTextView.getText().length() - 1));
            }
        });
        calcEqualsTextView.setOnClickListener(v -> {
            //follow the rules of bedmas
            //first divide
            try {
                String calcValue = calcResultTextView.getText().toString();
                //look for digits with the divide symbol separating them
                Pattern dividePattern = Pattern.compile("\\d+\\.?\\d*\\/\\d+\\.?\\d*");
                Matcher divideMatcher = dividePattern.matcher(calcValue);
                while (divideMatcher.find()) {
                    String[] divideables = divideMatcher.group().split("/");
                    Double first = Double.parseDouble(divideables[0]);
                    Double second = Double.parseDouble(divideables[1]);
                    Double result = first / second;
                    calcValue = calcValue.replace(divideMatcher.group(), result.toString());
                }
                Pattern multiplyPattern = Pattern.compile("\\d+\\.?\\d*x\\d+\\.?\\d*");
                Matcher multiplyMatcher = multiplyPattern.matcher(calcValue);
                while (multiplyMatcher.find()) {
                    String[] multipliables = multiplyMatcher.group().split("x");
                    Double first = Double.parseDouble(multipliables[0]);
                    Double second = Double.parseDouble(multipliables[1]);
                    Double result = first * second;
                    calcValue = calcValue.replace(multiplyMatcher.group(), result.toString());
                }
                Pattern addPattern = Pattern.compile("\\d+\\.?\\d*\\+\\d+\\.?\\d*");
                Matcher addMatcher = addPattern.matcher(calcValue);
                while (addMatcher.find()) {
                    String[] addables = addMatcher.group().split("\\+");
                    Double first = Double.parseDouble(addables[0]);
                    Double second = Double.parseDouble(addables[1]);
                    Double result = first + second;
                    calcValue = calcValue.replace(addMatcher.group(), result.toString());
                }
                Pattern subtractPattern = Pattern.compile("\\d+\\.?\\d*\\-\\d+\\.?\\d*");
                Matcher subtractMatcher = subtractPattern.matcher(calcValue);
                while (subtractMatcher.find()) {
                    String[] subtractables = subtractMatcher.group().split("\\-");
                    Double first = Double.parseDouble(subtractables[0]);
                    Double second = Double.parseDouble(subtractables[1]);
                    Double result = first - second;
                    calcValue = calcValue.replace(subtractMatcher.group(), result.toString());
                }

                calcResultTextView.setText(calcValue);
            } catch (Exception e) {
                calcResultTextView.setText("");
            }
        });
        calcClearTextView.setOnClickListener(v -> {
            calcResultTextView.setText("");
        });

    }
}
