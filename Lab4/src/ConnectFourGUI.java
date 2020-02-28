import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class ConnectFourGUI extends JFrame implements ActionListener
{
    private static final int SIZE = 6;
    private JFrame frame;
    private JPanel messagePanel;
    private JLabel message;
    private JPanel board;
    private JPanel buttonPanel;
    private final JLabel[][] label_board = new JLabel[SIZE][SIZE];
    private final JButton[] bdButton = new JButton[SIZE];
    private final JMenuBar mBar = new JMenuBar();
    
    // each button click's label and col for undo
    private JLabel label;
    private int col;
    private JButton button;
    
    final Border thickBorder = new LineBorder(Color.BLACK, 2);
    final Border redBorder = new LineBorder(Color.RED, 2);
    final Border blueBorder = new LineBorder(Color.BLUE, 2);
    
    // create a connect four
    ConnectFour connectFour = new ConnectFour(); 
    
    ImageIcon player1 = new ImageIcon("src/main/java/red.png");
    Image image= player1.getImage().getScaledInstance(85, 85, Image.SCALE_SMOOTH);
    ImageIcon player1_resize = new ImageIcon(image);
    
    ImageIcon player2 = new ImageIcon("src/main/java/blue.png");
    Image image2 = player2.getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH);
    ImageIcon player2_resize = new ImageIcon(image2);
    
    ImageIcon arrow = new ImageIcon("src/main/java/arrowdownred.png");
    Image arrow_image = arrow.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
    ImageIcon arrow_resize = new ImageIcon(arrow_image);
   
    // Menu Variable
    JMenu options = new JMenu("Options");
    JMenu load = new JMenu("Load a Game");
    JMenu save = new JMenu("Save a Game");
    
    JMenuItem restart = new JMenuItem("Start a New Game");
    JMenuItem undo = new JMenuItem("Undo a Move");
    JMenuItem load_browse = new JMenuItem("Choose a File");
    JMenuItem load_from_path = new JMenuItem("Enter a Path");
    
    JMenuItem save_browse = new JMenuItem("Choose a File");
    JMenuItem save_to_path = new JMenuItem("save a Game");
    
    // for loading from a path given a path name
    JFrame path_frame = new JFrame("Enter a Path for Loading");
    JPanel path_panel = new JPanel();
    JLabel path_label = new JLabel("Enter the Path");
    JTextField path_field = new JTextField(20);
    JButton path_button = new JButton("Confirm");
    
    // for loading from a path given a path name
    JFrame save_frame = new JFrame("Enter a Path for Saving");
    JPanel save_panel = new JPanel();
    JLabel save_label = new JLabel("Enter the Path");
    JTextField save_field = new JTextField(20);
    JButton save_button = new JButton("Confirm");
    
    JPanel SouthPanel = new JPanel();
    
    static int Red = 0;
    static int Blue = 0;
    
    JPanel winPanel = new JPanel(new BorderLayout());
    JLabel winRecord = new JLabel();
    
    
    public ConnectFourGUI()
    {
        // set up the Frame
        frame = new JFrame("hsuth - ConnectFour");
        frame.setVisible(true);
        frame.setSize(600, 600);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // set the back gorund to green
        //this.getContentPane().setBackground(Color.GREEN);
        
        initializeBoard();
        ClickButton();
        addMenus();
        setSouthPanel();


    }
    
    public void initializeBoard()
    {
        winRecord.setText("Player Red wins: " + Red + " times \n Player Blue wins: " + Blue + " times");
        
        // set a message to tell whose turn it is
        messagePanel = new JPanel();
        message = new JLabel(playerColor(connectFour.turn()) + " Player " + "Turn.");
        //message.setFont(message.getFont().deriveFont(18.0f));
        messagePanel.add(message);
        frame.add(messagePanel,BorderLayout.SOUTH);
        
        // create a panel for the button array
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1,SIZE));
        buttonPanel.setBorder(thickBorder); 
        for (int i = 0; i < SIZE; i++)
        {
            bdButton[i] = new JButton(arrow_resize);
            bdButton[i].setVisible(true);
            bdButton[i].setBorder(thickBorder);
            buttonPanel.add(bdButton[i]);
        }
        buttonPanel.setVisible(true);
        frame.add(buttonPanel, BorderLayout.NORTH);
        
        // set a Panel for the board
        board = new JPanel(new GridLayout(SIZE, SIZE));
        for (int i = 0; i < SIZE; i++)
        {
            for (int j = 0; j < SIZE; j++)
            {
                label_board[i][j] = new JLabel(" ", JLabel.CENTER);
                label_board[i][j].setVisible(true);
                label_board[i][j].setBorder(thickBorder);
                //label_board[i][j].setOpaque(true);
                board.add(label_board[i][j]);
            }
        }
        board.setVisible(true);
        frame.add(board, BorderLayout.CENTER);  
    }
    
    public void ClickButton() {
        for (int i = 0; i < SIZE; i++)
        {          
            bdButton[i].addActionListener(this);
            bdButton[i].setActionCommand(" " + i);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        undo.setEnabled(true);
        if (connectFour.gameStatus() != -1) {
            String s = e.getActionCommand();
            if (s.length() == 2) {

                Scanner input = new Scanner(s);
                String colString = input.next();
                col = Integer.parseInt(colString);
                
                int turn = connectFour.turn();
                ImageIcon player = player1_resize;
                if (turn == ConnectFour.PLAYER2)
                {
                    player = player2_resize;
                }
                connectFour.makeMove(col);

                button = bdButton[col];
                label = label_board[connectFour.rowIndex(col)][col];
                message.setText(playerColor(connectFour.turn()) + " player " + "turn");

                label.setIcon(player);
                label.setVisible(true);
                
                checkForStatus();
            }
        }   
    }
    
    private void checkForStatus()
    {
        if (connectFour.rowIndex(col) == 0) { button.setEnabled(false); }
                
        if (connectFour.gameStatus() == -1) { message.setText("Game Over - Tie"); }

        if (connectFour.gameStatus() == ConnectFour.PLAYER1 || connectFour.gameStatus() == ConnectFour.PLAYER2)
        {
           if (connectFour.gameStatus() == ConnectFour.PLAYER1) { Red += 1; }
           else if (connectFour.gameStatus() == ConnectFour.PLAYER2 ) { Blue += 1; }
           message.setText(playerColor(connectFour.gameStatus()) + " Player" + " Wins");
           winRecord.setText("Player Red wins: " + Red + " times \n Player Blue wins: " + Blue + " times");
           undo.setEnabled(false);
           disableAllButtons();
        }
    }
    
    private String playerColor(int i)
    {
        if (Integer.compare(i, ConnectFour.PLAYER1) == 1) { return "Blue"; }
        else  { return "Red"; }
    }
    
    private void addMenus()
    {
        // set the menu bar to the frame
        frame.setJMenuBar(mBar);
        mBar.add(options);
         
        options.add(restart);
        options.addSeparator();
        options.add(undo);
        options.addSeparator();
        options.add(load);
        options.addSeparator();
        options.add(save);
        
        // load subMenu
        load.add(load_browse);
        load.addSeparator();
        load.add(load_from_path);
        
        // save subMenu
        save.add(save_browse);
        save.addSeparator();
        save.add(save_to_path);
           
        restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearLabelBoard();
            }
        });  
        
        undo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UndoAMove();
                undo.setEnabled(false);
            }
        });
        
        load_browse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser(); 
                fc.setDialogTitle("Please Select a Text File to load to your board");
                fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
                if (fc.showOpenDialog(ConnectFourGUI.this) == JFileChooser.APPROVE_OPTION )
                {
                    File file = fc.getSelectedFile();
                    connectFour.loadBoard(file.getAbsolutePath());
                    setBoard();
                    checkForStatus();
                }
                else { message.setText("Please only select a text File"); }
            }
        });
        
        load_from_path.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                path_frame.setSize(300,150);
                path_frame.setResizable(false);
                path_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                path_frame.add(path_panel);
                
                path_label.setBounds(10, 20, 80, 25);
                path_panel.add(path_label, BorderLayout.NORTH);
                
                path_field.setBounds(100, 50, 165, 50);
                path_panel.add(path_field, BorderLayout.EAST);
                
                path_panel.add(path_button,BorderLayout.SOUTH);
                
                path_frame.setVisible(true);
            }
        });
        
        path_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                path_frame.setVisible(false);
                String pathName = path_field.getText();
                connectFour.loadBoard(pathName);
                setBoard();
                checkForStatus();
            }
        });
                
        save_browse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser(); 
                fc.setDialogTitle("Please Select a Text File to save to your board");
                fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
                if (fc.showOpenDialog(ConnectFourGUI.this) == JFileChooser.APPROVE_OPTION )
                {
                    File file = fc.getSelectedFile();
                    connectFour.saveBoard(file.getAbsolutePath());
                }
            }
        });
        
        save_to_path.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                save_frame.setSize(300,150);
                save_frame.setResizable(false);
                save_frame.setDefaultCloseOperation(save_frame.DISPOSE_ON_CLOSE);
                save_frame.add(save_panel);
                
                save_label.setBounds(10, 20, 80, 25);
                save_panel.add(save_label, BorderLayout.NORTH);
                
                save_field.setBounds(100, 50, 165, 50);
                save_panel.add(save_field, BorderLayout.EAST);
                
                save_panel.add(save_button,BorderLayout.SOUTH);
                
                save_frame.setVisible(true);
                
            }
        });
        
        save_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                save_frame.setVisible(false);
                String pathName = save_field.getText();
                connectFour.saveBoard(pathName);
            }
        }); 
    }
    
    private void setBoard()
    {
        for (int row = 0; row < SIZE; row++)
        {
            for (int col = 0; col < SIZE; col ++)
            {
                switch (connectFour.board[row][col]) {
                    case ConnectFour.PLAYER1:
                        label_board[row][col].setIcon(player1_resize);
                        break;
                    case ConnectFour.PLAYER2:
                        label_board[row][col].setIcon(player2_resize);
                        break;
                    default:
                        label_board[row][col].setIcon(null);
                        break;
                }
            }
        }
    }
    
    private void clearLabelBoard() {
        new ConnectFourGUI();
        frame.setVisible(false);
        frame.dispose();
    }
    
    private void UndoAMove() {
        label.setIcon(null);
        connectFour.UndoAnIndex(col);
    }
    
    private void disableAllButtons()
    {
        for (int i = 0; i < SIZE; i++)
        {
            for (int j = 0; j < SIZE; j++)
            {
                bdButton[i].setEnabled(false);
            }
        }
    }
    
    private void setSouthPanel()
    {
        winPanel.add(winRecord);
        SouthPanel.add(messagePanel,BorderLayout.NORTH);
        SouthPanel.add(winPanel,BorderLayout.SOUTH);
        messagePanel.setBorder(redBorder);
        winPanel.setBorder(blueBorder);
        frame.add(SouthPanel, BorderLayout.SOUTH);
    }
    
    public static void main(String args[])
    {
	ConnectFourGUI connectFourGame = new ConnectFourGUI();
    }
    

}
