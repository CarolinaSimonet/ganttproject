package net.sourceforge.ganttproject.action;

import biz.ganttproject.core.option.GPOptionGroup;
import net.sourceforge.ganttproject.gui.UIFacade;
import net.sourceforge.ganttproject.gui.options.OptionsPageBuilder;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;


/**
 * Collection of actions from Help menu.
 *
 * @author Carolina Simonet & Margarida Carvalho
 */
public class TestButton extends GPAction {
    private final UIFacade myUiFacade;


    public TestButton(UIFacade uifacade) {
        super("task.dailyInfo", IconSize.MENU);
        myUiFacade = uifacade;
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

        myUiFacade.createDialog(createDialogComponent(), new Action[] { okAction }, "").show();
    }


    private void rollback() {
        for (GPOptionGroup group : myUiFacade.getOptions()) {
            group.rollback();
        }
    }


    //mudar para coisas da daily task
    private Component createDialogComponent() {
        OptionsPageBuilder builder = new OptionsPageBuilder();
        builder.setUiFacade(myUiFacade);
        JComponent comp = builder.buildPage(myUiFacade.getOptions(), "dailyInformation");
        comp.setBorder(new EmptyBorder(5, 5, 5, 5));
        return comp;
    }
}