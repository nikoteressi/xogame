import javax.swing.*;
import java.awt.*;

public class SettingsWindow extends JFrame {
    private static final int WINDOW_WIDTH = 350;
    private static final int WINDOW_HEIGHT = 300;
    private static final int MIN_WIN_LENGTH = 3;
    private static final int MIN_FIELD_SIZE = 3;
    private static final int MAX_FIELD_SIZE = 10;
    private static final String FIELD_SIZE_PREFIX = "Field size: ";
    private static final String WIN_LENGTH_PREFIX = "Win length: ";

    private JSlider sliderWinLength;
    private JSlider sliderFieldSize;
    private JRadioButton humanVsAI;

    public SettingsWindow(GameWindow gameWindow) {
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocationRelativeTo(gameWindow);
        setResizable(false);
        setTitle("Settings. Create your new game");
        setLayout(new GridLayout(10, 1));

        addChooseGameMode();
        addSetFieldSize();

        JButton buttonStart = new JButton("Start new game");
        buttonStart.addActionListener(e -> submitSettings(gameWindow));
        add(buttonStart);
    }

    private void submitSettings(GameWindow gameWindow) {
        int gameMode;
        if (humanVsAI.isSelected()) {
            gameMode = Map.MODE_VS_AI;
        } else {
            gameMode = Map.MODE_VS_HUMAN;
        }
        int fieldSize = sliderFieldSize.getValue();
        int winLength = sliderWinLength.getValue();
        gameWindow.startGame(gameMode, fieldSize, winLength);
        setVisible(false);
    }

    private void addSetFieldSize() {
        JLabel labelFieldSize = new JLabel(FIELD_SIZE_PREFIX + MIN_FIELD_SIZE);
        JLabel labelWinLength = new JLabel(WIN_LENGTH_PREFIX + MIN_WIN_LENGTH);

        sliderFieldSize = new JSlider(MIN_FIELD_SIZE, MAX_FIELD_SIZE, MIN_FIELD_SIZE);
        sliderWinLength = new JSlider(MIN_WIN_LENGTH, MAX_FIELD_SIZE, MIN_FIELD_SIZE);

        sliderFieldSize.addChangeListener(e -> {
            int currentValue = sliderFieldSize.getValue();
            labelFieldSize.setText(FIELD_SIZE_PREFIX + currentValue);
            sliderWinLength.setMaximum(currentValue);
        });

        sliderWinLength.addChangeListener(e -> labelWinLength.setText(WIN_LENGTH_PREFIX + sliderWinLength.getValue()));
        add(new JLabel("Choose field size:"));
        add(labelFieldSize);
        add(sliderFieldSize);
        add(new JLabel("Choose win length:"));
        add(labelWinLength);
        add(sliderWinLength);
    }

    private void addChooseGameMode() {
        add(new JLabel("Choose game mode:"));
        humanVsAI = new JRadioButton("Human versus AI", true);
        JRadioButton humanVsHuman = new JRadioButton("Human versus human");
        ButtonGroup gameMode = new ButtonGroup();
        gameMode.add(humanVsAI);
        gameMode.add(humanVsHuman);
        add(humanVsAI);
        add(humanVsHuman);
    }
}
