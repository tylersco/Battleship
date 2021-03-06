package com.csci4448.MediaManagementSystem.ui.states;

import com.csci4448.MediaManagementSystem.controller.MainController;
import com.csci4448.MediaManagementSystem.model.media.MediaInfo;
import com.csci4448.MediaManagementSystem.ui.design.Confirmation;
import com.csci4448.MediaManagementSystem.ui.components.ConfirmationWindow;
import com.csci4448.MediaManagementSystem.model.media.SystemInventory;
import com.csci4448.MediaManagementSystem.ui.components.BorderedButton;
import com.csci4448.MediaManagementSystem.ui.components.EnterTextField;
import com.csci4448.MediaManagementSystem.ui.components.TextButton;
import com.csci4448.MediaManagementSystem.ui.components.TextPane;
import com.csci4448.MediaManagementSystem.ui.design.Style;
import com.csci4448.MediaManagementSystem.ui.design.TextComponentFactory;
import com.csci4448.MediaManagementSystem.ui.design.UIFont;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.Exchanger;

public class AdminEditPanel extends MainContentPanel {

    // TODO: Move these to their own place
    private static final Color TEXT_COLOR = Color.DARK_GRAY;
    private static final Color BORDER_COLOR = Color.DARK_GRAY;
    private static final Color ACTION_BUTTON_DEFAULT_COLOR = Color.GRAY;
    private static final Color ACTION_BUTTON_ENTERED_COLOR = Color.LIGHT_GRAY;

    private BorderedButton saveButton;
    private BorderedButton cancelButton;
    private BorderedButton newButton;

    private TextPane titleLabel;
    private TextPane descriptionLabel;
    private TextPane typeLabel;
    private TextPane genreLabel;
    private TextPane priceLabel;
    private TextPane sellPriceLabel;
    private TextPane purchaseLabel;

    private EnterTextField titleText;
    private EnterTextField descriptionText;
    private TextButton movieChoice, bookChoice, musicChoice, tvChoice, audioChoice;
    private EnterTextField genreText;
    private EnterTextField priceText;
    private EnterTextField sellPriceText;
    private TextButton rentChoice, buyChoice;

    private MediaInfo savedMediaInfo = null;

    public AdminEditPanel(MainController controller, MediaInfo info) {
        super(controller);

        JLayeredPane content = getContent();
        savedMediaInfo = info;

        saveButton = new BorderedButton(this, "Save Changes", UIFont.FONT_20B.getFont(),
                TEXT_COLOR, TEXT_COLOR, TEXT_COLOR,
                ACTION_BUTTON_DEFAULT_COLOR, ACTION_BUTTON_ENTERED_COLOR, ACTION_BUTTON_DEFAULT_COLOR,
                BORDER_COLOR, BORDER_COLOR, BORDER_COLOR);
        saveButton.setSize(170, 30);
        saveButton.setLocation(573, 10);
        content.add(saveButton);

        cancelButton = new BorderedButton(this, "Undo Changes", UIFont.FONT_20B.getFont(),
                TEXT_COLOR, TEXT_COLOR, TEXT_COLOR,
                ACTION_BUTTON_DEFAULT_COLOR, ACTION_BUTTON_ENTERED_COLOR, ACTION_BUTTON_DEFAULT_COLOR,
                BORDER_COLOR, BORDER_COLOR, BORDER_COLOR);
        cancelButton.setSize(170, 30);
        cancelButton.setLocation(382, 10);
        content.add(cancelButton);

        newButton = new BorderedButton(this, "New Media", UIFont.FONT_20B.getFont(),
                TEXT_COLOR, TEXT_COLOR, TEXT_COLOR,
                ACTION_BUTTON_DEFAULT_COLOR, ACTION_BUTTON_ENTERED_COLOR, ACTION_BUTTON_DEFAULT_COLOR,
                BORDER_COLOR, BORDER_COLOR, BORDER_COLOR);
        newButton.setSize(170, 30);
        newButton.setLocation(193, 10);
        content.add(newButton);

        titleLabel = TextComponentFactory.textPane("Title:", Style.ADMIN_LABEL);
        titleLabel.setSize(titleLabel.getPreferredSize());
        titleLabel.setLocation(15, 80);
        content.add(titleLabel);

        descriptionLabel = TextComponentFactory.textPane("Description:", Style.ADMIN_LABEL);
        descriptionLabel.setSize(descriptionLabel.getPreferredSize());
        descriptionLabel.setLocation(15, 120);
        content.add(descriptionLabel);

        typeLabel = TextComponentFactory.textPane("Type:", Style.ADMIN_LABEL);
        typeLabel.setSize(typeLabel.getPreferredSize());
        typeLabel.setLocation(15, 220);
        content.add(typeLabel);

        genreLabel = TextComponentFactory.textPane("Genre:", Style.ADMIN_LABEL);
        genreLabel.setSize(genreLabel.getPreferredSize());
        genreLabel.setLocation(15, 260);
        content.add(genreLabel);

        priceLabel = TextComponentFactory.textPane("Price:", Style.ADMIN_LABEL);
        priceLabel.setSize(priceLabel.getPreferredSize());
        priceLabel.setLocation(15, 300);
        content.add(priceLabel);

        sellPriceLabel = TextComponentFactory.textPane("Sell Price:", Style.ADMIN_LABEL);
        sellPriceLabel.setSize(sellPriceLabel.getPreferredSize());
        sellPriceLabel.setLocation(15, 340);
        content.add(sellPriceLabel);

        purchaseLabel = TextComponentFactory.textPane("Buy/Rent:", Style.ADMIN_LABEL);
        purchaseLabel.setSize(purchaseLabel.getPreferredSize());
        purchaseLabel.setLocation(15, 380);
        content.add(purchaseLabel);

        titleText = TextComponentFactory.enterText(this, "", Style.ADMIN_TEXT);
        titleText.setSize(725, 30);
        titleText.setLocation(175, 80);
        content.add(titleText);

        descriptionText = TextComponentFactory.enterText(this, "", Style.ADMIN_TEXT);
        descriptionText.setSize(725, 90);
        descriptionText.setLocation(175, 120);
        content.add(descriptionText);

        movieChoice = TextComponentFactory.smallButton(this, "Movie", Style.ADMIN_BUTTON);
        bookChoice = TextComponentFactory.smallButton(this, "Book", Style.ADMIN_BUTTON);
        musicChoice = TextComponentFactory.smallButton(this, "Music", Style.ADMIN_BUTTON);
        tvChoice = TextComponentFactory.smallButton(this, "TV Show", Style.ADMIN_BUTTON);
        audioChoice = TextComponentFactory.smallButton(this, "Audio Book", Style.ADMIN_BUTTON);
        movieChoice.setSize(movieChoice.getPreferredSize());
        movieChoice.setLocation(175, 220);
        bookChoice.setSize(bookChoice.getPreferredSize());
        bookChoice.setLocation(250, 220);
        musicChoice.setSize(musicChoice.getPreferredSize());
        musicChoice.setLocation(325, 220);
        tvChoice.setSize(tvChoice.getPreferredSize());
        tvChoice.setLocation(400, 220);
        audioChoice.setSize(audioChoice.getPreferredSize());
        audioChoice.setLocation(505, 220);
        content.add(movieChoice);
        content.add(bookChoice);
        content.add(musicChoice);
        content.add(tvChoice);
        content.add(audioChoice);
        movieChoice.setIsSelected(true);

        genreText = TextComponentFactory.enterText(this, "", Style.ADMIN_TEXT);
        genreText.setSize(225, 30);
        genreText.setLocation(175, 260);
        content.add(genreText);

        priceText = TextComponentFactory.enterText(this, "", Style.ADMIN_TEXT);
        priceText.setSize(50, 30);
        priceText.setLocation(175, 300);
        content.add(priceText);

        sellPriceText = TextComponentFactory.enterText(this, "", Style.ADMIN_TEXT);
        sellPriceText.setSize(50, 30);
        sellPriceText.setLocation(175, 340);
        content.add(sellPriceText);

        rentChoice = TextComponentFactory.smallButton(this, "Rent", Style.ADMIN_BUTTON);
        buyChoice = TextComponentFactory.smallButton(this, "Buy", Style.ADMIN_BUTTON);
        rentChoice.setSize(rentChoice.getPreferredSize());
        rentChoice.setLocation(175, 380);
        buyChoice.setSize(buyChoice.getPreferredSize());
        buyChoice.setLocation(250, 380);
        content.add(rentChoice);
        content.add(buyChoice);
        rentChoice.setIsSelected(true);

        content.setSize(935, 520);
        updateContentSize();

        if (info != null) {
            setFields(info.getTitle(), info.getDescription(), info.getType(), info.getGenre(), info.getPrice(), info.getSellPrice(),
                    info.getIsRentable());
        }
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Object component = event.getSource();

        // Section for media control buttons
        if (component.equals(newButton)) {
            if (hasUnsavedChanges()) {
                setPopUpWindow(new ConfirmationWindow(getController(), Confirmation.ADMINNEWEXISTING));
            } else {
                setPopUpWindow(new ConfirmationWindow(getController(), Confirmation.ADMINNEW));
            }
        } else if (component.equals(saveButton)) {
            if (!hasUnsavedChanges()) {
                setPopUpWindow(new ConfirmationWindow(getController(), Confirmation.ADMINNOCHANGES));
                return;
            }

            setPopUpWindow(new ConfirmationWindow(getController(), Confirmation.ADMINSAVE));
        } else if (component.equals(cancelButton)) {
            if (hasUnsavedChanges()) {
                setPopUpWindow(new ConfirmationWindow(getController(), Confirmation.ADMINCANCEL));
            }
        }

        // Section for media type options
        else if (component.equals(movieChoice)) {
            setSelectedMediaType("Movie");
        } else if (component.equals(bookChoice)) {
            setSelectedMediaType("Book");
        } else if (component.equals(musicChoice)) {
            setSelectedMediaType("Music");
        } else if (component.equals(tvChoice)) {
            setSelectedMediaType("TV Show");
        } else if (component.equals(audioChoice)) {
            setSelectedMediaType("Audio Book");
        }

        // Section for buy/sell options
        else if (component.equals(rentChoice)) {
            setRentable(true);
        } else if (component.equals(buyChoice)) {
            setRentable(false);
        }
    }

    public void createNewMedia() {
        savedMediaInfo = null;
        setFields("", "", "Movie", "", 0, 0, true);
    }

    public boolean saveMediaChanges() {
        if (!validateInteger(priceText.getText())) {
            setPopUpWindow(new ConfirmationWindow(getController(), Confirmation.ADMINBADVALUE));
            priceText.errorText(priceText.getText());
            return false;
        }
        if (!validateInteger(sellPriceText.getText())) {
            setPopUpWindow(new ConfirmationWindow(getController(), Confirmation.ADMINBADVALUE));
            sellPriceText.errorText(sellPriceText.getText());
            return false;
        }

        String  title = titleText.getText(),
                desc = descriptionText.getText(),
                type = getSelectedMediaType(),
                genre = genreText.getText();
        int     id = isEditingExistingMedia() ? savedMediaInfo.getMediaID() : -1,
                price = Integer.parseInt(priceText.getText()),
                sellPrice = Integer.parseInt(sellPriceText.getText()),
                invCount = 5; // TODO: Allow for inventory count editing
        boolean rent = getRentable();

        int res = -1;
        if (isEditingExistingMedia()) {
            res = getController().updateMediaRequest(id, title, desc, type, genre, price, sellPrice, invCount, rent);
            if (res < 0) {
                setPopUpWindow(new ConfirmationWindow(getController(), Confirmation.MEDIAEDITFAIL));
                return false;
            }
        } else {
            res = getController().createNewMediaRequest(title, desc, type, genre, price, sellPrice, invCount, rent);
            if (res < 0) {
                setPopUpWindow(new ConfirmationWindow(getController(), Confirmation.MEDIACREATEFAIL));
                return false;
            }
        }

        savedMediaInfo = MediaInfo.createFromMedia(getController().getMediaDAO()
                .getMedia(isEditingExistingMedia() ? savedMediaInfo.getMediaID() : res));
        return true;
    }

    private boolean validateInteger(String val) {
        Scanner scanner = new Scanner(val);
        if (!scanner.hasNextInt()) return false;
        scanner.nextInt();
        return !scanner.hasNext();
    }

    public void revertMediaChanges() {
        setFields(savedMediaInfo.getTitle(), savedMediaInfo.getDescription(), savedMediaInfo.getType(), savedMediaInfo.getGenre(),
                savedMediaInfo.getPrice(), savedMediaInfo.getSellPrice(), savedMediaInfo.getIsRentable());
    }

    public boolean hasUnsavedChanges () {
        boolean existing = isEditingExistingMedia();
        return (!titleText.getText().equals(existing ? savedMediaInfo.getTitle() : "")) ||
                (!descriptionText.getText().equals(existing ? savedMediaInfo.getDescription() : "")) ||
                (!getSelectedMediaType().equals(existing ? savedMediaInfo.getType() : getSelectedMediaType())) ||
                (!genreText.getText().equals(existing ? savedMediaInfo.getGenre() : "")) ||
                (!priceText.getText().equals(existing ? "" + savedMediaInfo.getPrice() : "0")) ||
                (!sellPriceText.getText().equals(existing ? "" + savedMediaInfo.getSellPrice() : "0")) ||
                (getRentable() != (existing ? savedMediaInfo.getIsRentable() : true));
    }

    private void setFields(String title, String desc, String type, String genre, int price, int sellPrice, boolean rentable) {
        titleText.setText(title);
        descriptionText.setText(desc);
        setSelectedMediaType(type);
        genreText.setText(genre);
        priceText.setText("" + price);
        sellPriceText.setText("" + sellPrice);
        setRentable(rentable);
    }

    public void setSelectedMediaType(String type) {
        if (!SystemInventory.getSystemInventory().isValidType(type)) {
            // TODO: Report the error to the user
            System.out.println("The type '" + type + "' is not a valid media type.");
            return;
        }

        movieChoice.setIsSelected("Movie".equals(type));
        bookChoice.setIsSelected("Book".equals(type));
        musicChoice.setIsSelected("Music".equals(type));
        tvChoice.setIsSelected("TV Show".equals(type));
        audioChoice.setIsSelected("Audio Book".equals(type));
    }

    public String getSelectedMediaType() {
        if (movieChoice.getIsSelected()) {
            return "Movie";
        } else if (bookChoice.getIsSelected()) {
            return "Book";
        } else if (musicChoice.getIsSelected()) {
            return "Music";
        } else if (tvChoice.getIsSelected()) {
            return "TV Show";
        } else if (audioChoice.getIsSelected()) {
            return "Audio Book";
        } else {
            // TODO: Report this very unlikely error
            System.out.println("There was no valid selection for media type.");
            return "";
        }
    }

    public void setRentable(boolean rentable) {
        rentChoice.setIsSelected(rentable);
        buyChoice.setIsSelected(!rentable);
    }

    public boolean getRentable() {
        return rentChoice.getIsSelected();
    }

    public boolean isEditingExistingMedia() {
        return savedMediaInfo != null;
    }
}
