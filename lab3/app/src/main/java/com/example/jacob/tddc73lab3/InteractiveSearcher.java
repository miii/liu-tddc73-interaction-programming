package com.example.jacob.tddc73lab3;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class InteractiveSearcher extends android.support.v7.widget.AppCompatEditText {

    // Name TextView padding
    private final int PADDING = 32;
    private int threshold = 1;

    private Context context;
    private PopupWindow popup;
    private LinearLayout layout;

    private Integer id = 0;
    private GetNameSuggestions suggestions = null;
    private boolean skipSuggestion = false;

    public InteractiveSearcher(Context ctx) {
        super(ctx);
        context = ctx;

        // Setup popup content layout
        layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setBackgroundColor(Color.BLACK);

        // Setup popup
        popup = new PopupWindow(context);
        popup.setContentView(layout);
        popup.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
        popup.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);

        // Set EditText width/height
        setLayoutParams(
            new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        );

        // Listen on EditText input changes
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                popup.dismiss();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                onTextChange(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });
    }

    public void setThreshold(int t) {
        threshold = t;
    }

    public int getThreshold() {
        return threshold;
    }

    private void onTextChange(String text) {
        // If user picked a suggestion from the list
        // new suggestions should not be fetched
        if (skipSuggestion) {
            skipSuggestion = false;
            return;
        }

        // Cancel current fetch
        if (suggestions != null)
            suggestions.cancel(true);

        // Do nothing if too few characters
        if (text.length() < threshold)
            return;

        // Fetch suggestions
        suggestions = new GetNameSuggestions(id++);
        suggestions.execute(text);
    }

    private void addResult(String name) {
        final ResultItem item = new ResultItem(context, name);
        item.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                // Prevent the next suggestion since the user chose a name from the list
                skipSuggestion = true;

                // Add name to input and place the marker at the end
                InteractiveSearcher.this.setText(item.getName());
                InteractiveSearcher.this.setSelection(item.getName().length());
            }
        });

        /*
        final View tv = new TextView(context);
        tv.setText(name);

        // TextView style
        tv.setPadding(PADDING, PADDING, PADDING, PADDING);
        tv.setTextSize(16);
        tv.setTextColor(Color.LTGRAY);
        tv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                // Prevent the next suggestion since the user chose a name from the list
                skipSuggestion = true;

                // Add name to input and place the marker at the end
                InteractiveSearcher.this.setText(tv.getText());
                InteractiveSearcher.this.setSelection(tv.getText().length());
            }
        });
        */

        layout.addView(item);
    }

    private class GetNameSuggestions extends AsyncTask<String, Void, List<String>> {

        private int id;

        public GetNameSuggestions(int i) {
            id = i;
        }

        @Override
        protected List<String> doInBackground(String... names) {
            try {
                // Make request to API
                URL url = new URL("http://andla.pythonanywhere.com/getnames/" + id + "/" + names[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                // Initiate buffer
                InputStream stream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer str = new StringBuffer();
                String line;

                // Append each line to JSON string
                while ((line = reader.readLine()) != null)
                    str.append(line);

                // Close reader buffer
                reader.close();

                // Fetch results from JSON object
                JSONObject json = new JSONObject(str.toString());
                JSONArray rawResults = json.getJSONArray("result");
                List<String> results = new ArrayList<>(rawResults.length());

                for (int i = 0; i < rawResults.length(); i++) {
                    // Save capitalized name
                    String name = rawResults.get(i).toString();
                    results.add(name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase());
                }

                // Result list of names
                return results;
            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(List<String> results) {
            // Do nothing if no results was found
            if (results.size() == 0)
                return;

            // Clear all results in UI
            layout.removeAllViews();

            // Add names
            for (String name : results)
                addResult(name);

            // Show popup below EditText
            popup.showAsDropDown(InteractiveSearcher.this);
        }
    }

}
