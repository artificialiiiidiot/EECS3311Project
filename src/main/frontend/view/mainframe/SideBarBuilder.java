package main.frontend.view.mainframe;

import main.frontend.common.PageBuilder;
import main.frontend.component.AccountPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;
import java.util.Map;

public class SideBarBuilder extends PageBuilder {
    private JPanel accountPanel;
    private Map<String, JButton> buttonMap = new LinkedHashMap<>();
    private FrontEnd frontEnd;

    public SideBarBuilder(JPanel page, FrontEnd frontEnd) {
        super(page);
        this.frontEnd = frontEnd;

        constraints = new GridBagConstraints();
        page.setLayout(new GridBagLayout());
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.gridwidth = 0; // one component per row
    }

    public void buildAccountPanel(String username) {
        accountPanel = new AccountPanel(username, frontEnd);
        constraints.gridy = gridy++;
        page.add(accountPanel);
    }

    @Override
    public void setUp() {
        Dimension dimension = new Dimension(200, 50);
        Font buttonFont = new Font("Arial", Font.BOLD, 14);
        String[] pageList = {"Home", "My Profile", "My Exercise Log", "My Meal Log"};
        for (int i=0; i<pageList.length; i++) {
            buttonMap.put(pageList[i], new JButton(pageList[i]));
            buttonMap.get(pageList[i]).setPreferredSize(dimension);
            buttonMap.get(pageList[i]).setFont(buttonFont);
        }
    }

    @Override
    public void buildMainContent() {
        // Add some empty space after the content
        constraints.gridy = gridy++;
        page.add(Box.createVerticalStrut(10), constraints);

        for (Map.Entry<String, JButton> entry : buttonMap.entrySet()) {
            constraints.gridy = gridy++;
            page.add(entry.getValue(), constraints);
        }
    }

    public void buildFooter() {
        constraints.gridy = gridy++;
        page.add(Box.createVerticalStrut(350), constraints);

        Font buttonFont = new Font("Arial", Font.PLAIN, 14);
        JLabel versionLabel = new JLabel("ver.0.1.0");
        versionLabel.setFont(buttonFont);

        constraints.gridy = gridy++;
        page.add(versionLabel, constraints);
    }

    public void setListener() {
        ActionListener homeListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Home button clicked!");
                frontEnd.switchContentPanel(frontEnd.getPageMap().get("Home"));
            }
        };
        buttonMap.get("Home").addActionListener(homeListener);

        ActionListener profileListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("profile button clicked!");
                frontEnd.switchContentPanel(frontEnd.getPageMap().get("UserProfile"));
            }
        };
        buttonMap.get("My Profile").addActionListener(profileListener);

        ActionListener exerciseListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("exercise button clicked!");
                frontEnd.switchContentPanel(frontEnd.getPageMap().get("Exercise"));
            }
        };
        buttonMap.get("My Exercise Log").addActionListener(exerciseListener);

        ActionListener mealListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("meal button clicked!");
                frontEnd.switchContentPanel(frontEnd.getPageMap().get("Meal"));
            }
        };
        buttonMap.get("My Meal Log").addActionListener(mealListener);
    }
}
