package com.airplaneSoft.translateMeDude.winApp.view;

import com.airplaneSoft.translateMeDude.models.vocabulary.WordsGroup;
import com.airplaneSoft.translateMeDude.winApp.App;
import com.airplaneSoft.translateMeDude.winApp.AppUtils;
import com.airplaneSoft.translateMeDude.winApp.dialogComponent.LoadingDialogView;
import com.airplaneSoft.translateMeDude.winApp.settings.settingsModel.SettingsImpl;
import com.airplaneSoft.translateMeDude.winApp.settings.settingsModel.SettingsKeys;
import com.airplaneSoft.translateMeDude.winApp.settings.settingsView.SettingsDialogView;
import com.airplaneSoft.translateMeDude.winApp.tasks.UpdateVocabularyTask;
import com.airplaneSoft.translateMeDude.winApp.utils.GuiUtils;
import com.airplaneSoft.translateMeDude.winApp.viewModel.AWTMenuViewModel;
import javafx.application.Platform;
import javafx.concurrent.Worker;

import java.awt.*;
import java.util.*;
import java.util.concurrent.ExecutionException;

import static com.airplaneSoft.translateMeDude.winApp.AppUtils.getStringProperty;

/**
 * Created by Mezentsev.Y on 10/25/2016.
 */
public class AWTMenuView extends  java.awt.PopupMenu {
    private LoadingDialogView loadingDialog;
    private CallFunction callToExitApp;
    private AWTMenuViewModel model;
    public AWTMenuView(CallFunction callToExitApp) throws HeadlessException {
        this.model = new AWTMenuViewModel();
        this.callToExitApp = callToExitApp;
        init();
    }

    private void init(){
        add(createMenuItemStyled(getStringProperty("ui.mainView.tray.menu.settings"), ()-> new SettingsDialogView().show()));
        add(createMenuItemStyledMainThread(getStringProperty("ui.mainView.tray.menu.update"),
                ()-> {
                    Platform.runLater(() -> {
                        loadingDialog = new LoadingDialogView(AppUtils.getStringProperty("ui.loading.dialog.loading.vocabulary"));
                        loadingDialog.show();
                    });

                        UpdateVocabularyTask task = new UpdateVocabularyTask(SettingsImpl.getInstance().get(SettingsKeys.URL),
                                SettingsImpl.getInstance().get(SettingsKeys.SSOID),
                                SettingsImpl.getInstance().get(SettingsKeys.PASSWORD));
                        task.stateProperty().addListener((observableValue, oldState, newState) -> {
                            java.util.List<WordsGroup> wordsGroupList = null;
                            if (newState == Worker.State.SUCCEEDED || newState == Worker.State.FAILED)
                                    loadingDialog.close();
                                    if (newState == Worker.State.FAILED) {
                                        GuiUtils.showErrorAlert(AppUtils.getStringProperty("ui.alert.error"), AppUtils.getStringProperty("ui.alert.update.error"));
                                    }
                                    System.out.println("Update finished with status " + newState);
                        });
                        new Thread(task).run();
                }));
        addSeparator();
        add(createMenuItem(getStringProperty("ui.mainView.tray.menu.exit"), this.callToExitApp));
    }


    private java.awt.MenuItem createMenuItemStyledMainThread(String label, CallFunction callFunction){
        java.awt.MenuItem menuItem = new java.awt.MenuItem(label);
        java.awt.Font defaultFont = java.awt.Font.decode(null);
        java.awt.Font boldFont = defaultFont.deriveFont(java.awt.Font.BOLD);
        menuItem.setFont(boldFont);
        menuItem.addActionListener(event -> {
            callFunction.call();
        });
        return menuItem;
    }

    private java.awt.MenuItem createMenuItemStyled(String label, CallFunction callFunction){
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

    private java.awt.MenuItem createMenuItem(String label, CallFunction callFunction){
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
