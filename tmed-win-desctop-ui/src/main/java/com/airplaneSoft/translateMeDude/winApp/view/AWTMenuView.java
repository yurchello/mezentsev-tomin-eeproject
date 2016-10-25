package com.airplaneSoft.translateMeDude.winApp.view;

import com.airplaneSoft.translateMeDude.winApp.viewModel.AWTMenuModel;
import javafx.application.Platform;

import java.awt.*;

import static com.airplaneSoft.translateMeDude.winApp.utils.AppUtils.getStringProperty;

/**
 * Created by Mezentsev.Y on 10/25/2016.
 */
public class AWTMenuView extends java.awt.PopupMenu {
    private CallFunction callToExitApp;
    private AWTMenuModel model;

    public AWTMenuView(CallFunction callToExitApp) throws HeadlessException {
        this.model = new AWTMenuModel();
        this.callToExitApp = callToExitApp;
        init();
    }

    private void init() {
        add(createMenuItemStyled(getStringProperty("ui.mainView.tray.menu.settings"), () -> new SettingsDialogView().show()));
        add(createMenuItemStyledMainThread(getStringProperty("ui.mainView.tray.menu.update"), () -> {
            model.update();
        }));
        addSeparator();
        add(createMenuItem(getStringProperty("ui.mainView.tray.menu.exit"), this.callToExitApp));
    }


    private java.awt.MenuItem createMenuItemStyledMainThread(String label, CallFunction callFunction) {
        java.awt.MenuItem menuItem = new java.awt.MenuItem(label);
        java.awt.Font defaultFont = java.awt.Font.decode(null);
        java.awt.Font boldFont = defaultFont.deriveFont(java.awt.Font.BOLD);
        menuItem.setFont(boldFont);
        menuItem.addActionListener(event -> {
            callFunction.call();
        });
        return menuItem;
    }

    private java.awt.MenuItem createMenuItemStyled(String label, CallFunction callFunction) {
        java.awt.MenuItem menuItem = new java.awt.MenuItem(label);
        java.awt.Font defaultFont = java.awt.Font.decode(null);
        java.awt.Font boldFont = defaultFont.deriveFont(java.awt.Font.BOLD);
        menuItem.setFont(boldFont);
        menuItem.addActionListener(event -> {
            Platform.runLater(() -> {
                callFunction.call();
            });
        });
        return menuItem;
    }

    private java.awt.MenuItem createMenuItem(String label, CallFunction callFunction) {
        java.awt.MenuItem menuItem = new java.awt.MenuItem(label);
        menuItem.addActionListener(event -> {
            Platform.runLater(() -> {
                callFunction.call();
            });
        });
        return menuItem;
    }

    @FunctionalInterface
    public interface CallFunction {
        void call();
    }
}
