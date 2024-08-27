package tw.RC.tutor;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class Clocktest extends JPanel {
    private Timer timer;

    public Clocktest() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                repaint();
            }
        }, 0, 10); // 每10毫秒更新一次
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawClockFace(g);
        drawClockHands(g);
    }

    private void drawClockFace(Graphics g) {
        int radius = Math.min(getWidth(), getHeight()) / 2 - 10;
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        g.setColor(Color.BLACK);
        g.drawOval(centerX - radius, centerY - radius, 2 * radius, 2 * radius);

        for (int i = 0; i < 12; i++) {
            double angle = Math.toRadians(i * 30);
            int x = (int) (centerX + radius * 0.8 * Math.sin(angle));
            int y = (int) (centerY - radius * 0.8 * Math.cos(angle));
            g.drawString(String.valueOf(i == 0 ? 12 : i), x - 5, y + 5);
        }
    }

    private void drawClockHands(Graphics g) {
        Calendar now = Calendar.getInstance();
        int hours = now.get(Calendar.HOUR);
        int minutes = now.get(Calendar.MINUTE);
        int seconds = now.get(Calendar.SECOND);
        int milliseconds = now.get(Calendar.MILLISECOND);

        int radius = Math.min(getWidth(), getHeight()) / 2 - 10;
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        // 秒针
        double secondAngle = Math.toRadians((seconds * 1000 + milliseconds) * 0.006);
        int secondHandLength = (int) (radius * 0.9);
        drawHand(g, centerX, centerY, secondAngle, secondHandLength, Color.RED);

        // 分针
        double minuteAngle = Math.toRadians(minutes * 6 + seconds * 0.1);
        int minuteHandLength = (int) (radius * 0.7);
        drawHand(g, centerX, centerY, minuteAngle, minuteHandLength, Color.BLACK);

        // 时针
        double hourAngle = Math.toRadians((hours % 12) * 30 + minutes * 0.5);
        int hourHandLength = (int) (radius * 0.5);
        drawHand(g, centerX, centerY, hourAngle, hourHandLength, Color.BLACK);
    }

    private void drawHand(Graphics g, int centerX, int centerY, double angle, int length, Color color) {
        int x = (int) (centerX + length * Math.sin(angle));
        int y = (int) (centerY - length * Math.cos(angle));
        g.setColor(color);
        g.drawLine(centerX, centerY, x, y);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Analog Clock");
        Clocktest clock = new Clocktest();
        frame.add(clock);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
