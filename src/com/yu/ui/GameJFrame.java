package com.yu.ui;
//创建一个游戏界面
//JavaBean类描述界面的属性和行为
//统计步数代码逻辑
//一键通关代码逻辑
//查看最终效果
import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GameJFrame extends JFrame implements KeyListener, ActionListener {
    //JFrame表示窗口
    //规定GameJFrame就是这个游戏的主窗口

    //定义一个二维数组，用于存储图片编号
    int[][] data = new int[4][4];
    //定义一个变量，用于记录空白图片的坐标
    int x = 0;
    int y = 0;

    String path = "puzzlegame\\image\\animal\\animal8\\";

    //定义一个二维数组，用于存储通关后的图片编号
    int[][] win = {
        {1,2,3,4},
        {5,6,7,8},
        {9,10,11,12},
        {13,14,15,0}
    };

    //创建菜单条目
    JMenuItem exitItem = new JMenuItem("退出游戏");
    JMenuItem restartItem = new JMenuItem("重新开始");
    JMenuItem reLoginItem = new JMenuItem("重新登录");

    JMenuItem accountItem = new JMenuItem("公众号");

    //定义一个变量，用于记录步数
    int step = 0;

    public GameJFrame() {
        //初始化窗口
        initJFrame();

        //初始化菜单
        initJMenuBar();

        //初始化数据（打乱）
        initData();

        //初始化图片（根据打乱后的数据去加载图片）
        initImage();

        //设置窗口的可见性
        this.setVisible(true);
    }

    //初始化数据（打乱）
    private void initData() {
        //1.定义一个一维数组，用于存储图片编号
        int[] imageNumber = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};

        //2.打乱数组中的元素
        Random r = new Random();
        for (int i = 0; i < imageNumber.length; i++) {
            //获得随机索引
            int index = r.nextInt(imageNumber.length);
            //交换元素
            int temp = imageNumber[i];
            imageNumber[i] = imageNumber[index];
            imageNumber[index] = temp;
        }

        //给二维数组添加数据
        for (int i = 0; i < imageNumber.length; i++) {
            if (imageNumber[i] == 0) {
                //将0设置为空白图片的坐标
                x = i / 4;
                y = i % 4;
            }
            data[i / 4][i % 4] = imageNumber[i];
        }

    }

    //初始化图片（根据打乱后的数据去加载图片）
    private void initImage() {
        //清空窗口中的图片
        this.getContentPane().removeAll();

        //判断是否通关
        if (victory()) {
            //如果通关，就弹出一张win的图片
            JLabel winJLabel = new JLabel(new ImageIcon("puzzlegame\\image\\win.png"));
            winJLabel.setBounds(203,283,197,73);
            this.getContentPane().add(winJLabel);//将win的图片添加到窗口中
        }

        //添加步数标签
        JLabel stepJLabel = new JLabel("步数：" + step);//创建一个JLabel对象(管理容器)
        stepJLabel.setBounds(50,30,100,20);//设置步数标签的位置
        this.getContentPane().add(stepJLabel);//将步数标签添加到窗口中

        //添加4行4列的图片
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int number = data[i][j];//获取当前图片编号
                //创建一个JLabel对象(管理容器)
                JLabel jLabel = new JLabel(new ImageIcon(path+ number +".jpg"));
                //指定图片位置
                jLabel.setBounds(105 * j + 83,105 * i + 134,105,105);
                //给图片添加边框
                jLabel.setBorder(new BevelBorder(BevelBorder.RAISED));
                //管理容器添加到窗口中
                this.getContentPane().add(jLabel);
            }
        }

        //添加背景图片
        JLabel background = new JLabel(new ImageIcon("puzzlegame\\image\\background.png"));
        background.setBounds(40,40,508,560);
        this.getContentPane().add(background);

        //刷新窗口
        this.getContentPane().repaint();

    }


    //初始化窗口
    private void initJFrame() {
        //设置窗口的大小
        this.setSize(603,680);
        //设置窗口的标题
        this.setTitle("拼图1.0");
        //设置界面置顶
        this.setAlwaysOnTop(true);
        //设置界面居中
        this.setLocationRelativeTo(null);
        //设置关闭模式
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//0123都可以
        //取消默认的居中位置，设置布局为null
        this.setLayout(null);
        //添加键盘监听
        this.addKeyListener(this);

    }

    //初始化菜单
    private void initJMenuBar() {
        //初始化菜单
        //创建菜单栏
        JMenuBar jMenuBar = new JMenuBar();

        //创建功能和关于我们菜单
        JMenu functionJMenu = new JMenu("功能");
        JMenu aboutJMenu = new JMenu("关于我们");



        //将菜单条目添加到功能菜单中
        functionJMenu.add(exitItem);
        functionJMenu.add(restartItem);
        functionJMenu.add(reLoginItem);

        aboutJMenu.add(accountItem);

        //添加事件监听
        exitItem.addActionListener((ActionListener) this);
        restartItem.addActionListener((ActionListener) this);
        reLoginItem.addActionListener((ActionListener) this);
        accountItem.addActionListener((ActionListener) this);


        //将关于我们和设置菜单添加到功能菜单中
        jMenuBar.add(functionJMenu);
        jMenuBar.add(aboutJMenu);

        //将菜单栏添加到窗口中
        this.setJMenuBar(jMenuBar);
    }

    //键盘输入事件
    @Override
    public void keyTyped(KeyEvent e) {

    }

    //键盘按下事件
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();//获取用户按下的键的键码
        if (code == 65) {
            //A键查看通关后的图片

            //把界面所有图片删除
            this.getContentPane().removeAll();
            //加载一张完整的图片
            JLabel all = new JLabel(new ImageIcon(path + "all.jpg"));
            all.setBounds(83,134,420,420);
            //将图片添加到界面中
            this.getContentPane().add(all);
            //加载背景图片
            JLabel background = new JLabel(new ImageIcon("puzzlegame\\image\\background.png"));
            background.setBounds(40,40,508,560);
            //将图片添加到界面中
            this.getContentPane().add(background);
            //刷新界面
            this.getContentPane().repaint();
        }


    }



    //键盘松开事件
    @Override
    public void keyReleased(KeyEvent e) {

        //判断是否通关,如果通关,就直接返回
        if (victory()) {
            //结束游戏
            return;
        }


        //获取用户按下的键,对上下左右键进行判断
        //左：37 右：39 上：38 下：40
        int key = e.getKeyCode();
        //System.out.println(key);              看键码
        if (key == 39) {
            //左键
            if (y == 3) {
                return;
            }
            //交换当前图片和左方图片
            data[x][y] = data[x][y + 1];
            data[x][y + 1] = 0;
            //更新当前图片的坐标
            y++;
            //更新步数
            step++;
            //刷新图片
            initImage();
        } else if (key == 37) {
            //右键
            if (y == 0) {
                return;
            }
            //交换当前图片和右图片
            data[x][y] = data[x][y - 1];
            data[x][y - 1] = 0;
            //更新当前图片的坐标
            y--;
            //更新步数
            step++;
            //刷新图片
            initImage();
        } else if (key == 40) {
            //上键
            //交换当前图片和上方图片
            if (x == 3) {
                return;
            }
            data[x][y] = data[x + 1][y];
            data[x + 1][y] = 0;
            //更新当前图片的坐标
            x++;
            //更新步数
            step++;
            //刷新图片
            initImage();
        } else if (key == 38) {
            //下键
            if (x == 0) {
                return;
            }
            //交换当前图片和下方图片
            data[x][y] = data[x - 1][y];
            data[x - 1][y] = 0;
            //更新当前图片的坐标
            x--;
            //更新步数
            step++;
            //刷新图片
            initImage();
        }else if (key == 65) {
            //A键松开
            initImage();
        }else if (key == 10) {
            //Enter键
            data = new int[][]{
                    {1,2,3,4},
                    {5,6,7,8},
                    {9,10,11,12},
                    {13,14,15,0}
            };
            //刷新图片
            initImage();
        }
    }

    //判断是否通关
    public boolean victory() {
        //判断是否通关
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (data[i][j] != win[i][j]) {
                    //如果有一个图片编号不一致，就返回false
                    return false;
                }
            }
        }
        return true;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        //获取点击的菜单条目
        Object obj = e.getSource();
        //判断点击的是哪个菜单条目
        if (obj == restartItem) {
            //重新开始游戏

            //计步器重置
            step = 0;
            //重新打乱数据
            initData();
            //重新初始化图片
            initImage();
        }else if (obj == reLoginItem) {
            //重新登录

            //关闭当前窗口
            this.setVisible(false);
            //打开登录窗口
            new LoginJFrame();
        }else if (obj == exitItem) {
            //退出游戏
            //直接退出程序
            System.exit(0);
        }else if (obj == accountItem) {
            //关于我们
            //创建一个弹窗对象
            JDialog jDialog = new JDialog();
            JLabel jLabel = new JLabel(new ImageIcon("puzzlegame\\image\\about.png"));
            jLabel.setBounds(0,0,258,258);//设置图片的大小
            //将图片添加到弹窗中
            jDialog.getContentPane().add(jLabel);
            //设置弹窗的大小
            jDialog.setSize(344,344);
            //设置弹窗的 alwaysOnTop 为 true，让弹窗置顶
            jDialog.setAlwaysOnTop(true);
           //让弹窗居中
           jDialog.setLocationRelativeTo(null);
           //弹窗不关闭则无法操作其他窗口
           jDialog.setModal(true);
           //设置弹窗的 visible 为 true，显示弹窗
           jDialog.setVisible(true);

        }
    }
}
