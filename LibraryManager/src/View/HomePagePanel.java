package View;

import ObserverUI.ObserverBook;
import ObserverUI.ObserverLoan;
import ObserverUI.ObserverLogin;
import ObserverUI.ObserverReader;
import ReaderModel.Reader;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import BookModel.BookItem;
import BookModel.BookTitle;
import LoanModel.Loan;

public class HomePagePanel extends  JPanel implements ObserverLogin, ObserverBook, ObserverReader, ObserverLoan {
    JPanel homePanel;


    JLabel titleLable, labelDate, overviewLabel;
    String username = "";

    JPanel padBook, padReader, padLoan, padOverDue, padQueue;
    JLabel titelBook, titleReader, titleLoan, titleOverDue, titleQueue;
    JLabel quantityBook, quantityReader, quantityLoan, quantityOverDue, quantityQueue;


    Font fontTitle = new Font("Inter", Font.BOLD, 18);
    


    public HomePagePanel() {
        setLayout(new BorderLayout());

        homePanel = new JPanel(null);
        homePanel.setBackground(Color.WHITE);


        titleLable = new JLabel("XIN CHÀO");
        titleLable.setFont(new Font("Inter", Font.BOLD, 18));
        titleLable.setBounds(30, 30, 400, titleLable.getPreferredSize().height);

        labelDate = new JLabel("Hôm nay ");
        labelDate.setFont(new Font("Inter", Font.BOLD, 13));
        labelDate.setBounds(30, 60, 200, labelDate.getPreferredSize().height);


        
        overviewLabel = new JLabel("TỔNG QUAN");
        overviewLabel.setFont(new Font("Inter", Font.BOLD, 18));
        overviewLabel.setBounds(30, 100, 200, titleLable.getPreferredSize().height);
       





        padBook = new JPanel(new BorderLayout());
        padReader = new JPanel(new BorderLayout());
        padLoan = new JPanel(new BorderLayout());
        padOverDue = new JPanel(new BorderLayout());
        padQueue = new JPanel(new BorderLayout());



        titelBook = new JLabel("ĐẦU SÁCH", JLabel.CENTER);
        titleReader = new JLabel("ĐỘC GIẢ", JLabel.CENTER);
        titleLoan = new JLabel("PHIẾU MƯỢN", JLabel.CENTER);
        titleOverDue = new JLabel("ĐANG QUÁ HẠN", JLabel.CENTER);
        titleQueue = new JLabel("HÀNG ĐỢI", JLabel.CENTER);


        quantityBook = new JLabel("0", JLabel.CENTER);
        quantityReader = new JLabel("0", JLabel.CENTER);
        quantityLoan = new JLabel("0", JLabel.CENTER);
        quantityOverDue = new JLabel("0", JLabel.CENTER);
        quantityQueue = new JLabel("0", JLabel.CENTER);






        


        List<JLabel> titles = Arrays.asList(titelBook, titleReader, titleLoan, titleOverDue, titleQueue);
        List<JLabel> quatitys = Arrays.asList(quantityBook, quantityReader, quantityLoan, quantityOverDue, quantityQueue);
        List<JPanel> pads = Arrays.asList(padBook, padReader, padLoan, padOverDue, padQueue);

        

        padBook.setBounds(30, 130, 220, 130);
        padReader.setBounds(260, 130, 220, 130);
        padLoan.setBounds(490, 130, 220, 130);
        padOverDue.setBounds(720, 130, 220, 130);


        for (int i = 0; i < titles.size(); i++) {
            pads.get(i).add(titles.get(i), BorderLayout.NORTH);
            pads.get(i).setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
            pads.get(i).add(quatitys.get(i), BorderLayout.CENTER);
            titles.get(i).setFont(fontTitle);
            homePanel.add(pads.get(i));
        }

        

        for (JLabel l : quatitys) {
        	l.setFont(new Font("Inter", Font.BOLD, 30));
        	l.setForeground(Color.getHSBColor(0.56f, 1, 1));
        }





        homePanel.add(titleLable);
        homePanel.add(labelDate);
        homePanel.add(overviewLabel);







        add(homePanel, BorderLayout.CENTER);
    }

    public void setDisplayTime(LocalDateTime dateTime) {
        labelDate.setText("Hôm nay " + dateTime.getDayOfMonth() + "/" + dateTime.getMonthValue() + "/" + dateTime.getYear());
    }


    @Override
    public void login(String username) {
        titleLable.setText("Xin chào " + username);
    }

    @Override
    public void logout() {
        titleLable.setText("Xin chào ");
    }

	@Override
	public void updateDisplay(List<Reader> list) {
		quantityReader.setText(list.size() + "");
	}

	@Override
	public void updateDisplayBookTitle(List<BookTitle> titles) {
		quantityBook.setText(titles.size() + "");
	}

	@Override
	public void updateDisplayLoan(List<Loan> list) {
		quantityLoan.setText(list.size() + "");
	}

	@Override
	public void updateDisplayBookItem(List<BookItem> bookItems) {
		
	}
}
