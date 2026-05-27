package com.example.proyectofinal;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.Gravity;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    private static final String[] CONSONANTS = {
            "b", "c", "d", "f", "g", "h", "j", "k", "l", "m", "n",
            "p", "r", "s", "t", "v", "w", "x", "y", "z"
    };

    private static final String[] VOWELS = {"a", "e", "i", "o", "u"};

    private TextToSpeech textToSpeech;
    private boolean speechReady = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        textToSpeech = new TextToSpeech(this, this);
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                showStartScreen();
            }
        });
        showStartScreen();
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            int result = textToSpeech.setLanguage(Locale.forLanguageTag("es-MX"));
            speechReady = result != TextToSpeech.LANG_MISSING_DATA
                    && result != TextToSpeech.LANG_NOT_SUPPORTED;
        }
    }

    private void showStartScreen() {
        LinearLayout content = createBaseLayout();
        content.addView(createTitle("Silabas Directas"));
        content.addView(createSubtitle("Selecciona una opcion"));

        Button consonantsButton = createFullWidthButton("Ver consonantes", getColorFromHex("#1976D2"), 20);
        consonantsButton.setOnClickListener(view -> showConsonantScreen());

        Button creditsButton = createFullWidthButton("Ver creditos", getColorFromHex("#1976D2"), 20);
        creditsButton.setOnClickListener(view -> showCreditsScreen());

        content.addView(consonantsButton);
        content.addView(creditsButton);
        setContentView(wrapInScroll(content));
    }

    private void showCreditsScreen() {
        LinearLayout content = createBaseLayout();
        content.addView(createTitle(getString(R.string.credits_title)));
        content.addView(createInfoText(getString(R.string.credits_professor)));
        content.addView(createInfoText(getString(R.string.credits_group)));
        content.addView(createInfoText(getString(R.string.credits_subject)));
        content.addView(createInfoText(getString(R.string.credits_school)));
        content.addView(createInfoText(getString(R.string.credits_student)));

        Button backButton = createFullWidthButton("Regresar", getColorFromHex("#D32F2F"), 20);
        backButton.setOnClickListener(view -> showStartScreen());

        content.addView(backButton);
        setContentView(wrapInScroll(content));
    }

    private Button createFullWidthButton(String text, int backgroundColor, int textSize) {
        Button button = createButton(text, backgroundColor, textSize);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(dp(6), dp(8), dp(6), dp(8));
        button.setLayoutParams(params);
        return button;
    }

    private void showConsonantScreen() {
        LinearLayout content = createBaseLayout();
        content.addView(createTitle("Selecciona una consonante"));
        content.addView(createSubtitle("Despues elige la silaba que quieres escuchar."));

        GridLayout grid = createGrid(4);
        for (String consonant : CONSONANTS) {
            Button button = createBlueButton(consonant.toUpperCase(Locale.forLanguageTag("es-MX")), 24);
            button.setOnClickListener(view -> showSyllableScreen(consonant));
            grid.addView(button);
        }

        Button backButton = createFullWidthButton("Regresar", getColorFromHex("#D32F2F"), 20);
        backButton.setOnClickListener(view -> showStartScreen());

        content.addView(grid);
        content.addView(backButton);
        setContentView(wrapInScroll(content));
    }

    private void showSyllableScreen(String consonant) {
        LinearLayout content = createBaseLayout();
        content.addView(createTitle("Silabas con " + consonant.toUpperCase(Locale.forLanguageTag("es-MX"))));
        content.addView(createSubtitle("Toca un boton para escuchar su sonido."));

        GridLayout grid = createGrid(2);
        for (String vowel : VOWELS) {
            String syllable = consonant + vowel;
            Button button = createBlueButton(syllable, 28);
            button.setOnClickListener(view -> speak(syllable));
            grid.addView(button);
        }

        Button backButton = createButton("Regresar", getColorFromHex("#D32F2F"), 20);
        LinearLayout.LayoutParams backParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        backParams.setMargins(dp(6), dp(16), dp(6), dp(6));
        backButton.setLayoutParams(backParams);
        backButton.setOnClickListener(view -> showConsonantScreen());

        content.addView(grid);
        content.addView(backButton);
        setContentView(wrapInScroll(content));
    }

    private void speak(String text) {
        if (!speechReady) {
            Toast.makeText(this, "TextToSpeech no esta listo.", Toast.LENGTH_SHORT).show();
            return;
        }

        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, (Bundle) null, "silaba_" + text);
    }

    private ScrollView wrapInScroll(LinearLayout content) {
        ScrollView scrollView = new ScrollView(this);
        scrollView.setFillViewport(true);
        scrollView.setBackgroundColor(getColorFromHex("#F7FAFE"));
        scrollView.addView(content);
        return scrollView;
    }

    private LinearLayout createBaseLayout() {
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(Gravity.CENTER_HORIZONTAL);
        layout.setPadding(dp(24), dp(32), dp(24), dp(24));
        layout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        return layout;
    }

    private TextView createTitle(String text) {
        TextView title = new TextView(this);
        title.setText(text);
        title.setTextColor(getColorFromHex("#102033"));
        title.setTextSize(26);
        title.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        title.setGravity(Gravity.CENTER);
        title.setPadding(0, 0, 0, dp(8));
        return title;
    }

    private TextView createSubtitle(String text) {
        TextView subtitle = new TextView(this);
        subtitle.setText(text);
        subtitle.setTextColor(getColorFromHex("#516172"));
        subtitle.setTextSize(16);
        subtitle.setGravity(Gravity.CENTER);
        subtitle.setPadding(0, 0, 0, dp(20));
        return subtitle;
    }

    private TextView createInfoText(String text) {
        TextView info = new TextView(this);
        info.setText(text);
        info.setTextColor(getColorFromHex("#102033"));
        info.setTextSize(18);
        info.setGravity(Gravity.CENTER);
        info.setPadding(0, dp(8), 0, dp(8));
        return info;
    }

    private GridLayout createGrid(int columns) {
        GridLayout grid = new GridLayout(this);
        grid.setColumnCount(columns);
        grid.setUseDefaultMargins(false);
        grid.setAlignmentMode(GridLayout.ALIGN_BOUNDS);
        grid.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        return grid;
    }

    private Button createBlueButton(String text, int textSize) {
        return createButton(text, getColorFromHex("#1976D2"), textSize);
    }

    private Button createButton(String text, int backgroundColor, int textSize) {
        Button button = new Button(this);
        button.setText(text);
        button.setTextColor(Color.WHITE);
        button.setTextSize(textSize);
        button.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        button.setAllCaps(false);
        button.setMinHeight(dp(64));
        button.setBackground(createRoundedBackground(backgroundColor));

        GridLayout.LayoutParams params = new GridLayout.LayoutParams();
        params.width = 0;
        params.height = GridLayout.LayoutParams.WRAP_CONTENT;
        params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
        params.setMargins(dp(6), dp(6), dp(6), dp(6));
        button.setLayoutParams(params);
        return button;
    }

    private GradientDrawable createRoundedBackground(int color) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setColor(color);
        drawable.setCornerRadius(dp(8));
        return drawable;
    }

    private int getColorFromHex(String color) {
        return Color.parseColor(color);
    }

    private int dp(int value) {
        return Math.round(value * getResources().getDisplayMetrics().density);
    }

    @Override
    protected void onDestroy() {
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onDestroy();
    }
}
