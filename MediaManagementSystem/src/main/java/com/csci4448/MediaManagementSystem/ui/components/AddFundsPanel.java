package com.csci4448.MediaManagementSystem.ui.components;

import com.csci4448.MediaManagementSystem.controller.MainController;
import com.csci4448.MediaManagementSystem.ui.design.TextComponentFactory;
import com.csci4448.MediaManagementSystem.ui.design.Style;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddFundsPanel extends JPanel implements ActionListener {

    private MainController controller;

    private TextPane dollarSign;
    private EnterTextField fundsEnterText;
    private TextButton submit;
    private TextButton cancel;

    public AddFundsPanel(MainController controller) {
        this.controller = controller;

        setLayout(new GridBagLayout());
        setBackground(new Color(250, 250, 250));
        setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(205, 205, 205)));

        dollarSign = TextComponentFactory.textPane("$", Style.LOGIN_BASIC);
        GridBagConstraints dollConst = new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(20,20,0,0), 0, 0);
        add(dollarSign, dollConst);

        fundsEnterText = TextComponentFactory.enterText(this, "Enter Amount", Style.LOGIN_BASIC, 200, 30);
        GridBagConstraints textConst = new GridBagConstraints(1, 0, 2, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(20,0,0,20), 0, 0);
        add(fundsEnterText, textConst);

        submit = TextComponentFactory.smallButton(this, "Add Funds", Style.CONFIRM_OK);
        GridBagConstraints submConst = new GridBagConstraints(2, 1, 1, 1, .5, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(10,10,20,0), 0, 0);
        add(submit, submConst);

        cancel = TextComponentFactory.smallButton(this, "Cancel", Style.CONFIRM_CANCEL);
        GridBagConstraints cancConst = new GridBagConstraints(1, 1, 1, 1, .5, 0, GridBagConstraints.LINE_END, GridBagConstraints.NONE, new Insets(10,0,20,15), 0, 0);
        add(cancel, cancConst);
    }

    public void actionPerformed(ActionEvent event) {
        Object component = event.getSource();
        if (component.equals(cancel)) {
            controller.addFundsCancelRequest();
        } else if (component.equals(submit)) {
            Double amount;
            try {
                amount = Double.parseDouble(fundsEnterText.getText().trim());
                controller.addFundsSubmitRequest(amount);
            } catch (NumberFormatException e) {
                //ToDo: inform user of incorrect amount
                System.out.println(e);
            }
        }
    }
}
