package game;

import java.util.Random;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class GameJframe extends JFrame implements KeyListener  {
    //陣列長度
    int pictureLength = 3;
    //創建管理圖片位置的數組
    int[][] data = new int [pictureLength][pictureLength];
    //紀錄空白方塊的位置 
    int blankX = 0;
    int blankY = 0;
    //邊界條件
    int borderRight = pictureLength;
    int borderLeft = 0;
    int borderTop = 3;
    int borderBottom = 0;

    // 圖片路徑
    String filepath = "E:\\JavaProject\\Demo1\\src\\game\\";
    int[][] wincond = {
        {0,1,2},
        {3,4,5},
        {6,7,8}
    } ;
    public GameJframe(){
        //初始化畫面
        initJFrame();
        //初始介面
        initJMenuBar();
        //圖片順序打亂
        initdata();
        //生成圖片
        initImage();

        //顯示畫面
        this.setVisible(true);

    }

    private boolean victory(){
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (data[i][j] != wincond[i][j]) {
                    return false;
                }
            }
        }
        return true;
    };
    private void initdata() {
        int [] tempArray = {0,1,2,3,4,5,6,7,8};
        Random r = new Random();
        for (int i = 0; i < tempArray.length; i++) {
            int index = r.nextInt(tempArray.length);
            int temp = tempArray[i];
            tempArray[i] = tempArray[index];
            tempArray[index] = temp; 
        }
        for (int i = 0; i < tempArray.length; i++) {
            if(tempArray[i] == 0){
                blankX = i / pictureLength;
                blankY = i % pictureLength;
            }
            else{
                data[i / pictureLength][i % pictureLength] = tempArray[i];
            }
            
        }

    }
    //初始化圖片
    private void initImage() {
        // 重置所有圖片
        this.getContentPane().removeAll();
        if (victory()){
            JLabel jLabeVictory = new JLabel(new ImageIcon(filepath+"win.png"));
            jLabeVictory.setBounds(300,300,500,500);
            //畫面添加jlabel
            this.getContentPane().add(jLabeVictory);
        }
        //標題圖片
        ImageIcon title = new ImageIcon(filepath+"title.jpg");
        JLabel jLabeltitle = new JLabel(title);
        //設定圖片位置
        jLabeltitle .setBounds(200,0,400,100);
        //畫面添加jlabel
        this.getContentPane().add(jLabeltitle);
        // int num = 0 ;
        for (int i = 0; i < pictureLength; i++) {
            for (int j = 0; j < pictureLength; j++) {
                //創建一個圖片ImageIcon的的對象
                int num = data[i][j];
                ImageIcon icon = new ImageIcon(filepath+num+".jpg");
                JLabel jLabel = new JLabel(icon);
                //設定圖片位置
                jLabel.setBounds(j*264,(i*185)+100,264,185);
                //畫面添加jlabel
                this.getContentPane().add(jLabel);
                // System.out.print("check:" + num);
                // num ++ ;
            }
        }
        //背景圖片
        ImageIcon background = new ImageIcon(filepath+"back.jpg");
        JLabel jLabel = new JLabel(background);
        //設定圖片位置
        jLabel.setBounds(0,0,800,700);
        //畫面添加jlabel
        this.getContentPane().add(jLabel);
        //refresh
        this.getContentPane().repaint();
    };
    // 初始化menu
    private void initJMenuBar() {
        //創建jMenuBar
        JMenuBar jMenuBar = new JMenuBar();

        //創建JMenu
        JMenu functionJMenu = new JMenu("功能");
        // JMenu cheatJMenu = new JMenu("作弊");

        //創建JMenuItem
        JMenuItem replayItem = new JMenuItem("重新遊戲");
        JMenuItem reLoginItem = new JMenuItem("重新登錄");
        JMenuItem closeItem = new JMenuItem("關閉遊戲");
        // JMenuItem accountItem = new JMenuItem("作弊");

        //JMenu添加選項
        functionJMenu.add(replayItem);
        functionJMenu.add(reLoginItem);
        functionJMenu.add(closeItem);
        // functionJMenu.add(accountItem);
        // cheatJMenu.add(accountItem);

        //JMenuBar添加JMenu
        jMenuBar.add(functionJMenu);
        // jMenuBar.add(cheatJMenu);

        //畫面添加JMenuBar
        this.setJMenuBar(jMenuBar);

    };
    // 初始化介面
    private void initJFrame() {
        //設定介面大小
        this.setSize(822,650);
        //標題
        this.setTitle("遊戲主畫面");
        //設定畫面置頂
        this.setAlwaysOnTop(true);
        //設定畫面置中
        this.setLocationRelativeTo(null);
        //設定關閉畫面會關掉程式
        this.setDefaultCloseOperation(3);

        // 取消 放圖片區域的居中放置 取消圖片label才能按照位置放
        this.setLayout(null);
        // 此界面增加事件
        this.addKeyListener(this);
    }
    // KeyListener要求要復寫的內容
    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        // alt鍵
        int code = e.getKeyCode();
        if (code == 18){
            this.getContentPane().removeAll();
            ImageIcon title = new ImageIcon(filepath+"title.jpg");
            JLabel jLabeltitle = new JLabel(title);
            //設定圖片位置
            jLabeltitle .setBounds(200,0,400,100);
            //畫面添加jlabel
            this.getContentPane().add(jLabeltitle);
            ImageIcon image = new ImageIcon(filepath+"cat.jpg");
            JLabel origin = new JLabel(image);
            origin.setBounds(0,100,792,555);
            this.getContentPane().add(origin);

            ImageIcon background = new ImageIcon(filepath+"back.jpg");
            JLabel jLabel = new JLabel(background);
                    //設定圖片位置
            jLabel.setBounds(0,0,800,700);
                    //畫面添加jlabel
            this.getContentPane().add(jLabel);
            this.getContentPane().repaint();
        }

        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        // 左:37 ,右:39, 上:38 ,下:40
        int code =  e.getKeyCode();
        // System.out.println(code);
        if (code == 37){
            // System.out.println("向左");
            if(blankY == borderTop){
                return;
            }
            data[blankX][blankY] = data[blankX][blankY + 1];
            data[blankX][blankY + 1] = 0 ;
            blankY++;
            initImage();
        }
        else if (code == 38){
            // System.out.println("向上");
            if(blankX == borderRight){
                return;
            }
            data[blankX][blankY] = data[blankX + 1][blankY];
            data[blankX + 1][blankY] = 0 ;
            blankX++;
            initImage();
        }

        else if(code == 39){
            // System.out.println("向右")
            if(blankY == borderBottom){
                return;
            }
            data[blankX][blankY] = data[blankX][blankY - 1];
            data[blankX][blankY - 1] = 0 ;
            blankY--;
            initImage();
        }
        else if(code == 40){
            // System.out.println("向下")
            if(blankX == borderLeft){
                return;
            }
            data[blankX][blankY] = data[blankX - 1][blankY];
            data[blankX - 1][blankY] = 0 ;
            blankX--;
            initImage();
        }
        else if(code == 18){
            initImage();
        }

        
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
    };
}
