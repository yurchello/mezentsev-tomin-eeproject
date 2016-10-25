package com.airplaneSoft.translateMeDude.winApp.viewModel;

import com.airplaneSoft.translateMeDude.models.vocabulary.WordsGroup;
import com.airplaneSoft.translateMeDude.winApp.AppUtils;
import com.airplaneSoft.translateMeDude.winApp.dialogComponent.LoadingDialogView;
import com.airplaneSoft.translateMeDude.winApp.settings.settingsModel.Settings;
import com.airplaneSoft.translateMeDude.winApp.settings.settingsModel.SettingsImpl;
import com.airplaneSoft.translateMeDude.winApp.settings.settingsModel.SettingsKeys;
import com.airplaneSoft.translateMeDude.winApp.tasks.UpdateVocabularyTask;
import com.airplaneSoft.translateMeDude.winApp.utils.GuiUtils;
import javafx.application.Platform;
import javafx.concurrent.Worker;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Mezentsev.Y on 10/25/2016.
 */
public class AWTMenuViewModel {
    private Settings settings;
    //LoadingDialogView loadingDialog;

    public AWTMenuViewModel() {
        this.settings = SettingsImpl.getInstance();
    }

//    public void update(){
//        UpdateVocabularyTask task = new UpdateVocabularyTask(settings.get(SettingsKeys.URL),
//                settings.get(SettingsKeys.SSOID),
//                settings.get(SettingsKeys.PASSWORD));
//        task.stateProperty().addListener((observableValue, oldState, newState) -> {
//
//            List<WordsGroup> wordsGroupList = null;
//            if (newState == Worker.State.SUCCEEDED || newState == Worker.State.FAILED) {
//                try {
//                    wordsGroupList = task.get();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                } catch (ExecutionException e) {
//                    e.printStackTrace();
//                }
//
//            }
////            if (wordsGroupList == null){
////                GuiUtils.showErrorAlert(AppUtils.getStringProperty("ui.alert.error"),AppUtils.getStringProperty("ui.alert.update.error"));
////            }
////            try {
////                wordsGroupList = task.get();
////            } catch (InterruptedException | ExecutionException e) {
////                System.out.println("Update task failed.");
////                e.printStackTrace();
////            }finally {
////                if (newState == Worker.State.FAILED){
////                    GuiUtils.showErrorAlert(AppUtils.getStringProperty("ui.alert.error"),AppUtils.getStringProperty("ui.alert.update.error"));
////                }
////                System.out.println("Update finished with status " + newState);
////            }
//        });
//        new Thread(task).run();
//    }
}
