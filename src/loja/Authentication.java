package loja;


	import javax.swing.BoxLayout;
	import javax.swing.JFrame;
	import javax.swing.JLabel;
	import javax.swing.JOptionPane;
	import javax.swing.JPanel;
	import javax.swing.JPasswordField;
	import javax.swing.JTextField;


	final class Authentication {

		
		 
	    private JFrame frame;
	   
	    
	    private String log;
	    private String pass;
	 
	        Authentication(JFrame frame) {
	                this.frame = frame;
	        }
	       
	        /**
	         * Metodo para criar a caixa de log in
	         *
	         * @return ok se o butao ok for clicado e guarda os atributos inseridos.
	         */
	    boolean login() {
	       
	        JPanel panel = new JPanel(new java.awt.GridLayout(1, 1));
	        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
	     
	        JPanel loginbox = new JPanel();
	        JLabel loginlabel = new JLabel("Insira o seu Username: ");
	        JTextField login = new JTextField(10);
	        loginbox.add(loginlabel);
	        loginbox.add(login);
	      
	        JPanel passwordbox = new JPanel();
	        JLabel passwordlabel = new JLabel("Insira a sua Password: ");
	        JPasswordField password = new JPasswordField(10);
	        passwordbox.add(passwordlabel);
	        passwordbox.add(password);
	     
	        panel.add(loginbox);
	        panel.add(passwordbox);
	        
	        if(JOptionPane.showConfirmDialog(frame, panel, "Por Favor insira a sua Autenticação", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE) == 0) {
	                
	                log = login.getText();
	                pass = String.valueOf(password.getPassword());
	                return true;
	        }
	        return false;
	     }
	   
	    /**
	     * retorna a login
	     *
	     * @return login.
	     */
	    String getLogin() {
	        return log;
	    }
	   
	    /**
	     * retorna password.
	     *
	     * @return password.
	     */
	    String getPassword() {
	        return pass;
	    }
	   
	
	}

