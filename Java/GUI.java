package Java;




import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.management.ThreadInfo;
import java.util.ArrayList;
import java.util.List;


import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

import static java.lang.Thread.sleep;

public class GUI {




    private static void redraw(Graphics g){
        game.getTurn().getPiece().setTurn(true);
        game.getBoard().redraw(g, boardDrawing.getHeight(), boardDrawing.getWidth());//draws the board to the GUI
        game.getTurn().getPiece().setTurn(false);
    }

    private static void redrawHand(Graphics g, int width, int height){
        game.getTurn().drawHand(g,width,height);//draws hand

    }



    private static Game game;
    private static Board board;

    private static JComponent boardDrawing;
    private static JComponent dieDrawing;
    private static JComponent handDrawing;
    private static JFrame frame;
    private static final int DEFAULT_DRAWING_HEIGHT = 800;
    private static final int DEFAULT_DRAWING_WIDTH = 800;
    private static int movesLeft;
    private static int dieOne;
    private static int dieTwo;


//starts the game of cluedo, this controls the flow of the game
    private static void startGame(){
        try {
            //opens a dialogue message that allows to user to select the number of palyers
            Object[] possibilities = { "3", "4", "5","6"};
            String s = (String)JOptionPane.showInputDialog(
                    frame,
                    "Select the number of Players",
                    "New Cluedo game",
                    JOptionPane.DEFAULT_OPTION,
                    new ImageIcon(ImageIO.read(new File("src/images/cluedo.png"))),
                    possibilities,
                    "1");
            int numOfPlayers = Integer.parseInt(s);


            JTextField PlayerName = new JTextField(20);
            PlayerName.setPreferredSize(new Dimension(50,25));


            JPanel playerInfoPanel = new JPanel();
            playerInfoPanel.setLayout(new GridLayout(2,3));
            //load character images for the dialogue box
            BufferedImage scar = ImageIO.read(new File("src/images/CharacterIcons/miss_scarlett.png"));
            BufferedImage scarSel = ImageIO.read(new File("src/images/CharacterIcons/miss_scarlett_selected.png"));
            BufferedImage scarGray = ImageIO.read(new File("src/images/CharacterIcons/miss_scarlett_grey.png"));

            BufferedImage green = ImageIO.read(new File("src/images/CharacterIcons/mr_Green.png"));
            BufferedImage greenSel = ImageIO.read(new File("src/images/CharacterIcons/mr_Green_sel.png"));
            BufferedImage greenGray = ImageIO.read(new File("src/images/CharacterIcons/green_gray.png"));

            BufferedImage col = ImageIO.read(new File("src/images/CharacterIcons/col_must.png"));
            BufferedImage colSel = ImageIO.read(new File("src/images/CharacterIcons/col_must_sel.png"));
            BufferedImage colGray = ImageIO.read(new File("src/images/CharacterIcons/col_grey.png"));

            BufferedImage pea = ImageIO.read(new File("src/images/CharacterIcons/mrs_peacock.png"));
            BufferedImage peaSel = ImageIO.read(new File("src/images/CharacterIcons/mrs_peacock_sel.png"));
            BufferedImage peaGray = ImageIO.read(new File("src/images/CharacterIcons/pea-grey.png"));

            BufferedImage plum = ImageIO.read(new File("src/images/CharacterIcons/prof_plum.png"));
            BufferedImage plumSel = ImageIO.read(new File("src/images/CharacterIcons/prof_plum_sel.png"));
            BufferedImage plumGray = ImageIO.read(new File("src/images/CharacterIcons/plum_grey.png"));

            BufferedImage white = ImageIO.read(new File("src/images/CharacterIcons/mrs_White.png"));
            BufferedImage whiteSel = ImageIO.read(new File("src/images/CharacterIcons/mrs_White_sel.png"));
            BufferedImage whiteGray = ImageIO.read(new File("src/images/CharacterIcons/white_grey.png"));
            //format the text field and label with spacing
            playerInfoPanel.add(Box.createRigidArea(new Dimension(15, 0)));
            playerInfoPanel.add(new JLabel("Enter your name: "));
            playerInfoPanel.add(Box.createRigidArea(new Dimension(15, 0)));
            playerInfoPanel.add(Box.createRigidArea(new Dimension(15, 0)));
            playerInfoPanel.add(PlayerName);
            playerInfoPanel.add(Box.createRigidArea(new Dimension(15, 0)));

            //create radio buttons for characters and add their icons
            JRadioButton missScarlett = new JRadioButton("Miss Scarlett");
            missScarlett.setVerticalTextPosition(SwingConstants.BOTTOM);
            missScarlett.setHorizontalTextPosition(SwingConstants.CENTER);
            missScarlett.setIcon(new ImageIcon(scar));
            missScarlett.setSelectedIcon(new ImageIcon(scarSel));
            missScarlett.setDisabledIcon(new ImageIcon(scarGray));

            JRadioButton colMustard = new JRadioButton("Colonel Mustard");
            colMustard.setVerticalTextPosition(SwingConstants.BOTTOM);
            colMustard.setHorizontalTextPosition(SwingConstants.CENTER);
            colMustard.setIcon(new ImageIcon(col));
            colMustard.setSelectedIcon(new ImageIcon(colSel));
            colMustard.setDisabledIcon(new ImageIcon(colGray));

            JRadioButton mrsWhite = new JRadioButton("Mrs White");
            mrsWhite.setVerticalTextPosition(SwingConstants.BOTTOM);
            mrsWhite.setHorizontalTextPosition(SwingConstants.CENTER);
            mrsWhite.setIcon(new ImageIcon(white));
            mrsWhite.setSelectedIcon(new ImageIcon(whiteSel));
            mrsWhite.setDisabledIcon(new ImageIcon(whiteGray));

            JRadioButton mrGreen = new JRadioButton("Mr Green");
            mrGreen.setVerticalTextPosition(SwingConstants.BOTTOM);
            mrGreen.setHorizontalTextPosition(SwingConstants.CENTER);
            mrGreen.setIcon(new ImageIcon(green));
            mrGreen.setSelectedIcon(new ImageIcon(greenSel));
            mrGreen.setDisabledIcon(new ImageIcon(greenGray));

            JRadioButton mrsPeacock = new JRadioButton("Mrs Peacock");
            mrsPeacock.setVerticalTextPosition(SwingConstants.BOTTOM);
            mrsPeacock.setHorizontalTextPosition(SwingConstants.CENTER);
            mrsPeacock.setIcon(new ImageIcon(pea));
            mrsPeacock.setSelectedIcon(new ImageIcon(peaSel));
            mrsPeacock.setDisabledIcon(new ImageIcon(peaGray));

            JRadioButton profPlum = new JRadioButton("Professor Plum");
            profPlum.setVerticalTextPosition(SwingConstants.BOTTOM);
            profPlum.setHorizontalTextPosition(SwingConstants.CENTER);
            profPlum.setIcon(new ImageIcon(plum));
            profPlum.setSelectedIcon(new ImageIcon(plumSel));
            profPlum.setDisabledIcon(new ImageIcon(plumGray));

            //add buttons to button group
            ButtonGroup buttonGroup = new ButtonGroup();
            buttonGroup.add(profPlum);
            buttonGroup.add(missScarlett);
            buttonGroup.add(mrsWhite);
            buttonGroup.add(mrGreen);
            buttonGroup.add(colMustard);
            buttonGroup.add(mrsPeacock);


            //add buttons to the panel
            JPanel charPanel = new JPanel();
            charPanel.setLayout(new GridLayout(2,3));
            charPanel.add(missScarlett);
            charPanel.add(colMustard);
            charPanel.add(mrsWhite);
            charPanel.add(mrGreen);
            charPanel.add(mrsPeacock);
            charPanel.add(profPlum);
            charPanel.setBorder(BorderFactory.createEmptyBorder(25,25,10,10));

            //adds the characters and the player info panel to the second dialogue box
            JPanel DialoguePanel = new JPanel();
            DialoguePanel.setLayout(new BorderLayout());
            DialoguePanel.add(charPanel,BorderLayout.NORTH);
            DialoguePanel.add(playerInfoPanel,BorderLayout.SOUTH);

            ArrayList<String> tokens = new ArrayList<>();
            ArrayList<String> names = new ArrayList<>();



            //gets the player names and their associated tokens
            for(int i = 0; i <numOfPlayers;) {
                int result = JOptionPane.showConfirmDialog(null, DialoguePanel,
                        "PLAYER "+(i+1), JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE);
                if (result == JOptionPane.OK_OPTION) {
                    if (PlayerName.getText().equals("")) {

                        JOptionPane.showMessageDialog(null,"Please enter a name!");
                        continue;
                    }
                    if (names.contains(PlayerName.getText())){
                        JOptionPane.showMessageDialog(null,"Please enter a unique name!");
                        continue;
                    }
                    for(Component j:charPanel.getComponents()){
                        if((j instanceof JRadioButton)&&((JRadioButton) j).isSelected()){
                            tokens.add(((JRadioButton) j).getText());
                            ((JRadioButton) j).setSelected(false);
                            ((JRadioButton) j).getModel().setEnabled(false);
                            names.add(PlayerName.getText());
                            PlayerName.setText("");
                            i++; //i only incremented when valid input is given
                        }
                    }

                }
                if (result == JOptionPane.CLOSED_OPTION) return;
            }

            game = new Game();
            game.initializePlayers(names,tokens);//sets the player names and their tokens
            initialise();
            board = game.getBoard();
            board.initializeBoard();
            game.dealCards();

            new Thread(() -> {//updates the tooltip text depending on mouse position, this gives the user extra information
                while(1>0) {
                    updateToolText((int) MouseInfo.getPointerInfo().getLocation().getY() - (int) boardDrawing.getLocationOnScreen().getY(),
                            (int) MouseInfo.getPointerInfo().getLocation().getX() - (int) boardDrawing.getLocationOnScreen().getX());
                    try {
                        sleep(20);
                    }catch(InterruptedException e){}
                }
            }).start();

            //this loop executes for the duration of the game
            while (!game.isGameWon()) {
                Player currentTurn = game.getTurn();
                dieOne = game.rollDie();
                dieTwo = game.rollDie();
                movesLeft = dieOne + dieTwo;
                frame.setTitle(currentTurn.toString()+"'s turn");

                while (currentTurn == game.getTurn()) {
                    sleep(5);
                }
                redraw();
            }
        }
        catch(Exception e){}
    }
    //moves the player based on the position clicked on their screen
    private static void movePlayer(MouseEvent e){
        int x = e.getX()*24/ boardDrawing.getWidth();
        int y = e.getY()*25/ boardDrawing.getHeight();
        PlayerPiece p = game.getTurn().getPiece();

        List<Square> moves = board.moveToString(p,board.getSquare(y,x));
        if ( moves== null) return; //invalid move
        if(moves.size() -1 <= movesLeft) {
            movesLeft -= board.numOfMoves(p,moves);//update the number of moves the user has left
            for(Square s:moves) {
                board.movePlayer(p, s);
                redraw();
                try {
                    sleep(200);
                } catch (Exception e1){}
            }
        } else {
            JOptionPane.showMessageDialog(null,"Try, moving somewhere closer.","You can not move that far!",JOptionPane.OK_OPTION);
        }
        if(movesLeft == 0){
            game.nextTurn();
            JOptionPane.showMessageDialog(null,"It's " + game.getTurn().getName() + "\'s turn!","Turn over!",JOptionPane.OK_OPTION);
        }

    }

    /**
     * Redraws the entire GUI
     */
    private static void redraw() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                frame.repaint();
            }
        });

    }

    /**
     * Creates the required swing components to support the GUI
     */
    private static void initialise(){

        JPanel actions = new JPanel();
        actions.setLayout(new GridLayout(4,1));
        actions.add(new JPanel());
        actions.setMaximumSize(new Dimension(50,25));
        //add next turn button
        JButton nextTurn = new JButton("Next Turn");
        nextTurn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.nextTurn();
            }
        });
        //add next turn hot key
        nextTurn.setMnemonic(KeyEvent.VK_ENTER);
        nextTurn.setToolTipText("alt+enter");

        //this JComponent displays the board
        boardDrawing = new JComponent() {
            protected void paintComponent(Graphics g) {
                GUI.redraw(g);
            }
        };
        boardDrawing.setPreferredSize(new Dimension(DEFAULT_DRAWING_WIDTH,
                DEFAULT_DRAWING_HEIGHT));

        //when mouse is released player is moved and the board is redrawn
        boardDrawing.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                Thread queryThread = new Thread() {
                    public void run() {
                        movePlayer(e);
                    }
                };
                queryThread.start();
            }
        });
        //draws the die
        dieDrawing = new JComponent() {
            protected void paintComponent(Graphics g) {
                try {
                    BufferedImage image = ImageIO.read(new File("src/images/die"+dieOne+".png"));
                    g.drawImage(image,20,20, dieDrawing.getWidth()/3,dieDrawing.getWidth()/3,null);
                    BufferedImage image2 = ImageIO.read(new File("src/images/die"+dieTwo+".png"));
                    g.drawImage(image2,dieDrawing.getWidth()/3+40,20,dieDrawing.getWidth()/3,dieDrawing.getWidth()/3,null);
                }
                catch(IOException e){}
            }
        };
        dieDrawing.setPreferredSize(new Dimension(DEFAULT_DRAWING_WIDTH/4,
                DEFAULT_DRAWING_HEIGHT/6));
        //draws the player's hand
        handDrawing = new JComponent() {
            protected void paintComponent(Graphics g) {
                redrawHand(g,handDrawing.getWidth(),handDrawing.getHeight());
            }
        };
        handDrawing.setPreferredSize(new Dimension(3*DEFAULT_DRAWING_WIDTH/4,
                DEFAULT_DRAWING_HEIGHT/6));
        // this prevents a bug where the component won't be
        // drawn until it is resized.
        dieDrawing.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
            }
            public void mouseEntered(MouseEvent e){
                dieDrawing.setToolTipText(Integer.toString(dieOne+dieTwo));
            }
        });
        // this prevents a bug where the component won't be
        // drawn until it is resized.
        handDrawing.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {

            }
        });



        //The hud contains the player's hand, the dice and the next turn button
        JPanel hud = new JPanel();
        hud.setLayout(new BorderLayout());
        hud.add(dieDrawing,BorderLayout.WEST);
        hud.add(handDrawing,BorderLayout.CENTER);
        hud.add(nextTurn,BorderLayout.EAST);
        hud.setPreferredSize(new Dimension(DEFAULT_DRAWING_WIDTH,
                DEFAULT_DRAWING_HEIGHT/6));

        //creates a new menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Actions");
        menu.setMnemonic(KeyEvent.VK_O);

        //add options to the menu
        JMenuItem suggest = new JMenuItem("Make Suggestion");
        suggest.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
        JMenuItem accuse = new JMenuItem("Make Accusation");
        accuse.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_DOWN_MASK));
        JMenuItem quit = new JMenuItem("Quit");
        quit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_DOWN_MASK));

        //adds events to the menu items when there are selected
        suggest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                makeSuggestAcc(false);
            }
        });
        accuse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                makeSuggestAcc(true);
            }
        });
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
        });

        menu.add(suggest);
        menu.add(accuse);
        menu.add(quit);
        menuBar.add(menu);

        dieDrawing.setVisible(true);

        Border edge = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        boardDrawing.setBorder(edge);
        boardDrawing.setVisible(true);

        //add all the components to the frame
        frame = new JFrame("Cluedo");
        frame.setSize(1000,1000);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(boardDrawing, BorderLayout.CENTER);
        frame.add(actions, BorderLayout.EAST);
        frame.add(hud, BorderLayout.SOUTH);
        frame.setJMenuBar(menuBar);


        frame.setVisible(true);

    }

    //this method is called when a user makes a suggestion or accusation
    private static void makeSuggestAcc(Boolean accusation) {
        PlayerPiece p = game.getTurn().getPiece();
        if (!accusation && !(p.getLocation() instanceof RoomSquare)) {
            JOptionPane.showConfirmDialog(null,"You must be in a room to make a suggestion!","Hey!",JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE);
            return;
        }

        String killer = "";
        String weapon = "";

        //creates radio buttons for the character options
        JRadioButton missScarlett = new JRadioButton("Miss Scarlett");
        JRadioButton colMustard = new JRadioButton("Colonel Mustard");
        JRadioButton mrsWhite = new JRadioButton("Mrs White");
        JRadioButton mrGreen = new JRadioButton("Mr Green");
        JRadioButton mrsPeacock = new JRadioButton("Mrs Peacock");
        JRadioButton profPlum = new JRadioButton("Professor Plum");
        ButtonGroup killers = new ButtonGroup();
        //adds to button group so only one maybe selected at a time
        killers.add(profPlum);
        killers.add(missScarlett);
        killers.add(mrsWhite);
        killers.add(mrGreen);
        killers.add(colMustard);
        killers.add(mrsPeacock);
        //add to a Jpanel so the buttons can be displayed nicely
        JPanel charPanel = new JPanel();
        charPanel.setLayout(new GridLayout(2, 3));
        charPanel.add(missScarlett);
        charPanel.add(colMustard);
        charPanel.add(mrsWhite);
        charPanel.add(mrGreen);
        charPanel.add(mrsPeacock);
        charPanel.add(profPlum);
        charPanel.setBorder(BorderFactory.createEmptyBorder(25, 25, 10, 10));


        //gets the user option from a dialogue box, exits if cancelled
        int result = JOptionPane.showConfirmDialog(null, charPanel,
                "Who's the killer?", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            for (Component j : charPanel.getComponents()) {
                if ((j instanceof JRadioButton) && ((JRadioButton) j).isSelected()) {
                    killer = ((JRadioButton) j).getText();
                }
            }
        }
        if (result == JOptionPane.CANCEL_OPTION){
            return;
        }
        //creates radio buttons for the weapons
        JRadioButton Candlestick = new JRadioButton("Candlestick");
        JRadioButton Dagger = new JRadioButton("Dagger");
        JRadioButton Lead_Pipe = new JRadioButton("Lead Pipe");
        JRadioButton Revolver = new JRadioButton("Revolver");
        JRadioButton Rope = new JRadioButton("Rope");
        JRadioButton Spanner = new JRadioButton("Spanner");
        ButtonGroup weapons = new ButtonGroup();
       //add the weapon radio buttons to a button group
        weapons.add(Candlestick);
        weapons.add(Dagger);
        weapons.add(Lead_Pipe);
        weapons.add(Revolver);
        weapons.add(Rope);
        weapons.add(Spanner);
        //add the weapon radio buttons to a jpanel
        JPanel weapPanel = new JPanel();
        weapPanel.setLayout(new GridLayout(2, 3));
        weapPanel.add(Candlestick);
        weapPanel.add(Dagger);
        weapPanel.add(Revolver);
        weapPanel.add(Rope);
        weapPanel.add(Spanner);
        weapPanel.add(Lead_Pipe);
        weapPanel.setBorder(BorderFactory.createEmptyBorder(25, 25, 10, 10));

       //gets the user choice
        result = JOptionPane.showConfirmDialog(null, weapPanel,
                "What was the murder weapon?", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            for (Component j : weapPanel.getComponents()) {
                if ((j instanceof JRadioButton) && ((JRadioButton) j).isSelected()) {
                    weapon = ((JRadioButton) j).getText();
                }
            }
        }
        if (result == JOptionPane.CANCEL_OPTION){
            return;
        }
        //if an accusation is being made we also need to ask the use which room
        if(!accusation){
            game.makeSuggestion(game.getTurn(),killer,((RoomSquare) p.getLocation()).getRoom().getRoomName(),weapon);

        }
        else{//creates radio buttons for the room
            JRadioButton Lounge = new JRadioButton("Lounge");
            JRadioButton Study = new JRadioButton("Study");
            JRadioButton Dinning_Room = new JRadioButton("Dining Room");
            JRadioButton Conservatory = new JRadioButton("Conservatory");
            JRadioButton Ball_Room = new JRadioButton("Ball Room");
            JRadioButton Billiard_Room = new JRadioButton("Billiard Room");
            JRadioButton kitchen = new JRadioButton("Kitchen");
            JRadioButton Library = new JRadioButton("Library");
            JRadioButton Hall = new JRadioButton("Hall");
            ButtonGroup rooms = new ButtonGroup();
            //adds the room radio buttons to a button group
            rooms.add(Lounge);
            rooms.add(Study);
            rooms.add(Dinning_Room);
            rooms.add(Conservatory);
            rooms.add(Ball_Room);
            rooms.add(Billiard_Room);
            rooms.add(Hall);
            rooms.add(kitchen);
            rooms.add(Library);
            //adds the buttons to a Jpanel
            JPanel roomPanel = new JPanel();
            roomPanel.setLayout(new GridLayout(3, 3));
            roomPanel.add(Lounge);
            roomPanel.add(Study);
            roomPanel.add(Conservatory);
            roomPanel.add(Ball_Room);
            roomPanel.add(Billiard_Room);
            roomPanel.add(Dinning_Room);
            roomPanel.add(Hall);
            roomPanel.add(kitchen);
            roomPanel.add(Library);
            roomPanel.setBorder(BorderFactory.createEmptyBorder(25, 25, 10, 10));

            //gets the users choice
            String room = "";
            result = JOptionPane.showConfirmDialog(null, roomPanel,
                    "What was the murder weapon?", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                for (Component j : roomPanel.getComponents()) {
                    if ((j instanceof JRadioButton) && ((JRadioButton) j).isSelected()) {
                        room = ((JRadioButton) j).getText();
                    }
                }
            }
            if (result == JOptionPane.CANCEL_OPTION){
                return;
            }
            game.makeAccusation(game.getTurn(),killer,room,weapon);
            if(game.gameIsWon){
                JOptionPane.showConfirmDialog(null,game.getTurn().getName()+" wins!","Congratulations!",JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE);
            }
            else{
                JOptionPane.showConfirmDialog(null,game.getTurn().getName()+" has been excluded!","...!",JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE);
            }

        }

        redraw();
        game.nextTurn();//commences the next turn


    }

    //updates the boards tool text depending on what the mouse is hovering over
    private static void updateToolText(int y, int x){
        if(!boardDrawing.contains(x,y)) return;
        Square s = board.getSquareFromMousePosition(boardDrawing.getHeight(), boardDrawing.getWidth(), y, x);
        if (s instanceof RoomSquare) {
            if (((RoomSquare) s).getPlayer() != null) boardDrawing.setToolTipText(s.getPlayer().getOwner());
            else if (((RoomSquare) s).getWeapon() != null)
                boardDrawing.setToolTipText(((RoomSquare) s).getWeapon().getName());
            else boardDrawing.setToolTipText(((RoomSquare) s).getRoom().getRoomName());
        } else if (s.getPlayer() != null) boardDrawing.setToolTipText(s.getPlayer().getOwner());
        else boardDrawing.setToolTipText(null);
    }


    public static void main(String[] args){

        startGame();
    }






}
