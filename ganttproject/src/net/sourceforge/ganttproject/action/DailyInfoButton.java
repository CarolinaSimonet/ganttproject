package net.sourceforge.ganttproject.action;

import biz.ganttproject.core.option.GPOptionGroup;
import net.sourceforge.ganttproject.IGanttProject;
import net.sourceforge.ganttproject.gui.UIFacade;
import net.sourceforge.ganttproject.language.GanttLanguage;
import net.sourceforge.ganttproject.task.TaskManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;


/**
 * Collection of actions from Help menu.
 *
 * @author Carolina Simonet & Margarida Carvalho
 */
public class DailyInfoButton extends GPAction {
    private final UIFacade myUiFacade;

    private IGanttProject myProject;

    private DailyInfoButtonComponent contentPanel;

    private TaskManager myTaskManager;

    private JTextField nameField1;

    private static final GanttLanguage language = GanttLanguage.getInstance();


    public DailyInfoButton(IGanttProject project, UIFacade uifacade) {
        super("task.dailyInfo", IconSize.MENU);
        myProject = project;
        myUiFacade = uifacade;
        myTaskManager = project.getTaskManager();
        //contentPanel = new MyTestComponent(myTaskManager);
    }

    public void updateProject(IGanttProject project) {
        myProject = project;
        myTaskManager = project.getTaskManager();
    }

    private static void addEmptyRow(JPanel form) {
        form.add(Box.createRigidArea(new Dimension(1, 10)));
        form.add(Box.createRigidArea(new Dimension(1, 10)));
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        //showAboutDialog();
        for (GPOptionGroup group : myUiFacade.getOptions()) {
            group.lock();
        }
        final OkAction okAction = new OkAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rollback();
            }
        };

        myUiFacade.refresh();
        contentPanel = new MyTestComponent(myTaskManager);
        myUiFacade.createDialog(contentPanel.getComponent(), new Action[]{okAction}, language.getText("task.dailyInfo")).show();

      /*   OptionsPageBuilder optionsBuilder = new OptionsPageBuilder();
        optionsBuilder.setUiFacade(myUiFacade);
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(new JLabel(language.getText("name")));
        addEmptyRow(contentPanel);
        contentPanel.add(new JLabel("Resumo das tasks"));
        myUiFacade.createDialog(contentPanel, new Action[]{okAction}, "dailyInformation").show();


        //contentPanel.add(optionsBuilder.createGroupComponent(myUiFacade.getGanttChart().getBaselineColorOptions()), BorderLayout.SOUTH);
        contentPanel.add(new JLabel("Resumo das tasks"));
        addEmptyRow(contentPanel);
        contentPanel.add(new JLabel("Outro resumo das tasks"));
        //vamos ter de fazer algo assim
        //contentPanel.add(optionsBuilder.createGroupComponent()

      */

    }


    private void rollback() {
        for (GPOptionGroup group : myUiFacade.getOptions()) {
            group.rollback();
        }
    }


    public Component getComponentTest() {
        MyTestComponent c = new MyTestComponent(myTaskManager);
        return c.getComponent();
    }

}


//mudar para coisas da daily task
      /*     private Component createDialogComponent() {
                 OptionsPageBuilder builder = new OptionsPageBuilder();
                 builder.setUiFacade(myUiFacade);
                 JComponent comp = builder.buildPage(myUiFacade.getOptions(), "dailyInformation");
                 comp.setBorder(new EmptyBorder(5, 5, 5, 5));
                 return comp;

             }*/