import javax.swing.*;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class Formulario extends JFrame implements ItemListener{

    /*Estados, conexión y sentencias de SQL*/
    public Connection con;
    public Statement state;
    private ResultSet resultado;

    /*------------------------------------*/

    private JPanel rootPanel;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JTextField cod_input;
    private JTextField cc_input;
    private JTextField name_input;
    private JTextField fecha_input;
    private JButton borrarRegistroButton;
    private JButton actualizarRegistroButton;
    private JButton ingresarRegistroButton;
    private JButton limpiarFormularioButton;
    private JButton buscar_codeBotton;
    private JButton buscar_nombreButton;
    private JButton signo_Button;
    private JComboBox signoCombo;


    public Formulario() {
        ConectarBase();
        //Elementos para el combo box
        signoCombo.addItem("Aries");
        signoCombo.addItem("Leo");
        signoCombo.addItem("Escorpio");
        signoCombo.addItem("Cáncer");
        signoCombo.addItem("Tauro");
        signoCombo.addItem("Piscis");
        signoCombo.addItem("Capricornio");
        signoCombo.addItem("Sagitario");
        signoCombo.addItem("Géminis");
        signoCombo.addItem("Acuario");
        signoCombo.addItem("Virgo");
        signoCombo.addItem("Libra");
        signoCombo.addItemListener(this);

        /*SECCIÓN DE LISTENERS---------------------------------------------------------------------------*/

        //Borrar Registro
        borrarRegistroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        //Actualizar registro
        actualizarRegistroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        //Ingresar Regsitro
        ingresarRegistroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        //Limpiar Formulario
        limpiarFormularioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        /*------------------------------------------------------------------------------------------------*/

        //Listeners de busqueda en Database

        //Buscar por codigo
        buscar_codeBotton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            try{

                String QUERY = "SELECT ID_PER, CI_PER, NOMBRE_PER, FECHA_NAC, SIGNO_ZOD FROM personas WHERE ID_PER = '"+ cod_input.getText() +"' ";


                if(resultado.next()){
                    //Obtenemos el nombre del usuario
                    String name = resultado.getString("NOMBRE_EST");


                }else{
                    JOptionPane.showMessageDialog(null, "Usuario y/o contraseña son erróneos. Intente nuevamente!\n", "Error de credenciales", JOptionPane.ERROR_MESSAGE);
                }

            }catch(SQLException S){
                JOptionPane.showMessageDialog(null, S.getMessage(), "Usuario y/o Fecha no existente", JOptionPane.ERROR_MESSAGE);
            }

            }
        });

        //Buscar por nombre
        buscar_nombreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        //Buscar por signo
        signo_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Registro de Personas");
        frame.setContentPane(new Formulario().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setSize(600, 500);
    }

    public static String ConectarBase(){
        try {
            /*SQL DATABASE INTEGRATION*/
            final String DB_URL = "jdbc:mysql://localhost/prueba_crud";
            final String USER = "root";
            final String PASS = "root_bas3";

            Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement sentencia = con.createStatement();

            JOptionPane.showMessageDialog(null, "Éxito al conectar base de datos MySQL", "Conexión a base de datos", JOptionPane.INFORMATION_MESSAGE);

        }catch(SQLException EX){
            JOptionPane.showMessageDialog(null, "Error en conectar base de datos MySQL\n" + EX.getMessage(), "Error de credenciales", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if(e.getSource() == signoCombo){
            String seleccion = (String) signoCombo.getSelectedItem();
            setTitle(seleccion);
        }
    }
}
