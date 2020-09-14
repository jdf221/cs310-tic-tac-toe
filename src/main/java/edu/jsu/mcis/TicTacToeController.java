package edu.jsu.mcis;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeController implements ActionListener {

    private final TicTacToeModel model;
    private final TicTacToeView view;

    /* CONSTRUCTOR */

    public TicTacToeController(int width) {

        /* Initialize model, view, and width */

        model = new TicTacToeModel(width);
        view = new TicTacToeView(this, width);

    }

    public String getMarkAsString(int row, int col) {
        return (model.getMark(row, col).toString());
    }

    public TicTacToeView getView() {
        return view;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        JButton sourceButton = (JButton)event.getSource();

        if(!model.makeMark(Integer.parseInt(sourceButton.getName().substring(6,7)), Integer.parseInt(sourceButton.getName().substring(7,8)))){
            view.showResult("Location is already marked.");
        }
        else{
            view.showResult("Welcome to Tic-Tac-Toe!");
        }
        view.updateSquares();

        if(model.isGameover()) {
            view.disableSquares();
            view.showResult(model.getResult().toString());
        }
    }
}
