package tw.RC.tutor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.HashSet;
import java.util.Set;

public class SignInApp extends JFrame {
    private final YearMonth currentYearMonth;
    private final Set<Integer> signedInDates;
    private final JPanel calendarPanel;

    public SignInApp() {
        currentYearMonth = YearMonth.now(); // 当前的年月
        signedInDates = new HashSet<>();    // 用于存储已签到的日期
        calendarPanel = new JPanel(new GridLayout(0, 7)); // 7列表示一周7天

        setTitle("签到程序");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        createCalendar();
    }

    private void createCalendar() {
        LocalDate firstDayOfMonth = currentYearMonth.atDay(1);
        int daysInMonth = currentYearMonth.lengthOfMonth();
        int dayOfWeek = firstDayOfMonth.getDayOfWeek().getValue(); // 获取该月的第一天是星期几

        // 填充前面的空白
        for (int i = 1; i < dayOfWeek; i++) {
            calendarPanel.add(new JLabel(""));
        }

        // 填充日历的日期
        for (int day = 1; day <= daysInMonth; day++) {
            JButton dayButton = new JButton(String.valueOf(day));
            if (signedInDates.contains(day)) {
                dayButton.setBackground(Color.GREEN); // 已签到的日期标记为绿色
            }
            dayButton.addActionListener(new SignInAction(day));
            calendarPanel.add(dayButton);
        }

        add(calendarPanel);
    }

    private class SignInAction implements ActionListener {
        private final int day;

        public SignInAction(int day) {
            this.day = day;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!signedInDates.contains(day)) {
                signedInDates.add(day);
                ((JButton) e.getSource()).setBackground(Color.GREEN); // 签到后将日期标记为绿色
            } else {
                JOptionPane.showMessageDialog(null, "您已签到！");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SignInApp app = new SignInApp();
            app.setVisible(true);
        });
    }
}