
package net.sourceforge.ganttproject.test.task;

import biz.ganttproject.core.time.CalendarFactory;
import biz.ganttproject.core.time.GanttCalendar;
import net.sourceforge.ganttproject.action.DailyInfoButtonComponent;
import net.sourceforge.ganttproject.task.Task;
import net.sourceforge.ganttproject.task.TaskManager;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;



public class DailyInfoButtonComponentTest extends TaskTestCase {

    private static CalendarFactory.LocaleApi ourLocaleApi;


    public void testGetTodayTasks() {
        TaskManager mgr = getTaskManager();
        Date today = new Date();
        Date yesterday = new Date(2022, 12, 01);
        GanttCalendar calendar = new GanttCalendar(today,ourLocaleApi);
        Date endDay = new Date(2022, 12, 10);
        GanttCalendar calendarEnd = new GanttCalendar(endDay,ourLocaleApi);
        GanttCalendar calendarYesterday = new GanttCalendar(yesterday,ourLocaleApi);
        Task t2 = createTask();
        t2.setStart(calendar);
        t2.setEnd(calendarEnd);
        Task t3 = createTask();
        t3.setStart(calendarYesterday);
        t3.setEnd(calendarYesterday);

        DailyInfoButtonComponent button = new DailyInfoButtonComponent(mgr);
        ArrayList<Task> todayTasks = new ArrayList<Task>();
        if(button.isToday(t2)){
            todayTasks.add(t2);
        }
        if(button.isToday(t3)){
            todayTasks.add(t3);
        }

        assertTrue(todayTasks.contains(t2));
        assertFalse(todayTasks.contains(t3));
    }


    public void testGetAllTasksList() {
        Task t2 = createTask();
        Task t3 = createTask();
        ArrayList<Task> allTasks = new ArrayList<Task>();
        allTasks.add(t2);

        assertTrue(allTasks.contains(t2));
        assertFalse(allTasks.contains(t3));

    }


    public void testAddTodayTasks() throws Exception {
        TaskManager mgr = getTaskManager();
        Date today = new Date();
        Date yesterday = new Date(2022, 12, 01);
        GanttCalendar calendar = new GanttCalendar(today,ourLocaleApi);
        Date endDay = new Date(2022, 12, 10);
        GanttCalendar calendarEnd = new GanttCalendar(endDay,ourLocaleApi);
        GanttCalendar calendarYesterday = new GanttCalendar(yesterday,ourLocaleApi);
        Task t2 = createTask();
        t2.setStart(calendar);
        t2.setEnd(calendarEnd);
        Task t3 = createTask();
        t3.setStart(calendarYesterday);
        t3.setEnd(calendarYesterday);

        DailyInfoButtonComponent button = new DailyInfoButtonComponent(mgr);
        ArrayList<Task> todayTasks = new ArrayList<Task>();
        if(button.isToday(t2)){
            todayTasks.add(t2);
        }
        if(button.isToday(t3)){
            todayTasks.add(t3);
        }

        assertTrue(todayTasks.contains(t2));
        assertFalse(todayTasks.contains(t3));


    }

    public static interface LocaleApi {
        Locale getLocale();
        DateFormat getShortDateFormat();
    }


    public void testIsToday() {
        TaskManager mgr = getTaskManager();
        Date today = new Date();
        Date yesterday = new Date(2022, 12, 01);
        GanttCalendar calendar = new GanttCalendar(today,ourLocaleApi);
        Date endDay = new Date(2022, 12, 10);
        GanttCalendar calendarEnd = new GanttCalendar(endDay,ourLocaleApi);
        GanttCalendar calendarYesterday = new GanttCalendar(yesterday,ourLocaleApi);
        Task t2 = createTask();
        t2.setStart(calendar);
        t2.setEnd(calendarEnd);
        Task t3 = createTask();
        t3.setStart(calendarYesterday);
        t3.setEnd(calendarYesterday);

        DailyInfoButtonComponent button = new DailyInfoButtonComponent(mgr);

        assertTrue(button.isToday(t2));
        assertFalse(button.isToday(t3));


    }
}