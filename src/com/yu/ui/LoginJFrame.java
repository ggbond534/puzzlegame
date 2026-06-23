//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.yu.ui;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.*;

public class LoginJFrame extends JFrame implements MouseListener {

    //定义一个ArrayList，用于存储所有用户
    static ArrayList<User> allUsers = new ArrayList<>();
    static {
        allUsers.add(new User("zhangsan","123"));
        allUsers.add(new User("lisi","1234"));
    }

    //登录按钮
    //注册按钮
    JButton login = new JButton();
    JButton register = new JButton();

    //用户名输入框
    JTextField username = new JTextField();
    //JTextField password = new JTextField();
    //密码输入框
    JPasswordField password = new JPasswordField();
    //验证码输入框
    JTextField code = new JTextField();

    //正确的验证码
    JLabel rightCode = new JLabel();

    public LoginJFrame() {

        //初始化登录窗口
        initLogin();

        //初始化登录窗口的图片
        initLoginImage();

        //设置窗口的 visible 为 true，显示窗口
        this.setVisible(true);
    }

    //初始化登录窗口
    private void initLogin() {
        //设置窗口的大小
        this.setSize(488, 430);
        //设置窗口的标题
        this.setTitle("拼图1.0登录");
        //设置窗口的 defaultCloseOperation 为 3，点击关闭按钮时，关闭窗口
        this.setDefaultCloseOperation(3);
        //让窗口居中
        this.setLocationRelativeTo((Component)null);
        //设置窗口的 alwaysOnTop 为 true，让窗口置顶
        this.setAlwaysOnTop(true);
        this.setLayout(null);//取消内部默认布局

    }

    //初始化登录窗口的图片
    private void initLoginImage() {
        //1.添加用户名文字
        JLabel usernameText = new JLabel(new ImageIcon("puzzlegame\\image\\login\\用户名.png"));
        usernameText.setBounds(116, 135, 47, 17);
        this.getContentPane().add(usernameText);

        //2.添加用户名输入框
        username.setBounds(195, 134, 200, 30);
        this.getContentPane().add(username);

        //3.添加密码文字
        JLabel passwordText = new JLabel(new ImageIcon("puzzlegame\\image\\login\\密码.png"));
        passwordText.setBounds(130, 195, 32, 16);
        this.getContentPane().add(passwordText);

        //4.添加密码输入框
        password.setBounds(195, 195, 200, 30);
        this.getContentPane().add(password);

        //验证码提示
        JLabel codeText = new JLabel(new ImageIcon("puzzlegame\\image\\login\\验证码.png"));
        codeText.setBounds(133, 256, 50, 30);
        this.getContentPane().add(codeText);

        //验证码的输入框
        code.setBounds(195, 256, 100, 30);
        this.getContentPane().add(code);


        String codeStr = CodeUtil.getCode();
        //设置内容
        rightCode.setText(codeStr);
        //绑定鼠标事件
        rightCode.addMouseListener(this);
        //位置和宽高
        rightCode.setBounds(300, 256, 50, 30);
        //添加到界面
        this.getContentPane().add(rightCode);

        //5.添加登录按钮
        login.setBounds(123, 310, 128, 47);
        login.setIcon(new ImageIcon("puzzlegame\\image\\login\\登录按钮.png"));
        //去除按钮的边框
        login.setBorderPainted(false);
        //去除按钮的背景
        login.setContentAreaFilled(false);
        //给登录按钮绑定鼠标事件
        login.addMouseListener(this);
        this.getContentPane().add(login);

        //6.添加注册按钮
        register.setBounds(256, 310, 128, 47);
        register.setIcon(new ImageIcon("puzzlegame\\image\\login\\注册按钮.png"));
        //去除按钮的边框
        register.setBorderPainted(false);
        //去除按钮的背景
        register.setContentAreaFilled(false);
        //给注册按钮绑定鼠标事件
        register.addMouseListener(this);
        this.getContentPane().add(register);


        //加载登录窗口的图片
        JLabel background = new JLabel(new ImageIcon("puzzlegame\\image\\login\\background.png"));
        background.setBounds(0,0,470,390);
        //将图片添加到界面中
        this.getContentPane().add(background);
    }

    //点击鼠标事件
    @Override
    public void mouseClicked(MouseEvent e) {
        //判断点击的是哪个组件
        if (e.getSource() == login) {
            System.out.println("登录按钮被点击了");
            //获取文本框的内容
            String usernameInput = username.getText();
            String passwordInput = password.getText();
            //获取验证码输入框的内容
            String codeInput = code.getText();

            //创建一个User对象
            User user = new User(usernameInput, passwordInput);
            System.out.println("用户名：" + user.getUsername());
            System.out.println("密码：" + user.getPassword());

            if (codeInput.length() == 0) {
                showJDialog("验证码不能为空");
            } else if (usernameInput.length() == 0 || passwordInput.length() == 0) {
                //校验用户名或密码是否为空
                System.out.println("用户名或密码不能为空");

                //显示对话框
                showJDialog("用户名或密码不能为空");

            } else if (!codeInput.equals(rightCode.getText())) {
                //校验验证码是否正确
                //显示对话框
                showJDialog("验证码错误");
            } else if (contains(user)) {
                //校验用户名是否存在
                System.out.println("用户名和密码正确可以开始玩游戏了");
                //关闭登录窗口
                this.setVisible(false);
                //打开游戏窗口
                new GameJFrame();
            } else {
                System.out.println("用户名或密码错误");
                showJDialog("用户名或密码错误");
            }
        }else if (e.getSource() == register) {
            System.out.println("点击了注册按钮");
        } else if (e.getSource() == rightCode) {
            System.out.println("更换验证码");
            //获取一个新的验证码
            String code = CodeUtil.getCode();
            rightCode.setText(code);

        }
    }

    private void showJDialog(String content) {
        //创建一个JDialog对象
        JDialog jDialog = new JDialog();
        //设置对话框的大小
        jDialog.setSize(200, 150);
        //让弹框置顶
        jDialog.setAlwaysOnTop(true);
        //让弹框居中
        jDialog.setLocationRelativeTo(null);
        //弹框不关闭永远无法操作下面的界面
        jDialog.setModal(true);

        //添加到对话框中
        JLabel label = new JLabel(content);
        label.setBounds(0, 0, 200, 150);
        jDialog.add(label);

        //设置对话框的可见性
        jDialog.setVisible(true);
       }

       //鼠标按下不松开事件
    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() == login) {
            login.setIcon(new ImageIcon("puzzlegame\\image\\login\\登录按下.png"));
        } else if (e.getSource() == register) {
            register.setIcon(new ImageIcon("puzzlegame\\image\\login\\注册按下.png"));
        }
    }

    //鼠标松开事件
    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getSource() == login) {
            login.setIcon(new ImageIcon("puzzlegame\\image\\login\\登录按钮.png"));
        } else if (e.getSource() == register) {
            register.setIcon(new ImageIcon("puzzlegame\\image\\login\\注册按钮.png"));
        }
    }

    //鼠标进入事件
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    //鼠标退出事件
    @Override
    public void mouseExited(MouseEvent e) {

    }

    //判断用户在集合中是否存在
    public boolean contains(User userInput){
        for (int i = 0; i < allUsers.size(); i++) {
            User rightUser = allUsers.get(i);
            if(userInput.getUsername().equals(rightUser.getUsername()) && userInput.getPassword().equals(rightUser.getPassword())){
                //有相同的代表存在，返回true，后面的不需要再比了
                return true;
            }
        }
        //循环结束之后还没有找到就表示不存在
        return false;
    }
}
