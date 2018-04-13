package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class GuiWindow extends JFrame implements ActionListener {

    JButton zatwierdz;
    JButton test;
    JTextField liczbaKas;
    JTextField liczbaKlientów;
    JLabel kasy;
    JLabel klienci;
    int kasyLiczba;
    int klienciLiczba;
    boolean status;


    JLabel Customers;
    LinkedList<JLabel> Cash;
    LinkedList<Integer> queueSize;
    public GuiWindow(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLayout(null);
        setInputParams();
        setVisible(true);
        status=false;


    }

    public void setInputParams(){
        kasy=new JLabel("Podaj liczbe kas");
        kasy.setBounds(20,20,100,30);
        add(kasy);
        kasy.setVisible(true);

        liczbaKas=new JTextField();
        liczbaKas.setBounds(20,50, 40,20);
        add(liczbaKas);
        liczbaKas.setVisible(true);

        klienci=new JLabel("Podaj liczbe klientów");
        klienci.setBounds(20,80,130,30);
        add(klienci);
        klienci.setVisible(true);


        liczbaKlientów=new JTextField();
        liczbaKlientów.setBounds(20,120, 40,20);
        liczbaKlientów.setVisible(true);
        add(liczbaKlientów);
        liczbaKlientów.setVisible(true);

        zatwierdz=new JButton("OK");
        zatwierdz.setBounds(20,150, 60,30);
        zatwierdz.addActionListener(this);
        zatwierdz.setVisible(true);
        add(zatwierdz);

        /*
        test=new JButton("test");
        test.setBounds(150,150, 60,30);
        test.addActionListener(this);
        test.setVisible(true);
        add(test);
        */


    }

    public void setSimulationParams(){
        int y=230;
        Customers=new JLabel("Liczba klientów robiących zakupy to: "+klienciLiczba);
        Customers.setBounds(20,210,300,30);
        add(Customers);
        Customers.setVisible(true);

        Cash= new LinkedList();
        queueSize= new LinkedList();
        for(int i=0; i<kasyLiczba;i++){
            queueSize.add(0);
            Cash.add(new JLabel("KASA " +(i+1)+" - długość kolejki: "+queueSize.get(i)));
            Cash.get(i).setBounds(20, y, 250,30);
            add(Cash.get(i));
            Cash.get(i).setVisible(true);
            y+=20;

        }
        repaint();
    }

    public static void main(String[] args){
        GuiWindow okno=new GuiWindow();
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==zatwierdz){
            kasyLiczba=Integer.valueOf(liczbaKas.getText().toString());
            klienciLiczba=Integer.valueOf(liczbaKlientów.getText().toString());
            setSimulationParams();
            status=true;

        }
        /*
        else if(e.getSource()==test){
            addCustomertoCash(5);
           // endOfWaitingInQueue(5);
        }
        */
    }

    /*
    PONIZEJ 3 METODY, KTORE MOGA BYC UZYWANE NA POLECENIE FEDERATA
    /*


    /*
    metda getStatus zwraca wartość status ( jeśli true - to znaczy, ze uzytkownik podał juz
    preferowaną liczbe klientów oraz liczbe kas i zatwierdził swój wybór
     */
    boolean getStatus(){
        return status;
    }

    /*
     metoda inkrementuje liczbe osob czekających w kolejce do kasy o podanym  numerze - id
     zmiana ta wyswietlana jest od razu przez GUI
     */

    public void addCustomertoCash(int id){
        klienciLiczba--;
        queueSize.set(id-1, queueSize.get(id-1)+1);
        Customers.setText("Liczba klientów robiących zakupy to: "+klienciLiczba);
        Cash.get(id-1).setText("KASA " +(id)+" - długość kolejki: "+queueSize.get(id-1));
        repaint();
    }


    /*
    metoda dekrementuje liczbe osoób czekajcych w kolejce o numerze - id
    należy ją uruchomic gdy klient skończy byc obsługiwany w kasie
     */

    public void endOfWaitingInQueue(int id){
        queueSize.set(id-1, queueSize.get(id-1)-1);
        Cash.get(id-1).setText("KASA " +(id)+" - długość kolejki: "+queueSize.get(id-1));
        repaint();
    }
}
