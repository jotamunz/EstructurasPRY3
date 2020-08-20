
package Client.View;

public class MarketDisplay extends javax.swing.JFrame {

    public MarketDisplay() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelBG = new javax.swing.JPanel();
        jLabel_Torpedo = new javax.swing.JLabel();
        jLabel_MultiShot = new javax.swing.JLabel();
        jLabel_Iron3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea_Offer = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jSpinner_Price = new javax.swing.JSpinner();
        jButton_SendOffer = new javax.swing.JButton();
        jButton_SellIron = new javax.swing.JButton();
        jButton_SellTorpedo = new javax.swing.JButton();
        jButton_SellMultiShot = new javax.swing.JButton();
        jSpinner_Iron = new javax.swing.JSpinner();
        jSpinner_Torpedo = new javax.swing.JSpinner();
        jSpinner_MultiShot = new javax.swing.JSpinner();
        jButton_addTorpedo = new javax.swing.JButton();
        jButton_addIron = new javax.swing.JButton();
        jButton_addMultiShot = new javax.swing.JButton();
        jLabel_Trumpedo = new javax.swing.JLabel();
        jButton_SellTrumpedo = new javax.swing.JButton();
        jButton_addTrumpedo = new javax.swing.JButton();
        jSpinner_Trumpedo = new javax.swing.JSpinner();
        jButton_addSpy = new javax.swing.JButton();
        jSpinner_Spy = new javax.swing.JSpinner();
        jButton_SellSpy = new javax.swing.JButton();
        jLabel_Spy = new javax.swing.JLabel();
        jLabel_Bomb = new javax.swing.JLabel();
        jButton_SellBomb = new javax.swing.JButton();
        jSpinner_Bomb = new javax.swing.JSpinner();
        jButton_addBomb = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton_Cancel = new javax.swing.JButton();
        jSpinner_Player = new javax.swing.JSpinner();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanelBG.setBackground(new java.awt.Color(101, 67, 33));

        jLabel_Torpedo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_Torpedo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/Weapons/torpedo.png"))); // NOI18N

        jLabel_MultiShot.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_MultiShot.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/Weapons/multishot.png"))); // NOI18N

        jLabel_Iron3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_Iron3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/Weapons/iron.png"))); // NOI18N

        jTextArea_Offer.setEditable(false);
        jTextArea_Offer.setColumns(20);
        jTextArea_Offer.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jTextArea_Offer.setRows(5);
        jScrollPane1.setViewportView(jTextArea_Offer);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setText("Name your price:");

        jSpinner_Price.setModel(new javax.swing.SpinnerNumberModel(100, 100, null, 1));

        jButton_SendOffer.setText("SEND");

        jButton_SellIron.setText("SELL");

        jButton_SellTorpedo.setText("SELL");

        jButton_SellMultiShot.setText("SELL");

        jSpinner_Iron.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));

        jSpinner_Torpedo.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));

        jSpinner_MultiShot.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));

        jButton_addTorpedo.setText("ADD");

        jButton_addIron.setText("ADD");

        jButton_addMultiShot.setText("ADD");

        jLabel_Trumpedo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_Trumpedo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/Weapons/trumpedo.png"))); // NOI18N

        jButton_SellTrumpedo.setText("SELL");

        jButton_addTrumpedo.setText("ADD");

        jSpinner_Trumpedo.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));

        jButton_addSpy.setText("ADD");

        jSpinner_Spy.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));

        jButton_SellSpy.setText("SELL");

        jLabel_Spy.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_Spy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/Weapons/spy.png"))); // NOI18N

        jLabel_Bomb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_Bomb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/Weapons/bomb.png"))); // NOI18N

        jButton_SellBomb.setText("SELL");

        jSpinner_Bomb.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));

        jButton_addBomb.setText("ADD");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("YOUR OFFER");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setText("Pick a player:");

        jButton_Cancel.setText("CANCEL");

        jSpinner_Player.setModel(new javax.swing.SpinnerNumberModel(1, 1, 4, 1));

        javax.swing.GroupLayout jPanelBGLayout = new javax.swing.GroupLayout(jPanelBG);
        jPanelBG.setLayout(jPanelBGLayout);
        jPanelBGLayout.setHorizontalGroup(
            jPanelBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBGLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanelBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelBGLayout.createSequentialGroup()
                        .addGroup(jPanelBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel_Iron3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton_SellIron, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanelBGLayout.createSequentialGroup()
                                .addComponent(jSpinner_Iron, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton_addIron, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(46, 46, 46)
                        .addGroup(jPanelBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel_Torpedo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton_SellTorpedo, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE))
                            .addGroup(jPanelBGLayout.createSequentialGroup()
                                .addComponent(jSpinner_Torpedo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton_addTorpedo, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(28, 28, 28)
                        .addGroup(jPanelBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel_MultiShot, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton_SellMultiShot, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE))
                            .addGroup(jPanelBGLayout.createSequentialGroup()
                                .addComponent(jSpinner_MultiShot, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton_addMultiShot, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanelBGLayout.createSequentialGroup()
                        .addGroup(jPanelBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelBGLayout.createSequentialGroup()
                                .addComponent(jSpinner_Bomb, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton_addBomb, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel_Bomb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton_SellBomb, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(46, 46, 46)
                        .addGroup(jPanelBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel_Spy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton_SellSpy, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelBGLayout.createSequentialGroup()
                                .addComponent(jSpinner_Spy, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton_addSpy, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(28, 28, 28)
                        .addGroup(jPanelBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel_Trumpedo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton_SellTrumpedo, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelBGLayout.createSequentialGroup()
                                .addComponent(jSpinner_Trumpedo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton_addTrumpedo, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(32, 32, 32)
                .addGroup(jPanelBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanelBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanelBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanelBGLayout.createSequentialGroup()
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jSpinner_Price, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanelBGLayout.createSequentialGroup()
                        .addGroup(jPanelBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton_Cancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton_SendOffer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSpinner_Player))))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanelBGLayout.setVerticalGroup(
            jPanelBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBGLayout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(jPanelBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelBGLayout.createSequentialGroup()
                        .addGroup(jPanelBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel_Torpedo, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_MultiShot, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_Iron3, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton_SellIron)
                            .addComponent(jButton_SellTorpedo)
                            .addComponent(jButton_SellMultiShot))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jSpinner_Iron, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSpinner_Torpedo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSpinner_MultiShot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_addTorpedo, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_addIron, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_addMultiShot, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(52, 52, 52)
                        .addGroup(jPanelBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelBGLayout.createSequentialGroup()
                                .addGroup(jPanelBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel_Spy, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel_Trumpedo, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanelBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButton_SellSpy)
                                    .addComponent(jButton_SellTrumpedo))
                                .addGap(18, 18, 18)
                                .addGroup(jPanelBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jSpinner_Spy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jSpinner_Trumpedo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton_addSpy, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton_addTrumpedo, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanelBGLayout.createSequentialGroup()
                                .addComponent(jLabel_Bomb, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton_SellBomb)
                                .addGap(18, 18, 18)
                                .addGroup(jPanelBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jSpinner_Bomb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton_addBomb, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanelBGLayout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addGroup(jPanelBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanelBGLayout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(jSpinner_Price)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanelBGLayout.createSequentialGroup()
                                .addComponent(jSpinner_Player)
                                .addGap(2, 2, 2)))))
                .addGap(10, 10, 10)
                .addGroup(jPanelBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton_SendOffer, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                    .addComponent(jButton_Cancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelBG, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelBG, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton jButton_Cancel;
    public javax.swing.JButton jButton_SellBomb;
    public javax.swing.JButton jButton_SellIron;
    public javax.swing.JButton jButton_SellMultiShot;
    public javax.swing.JButton jButton_SellSpy;
    public javax.swing.JButton jButton_SellTorpedo;
    public javax.swing.JButton jButton_SellTrumpedo;
    public javax.swing.JButton jButton_SendOffer;
    public javax.swing.JButton jButton_addBomb;
    public javax.swing.JButton jButton_addIron;
    public javax.swing.JButton jButton_addMultiShot;
    public javax.swing.JButton jButton_addSpy;
    public javax.swing.JButton jButton_addTorpedo;
    public javax.swing.JButton jButton_addTrumpedo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    public javax.swing.JLabel jLabel_Bomb;
    public javax.swing.JLabel jLabel_Iron3;
    public javax.swing.JLabel jLabel_MultiShot;
    public javax.swing.JLabel jLabel_Spy;
    public javax.swing.JLabel jLabel_Torpedo;
    public javax.swing.JLabel jLabel_Trumpedo;
    public javax.swing.JPanel jPanelBG;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JSpinner jSpinner_Bomb;
    public javax.swing.JSpinner jSpinner_Iron;
    public javax.swing.JSpinner jSpinner_MultiShot;
    public javax.swing.JSpinner jSpinner_Player;
    public javax.swing.JSpinner jSpinner_Price;
    public javax.swing.JSpinner jSpinner_Spy;
    public javax.swing.JSpinner jSpinner_Torpedo;
    public javax.swing.JSpinner jSpinner_Trumpedo;
    public javax.swing.JTextArea jTextArea_Offer;
    // End of variables declaration//GEN-END:variables
}
