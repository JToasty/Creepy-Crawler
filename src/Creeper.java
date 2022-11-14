import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created on 5/10/17.
 */
public class Creeper{
    boolean allowRun;
    Crawler crawler;

    JFrame frame;
    JTextField startLink;
    JTextPane display;

    public Creeper(){
        allowRun = true;
        crawler = new Crawler();
        frame = new JFrame("Creepy Crawler");
        frame.setLayout(new GridLayout(2,1));
        frame.setPreferredSize(new Dimension(780,330));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel user_panel = new JPanel();
        user_panel.setPreferredSize(new Dimension(400, 20));
        JPanel crawler_panel = new JPanel();
        crawler_panel.setPreferredSize(new Dimension(740,550));
        crawler_panel.setBorder(new EmptyBorder(0,0,200,0));

        display = new JTextPane();
        display.setBorder(new TitledBorder(new EtchedBorder(), "Found Links"));
        display.setPreferredSize(new Dimension(740,145));
        display.setEditable(false);
        JScrollPane scroll = new JScrollPane(display);




        startLink = new JTextField("Starting Web Address");
        startLink.setPreferredSize(new Dimension(340, 20));
        JButton go = new JButton("GO");
        go.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                allowRun = true;
                display.setText("Crawling! This may take awhile...");
                startCrawl();
                display.setText("");
                updateWindow();
                frame.pack();
            }
        });
        JButton stop = new JButton("Clear");
        stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                allowRun = false;
                crawler.cleanUp();
                display.setText("");
                frame.pack();
            }
        });

        crawler_panel.add(scroll);
        user_panel.add(startLink);
        user_panel.add(go);
        user_panel.add(stop);
        frame.add(crawler_panel);
        frame.add(user_panel);
        frame.pack();
        frame.setLocationRelativeTo ( null );
        frame.setVisible(true);
    }

    public void startCrawl() {
        try {
            crawler.crawl(startLink.getText());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void updateWindow(){
        for(String L : crawler.getWeblinks())
            display.setText(display.getText()+"\n"+L);
    }

}
