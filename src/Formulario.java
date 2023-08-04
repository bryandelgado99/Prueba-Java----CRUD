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
                try {
                    JOptionPane.showMessageDialog(null, "Registro de usuario borrado correctamente!\n", "Error!", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception M) {
                    JOptionPane.showMessageDialog(null, "El registro no se pudo borrar!\n" + M.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        //Actualizar registro
        actualizarRegistroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JOptionPane.showMessageDialog(null, "Actualización de usuario realizada correctamente!\n", "Actualización de usuario", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception z) {
                    JOptionPane.showMessageDialog(null, "El registro no se pudo actualizar!\n" + z.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        //Ingresar Regsitro
        ingresarRegistroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JOptionPane.showMessageDialog(null, "Ingreso de usuario realizado correctamente!\n", "Registro de usuario", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception x) {
                    JOptionPane.showMessageDialog(null, "El registro no se pudo ingresar!\n" + x.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        //Limpiar Formulario
        limpiarFormularioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cod_input.setText(" ");
                name_input.setText(" ");
                cc_input.setText(" ");
                fecha_input.setText(" ");
                signoCombo.setSelectedItem(" ");
            }
        });

        /*------------------------------------------------------------------------------------------------*/

        //Listeners de busqueda en Database

        //Buscar por codigo
        buscar_codeBotton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try{
                    Formulario Con = new Formulario();
                    Con.ConectarBase();
                    //Variables del Login
                    String QUERY = "SELECT * FROM personas WHERE ID_PER = '"+ cod_input.getText() +"'";
                    Con.resultado = Con.state.executeQuery(QUERY);  //Query Read

                    if(resultado.next()){
                        //Obtenemos el nombre del usuario y seteamos
                        String id_per = resultado.getString("ID_PER");
                        cod_input.setText(id_per);
                        String ci_per = resultado.getString("CI_PER");
                        cc_input.setText(ci_per);
                        String name_per = resultado.getString("NOMBRE_PER");
                        name_input.setText(name_per);
                        String fecha_per = resultado.getString("FECHA_NAC");
                        fecha_input.setText(fecha_per);
                        String signo_per = resultado.getString("SIGNO_ZOD");
                        signoCombo.setSelectedItem(signo_per);

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
                try{
                    Formulario Con = new Formulario();
                    Con.ConectarBase();
                    //Variables del Login
                    String QUERY = "SELECT * FROM personas WHERE ID_PER = '"+ name_input.getText() +"'";
                    Con.resultado = Con.state.executeQuery(QUERY);  //Query Read

                    if(resultado.next()){
                        //Obtenemos el nombre del usuario y seteamos
                        String id_per = resultado.getString("ID_PER");
                            cod_input.setText(id_per);
                        String ci_per = resultado.getString("CI_PER");
                            cc_input.setText(ci_per);
                        String name_per = resultado.getString("NOMBRE_PER");
                            name_input.setText(name_per);
                        String fecha_per = resultado.getString("FECHA_NAC");
                            fecha_input.setText(fecha_per);
                        String signo_per = resultado.getString("SIGNO_ZOD");
                            signoCombo.setSelectedItem(signo_per);

                    }else{
                        JOptionPane.showMessageDialog(null, "Usuario y/o contraseña son erróneos. Intente nuevamente!\n", "Error de credenciales", JOptionPane.ERROR_MESSAGE);
                    }

                }catch(SQLException S){
                    JOptionPane.showMessageDialog(null, S.getMessage(), "Usuario y/o Fecha no existente", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        //Buscar por signo
        signo_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Formulario Con = new Formulario();
                    Con.ConectarBase();
                    //Variables del Login
                    String query = "SELECT * FROM PERSONAS WHERE ID_PER = "+ signoCombo.getSelectedItem();
                    Con.resultado = Con.state.executeQuery(query);  //Query Read

                    if(resultado.next()){
                        //Obtenemos el nombre del usuario y seteamos
                        String id_per = resultado.getString("ID_PER");
                        cod_input.setText(id_per);
                        String ci_per = resultado.getString("CI_PER");
                        cc_input.setText(ci_per);
                        String name_per = resultado.getString("NOMBRE_PER");
                        name_input.setText(name_per);
                        String fecha_per = resultado.getString("FECHA_NAC");
                        fecha_input.setText(fecha_per);
                        String signo_per = resultado.getString("SIGNO_ZOD");
                        signoCombo.setSelectedItem(signo_per);

                    }else{
                        JOptionPane.showMessageDialog(null, "Usuario y/o contraseña son erróneos. Intente nuevamente!\n", "Error de credenciales", JOptionPane.ERROR_MESSAGE);
                    }

                }catch(SQLException S){
                    JOptionPane.showMessageDialog(null, S.getMessage(), "Usuario y/o Fecha no existente", JOptionPane.ERROR_MESSAGE);
                }
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

    public static void ConectarBase(){
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
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if(e.getSource() == signoCombo){
            String seleccion = (String) signoCombo.getSelectedItem();
            setTitle(seleccion);
        }
    }
}
