package net.sourceforge.ganttproject.action;

import biz.ganttproject.core.option.GPOptionGroup;
import net.sourceforge.ganttproject.IGanttProject;
import net.sourceforge.ganttproject.gui.UIFacade;
import net.sourceforge.ganttproject.language.GanttLanguage;
import net.sourceforge.ganttproject.task.TaskManager;

import javax.swing.*;
import java.awt.event.ActionEvent;


/**
 * Button with the daily information dialog about tasks
 *
 * @author Carolina Simonet & Margarida Carvalho & Filipe Santo
 */
public class DailyInfoButton extends GPAction {
    private final UIFacade myUiFacade;

    private IGanttProject myProject;

    private DailyInfoButtonComponent contentPanel;

    private TaskManager myTaskManager;


    private static final GanttLanguage language = GanttLanguage.getInstance();


    public DailyInfoButton(IGanttProject project, UIFacade uifacade) {
        super("task.dailyInfo", IconSize.MENU);
        myProject = project;
        myUiFacade = uifacade;
        myTaskManager = project.getTaskManager();
    }


    public void updateProject(IGanttProject project) {
        myProject = project;
        myTaskManager = project.getTaskManager();
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
        contentPanel = new DailyInfoButtonComponent(myTaskManager);
        myUiFacade.createDialog(contentPanel.getComponent(), new Action[]{okAction}, language.getText("task.dailyInfo")).show();


    }


    private void rollback() {
        for (GPOptionGroup group : myUiFacade.getOptions()) {
            group.rollback();
        }
    }

}

