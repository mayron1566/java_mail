/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java_mail;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.search.AndTerm;
import javax.mail.search.FlagTerm;
import javax.mail.search.FromTerm;
import javax.mail.search.SearchTerm;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class java_mail extends JFrame implements Runnable, ActionListener{
    
    private static boolean correoRecibido;
    private static boolean correoRecibidoG;
    private static boolean correoRecibido2;
    private static boolean correoRecibidoG2;
    private JLabel texto;
    private JLabel texto2; // etiqueta o texto no editable
    private JTextField caja;        // caja de texto, para insertar datos
    private JButton boton;          // boton con una determinada accion
    private String date;
    private Object[][] datos;
    private JScrollPane scroll;
    
    public java_mail() {
        super();                    // usamos el contructor de la clase padre JFrame
        configurarVentana();        // configuramos la ventana
        inicializarComponentes();   // inicializamos los atributos o componentes
    }
    
    //ventana de inicio
    private void configurarVentana() {
        this.setTitle("Mail bot");                   // colocamos titulo a la ventana
        this.setSize(512, 210);                                 // colocamos tamanio a la ventana (ancho, alto)
        this.setLocationRelativeTo(null);                       // centramos la ventana en la pantalla
        this.setLayout(null);                                   // no usamos ningun layout, solo asi podremos dar posiciones a los componentes
        this.setResizable(false);                               // hacemos que la ventana no sea redimiensionable
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    // hacemos que cuando se cierre la ventana termina todo proceso
    }
   
    //configura la ventana de inicio del servicio
    private void configurarVentana2() {
        this.setTitle("Mail bot");                   // colocamos titulo a la ventana
        this.setSize(300, 200);                                 // colocamos tamanio a la ventana (ancho, alto)
        this.setLocationRelativeTo(null);                       // centramos la ventana en la pantalla
        this.setLayout(null);                                   // no usamos ningun layout, solo asi podremos dar posiciones a los componentes
        this.setResizable(false);                               // hacemos que la ventana no sea redimiensionable
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   // hacemos que cuando se cierre la ventana termina todo proceso
        this.scroll = new JScrollPane();
        scroll.setBounds(512, 130, 500, 500);
        getContentPane().add(scroll);
    }
    
    //inicializa los componentes de las ventanas (botones, textos, etc.)
    private void inicializarComponentes() {
        // creamos los componentes
        texto = new JLabel();
        caja = new JTextField();
        boton = new JButton();
        // configuramos los componentes
        texto.setText("Comprobacion de correos");    // colocamos un texto a la etiqueta
        texto.setBounds(150, 20, 500, 25);   // colocamos posicion y tamanio al texto (x, y, ancho, alto)
        caja.setBounds(160, 50, 100, 25);   // colocamos posicion y tamanio a la caja (x, y, ancho, alto)
        boton.setText("Activar bot");   // colocamos un texto al boton
        boton.setBounds(160, 100, 200, 30);  // colocamos posicion y tamanio al boton (x, y, ancho, alto)
        boton.addActionListener(this);      // hacemos que el boton tenga una accion y esa accion estara en esta clase
        // adicionamos los componentes a la ventana
        this.add(texto);
        this.add(boton);
    }
    
    private void inicializarComponentes2() {
        this.add(texto);
        this.remove(texto);
        this.remove(boton);
        texto2 = new JLabel();
        // creamos los componentes, poner cuenta 
        // configuramos los componentes
        texto.setText("Bot corriendo");    // colocamos un texto a la etiqueta
        texto.setBounds(100, 20, 100, 100);
        texto2.setText("Para detener cerrar la ventana");    // colocamos un texto a la etiqueta
        texto2.setBounds(50, 40, 200, 100); // colocamos posicion y tamanio al texto (x, y, ancho, alto)
        this.add(texto);
        this.add(texto2);
    }
    
    //metodo que se activa al presionar el boton de iniciar bot
    //Inicia todo el proceso de los correos electronicos
    @Override
    public void actionPerformed(ActionEvent e) {
            //realiza accion completa del envio y recibo de correos al presionar el
            configurarVentana2();
            inicializarComponentes2();
            ScheduledExecutorService scheduler
                = Executors.newScheduledThreadPool(2);
            Runnable task = new java_mail();
            Runnable task2 = new java_mail();
            int initialDelay = 0;
            int initialDelay2 = 10;
            int periodicDelay = 30;
            scheduler.scheduleAtFixedRate(task, initialDelay, periodicDelay,
                TimeUnit.MINUTES
            );
            scheduler.scheduleAtFixedRate(task2, initialDelay2, periodicDelay,
                TimeUnit.MINUTES
            );
        }
        
    
    //clase que se ejecuta asta que se cierre la aplicacion
    @Override
    public void run() {
        Date hora = new Date(System.currentTimeMillis());
        Calendar c = Calendar.getInstance();
        c.setTime(hora);
        System.out.println(c.get(Calendar.HOUR_OF_DAY));
        // Si la hora es posterior a las 8am se programa la alarma para el dia siguiente
        if ((c.get(Calendar.HOUR_OF_DAY) <= 8 || c.get(Calendar.HOUR_OF_DAY) >= 18)) {
            java_mail.correoRecibido=false;
            java_mail.correoRecibidoG=false;
            java_mail.correoRecibido2=false;
            java_mail.correoRecibidoG2=false;
            java_mail V = new java_mail();
            V.enviarCorreo();
            V.enviarCorreoG();
            try {
                Thread.sleep(250000);
            } catch (InterruptedException ex) {
                Logger.getLogger(java_mail.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                V.recibirCorreo2();
            } catch (MessagingException ex) {
                Logger.getLogger(java_mail.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                V.recibirCorreoG2();
            } catch (MessagingException ex) {
                Logger.getLogger(java_mail.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (java_mail.correoRecibido2==false){
                V.enviarCorreoNegativo();
                if (java_mail.correoRecibidoG2==false) V.enviarCorreoNegativo();
            }
            else if (java_mail.correoRecibidoG2==false){
                V.enviarCorreoNegativo();
                if (java_mail.correoRecibido2==false) V.enviarCorreoNegativo();
            }
            else{
                V.enviarCorreo2();
                V.enviarCorreoG2();
                try {
                    Thread.sleep(450000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(java_mail.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    V.recibirCorreo();
                } catch (MessagingException ex2) {
                    Logger.getLogger(java_mail.class.getName()).log(Level.SEVERE, null, ex2);
                }
                if (java_mail.correoRecibido == false)
                    V.enviarCorreoNegativo();
                try {
                    Thread.sleep(450000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(java_mail.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    V.recibirCorreoG();
                } catch (MessagingException ex2) {
                    Logger.getLogger(java_mail.class.getName()).log(Level.SEVERE, null, ex2);
                }
                if (java_mail.correoRecibidoG == false)
                    V.enviarCorreoNegativo();
            }
        }
        else{
                JOptionPane.showMessageDialog(null, "Sistema en reposo hasta las 18 horas");
                System.out.println("Sistema suspendido hasta la hora correspondiente");
            }
    }
            /*try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(java_mail.class.getName()).log(Level.SEVERE, null, ex);
            }
                */
    

//metodo que revisa los correos recibidos de la cuenta yahoo    
public void recibirCorreo() throws NoSuchProviderException, MessagingException{
    int messageCount;
    Properties props = System.getProperties();
    props.setProperty("mail.store.protocol", "imaps");
    try {
        Session session = Session.getDefaultInstance(props, null);
        session.setDebug(true);
        Store store = session.getStore("imaps");
        store.connect("imap.mail.yahoo.com", "mail@yahoo.com", "password");//reemplazar con mail y clave yahoo
        System.out.println(store);
        Folder inbox = store.getFolder("Inbox");
        inbox.open(Folder.READ_WRITE);
        messageCount = inbox.getUnreadMessageCount();
        inbox.close(true);
            inbox.open(Folder.READ_WRITE);
            if (inbox.getUnreadMessageCount() >= messageCount) {
                System.out.println("New mail has arrived");
                FlagTerm unseenFlagTerm = new FlagTerm(new Flags(Flags.Flag.SEEN), false);
                FromTerm senderFlagTerm = new FromTerm(new InternetAddress("correo_empresa_1"));//colocar correo empresarial 1 aqui
                SearchTerm searchTerm = new AndTerm(unseenFlagTerm, senderFlagTerm);
                Message messages[] = inbox.search(searchTerm);
                if (messages.length>0) {
                    for (Message message : messages) {
                        message.setFlag(Flags.Flag.SEEN, true);
                    }
                    java_mail.correoRecibido=true;
                    }
            }
    } catch (NoSuchProviderException e) {
    } catch (MessagingException e) {
    }

}

//metodo que revisa los correos recibidos de la cuenta gmail  
public void recibirCorreoG() throws NoSuchProviderException, MessagingException{
    int messageCount;
    Properties props = System.getProperties();
    props.setProperty("mail.store.protocol", "imaps");
    try {
        Session session = Session.getDefaultInstance(props, null);
        session.setDebug(true);
        Store store = session.getStore("imaps");
        store.connect("smtp.gmail.com", "mail@gmail.com", "password");//reemplazar mail y clave gmail
        System.out.println(store);
        Folder inbox = store.getFolder("Inbox");
        inbox.open(Folder.READ_WRITE);
        messageCount = inbox.getUnreadMessageCount();
        inbox.close(true);
            inbox.open(Folder.READ_WRITE);
            if (inbox.getUnreadMessageCount() >= messageCount) {
                System.out.println("New mail has arrived");
                FlagTerm unseenFlagTerm = new FlagTerm(new Flags(Flags.Flag.SEEN), false);
                FromTerm senderFlagTerm = new FromTerm(new InternetAddress("correo_empresa_2"));//colocar correo empresa 2 aqui
                SearchTerm searchTerm = new AndTerm(unseenFlagTerm, senderFlagTerm);
                Message messages[] = inbox.search(searchTerm);
                if (messages.length>0) {
                    for (Message message : messages) {
                        message.setFlag(Flags.Flag.SEEN, true);
                    }
                    java_mail.correoRecibidoG=true;
                    }
            }
    } catch (NoSuchProviderException e) {
    } catch (MessagingException e) {
    }

}

public void recibirCorreo2() throws NoSuchProviderException, MessagingException{
    int messageCount;
    Properties props = System.getProperties();
    props.setProperty("mail.store.protocol", "imaps");
    try {
        Session session = Session.getDefaultInstance(props, null);
        session.setDebug(true);
        Store store = session.getStore("imaps");
        store.connect("imap.mail.yahoo.com", "mail@yahoo.com", "password");//reemplazar con mail y clave yahoo
        System.out.println(store);
        Folder inbox = store.getFolder("Inbox");
        inbox.open(Folder.READ_WRITE);
        messageCount = inbox.getUnreadMessageCount();
        inbox.close(true);
            inbox.open(Folder.READ_WRITE);
            if (inbox.getUnreadMessageCount() >= messageCount) {
                System.out.println("New mail has arrived");
                FlagTerm unseenFlagTerm = new FlagTerm(new Flags(Flags.Flag.SEEN), false);
                FromTerm senderFlagTerm = new FromTerm(new InternetAddress("correo_empresa_1"));//colocar correo empresa 1 aqui
                SearchTerm searchTerm = new AndTerm(unseenFlagTerm, senderFlagTerm);
                Message messages[] = inbox.search(searchTerm);
                if (messages.length>0) {
                    for (Message message : messages) {
                        message.setFlag(Flags.Flag.SEEN, true);
                    }
                    java_mail.correoRecibido2=true;
                    }
            }
    } catch (NoSuchProviderException e) {
    } catch (MessagingException e) {
    }

}

public void recibirCorreoG2() throws NoSuchProviderException, MessagingException{
    int messageCount;
    Properties props = System.getProperties();
    props.setProperty("mail.store.protocol", "imaps");
    try {
        Session session = Session.getDefaultInstance(props, null);
        session.setDebug(true);
        Store store = session.getStore("imaps");
        store.connect("smtp.gmail.com", "mail@gmail.com", "password");//reemplazar con mail y clave gmail
        System.out.println(store);
        Folder inbox = store.getFolder("Inbox");
        inbox.open(Folder.READ_WRITE);
        messageCount = inbox.getUnreadMessageCount();
        inbox.close(true);
            inbox.open(Folder.READ_WRITE);
            if (inbox.getUnreadMessageCount() >= messageCount) {
                System.out.println("New mail has arrived");
                FlagTerm unseenFlagTerm = new FlagTerm(new Flags(Flags.Flag.SEEN), false);
                FromTerm senderFlagTerm = new FromTerm(new InternetAddress("correo_empresa_2"));//colocar correo empresa 2 aqui
                SearchTerm searchTerm = new AndTerm(unseenFlagTerm, senderFlagTerm);
                Message messages[] = inbox.search(searchTerm);
                if (messages.length>0) {
                    for (Message message : messages) {
                        message.setFlag(Flags.Flag.SEEN, true);
                    }
                    java_mail.correoRecibidoG2=true;
                    }
            }
    } catch (NoSuchProviderException e) {
    } catch (MessagingException e) {
    }

}

    //metodo que envia el correo de comprobacion a la cuenta outlook 365 desde yahoo
    public void enviarCorreo(){
        // Mention the Recipient's email address
        String to = "correo_empresa_1";//colocar correo empresa 1 aqui
        // Mention the Sender's email address
        String from = "mail@yahoo.com";//reemplazar cuenta externa
        // Mention the SMTP server address. Below Gmail's SMTP server is being used to send email
        String host = "smtp.mail.yahoo.com";
        // Get system properties
        Properties properties = System.getProperties();
        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        Session session;
        session = Session.getInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("mail@yahoo.com", "password");//reemplazar
                        }
        });
        session.setDebug(true);
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Prueba");
            message.setText("Revision de los sistemas de correo");
            System.out.println("sending...");
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            }
    }
 
        public void enviarCorreo2(){
                // Mention the Recipient's email address
        String to = "correo_empresa_1";
        // Mention the Sender's email address
        String from = "mail@yahoo.com";//reemplazar cuenta externa
        // Mention the SMTP server address. Below Gmail's SMTP server is being used to send email
        String host = "smtp.mail.yahoo.com";
        // Get system properties
        Properties properties = System.getProperties();
        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        Session session;
        session = Session.getInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("mail@yahoo.com", "clave");//reemplazar
                        }
        });
        session.setDebug(true);
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Recibido");
            message.setText("Revision de los sistemas de correo");
            System.out.println("sending...");
            Transport.send(message); //nsnjxi
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            }
    }
    
    
    //metodo que envia el correo de comprobacion a la cuenta outlook 365 desde gmail
    public void enviarCorreoG(){
               // Mention the Recipient's email address
        String to = "correo_empresa_2";//colocar cuenta empresa 2 aqui
        // Mention the Sender's email address
        String from = "mail@gmail.com";//reemplazar cuenta externa 
        // Mention the SMTP server address. Below Gmail's SMTP server is being used to send email
        String host = "smtp.gmail.com";
        // Get system properties
        Properties properties = System.getProperties();
        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        Session session;
        session = Session.getInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("mail@gmail.com", "password");//reemplazar
                        }
        });
        session.setDebug(true);
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Prueba");//vvgfhfhhhhjhhhh                                                                                                                                                                 
            message.setText("Revision de los sistemas de correo");
            System.out.println("sending...");
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            }
    }
    
        public void enviarCorreoG2(){
               // Mention the Recipient's email address
        String to = "correo_empresa_2";//colocar cuenta empresa 2 aqui
        // Mention the Sender's email address
        String from = "mail@gmail.com";//reemplazar cuenta externa
        // Mention the SMTP server address. Below Gmail's SMTP server is being used to send email
        String host = "smtp.gmail.com";
        // Get system properties
        Properties properties = System.getProperties();
        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        Session session;
        session = Session.getInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("mail@gmail.com", "password");//reemplazar
                        }
        });
        session.setDebug(true);
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Recibido");                                                                                                                                                                 
            message.setText("Revision de los sistemas de correo");
            System.out.println("sending...");
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            }
    }
    
    
    
    //correo de alerta cuando el sistema noi este funcionando
    public void enviarCorreoNegativo(){
        String to = "correo_encargado";//correo encargado
        String from = "mail@yahoo.com";//cuenta externa
        String host = "smtp.mail.yahoo.com";
        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        Session session;
        session = Session.getInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("mail@yahoo.com", "password");//reemplazar
                        }
        });
        // Used to debug SMTP issues
        session.setDebug(true);
        try {
            // Create a default MimeMessage object:
            MimeMessage message = new MimeMessage(session);
            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));
            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            // Set Subject: header field
            message.setSubject("Error en sistema de correos");
            // Now set the actual message
            message.setText("Hay un problema en el sistema de correos");
            System.out.println("sending...");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            }
    }
    
    public static void main(String[] args) {
        java_mail P = new java_mail();      // creamos una ventana
        P.setVisible(true);    // hacemos visible la ventana creada
    }

    
}
    
