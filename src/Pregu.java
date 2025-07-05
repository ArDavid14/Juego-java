
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import javax.swing.Timer;

public class Pregu extends javax.swing.JFrame {

    String[] preguntas = {
        "¿En cuantos pasos se lleva a cabo el ciclo de Krebs?",
        "¿Qué enzima cataliza la condensación del Acetil-CoA y el oxalacetato?",
        "¿En qué parte de la célula eucariota se lleva a cabo el ciclo de Krebs?",
        "¿Cuál de los siguientes NO es un producto del ciclo de Krebs?",
        "La enzima aconitasa es la encargada de:",
        "¿Qué vía de metabolismo presenta el ciclo de Krebs?",
        "¿Cuál es el balance metabólico general del ciclo de Krebs?",
        "¿Cuántas moléculas de CO2 salen del ciclo de Krebs?",
        "¿Cuáles son las tres vitaminas indispensables en el ciclo de Krebs?",
        "La síntesis de ácido málico es catalizada por la enzima:",
        "El proceso de catabolismo de proteínas se denomina:",
        "¿Mediante qué tipo de enzimas son hidrolizados los triglicéridos?",
        "El fumarato puede llegar al ciclo de Krebs mediante: ",
        "¿Cuál de las siguientes opciones NO es una vía de obtención de acetil CoA?",
        "El oxaloacetato puede llegar al ciclo de Krebs mediante:",
        "El ciclo de Krebs hace parte de la respiración celular:",
        "La biosíntesis de ácidos grasos y colesterol pueden sintetizar:",
        "En la síntesis de acido alfa cetolutarico interfiere un intermediario llamado:",
        "Cual es la coenzima de la vitamina B5 o acido pantoténico ",
        "El otro nombre de la vitamina B3 es:"
    };

    String[][] opciones = {
        {"6", "5", "8"},
        {"Aconitasa", "Citrato sintasa", "Fumarato hidratasa"},
        {"Mitocondria", "Citosol", "Retículo endoplasmático"},
        {"NADH", "Acetil CoA", "Dióxido de carbono"},
        {" La oxidación de isocitrato a α-cetoglutarato", " La hidratación de fumarato a malato", " La hidratación de citrato a isocitrato"},
        {" Anabólica", " Catabólica", " Anfibólica"},
        {"8 ATP", " 10 ATP", " 12 ATP"},
        {"Dos moléculas", "Una molécula", "No hay salida de CO2"},
        {" B1, B2, B3", "B1, B5, B2", "B2, B3, B5"},
        {"Fumarasa", "Malato deshidrogenasa", "Succinato deshidrogenasa"},
        {"Síntesis", "Proteólisis ", "Transcripción "},
        {"Liasas", "Ligasas", "Lipasas"},
        {"Síntesis de glutamato", "Ciclo de la urea ", "Ciclo de cori "},
        {"Beta oxidación ", "Degradación de lisina", "Síntesis de alanina "},
        {"Gluconeogénesis", "Oxidación y cadena de transporte electrónico", "Fosforilación oxidativa"},
        {"Aeróbica", "Anaeróbica", "Puede estar presente en los dos tipos de respiración"},
        {"Malato", " Succinil CoA ", "Citrato"},
        {"Ácido oxalosuccinico", "Ácido oxalocetico", "Acido succínico "},
        {"SH-CoA", "FAD – FMN ", "NAD – NADP "},
        {"Piridoxina", "Niacina ", "Cianocobalamina "},};

    char[] respuestas = {
        'C',
        'B',
        'A',
        'B',
        'C',
        'C',
        'C',
        'A',
        'C',
        'A',
        'B',
        'C',
        'B',
        'B',
        'A',
        'A',
        'C',
        'A',
        'A',
        'B',
        'C',};

    char respuesta;
    int index = 0;
    int indep = 0;
    int correctas = 0;
    int total_preguntas = preguntas.length;
    int total_opciones = opciones.length;
    int segundos = 20;

    Timer timer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            segundos--;
            labelSegundos.setText(String.valueOf(segundos));
            if (segundos <= 0) {
                resultadoFinal();

            }
        }
    });

    public Pregu() {
        initComponents();
        this.setLocationRelativeTo(null);
        siguientePregunta();
        buenas.setVisible(false);
        labelSegundos.setVisible(false);
        setLocation(950, 0);

    }

    private void verRespuesta() {
        timer.stop();
        botonA.setEnabled(false);
        botonB.setEnabled(false);
        botonC.setEnabled(false);
        //botonD.setEnabled(false);

        if (respuestas[indep] != 'A') {
            botonA.setForeground(Color.red);
        }
        if (respuestas[indep] != 'B') {
            botonB.setForeground(Color.red);
        }
        if (respuestas[indep] != 'C') {
            botonC.setForeground(Color.red);
        }
        /*if (respuestas[index] != 'D') {
            respuestaD.setForeground(Color.red);
        }*/

        Timer pause = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); // Generated from 
                botonA.setForeground(Color.BLACK);
                botonB.setForeground(Color.BLACK);
                botonC.setForeground(Color.BLACK);
                //respuestaD.setForeground(Color.BLACK);

                respuesta = '-';
                segundos = 20;
                labelSegundos.setText(String.valueOf(segundos));
                botonA.setEnabled(true);
                botonB.setEnabled(true);
                botonC.setEnabled(true);
                // botonD.setEnabled(true);
                /*if (indep == 2) {
                    indep = 0;
                } else {
                    indep++;
                }*/
                indep++;
                index++;

                siguientePregunta();
            }
        });

        pause.setRepeats(false);
        pause.start();

    }

    private void resultadoFinal() {
        botonA.setEnabled(false);
        botonB.setEnabled(false);
        botonC.setEnabled(false);
        //botonD.setEnabled(false);

        timer.start();
        field.setText("Resultado");
        area.setText("");
        botonA.setVisible(false);
        botonB.setVisible(false);
        botonC.setVisible(false);
        //respuestaD.setText("");
        buenas.setVisible(true);
        buenas.setText("Correctas\n(" + correctas + "/" + total_preguntas + ")");
        labelSegundos.setVisible(true);
        if (segundos == 0) {
            System.exit(0);
        }

    }

    private void siguientePregunta() {
        if (index >= preguntas.length) {

            resultadoFinal();

        } else {

            field.setText("Pregunta:" + (index + 1));
            area.setText(preguntas[index]);
            botonA.setText(opciones[indep][0]);
            botonB.setText(opciones[indep][1]);
            botonC.setText(opciones[indep][2]);
            // respuestaD.setText(opciones[index][3]);
            //timer.start();
            dispose();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        botonA = new javax.swing.JButton();
        botonB = new javax.swing.JButton();
        botonC = new javax.swing.JButton();
        buenas = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        field = new javax.swing.JLabel();
        labelSegundos = new javax.swing.JLabel();
        area = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        botonA.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        botonA.setText("A");
        botonA.setBorder(null);
        botonA.setBorderPainted(false);
        botonA.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botonA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAActionPerformed(evt);
            }
        });

        botonB.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        botonB.setText("B");
        botonB.setBorder(null);
        botonB.setBorderPainted(false);
        botonB.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botonB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonBActionPerformed(evt);
            }
        });

        botonC.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        botonC.setText("C");
        botonC.setBorder(null);
        botonC.setBorderPainted(false);
        botonC.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botonC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCActionPerformed(evt);
            }
        });

        buenas.setFont(new java.awt.Font("Cambria", 2, 18)); // NOI18N
        buenas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        buenas.setText("Buenas");

        jPanel1.setBackground(new java.awt.Color(102, 255, 255));
        jPanel1.setForeground(new java.awt.Color(51, 51, 255));

        field.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        field.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        field.setText("Pregunta n");

        labelSegundos.setText("Tiempo");

        area.setFont(new java.awt.Font("Segoe UI Emoji", 2, 10)); // NOI18N
        area.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        area.setText("pregun");
        area.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(field, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelSegundos))
                    .addComponent(area, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(field, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(labelSegundos, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(area, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botonA, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonB, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonC, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buenas, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botonA, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botonB, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botonC, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buenas, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonBActionPerformed
        botonA.setEnabled(false);
        botonB.setEnabled(false);
        botonC.setEnabled(false);
        //botonD.setEnabled(false);

        if (evt.getSource() == botonB) {
            respuesta = 'B';
            if (respuesta == respuestas[indep]) {
                correctas++;
            }
        }
        verRespuesta();
    }//GEN-LAST:event_botonBActionPerformed

    private void botonAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAActionPerformed
        botonA.setEnabled(false);
        botonB.setEnabled(false);
        botonC.setEnabled(false);
        //botonD.setEnabled(false);

        if (evt.getSource() == botonA) {
            respuesta = 'A';
            if (respuesta == respuestas[indep]) {
                correctas++;
            }
        }
        verRespuesta();
    }//GEN-LAST:event_botonAActionPerformed

    private void botonCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCActionPerformed
        botonA.setEnabled(false);
        botonB.setEnabled(false);
        botonC.setEnabled(false);
        //botonD.setEnabled(false);

        if (evt.getSource() == botonC) {
            respuesta = 'C';
            if (respuesta == respuestas[indep]) {
                correctas++;
            }
        }
        verRespuesta();
    }//GEN-LAST:event_botonCActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windos".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Pregu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pregu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pregu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pregu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Pregu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel area;
    private javax.swing.JButton botonA;
    private javax.swing.JButton botonB;
    private javax.swing.JButton botonC;
    private javax.swing.JLabel buenas;
    private javax.swing.JLabel field;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labelSegundos;
    // End of variables declaration//GEN-END:variables
}
