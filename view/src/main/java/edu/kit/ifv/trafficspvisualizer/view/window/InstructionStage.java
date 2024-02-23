package edu.kit.ifv.trafficspvisualizer.view.window;

import edu.kit.ifv.trafficspvisualizer.view.ViewFacade;
import edu.kit.ifv.trafficspvisualizer.view.data.image.ImageLibrary;
import edu.kit.ifv.trafficspvisualizer.view.data.language.LanguageStrategy;
import edu.kit.ifv.trafficspvisualizer.view.style.Styler;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 * The {@link InstructionStage} inherits from {@link Stage} and is a sub-window of the application
 * which can be opened from the {@link MainApplicationWindow}
 * and on which the user can see how to use the application.
 *
 * @version 1.0
 */
public class InstructionStage extends Stage {

    private final ViewFacade viewFacade;


    private Text projectHeaderText;

    private Text projectContentText;

    private GridPane projectSectionGridPane;


    private Text creatingNewProjectHeaderText;

    private Text creatingNewProjectContentText;

    private GridPane creatingNewProjectSectionGridPane;


    private Text savingProjectHeaderText;

    private Text savingProjectContentText;

    private GridPane savingProjectSectionGridPane;


    private Text loadingProjectHeaderText;

    private Text loadingProjectContentText;

    private GridPane loadingProjectSectionGridPane;


    private Text previewHeaderText;

    private Text previewContentText;

    private GridPane previewSectionGridPane;


    private Text attributesWindowHeaderText;

    private Text attributesWindowFirstContentText;

    private Text attributesWindowSecondContentText;

    private GridPane attributesWindowSectionGridPane;


    private Text attributeSettingsWindowHeaderText;

    private Text attributeSettingsWindowContentText;

    private GridPane attributeSettingsWindowSectionGridPane;


    private Text choiceOptionsHeaderText;

    private Text choiceOptionsContentText;

    private GridPane choiceOptionsSectionGridPane;


    private Text choiceOptionSettingsWindowHeaderText;

    private Text choiceOptionSettingsWindowFirstContentText;

    private Text choiceOptionSettingsWindowSecondContentText;

    private Text choiceOptionSettingsWindowThirdContentText;

    private GridPane choiceOptionSettingsWindowSectionGridPane;


    private Text exportSettingsHeaderText;

    private Text exportSettingsContentText;

    private GridPane exportSettingsSectionGridPane;


    private Text exportHeaderText;

    private Text exportContentText;

    private GridPane exportSectionGridPane;


    private VBox textVBox;

    private ScrollPane bodyScrollPane;

    private Scene scene;


    /**
     * Creates the {@link InstructionStage}.
     *
     * @param viewFacade The {@link ViewFacade} through which this class can access the {@link LanguageStrategy}.
     */
    public InstructionStage(ViewFacade viewFacade) {
        this.viewFacade = viewFacade;
        buildStage();
        styleStage();

        show();
    }

    private void buildStage() {
        LanguageStrategy languageStrategy = viewFacade.getLanguageStrategy();

        projectHeaderText = new Text(languageStrategy.getInstructionProjectHeaderText());

        projectContentText = new Text(languageStrategy.getInstructionProjectContentText());

        projectSectionGridPane = new GridPane();
        projectSectionGridPane.add(projectHeaderText, 0, 0);
        projectSectionGridPane.add(projectContentText, 0, 1);


        creatingNewProjectHeaderText = new Text(languageStrategy.getInstructionCreatingNewProjectHeaderText());

        creatingNewProjectContentText = new Text(languageStrategy.getInstructionCreatingNewProjectContentText());

        creatingNewProjectSectionGridPane = new GridPane();
        creatingNewProjectSectionGridPane.add(creatingNewProjectHeaderText, 0, 0);
        creatingNewProjectSectionGridPane.add(creatingNewProjectContentText, 0, 1);


        savingProjectHeaderText = new Text(languageStrategy.getInstructionSavingProjectHeaderText());

        savingProjectContentText = new Text(languageStrategy.getInstructionSavingProjectContentText());

        savingProjectSectionGridPane = new GridPane();
        savingProjectSectionGridPane.add(savingProjectHeaderText, 0, 0);
        savingProjectSectionGridPane.add(savingProjectContentText, 0, 1);


        loadingProjectHeaderText = new Text(languageStrategy.getInstructionLoadingProjectHeaderText());

        loadingProjectContentText = new Text(languageStrategy.getInstructionLoadingProjectContentText());

        loadingProjectSectionGridPane = new GridPane();
        loadingProjectSectionGridPane.add(loadingProjectHeaderText, 0, 0);
        loadingProjectSectionGridPane.add(loadingProjectContentText, 0, 1);

        previewHeaderText = new Text(languageStrategy.getInstructionPreviewHeaderText());

        previewContentText = new Text(languageStrategy.getInstructionPreviewContentText());

        previewSectionGridPane = new GridPane();
        previewSectionGridPane.add(previewHeaderText, 0, 0);
        previewSectionGridPane.add(previewContentText, 0, 1);


        attributesWindowHeaderText = new Text(languageStrategy.getInstructionAttributesWindowHeaderText());

        attributesWindowFirstContentText = new Text(languageStrategy.getInstructionAttributesWindowFirstContentText());

        attributesWindowSecondContentText =
                new Text(languageStrategy.getInstructionAttributesWindowSecondContentText());

        attributesWindowSectionGridPane = new GridPane();
        attributesWindowSectionGridPane.add(attributesWindowHeaderText, 0, 0);
        attributesWindowSectionGridPane.add(attributesWindowFirstContentText, 0, 1);
        attributesWindowSectionGridPane.add(attributesWindowSecondContentText, 0, 2);


        attributeSettingsWindowHeaderText =
                new Text(languageStrategy.getInstructionAttributeSettingsWindowHeaderText());

        attributeSettingsWindowContentText =
                new Text(languageStrategy.getInstructionAttributeSettingsWindowContentText());

        attributeSettingsWindowSectionGridPane = new GridPane();
        attributeSettingsWindowSectionGridPane.add(attributeSettingsWindowHeaderText, 0, 0);
        attributeSettingsWindowSectionGridPane.add(attributeSettingsWindowContentText, 0, 1);


        choiceOptionsHeaderText = new Text(languageStrategy.getInstructionChoiceOptionsHeaderText());

        choiceOptionsContentText = new Text(languageStrategy.getInstructionChoiceOptionsContentText());

        choiceOptionsSectionGridPane = new GridPane();
        choiceOptionsSectionGridPane.add(choiceOptionsHeaderText, 0, 0);
        choiceOptionsSectionGridPane.add(choiceOptionsContentText, 0, 1);


        choiceOptionSettingsWindowHeaderText =
                new Text(languageStrategy.getInstructionChoiceOptionSettingsWindowHeaderText());

        choiceOptionSettingsWindowFirstContentText =
                new Text(languageStrategy.getInstructionChoiceOptionSettingsWindowFirstContentText());

        choiceOptionSettingsWindowSecondContentText =
                new Text(languageStrategy.getInstructionChoiceOptionSettingsWindowSecondContentText());

        choiceOptionSettingsWindowThirdContentText =
                new Text(languageStrategy.getInstructionChoiceOptionSettingsWindowThirdContentText());

        choiceOptionSettingsWindowSectionGridPane = new GridPane();
        choiceOptionSettingsWindowSectionGridPane.add(choiceOptionSettingsWindowHeaderText, 0, 0);
        choiceOptionSettingsWindowSectionGridPane.add(choiceOptionSettingsWindowFirstContentText, 0, 1);
        choiceOptionSettingsWindowSectionGridPane.add(choiceOptionSettingsWindowSecondContentText, 0, 2);
        choiceOptionSettingsWindowSectionGridPane.add(choiceOptionSettingsWindowThirdContentText, 0, 3);


        exportSettingsHeaderText = new Text(languageStrategy.getInstructionExportSettingsHeaderText());

        exportSettingsContentText = new Text(languageStrategy.getInstructionExportSettingsContentText());

        exportSettingsSectionGridPane = new GridPane();
        exportSettingsSectionGridPane.add(exportSettingsHeaderText, 0, 0);
        exportSettingsSectionGridPane.add(exportSettingsContentText, 0, 1);


        exportHeaderText = new Text(languageStrategy.getInstructionExportHeaderText());

        exportContentText = new Text(languageStrategy.getInstructionExportContentText());

        exportSectionGridPane = new GridPane();
        exportSectionGridPane.add(exportHeaderText, 0, 0);
        exportSectionGridPane.add(exportContentText, 0, 1);


        textVBox = new VBox(projectSectionGridPane, creatingNewProjectSectionGridPane, savingProjectSectionGridPane,
                loadingProjectSectionGridPane, previewSectionGridPane, attributesWindowSectionGridPane,
                attributeSettingsWindowSectionGridPane, choiceOptionsSectionGridPane,
                choiceOptionSettingsWindowSectionGridPane, exportSettingsSectionGridPane, exportSectionGridPane);

        bodyScrollPane = new ScrollPane(textVBox);

        scene = new Scene(bodyScrollPane);

        setTitle(languageStrategy.getInstructionTitle());
        setScene(scene);
        initModality(Modality.NONE);
        getIcons().add(ImageLibrary.getApplicationIcon());
    }

    private void styleStage() {
        Styler.continuousTextWithinGridPane(projectSectionGridPane, projectHeaderText, projectContentText);
        styleContentTexts(projectSectionGridPane, projectContentText);

        Styler.continuousTextWithinGridPane(creatingNewProjectSectionGridPane,
                creatingNewProjectHeaderText, creatingNewProjectContentText);
        styleContentTexts(creatingNewProjectSectionGridPane, creatingNewProjectContentText);

        Styler.continuousTextWithinGridPane(savingProjectSectionGridPane, savingProjectHeaderText,
                savingProjectContentText);
        styleContentTexts(savingProjectSectionGridPane, savingProjectContentText);

        Styler.continuousTextWithinGridPane(loadingProjectSectionGridPane, loadingProjectHeaderText,
                loadingProjectContentText);
        styleContentTexts(loadingProjectSectionGridPane, loadingProjectContentText);

        Styler.continuousTextWithinGridPane(previewSectionGridPane, previewHeaderText, previewContentText);
        styleContentTexts(previewSectionGridPane, previewContentText);

        Styler.continuousTextWithinGridPane(attributesWindowSectionGridPane, attributesWindowHeaderText,
                attributesWindowFirstContentText, attributesWindowSecondContentText);
        styleContentTexts(attributesWindowSectionGridPane, attributesWindowFirstContentText,
                attributesWindowSecondContentText);

        Styler.continuousTextWithinGridPane(attributeSettingsWindowSectionGridPane, attributeSettingsWindowHeaderText,
                attributeSettingsWindowContentText);
        styleContentTexts(attributeSettingsWindowSectionGridPane, attributeSettingsWindowContentText);

        Styler.continuousTextWithinGridPane(choiceOptionsSectionGridPane, choiceOptionsHeaderText,
                choiceOptionsContentText);
        styleContentTexts(choiceOptionsSectionGridPane, choiceOptionsContentText);

        Styler.continuousTextWithinGridPane(choiceOptionSettingsWindowSectionGridPane,
                choiceOptionSettingsWindowHeaderText, choiceOptionSettingsWindowFirstContentText,
                choiceOptionSettingsWindowSecondContentText, choiceOptionSettingsWindowThirdContentText);
        styleContentTexts(choiceOptionSettingsWindowSectionGridPane, choiceOptionSettingsWindowFirstContentText,
                choiceOptionSettingsWindowSecondContentText, choiceOptionSettingsWindowThirdContentText);

        Styler.continuousTextWithinGridPane(exportSettingsSectionGridPane, exportSettingsHeaderText,
                exportSettingsContentText);
        styleContentTexts(exportSettingsSectionGridPane, exportSettingsContentText);

        Styler.continuousTextWithinGridPane(exportSectionGridPane, exportHeaderText, exportContentText);
        styleContentTexts(exportSectionGridPane, exportContentText);


        bodyScrollPane.setFitToWidth(true);
        bodyScrollPane.setFitToHeight(true);

        Styler.midStage(this);
    }

    private void styleContentTexts(GridPane parentGridPane, Text... contentTexts) {
        for (Text contentText : contentTexts) {
            contentText.wrappingWidthProperty().bind(
                    scene.widthProperty()
                            .subtract(parentGridPane.hgapProperty().multiply(2))
                            .subtract(17));
        }
    }

}
