package net.sourceforge.ganttproject.action;

import net.sourceforge.ganttproject.language.GanttLanguage;
import net.sourceforge.ganttproject.task.Task;
import net.sourceforge.ganttproject.task.TaskManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

/**
 *
 */
public class DailyInfoButtonComponent {

    private static final GanttLanguage language = GanttLanguage.getInstance();

    private Box taskPanel;
    public final TaskManager myTaskManager;

    private final Task[] allTasks;

    private ArrayList<Task> allTasksList;

    private ArrayList<Task> todayTasks;

    private int SPACE = 50;

    public DailyInfoButtonComponent(TaskManager taskManager) {

        taskPanel = Box.createVerticalBox();
        myTaskManager = taskManager;
        allTasks = myTaskManager.getTasks();

        //tansform the array into an arraylist
        allTasksList = new ArrayList<Task>(Arrays.asList(allTasks));
        todayTasks = new ArrayList<Task>();
    }

    public JComponent getComponent() {

        SpringLayout panelLayout = new SpringLayout();
        JFrame frame = new JFrame();
        JPanel panel = new JPanel(panelLayout);
        JScrollPane scrollPane = new JScrollPane(panel);


        //JPanel panel = new JPanel(panelLayout);

        JComponent resumeComponent = new JLabel(language.getText("dailyInfo.resume"));

        panel.add(resumeComponent);
        panelLayout.putConstraint(SpringLayout.WEST, resumeComponent, 5, SpringLayout.WEST, scrollPane);
        panelLayout.putConstraint(SpringLayout.NORTH, resumeComponent, 5, SpringLayout.NORTH, scrollPane);

        addTodayTasks();

        Iterator it = todayTasks.iterator();
        if(todayTasks.isEmpty()){
            JComponent temp = new JLabel(language.getText("dailyInfo.empty"));
            panel.add(temp);
            panelLayout.putConstraint(SpringLayout.WEST, temp, SPACE, SpringLayout.WEST, scrollPane);
            panelLayout.putConstraint(SpringLayout.SOUTH, temp, SPACE, SpringLayout.NORTH, resumeComponent);
        }
        else
            while (it.hasNext()) {
                //String str = it.next().toString();
                Task task = (Task) it.next();
                Long time = task.getEnd().getTime().getTime() - (new Date()).getTime();
                Long days = TimeUnit.MILLISECONDS.toDays(time);
                String str = "Nome da tarefa: " + task.getName() + " | Dias restantes: " + days.toString() ;
                JComponent temp = new JLabel("- " + str);
                panel.add(temp);
                panelLayout.putConstraint(SpringLayout.WEST, temp, 50, SpringLayout.WEST, scrollPane);
                panelLayout.putConstraint(SpringLayout.SOUTH, temp, SPACE, SpringLayout.NORTH, resumeComponent);
                SPACE = SPACE + 30;
            }

        taskPanel.add(scrollPane);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(50, 30, 300, 50);
        scrollPane.setPreferredSize(new Dimension(500, 400));
        //panel.add(scrollPane);
        return taskPanel;
    }


    public ArrayList<Task> getTodayTasks() {
        return todayTasks;
    }

    public ArrayList<Task> getAllTasksList() {
        return allTasksList;
    }


    public void addTodayTasks() {
        Iterator<Task> allTasks = allTasksList.iterator();
        while(allTasks.hasNext()){
            Task task = allTasks.next();
            if (isToday(task))
                todayTasks.add(task);
        }
    }

    public boolean isToday(Task task) {
        Date today =  new Date();
        Date taskStart = task.getStart().getTime();
        Date taskEnd = task.getEnd().getTime();

        if((taskStart.before(today) || taskStart.equals(today)) && (taskEnd.after(today) || taskEnd.equals(today)))
            return true;

        return false;
    }

    private static void addEmptyRow(JPanel form) {
        form.add(Box.createRigidArea(new Dimension(1, 10)));
        form.add(Box.createRigidArea(new Dimension(1, 10)));
    }
}