package GUI;

public class airJFrame extends javax.swing.JFrame {

    public airJFrame() {
        initComponents();
        setSize(250, 230);
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14));
        jLabel1.setText("Airport Management");

        jButton1.setText("Airports");
        jButton1.addActionListener(evt -> {
            Airports o = new Airports();
            o.setVisible(true);
            this.dispose();
        });

        jButton2.setText("Flights");
        jButton2.addActionListener(evt -> {
            Flights o = new Flights();
            o.setVisible(true);
            this.dispose();
        });

        jButton3.setText("Employees");
        jButton3.addActionListener(evt -> {
            Employees o = new Employees();
            o.setVisible(true);
            this.dispose();
        });

        jButton4.setText("Tickets");
        jButton4.addActionListener(evt -> {
            Tickets o = new Tickets();
            o.setVisible(true);
            this.dispose();
        });

        jButton5.setText("Passengers");
        jButton5.addActionListener(evt -> {
            Passengers o = new Passengers();
            o.setVisible(true);
            this.dispose();
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(jLabel1)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup()
                                        .addComponent(jButton1)
                                        .addComponent(jButton5))
                                .addGap(20)
                                .addGroup(layout.createParallelGroup()
                                        .addComponent(jButton2)
                                        .addComponent(jButton4)))
                        .addComponent(jButton3)
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGap(15)
                        .addComponent(jLabel1)
                        .addGap(20)
                        .addGroup(layout.createParallelGroup()
                                .addComponent(jButton1)
                                .addComponent(jButton2))
                        .addGap(10)
                        .addGroup(layout.createParallelGroup()
                                .addComponent(jButton5)
                                .addComponent(jButton4))
                        .addGap(10)
                        .addComponent(jButton3)
        );

        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new airJFrame().setVisible(true);
        });
    }

    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
}
