package brad.masciotra.lab9;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;




public class MainActivity extends AppCompatActivity  {

    private Button toJSON;
    private Button calculate;
    private EditText jsonText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        toJSON = findViewById(R.id.toJSON);
        calculate = findViewById(R.id.calculate);
        jsonText = findViewById(R.id.jsonText);

        toJSON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    convertToJSON(view);
                } catch (JSONException | JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public void convertToJSON(View view) throws JSONException, JsonProcessingException {
        JSONObject operation = new JSONObject();
        JSONObject add = new JSONObject();
        JSONObject numbers = new JSONObject();
        EditText intOne = findViewById(R.id.intOne);
        EditText intTwo = findViewById(R.id.intTwo);


        int one = Integer.parseInt(intOne.getText().toString());
        int two = Integer.parseInt(intTwo.getText().toString());
        intOne.setText("");
        intTwo.setText("");
        System.out.println("ONE: " + one);
        System.out.println("TWO: " + two);

        numbers.put("a",one);
        numbers.put("b",two);
        add.put("add",numbers);
       operation.put("operation", add);
       System.out.println(operation);

       String JSONstring = createJSONString(operation);
       jsonText.setText(JSONstring);
    }

    private String createJSONString(JSONObject object) throws  JsonProcessingException {
        String inputJson  = object.toString();
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(objectMapper.readTree(inputJson));
    }
}