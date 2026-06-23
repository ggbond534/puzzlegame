# 拼图游戏 (Puzzle Game 1.0)

一个使用 **Java Swing** 构建的经典 **15 格滑块拼图游戏**。将打乱的拼图块按顺序排列，挑战你的逻辑思维！

---

## 游戏截图

游戏包含 31 套不同主题的拼图图片：

| 主题类别 | 套数 | 路径 |
|---------|------|------|
| 🐱 动物 | 8 套 | `image/animal/animal1` ~ `animal8` |
| 👧 女生 | 13 套 | `image/girl/girl1` ~ `girl13` |
| ⚽ 运动 | 10 套 | `image/sport/sport1` ~ `sport10` |

每套包含 16 张切割好的拼图块（`1.jpg` ~ `16.jpg`）和一张完整预览图（`all.jpg`）。

---

## 游戏操作

| 按键 | 功能 |
|------|------|
| ⬆️⬇️⬅️➡️ **方向键** | 移动拼图块 |
| **A 键**（按住） | 显示完整图片预览 |
| **A 键**（松开） | 恢复拼图状态 |
| **Enter 键** | 一键通关（立即完成拼图） |

### 菜单功能

- **重新开始** — 重新打乱拼图，步数归零
- **重新登录** — 返回登录界面
- **退出游戏** — 关闭程序
- **关于我们** — 查看公众号信息

---

## 项目结构

```
puzzlegame/
├── src/
│   ├── App.java                      # 程序入口
│   └── com/yu/ui/
│       ├── GameJFrame.java           # 游戏主窗口（核心逻辑）
│       ├── LoginJFrame.java          # 登录窗口
│       ├── RegisterJFrame.java       # 注册窗口（待实现）
│       ├── User.java                 # 用户数据模型
│       └── CodeUtil.java             # 验证码生成工具
├── image/                            # 图片资源
│   ├── background.png                # 游戏背景
│   ├── about.png                     # 关于页面图片
│   ├── win.png                       # 通关显示图片
│   ├── animal/                       # 动物主题（8 套）
│   ├── girl/                         # 女生主题（13 套）
│   ├── sport/                        # 运动主题（10 套）
│   ├── login/                        # 登录界面资源
│   └── register/                     # 注册界面资源
└── out/                              # 编译输出
```

---

## 快速开始

### 环境要求

- **JDK 8+**（无外部依赖，仅使用 Java 标准库）

### 方式一：命令行编译运行

```bash
# 进入项目目录
cd puzzlegame

# 编译所有源文件
javac -encoding UTF-8 -d out/production/puzzlegame src/App.java src/com/yu/ui/*.java

# 运行游戏
java -cp out/production/puzzlegame App
```

### 方式二：使用 IntelliJ IDEA

1. 用 IntelliJ IDEA 打开 `puzzlegame` 目录
2. IDEA 会自动识别 `.iml` 模块配置
3. 右键 `src/App.java` → **Run 'App.main()'**

---

## 游戏规则

1. 游戏面板是一个 **4×4 的网格**，包含编号 1~15 的拼图块和一个空白格
2. 目标是将拼图块按数字顺序排列：第一行 `1 2 3 4`，第二行 `5 6 7 8`，第三行 `9 10 11 12`，第四行 `13 14 15` + 空白
3. 使用**方向键**将相邻的拼图块滑入空白格
4. 当所有数字归位后，显示胜利画面 🎉

---

## 技术要点

- **GUI 框架：** Java Swing (`JFrame`)
- **布局方式：** 绝对定位（`setLayout(null)`）
- **事件处理：** `KeyListener`（键盘操作）+ `MouseListener`（登录按钮）+ `ActionListener`（菜单项）
- **图片加载：** `ImageIcon` 加载 JPG/PNG 资源
- **拼图打乱：** Fisher-Yates 洗牌算法
- **用户系统：** 内置测试账号（`zhangsan / 123`、`lisi / 1234`），验证码随机生成

---

## 待完善功能

- [ ] 游戏内切换图片主题（目前硬编码为 `animal8`）
- [ ] 注册窗口功能实现
- [ ] 步数排行榜 / 计时功能
- [ ] 用户数据持久化存储
- [ ] 音效与动画效果

---

## 许可证

本项目仅用于学习与交流目的。
