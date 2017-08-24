package com.tombigun.test.excel;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class TestJFrame extends JPanel {
    private static final long serialVersionUID = 1L;
    static final int WIDTH = 400;
    static final int HEIGHT = 200;
    JFrame loginframe;


    String inAbsolutePathFileName;
    String outAbsolutePathFileName;

    JLabel name;
    JTextField outFileName;
    JButton doBtn;

///按照网格组布局方式排列组件的方法
///x指控件位于第几列。
///y指控件位于第几行。
///w指控件需要占几列。
///h指控件需要占几行。
    public void add(Component c, GridBagConstraints constraints, int x, int y, int w, int h) {
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.gridwidth = w;
        constraints.gridheight = h;
        add(c, constraints);
    }

    TestJFrame() {
        loginframe = new JFrame("汇总工具");
        loginframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GridBagLayout lay = new GridBagLayout();
        setLayout(lay);
        loginframe.add(this, BorderLayout.WEST);
        loginframe.setSize(WIDTH, HEIGHT);
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;
        int x = (width - WIDTH) / 2;
        int y = (height - HEIGHT) / 2;
        loginframe.setLocation(x, y);

        name = new JLabel("请选择明细文件");
        JButton selectBtn = new JButton("请选择文件");

        JLabel outTip = new JLabel("汇总文件：");
        outFileName = new JTextField(20);

        doBtn = new JButton("汇总处理");
        doBtn.setEnabled(false);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.EAST;
        constraints.weightx = 3;
        constraints.weighty = 4;

        add(selectBtn, constraints, 0, 0, 1, 1);
        add(name, constraints, 1, 0, 1, 1);
        add(outTip, constraints, 0, 1, 1, 1);
        add(outFileName, constraints, 1, 1, 1, 1);
        add(doBtn, constraints, 0, 2, 1, 1);


        selectBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooseFile();
            }
        });

        doBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dealExcel();
            }
        });

        loginframe.setResizable(false);
        loginframe.setVisible(true);
    }

    private void dealExcel(){
        String outputFileName = TestExcel.getOutputFileName(inAbsolutePathFileName);

        try {
            TestExcel.deal(inAbsolutePathFileName, outputFileName);
        } catch (Exception e) {
            e.printStackTrace();

            outFileName.setText(e.getMessage());
            return;
        }

        outAbsolutePathFileName = outputFileName;
        outFileName.setText(outAbsolutePathFileName);
    }

    private void chooseFile(){
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("excel", "xls", "xlsx");
        chooser.setFileFilter(filter);

        FileSystemView fsv = FileSystemView.getFileSystemView();  //注意了，桌面路径为默认路径
        //System.out.println(fsv.getHomeDirectory());                //得到桌面路径
        chooser.setCurrentDirectory(fsv.getHomeDirectory());

        int returnVal = chooser.showOpenDialog(this);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            //System.out.println("You chose to open this file: " + chooser.getSelectedFile().getAbsolutePath());
            name.setText(chooser.getSelectedFile().getName());
            inAbsolutePathFileName = chooser.getSelectedFile().getAbsolutePath();
            doBtn.setEnabled(true);
        }
    }

    public static void main(String[] args) {
        new TestJFrame();
    }
}