/* Address Book GUI project
 * This program is a simple contact list that people can use to store up to 100 contacts with ease. It has a user friendly interface which asks for their first name, last name, cellphone number,
 email, address, city and postal code. Then when the user fills in their information in all the text fields, the program stores the contact in an array and on a list. The user can also clear 
 the text fields with the clear button if they made a mistake, search for a contact in the address book, edit a contact's information after searching, delete a contact, display a selected contact's
 information on a new dialog, and see previous and next contacts that were saved.
 * Andy Su
 * November 23rd, 2014
 */

import javax.swing.*;
import java.awt.*; 
import java.awt.event. *; //adding event action listener

import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JComponent;

import java.awt.GridLayout;
import java.awt.event.KeyEvent;

public class AddressBook3 extends JFrame implements ActionListener{ 
  //Creating GUI components
  
  JButton Button1 = new JButton("Save Contact"); //Creating Buttons for the GUI
  JButton Button11= new JButton("Clear Field");
  JButton Button2 = new JButton("Delete");
  JButton Button3 = new JButton("Update Contact");
  JButton Button4 = new JButton("Search");
  JButton Button5 = new JButton("Previous");
  JButton Button6 = new JButton("Next");
  JButton Button7= new JButton("Display Contact Info");
  
  JTextField firstnameField = new JTextField(7); //Creating TextFields for the user to type their information in
  JTextField lastnameField = new JTextField(7);
  JTextField cellphoneField = new JTextField(7);
  JTextField emailField = new JTextField(7);
  JTextField addressField = new JTextField(7);
  JTextField cityField = new JTextField(7);
  JTextField postalcodeField = new JTextField(7);
  JTextField provinceField = new JTextField(7);
  
  JLabel firstnameLabel = new JLabel("First Name: ", JLabel.CENTER); //Creating the labels for the program
  JLabel lastnameLabel = new JLabel("Last Name: ", JLabel.CENTER);
  JLabel cellphoneLabel = new JLabel("Cell Phone #: ", JLabel.CENTER);
  JLabel emailLabel = new JLabel("Email: ", JLabel.CENTER);
  JLabel addressLabel = new JLabel("Address: ", JLabel.CENTER);
  JLabel cityLabel = new JLabel("City: ", JLabel.CENTER);
  JLabel postalcodeLabel = new JLabel("Postal Code: ", JLabel.CENTER);
  JLabel provinceLabel = new JLabel("Province and Territories: ", JLabel.CENTER);
  JLabel keyeventLabel= new JLabel ("Contact Information: ", JLabel.LEFT);
  JLabel searchnameLabel= new JLabel ("Contact found: ", JLabel.LEFT);
  JLabel searchcellphoneLabel = new JLabel("Cell Phone #: ", JLabel.LEFT);
  JLabel searchemailLabel = new JLabel("Email: ", JLabel.LEFT);
  JLabel searchaddressLabel = new JLabel("Address: ", JLabel.LEFT);
  JLabel searchcityLabel = new JLabel("City: ", JLabel.LEFT);
  JLabel searchpostalcodeLabel = new JLabel("Postal Code: ", JLabel.LEFT);
  JLabel firstnameLabel2 = new JLabel("First Name: ", JLabel.CENTER); //lable
  JLabel lastnameLabel2 = new JLabel("Last Name: ", JLabel.CENTER);
  JLabel cellphoneLabel2 = new JLabel("Cell Phone #: ", JLabel.CENTER);
  JLabel emailLabel2 = new JLabel("Email: ", JLabel.CENTER);
  JLabel addressLabel2 = new JLabel("Address: ", JLabel.CENTER);
  JLabel cityLabel2 = new JLabel("City: ", JLabel.CENTER);
  JLabel postalcodeLabel2 = new JLabel("Postal Code: ", JLabel.CENTER);
  JLabel keyeventLabel2= new JLabel ("Please Enter your information in the boxes above.", JLabel.LEFT);
  JLabel keyeventLabel3= new JLabel ("Only use 'Search' and 'Update Contact' after a contact is saved.", JLabel.LEFT);
  JLabel keyeventLabel4= new JLabel ("Type in exact first and last names in the fields to search for a contact.", JLabel.LEFT);
  
  int c=0, index, location, value=-1,size, low=0, search=0; //Creating variables and arrays 
  String temp1,temp2,temp3,temp4,temp5,temp6,temp7,finding;
  int searching[] = new int[100];
  String firstname[] = new String[100];
  String lastname[] = new String[100];
  String cellphone[] = new String[100];
  String email[] = new String[100];
  String address[] = new String[100];
  String city[] = new String[100];
  String postalcode[] = new String[100];
  
  DefaultListModel<String> listModel; //Making a new list model for the list in the GUI
  JList<String> listbox; //Creating a new list
  
  JPanel pan1= new JPanel(); // Adding Panels for GUI
  JPanel pan2= new JPanel();
  JPanel pan3= new JPanel();
  JPanel pan4= new JPanel();
  JPanel pan5= new JPanel();
  JPanel pan6= new JPanel();
  JPanel pan7= new JPanel();
  JPanel pan8= new JPanel();
  JPanel pan9= new JPanel();
  JPanel pan10= new JPanel();
  JPanel pan11= new JPanel();
  
  JFrame frame1 = new JFrame("FrameDemo"); //frame for the GUI
  
  //Constructor for GUI
  public AddressBook3() {
    frame1.add(Button1, BorderLayout.CENTER); //adding a layout for the main frame
    
    JTabbedPane tabbedPane = new JTabbedPane(); //creating new tabs for adding a contact
    JComponent panel1 = makeTextPanel("Add a Contact");
    tabbedPane.addTab("Add a Contact", pan1);
    pan1.setPreferredSize(new Dimension(350, 350));
    tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
    
    JComponent panel2 = makeTextPanel("All Contacts"); //new tab for all contacts
    tabbedPane.addTab("All Contacts", pan2);
    pan1.setPreferredSize(new Dimension(350, 350));
    tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
    
    setLayout(new BorderLayout()); 
    listModel = new DefaultListModel<String>(); 
    listbox = new JList<String> (listModel); //making a new list using the default list model 
    
    listbox.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //set the selection mode for the list to be only single selection
    JScrollPane listscroll = new JScrollPane(listbox); // creating a scrolling wheel for the list if a lot of contacts were saved
    listbox.setFixedCellHeight(15); // dimensions
    listbox.setVisibleRowCount(-1);
    pan2.add(listscroll, BorderLayout.CENTER); //adding scroll list to panel 2
    
    listbox.addMouseListener(new MouseAdapter() { //creating a action listener for mouse clicks on the list
      public void mouseClicked(MouseEvent evt) {
        JList<String> listbox = (JList<String>)evt.getSource();
        if (evt.getClickCount() == 2) { //checks if list was doubleclicked on
          int index = listbox.getSelectedIndex(); //finds index of contact
          
          JDialog dialog =new JDialog(frame1,"Contact Information",true); //creating new popup dialog for double clicking on a contact
          dialog.setSize(new Dimension(400, 300));//dimensions
          dialog.setModal(true); //stops executing until dialog box is closed
          firstnameLabel2.setText("First Name: "+firstname[index]); //setting correct labels for the information
          lastnameLabel2.setText("Last Name: "+lastname[index]);
          cellphoneLabel2.setText("Cell Phone: "+cellphone[index]);
          emailLabel2.setText("Email: "+email[index]);
          addressLabel2.setText("Street: "+address[index]);
          cityLabel2.setText("City: "+city[index]);
          postalcodeLabel2.setText("Postal Code: "+postalcode[index]);
          dialog.add(pan8); //adding panel 8 to the dialog box
          dialog.setModal(true);
          dialog.setVisible(true); //display dialog
        }
      }
    });
    
    BoxLayout layout2=new BoxLayout(pan2,BoxLayout.Y_AXIS);//layouts
    BoxLayout layout4=new BoxLayout(pan3,BoxLayout.Y_AXIS);
    BoxLayout layout6=new BoxLayout(pan8,BoxLayout.Y_AXIS);
    BoxLayout layout7=new BoxLayout(pan6,BoxLayout.Y_AXIS);
    BoxLayout layout8=new BoxLayout(pan7,BoxLayout.Y_AXIS);
    FlowLayout layout42=new FlowLayout();
    GridLayout layout3=new GridLayout(0,2);
    
    setLayout(layout42); //frame layout
    pan1.setLayout(layout3); //setting layouts for each of the panels
    pan2.setLayout(layout2); 
    pan3.setLayout(layout4);
    pan4.setLayout(layout42);
    
    pan5.setLayout(layout3);
    pan6.setLayout(layout7);
    pan7.setLayout(layout8);
    pan8.setLayout(layout6);
    
    setTitle("THE ADDRESS Book"); //Title for the GUI address book program
    setSize(450, 515);//dimensions
    setResizable(false); //doesn't let user resize the program
    
    add(tabbedPane); //adding the tab on the frame
    pan1.add(firstnameLabel); // adding the labels to panel 1
    pan1.add (firstnameField);
    pan1.add(lastnameLabel);
    pan1.add (lastnameField);
    pan1.add(cellphoneLabel);
    pan1.add (cellphoneField);
    pan1.add(emailLabel);
    pan1.add (emailField);
    pan1.add(addressLabel);
    pan1.add (addressField);
    pan1.add(cityLabel);
    pan1.add (cityField);
    pan1.add(postalcodeLabel);
    pan1.add (postalcodeField);
    
    pan6.add(keyeventLabel); //adding more labels to specfic panels
    pan5.add(searchnameLabel);
    pan5.add(searchcellphoneLabel);
    pan5.add(searchemailLabel);
    pan5.add(searchaddressLabel);
    pan5.add(searchcityLabel);
    pan5.add(searchpostalcodeLabel);
    
    pan8.add(firstnameLabel2); //adding more labels to specific panels
    pan8.add(lastnameLabel2);
    pan8.add(cellphoneLabel2);
    pan8.add(emailLabel2);
    pan8.add(addressLabel2);
    pan8.add(cityLabel2);
    pan8.add(postalcodeLabel2);
    
    pan4.add(Button2); // adding buttons to each panel 
    Button2.addActionListener(this); // adding an actionlistener to the buttons so it performs an action when clicked
    pan4.add(Button7);
    Button7.addActionListener(this);
    pan4.add(Button5);
    Button5.addActionListener(this);
    pan4.add(Button6);
    Button6.addActionListener(this);
    pan1.add(Button1); 
    Button1.setAlignmentX(Component.CENTER_ALIGNMENT);
    Button1.addActionListener(this);
    pan1.add(Button11);
    Button11.addActionListener(this);
    pan1.add(Button3);
    Button3.addActionListener(this);
    pan1.add(Button4);
    Button4.addActionListener(this);
    pan9.add(keyeventLabel2); //adding more labels
    pan11.add(keyeventLabel4);
    pan10.add(keyeventLabel3);
    
    pan1.add(pan3); //adding panels inside panels
    pan2.add(pan4);
    pan2.add(pan6);
    pan6.add(pan5);
    add(pan9);
    add(pan11);
    add(pan10);
    
    setVisible(true); //display everything
  }
  
  protected JComponent makeTextPanel (String text){ //new text panel
    JPanel panel = new JPanel(false);
    JLabel filler = new JLabel(text);
    filler.setHorizontalAlignment(JLabel.CENTER);
    panel.setLayout(new GridLayout(1,1));
    panel.add(filler);
    return panel;
  }
  //Action listener, runs when a button is pressed
  public void actionPerformed(ActionEvent event) {
    String command = event.getActionCommand();//finds which buttom that was pressed
    
    if (command.equals ("Save Contact")){ //if the saved button is pressed
      
      if(c<100){ //checks if there is space to add new contacts
        //message in console
        System.out.println("Name:" + firstnameField.getText()+" "+ lastnameField.getText());
        System.out.println("CellPhone: "+cellphoneField.getText());
        System.out.println("Email: "+emailField.getText());
        System.out.println("Address: "+addressField.getText());
        System.out.println("City: "+cityField.getText());
        System.out.println("Postal Code: "+postalcodeField.getText());
        System.out.println(" ");
        keyeventLabel.setText(" "+firstnameField.getText()+" "+lastnameField.getText()+" was saved in the Contact List."); //change the event label to display the right message
        //checks if any information is left blank on the text fields
        if(firstnameField.getText().equals("")||lastnameField.getText().equals("")||cellphoneField.getText().equals("")||emailField.getText().equals("")||addressField.getText().equals("")||cityField.getText().equals("")||postalcodeField.getText().equals("")){
          keyeventLabel2.setText("Please fill in the required information in the correct fields.");//sets event label to an appropriate message
          
        }else{
          //Saving the input of the user in the arrays
          firstname[c]=firstnameField.getText();
          lastname[c]=lastnameField.getText();
          cellphone[c]=cellphoneField.getText();
          email[c]=emailField.getText();
          address[c]=addressField.getText();
          city[c]=cityField.getText();
          postalcode[c]=postalcodeField.getText();
          listModel.addElement(firstname[c]+" "+lastname[c]); //adding the first and last name on the list created
          
          keyeventLabel2.setText("Contact Saved!");//sets event labels to an appropriate messages
          keyeventLabel3.setText("");
          keyeventLabel4.setText("");
          c++; //this is the counter which counts the contacts added
          value++;//this is the counter for the previous and next buttons to show which contact to display
          
          //bubble sorting all the contacts by last name
          if (c>1){
            for (int i=0;i<c-1;i++){
              for (int k=0;k<c-1;k++){
                if (lastname[k].compareTo(lastname[k+1])>0){ //comparing the lastnames in the array
                  temp1=firstname[k];
                  temp2=lastname[k];
                  firstname[k]=firstname[k+1]; //move the contact position up
                  lastname[k]=lastname[k+1];
                  listModel.set(k,firstname[k+1]+" "+lastname[k+1]); //swapping the names on the list in order
                  firstname[k+1]=temp1; //swapping the contacts to place them in order
                  lastname[k+1]=temp2;
                  listModel.set(k+1,temp1+" "+temp2); //swapping the names on the list in order
                  
                  temp3=cellphone[k];//more sorting of information in the array
                  cellphone[k]=cellphone[k+1];
                  cellphone[k+1]=temp3;
                  temp4=email[k];
                  email[k]=email[k+1];
                  email[k+1]=temp4;
                  temp5=address[k];
                  address[k]=address[k+1];
                  address[k+1]=temp5;
                  temp6=city[k];
                  city[k]=city[k+1];
                  city[k+1]=temp6;
                  temp7=postalcode[k];
                  postalcode[k]=postalcode[k+1];
                  postalcode[k+1]=temp7;
                  
                }
              }
            }
          }
        }
      }else{
        keyeventLabel.setText("There are 100 contacts in the address book"); //if there are more than 100 contacts in the list, display this message
      }
    }
    
    if(command.equals ("Clear Field")){ //clears text fields so user can input new information
      firstnameField.setText("");
      lastnameField.setText("");
      cellphoneField.setText("");
      emailField.setText("");
      addressField.setText("");
      cityField.setText("");
      postalcodeField.setText("");
      System.out.println("Fields cleared!");//console message
      keyeventLabel2.setText("Fields cleared!");//sets event labels to an appropriate message
      keyeventLabel3.setText("");
      keyeventLabel4.setText("");
    }
    
    if(command.equals("Delete")){ //if delete button is pressed
      int index=listbox.getSelectedIndex(); //finds index to delete from
      listModel.removeElementAt(index); //deletes the element from the list at the selected index of the contacts
      if (search==1){ //checking for searched contacts
        index=searching[index];
      }
      
      keyeventLabel2.setText("Deleted!"); //sets event label to an appropriate message
      System.out.println("Delete Button Pressed!");//console message
      
      for (int k=index;k<c;k++){ // set i to the index of the selected contact and make a loop until it reached the current size of the contact list
        
        firstname[k]=firstname[k+1]; //moving contacts 1 spot over and deleting the selected contact
        lastname[k]=lastname[k+1];
        cellphone[k]=cellphone[k+1];
        email[k]=email[k+1];
        address[k]=address[k+1];
        city[k]=city[k+1];
        postalcode[k]=postalcode[k+1];
        
        keyeventLabel.setText("Contact Deleted.");  //sets event labels to an appropriate message
        searchnameLabel.setText("Name: ");
        searchcellphoneLabel.setText("Cell Phone: ");
        searchemailLabel.setText("Email: ");
        searchaddressLabel.setText("Address: ");
        searchcityLabel.setText("City: ");
        searchpostalcodeLabel.setText("Postal Code: ");
        
      }
      c--;
      searchnameLabel.setText("Name: ");//sets event labels to an appropriate messages
      searchcellphoneLabel.setText("Cell Phone: ");
      searchemailLabel.setText("Email: ");
      searchaddressLabel.setText("Address: ");
      searchcityLabel.setText("City: ");
      searchpostalcodeLabel.setText("Postal Code: ");
    }
    
    if(command.equals("Update Contact")){//if update contact button is pressed (edit)
      if (c<100){//checks if there are less than 100 contacts in the array
        
        System.out.println("Update Contact Pressed");//console message
        
        for (int i=0;i<c;i++){ //for loop
          if (firstname[i].equals(firstnameField.getText()) && lastname[i].equals(lastnameField.getText())){ //checks if the searched contact's information matches the text fields
            
            cellphone[i]=cellphoneField.getText(); //changes the stored information into the new information on the input fields
            email[i]=emailField.getText();
            address[i]=addressField.getText();
            city[i]=cityField.getText();
            postalcode[i]=postalcodeField.getText();
            searchnameLabel.setText("Contact Found: "+firstname[i]+" "+lastname[i]);//sets event labels to an appropriate messages
            searchcellphoneLabel.setText("Cell Phone: "+cellphone[i]);
            searchemailLabel.setText("Email: "+email[i]);
            searchaddressLabel.setText("Address: "+address[i]);
            searchcityLabel.setText("City: "+city[i]);
            searchpostalcodeLabel.setText("Postal Code: "+postalcode[i]);
            
            keyeventLabel.setText(firstnameField.getText()+ " " + lastnameField.getText()+"'s information is updated.");//sets event labels to an appropriate messages
            keyeventLabel2.setText("Updated! Click on 'All Contacts' to view the changes.");
            break;
          }else{
            
            keyeventLabel.setText("The name was not found in the contact list");//sets event labels to an appropriate messages
            keyeventLabel3.setText("");
            keyeventLabel4.setText("");
          }
        }
      }
    }
    
    if(command.equals("Search")){//if search button is pressed
      if (c<100){//checks if there are less than 100 contacts in the array
        
        System.out.println("Search Pressed");//console message
        System.out.println("Searched Contact Name: "+firstnameField.getText()+ " " + lastnameField.getText());
        int index=listbox.getSelectedIndex();//find index of the contact
        
        for (int i=0;i<c;i++){//for loop
          index=i;//set index to i 
          if(firstname[index].equals(firstnameField.getText()) && lastname[index].equals(lastnameField.getText())){//finds the name by the selected index
            keyeventLabel.setText(firstnameField.getText()+ " " + lastnameField.getText()+" exists in the contact list.");//sets event labels to an appropriate messages
            keyeventLabel2.setText("Searched! Click on 'All Contacts' to view the searched contact.");
            searchnameLabel.setText("Contact Found: "+firstname[index]+" "+lastname[index]);//sets event labels to an appropriate messages for the contacts selected at an index
            searchcellphoneLabel.setText("Cell Phone: "+cellphone[index]);
            searchemailLabel.setText("Email: "+email[index]);
            searchaddressLabel.setText("Address: "+address[index]);
            searchcityLabel.setText("City: "+city[index]);
            searchpostalcodeLabel.setText("Postal Code: "+postalcode[index]);
            break;
            
          }else{
            keyeventLabel.setText("Name not found in contact list.");//sets event labels to an appropriate messages
            keyeventLabel2.setText("Name not found in contact list.");
            searchnameLabel.setText("Name: ");
            searchcellphoneLabel.setText("Cell Phone: ");
            searchemailLabel.setText("Email: ");
            searchaddressLabel.setText("Address: ");
            searchcityLabel.setText("City: ");
            searchpostalcodeLabel.setText("Postal Code: ");
          }
        }
      }
    }
    
    if(command.equals("Display Contact Info")){//if display contact info is pressed
      int index=listbox.getSelectedIndex();//finds index on the list
      keyeventLabel3.setText("");//sets event labels to an appropriate messages
      keyeventLabel4.setText("");
      
      JDialog dialog =new JDialog(frame1,"Contact Information",true); //create a new dialog 
      dialog.setSize(new Dimension(400, 300)); //dimensions
      dialog.setModal(true);
      
      firstnameLabel2.setText("First Name: "+firstname[index]); //sets event labels to an appropriate messages
      lastnameLabel2.setText("Name: "+lastname[index]);
      cellphoneLabel2.setText("Cell Phone: "+cellphone[index]);
      emailLabel2.setText("Email: "+email[index]);
      addressLabel2.setText("Street: "+address[index]);
      cityLabel2.setText("City: "+city[index]);
      postalcodeLabel2.setText("Postal Code: "+postalcode[index]);
      dialog.add(pan8); //add panel 8 on dialog (contact information)
      dialog.setModal(true);
      dialog.setVisible(true);
    }
    
    if(command.equals("Previous")){//If Previous Button is pressed   
      if (c<100){//checks for space availiable 
        //Message in console for clicking the button
        System.out.println("Previous button pressed");//console message
        if (value>0){//checks if the position of the contact is greater than 0
          
          searchnameLabel.setText("Name: "+firstname[value-1]+" "+lastname[value-1]);//sets event labels to an appropriate messages at the position 1 before
          searchcellphoneLabel.setText("Cell Phone: "+cellphone[value-1]);
          searchemailLabel.setText("Email: "+email[value-1]);
          searchaddressLabel.setText("Address: "+address[value-1]);
          searchcityLabel.setText("City: "+city[value-1]);
          searchpostalcodeLabel.setText("Postal Code: "+postalcode[value-1]);
          keyeventLabel.setText("Moved to previous contact.");
          
          value--;
        }else{
          keyeventLabel.setText("No previous position.");//sets event labels to an appropriate messages
        }
      }
    }
    if(command.equals("Next")){//If Next Button is pressed
      if (c<100){//checks for space availiable 
        //Message in console for testing
        System.out.println("Next button pressed");//console message
        if (value<c-1){//checks if the position is less than count minus 1
          
          searchnameLabel.setText("Name: "+firstname[value+1]+" "+lastname[value+1]);//sets event labels to an appropriate messages at the position 1 after
          searchcellphoneLabel.setText("Cell Phone: "+cellphone[value+1]);
          searchemailLabel.setText("Email: "+email[value+1]);
          searchaddressLabel.setText("Address: "+address[value+1]);
          searchcityLabel.setText("City: "+city[value+1]);
          searchpostalcodeLabel.setText("Postal Code: "+postalcode[value+1]);
          keyeventLabel.setText("Moved to next contact.");//sets event label to an appropriate message
          value++;
          
        }else{
          keyeventLabel.setText("No next position.");//sets event label to an appropriate message
        }
      }
    }
  }
  //Main Method
  public static void main(String[] args) {
    AddressBook3 frame1 = new AddressBook3(); 
    frame1.setVisible(true); //making frame visible
  }
}


